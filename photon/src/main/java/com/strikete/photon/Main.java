package com.strikete.photon;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.strikete.photon.csv.CsvCueLevelParser;
import com.strikete.photon.events.ChannelCountUpdateEvent;
import com.strikete.photon.events.ChannelUpdateEvent;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.objects.DmxAddress;
import com.strikete.photon.osc.OscInstance;
import com.strikete.photon.osc.OscInstance.OscFormat;
import com.strikete.photon.wtsahtsi.Wtsahtsi;

public class Main {

	/*
	 * VARIABLES
	 */
	
	public static final String version = "SNAPSHOT 0.9.0";
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
		
		OscInstance osc = new OscInstance(OscFormat.ETC_EOS,"192.168.0.86",6300,6301);
		Main main = new Main();
		System.out.println(InetAddress.getLocalHost());
		
		osc.init();
		osc.getEventHandler().register(main);
		
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//osc.getOscSender().sendOscMessage(osc.getOscMap().KEY_BLIND);
		//osc.getOscSender().selectChannel(1);
		
		osc.getObjectUpdater().doBasicUpdate();
		
		//while(true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(osc.getOscParser().getChannelSize());
			//Wtsahtsi whut = new Wtsahtsi("/Users/rusettsten/Desktop/KC-ON YOUR FEET.csv",1,osc,1499);
			printCueLabels(osc);
			
			
		}
	//}
	
	public static void printCueLabels(OscInstance osc) {
		int cueSize = osc.getOscParser().getCuelistFromIndex(0).getCueSize();
		for(int x = 0; x < cueSize; x++) {
			if(!osc.getOscParser().getCuelistFromIndex(0).getCue(x).getName().isEmpty() && !osc.getOscParser().getCuelistFromIndex(0).getCue(x).getName().contains("copy")) {
				float cueNumber = osc.getOscParser().getCuelistFromIndex(0).getCue(x).getCueNumber();
				String cueName = osc.getOscParser().getCuelistFromIndex(0).getCue(x).getName();
				System.out.println("Q" + cueNumber + ": " + cueName);
				}
		}
	}

}
