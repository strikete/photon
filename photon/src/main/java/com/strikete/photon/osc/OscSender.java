package com.strikete.photon.osc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCSerializeException;
import com.illposed.osc.transport.udp.OSCPortOut;
import com.strikete.photon.Photon;

public class OscSender {
	
	/*
	 * VARIABLES
	 */
	private OSCPortOut sender;
	private InetAddress targetIp;
	private int targetPort;
	private int messagesSent = 0;

	
	/*
	 * METHODS - GETTERS
	 */
	public int getMessageSentCount() {
		return this.messagesSent;
	}
	public InetAddress getTargetIp() {
		return this.targetIp;
	}
	public int getTargetPort() {
		return this.targetPort;
	}
	
	
	/*
	 * METHODS - INITALIZATION
	 */
	private void initalizeOscSender() {
		try {
			this.sender = new OSCPortOut(this.targetIp,this.targetPort);
		} catch (IOException e) {
			Photon.log.error("OscSender ERROR: IOException on OSCPortOut Object initalization.",e);
		}
	}
	
	
	/*
	 * METHODS - PARAMETERIZE STRING
	 */
	public String parameterizeString(String address, ArrayList<String> parameters) {
		for(int x = 0; x < parameters.size(); x++) {
			String parameterString = "[parameter" + Integer.toString((x+1)) +"]";
			address = address.replace(parameterString, parameters.get(x));
		}
		return address;
	}
	
	
	
	/*
	 * METHODS - LOWER LEVEL OSC MESSAGE SENDING
	 */
	public synchronized void sendOscMessage(OSCMessage message) {
		try {
			this.sender.send(message);
		} catch (IOException e) {
			Photon.log.error("OscSender ERROR: IOException encountered on sendOscMessage.",e);
		} catch (OSCSerializeException e) {
			Photon.log.error("OscSender ERROR: OSCSerializeException on sendOscMessage.",e);
		}
		this.messagesSent++; //Count another message sent
	}
	public void sendOscMessage(String address, List<Object> arguments) { //For sending an address & List<Object>
		OSCMessage message = new OSCMessage(address,arguments);
		this.sendOscMessage(message);
	}
	public void sendOscMessage(String address, String argument) { //For sending an address & String argument
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		OSCMessage message = new OSCMessage(address,arguments);
		this.sendOscMessage(message);
	}
	public void sendOscMessage(String address, int argument) { //For sending an address & integer argument
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		OSCMessage message = new OSCMessage(address,arguments);
		this.sendOscMessage(message);
	}
	public void sendOscMessage(String address) { //For sending just an address
		OSCMessage message = new OSCMessage(address);
		this.sendOscMessage(message);
	}
	
	
	/*
	 * CONSTRUCTORS
	 */
	public OscSender(InetAddress targetIpIn, int targetPortIn) {
		this.targetIp = targetIpIn;
		this.targetPort = targetPortIn;
		initalizeOscSender();
	}
	public OscSender(String targetIpStringIn, int targetPortIn) {
		this.targetPort = targetPortIn;
		
		//Catch block for Unknown Host Exception
		try {
			this.targetIp = InetAddress.getByName(targetIpStringIn);
		} catch (UnknownHostException e) {
			Photon.log.error("OscSender ERROR: UnknownHostException on object creation. Use a valid IP address.",e);
		}
		initalizeOscSender();
	}	
}