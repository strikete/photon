package com.strikete.photon.osc;

import java.io.IOException;
import java.util.ArrayList;

import com.illposed.osc.MessageSelector;
import com.illposed.osc.OSCMessageEvent;
import com.illposed.osc.OSCMessageListener;
import com.illposed.osc.messageselector.OSCPatternAddressMessageSelector;
import com.illposed.osc.transport.udp.OSCPortIn;
import com.strikete.photon.Photon;

public class OscInterpreter implements OSCMessageListener{

	/*
	 * VARIABLES
	 */
	@SuppressWarnings("unused")
	private Photon photon;
	private OSCPortIn oscReceiver;
	private int portNumber;
	private MessageSelector messageSelector;
	private long lastMessageTime;
	
	private ArrayList<OscListener> oscListeners = new ArrayList<OscListener>();
	
	/*
	 * METHODS - GETTERS
	 */
	public long getLastTime() {
		return lastMessageTime;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	
	
	
	/*
	 * METHODS - ADD OSC LISTENER
	 */
	public void addOscListener(OscListener oscListenerIn) {
		this.oscListeners.add(oscListenerIn);
	}
	
	/*
	 * METHODS - INTERPRETER UTILITIES
	 */
	public boolean isTypePresent(String input) {
		if(input.contains("[int]") || input.contains("[float]")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean doArraysMatch(String[] array1, String[] array2) {
		for(int x = 1; x < array1.length; x++) {
			if(!array1[x].equals(array2[x])) {
				return false;
			}
		}
		return true;
	}

	
	/*
	 * METHODS - INTERPRET OSC
	 */
	@Override
	public void acceptMessage(OSCMessageEvent event) {
		lastMessageTime = System.nanoTime();
		
		if(oscListeners.size() > 0) { //If there is more than one event registered...
			for(int a = 0; a < oscListeners.size(); a++){							//Let's look for matches
				String messageAddress = event.getMessage().getAddress();
				String[] messageAddressArray = messageAddress.split("/");
				
				String listenerAddress = oscListeners.get(a).getListenerString();
				String[] listenerAddressArray = listenerAddress.split("/");
				
				if(oscListeners.get(a).getExactMatch()) {								//EXACT MATCHES
					try {
						if(messageAddressArray.length == listenerAddressArray.length) { //quick way to know if a match is exact or not.
							for(int b = 0; b < listenerAddressArray.length; b++) {
								if(isTypePresent(listenerAddressArray[b])) { //check for types (int, float, etc)
									if(listenerAddressArray[b].contains("[int]")) { //Attempt to cast to int
										int tempInt = Integer.parseInt(messageAddressArray[b]);
										String newString = Integer.toString(tempInt);
										listenerAddressArray[b] = newString;
									}else if(listenerAddressArray[b].contains("[float]")) { //Attempt to cast to float
										float tempFloat = Float.parseFloat(messageAddressArray[b]);
										String floatString = Float.toString(tempFloat);
										String newString;
										if(floatString.contains(".0")) { //This is necessary to make a match when Java adds a ".0" to the float
											newString = floatString.replace(".0", "");
										}else {
											newString = floatString;
										}
										listenerAddressArray[b] = newString;
									}
								}
							}
						}
					}catch(NumberFormatException e) {
						//e.printStackTrace();
						//They don't match.
					}
					if(doArraysMatch(messageAddressArray, listenerAddressArray)) { //Compare the two arrays
						oscListeners.get(a).prepareConsumer(event.getMessage());
						Thread thread = new Thread(oscListeners.get(a));
						thread.start();
						
						try {
							Thread.sleep(25); //Stabilizing sleep to help avoid data duplicates.
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}else {																	//TODO:NON-EXACT MATCHES
					
				}
			}
		}
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public OscInterpreter(Photon photonIn, int listeningPortIn) throws IOException {
		this.photon = photonIn;
		this.portNumber = listeningPortIn;
		this.oscReceiver = new OSCPortIn(portNumber);
		this.messageSelector = new OSCPatternAddressMessageSelector("//"); //Selects and listens to all incoming messages
		this.oscReceiver.getDispatcher().addListener(messageSelector,this);
		this.oscReceiver.startListening();
		lastMessageTime = System.nanoTime();
	}
}