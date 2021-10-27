package com.strikete.photon.photonosc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.illposed.osc.OSCMessage;
import com.strikete.photon.Photon;
import com.strikete.photon.osc.OscIncoming;
import com.strikete.photon.osc.OscListener;
import com.strikete.photon.osc.OscOutgoing;

public class PhotonOscRoutines {

	/*
	 * VARIABLES
	 */
	private Photon photon;
	private ArrayList<OscListener> listeners = new ArrayList<OscListener>();
	private int delay;
	
	/*
	 * METHODS - CREATE ROUTINES
	 */
	private void createVersionListener() {
		Consumer<OSCMessage> versionConsumer = message -> {
			List<Object> argList = message.getArguments();
			photon.setEosVersion((String) argList.get(0));
		};
		OscListener versionListener = new OscListener(photon, OscIncoming.RETURN_VERSION, versionConsumer, true);
		listeners.add(versionListener);
	}
	
	private void createCountListeners() {
		
		Consumer<OSCMessage> patchCountConsumer = message -> {
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			for(int b = 0; b < count; b++) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Add generic Thread sleep error
					e.printStackTrace();
				}
				ArrayList<String> indexParameter = new ArrayList<String>();
				indexParameter.add(Integer.toString(b));
				photon.sender.sendOscMessage(photon.sender.parameterizeString(OscOutgoing.GET_PATCH_INFO, indexParameter));
			}
		};
		OscListener patchCountListener = new OscListener(photon,OscIncoming.RETURN_PATCH,patchCountConsumer,true);
		listeners.add(patchCountListener);
		
		
	}
	
	
	
	/*
	 * REGISTRAR
	 */
	private void createConsumers() {
		createVersionListener();
		createCountListeners();
	}
	
	private void registerListeners() {
		for(int x = 0; x < listeners.size(); x++) {
			photon.interpreter.addOscListener(listeners.get(x));
		}
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public PhotonOscRoutines(Photon photonIn, int delayIn) {
		this.photon = photonIn;
		this.delay = delayIn;
		createConsumers();
		registerListeners();
	}
}
