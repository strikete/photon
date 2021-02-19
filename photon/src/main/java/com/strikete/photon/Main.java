package com.strikete.photon;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.strikete.photon.events.ChannelCountUpdateEvent;
import com.strikete.photon.events.ChannelUpdateEvent;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.objects.DmxAddress;
import com.strikete.photon.osc.OscInstance;
import com.strikete.photon.osc.OscInstance.OscFormat;

public class Main {

	/*
	 * VARIABLES
	 */
	
	public static final String version = "SNAPSHOT 0.0.1";
	public static Logger log;
	
	/*
	 * NON-MAIN METHODS
	 */
	
	private static void printWelcomeMessage() { //Only to be called after the logger object has been configured
		log.info("Welcome to Photon " + version + " .");
		log.info("Photon is authored by Benji Arrigo in conjunction with Strike Theatre Electronics.");	
	}
	
	@Subscribe
	public void onChannelUpdateEvent(ChannelUpdateEvent e) {
		Channel temp = e.getChannel();
		if(temp.getChannelNum() == 3000) {
			System.out.println("Channel: " + temp.getChannelNum());
			DmxAddress tempAddr = temp.getAddress();
			System.out.println("Universe: " + tempAddr.getUniverse().getUniverseNum());
			System.out.println("Address: " + tempAddr.getAddressNumber());
			System.out.println("Label: " + temp.getName());
			System.out.println("Manufactuer: " + temp.getManufacturer());
			System.out.println("Fixture Type: " + temp.getType());
			System.out.println("UID: " + temp.getUID());
			System.out.println("Level: " + temp.getLevel());
		}
	}
	
	
	
	/*
	 * MAIN METHOD
	 */
	
	public static void main(String[] args) throws IOException {
		
		//Create Logger
		log = Logger.getLogger(Main.class);
		BasicConfigurator.configure();
		
		//Print welcome message
		printWelcomeMessage();
		
		OscInstance osc = new OscInstance(OscFormat.ETC_EOS,"192.168.10.109",6300,6301);
		Main main = new Main();
		System.out.println(InetAddress.getLocalHost());
		
		osc.init();
		//osc.getEventHandler().register(main);
		osc.getObjectUpdater().doBasicUpdate();
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		osc.getOscSender().sendOscMessage("/eos/out/active/chan");
		System.out.println("message sent");
		//osc.getOscSender().sendOscMessage(osc.getOscMap().KEY_BLIND);
		//osc.getOscSender().selectChannel(1);
		while(true) {
			//osc.getOscSender().sendOscMessage(osc.getOscMap().GET_PATCH_COUNT);
			
		}
	}

}
