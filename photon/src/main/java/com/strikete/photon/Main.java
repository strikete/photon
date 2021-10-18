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
	 * NON-MAIN METHODS
	 */
	
	
	
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
		
		
	}
}