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
	
	/*
	 * METHODS - COUNT CONSUMERS
	 */
	
	private void performResponse(int count, String message) {
		for(int b = 0; b < count; b++) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Add generic Thread sleep error
				e.printStackTrace();
			}
			ArrayList<String> indexParameter = new ArrayList<String>();
			indexParameter.add(Integer.toString(b));
			photon.sender.sendOscMessage(photon.sender.parameterizeString(message, indexParameter));
		}
	}
	
	private void createCountListeners() {
		
		Consumer<OSCMessage> patchCountConsumer = message -> {							//PATCH COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_PATCH_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_PATCH,patchCountConsumer,true));
		
		Consumer<OSCMessage> cuelistCountConsumer = message -> {						//CUELIST COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_CUELIST_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_CUELIST_COUNT,cuelistCountConsumer,true));
		
		Consumer<OSCMessage> cueCountConsumer = message -> {							//CUE COUNT CONSUMER
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
				String messageAddress = message.getAddress();
				String[] messageAddressArray = messageAddress.split("/");
				indexParameter.add(messageAddressArray[5]);
				indexParameter.add(Integer.toString(b));
				photon.sender.sendOscMessage(photon.sender.parameterizeString(OscOutgoing.GET_CUE_INFO, indexParameter));
			}
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_CUE_COUNT,cueCountConsumer,true));
		
		Consumer<OSCMessage> groupCountConsumer = message -> {							//GROUP COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_GROUP_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_GROUP_COUNT,groupCountConsumer,true));
		
		Consumer<OSCMessage> macroCountConsumer = message -> {							//MACRO COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_MACRO_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_MACRO_COUNT,macroCountConsumer,true));
		
		Consumer<OSCMessage> subCountConsumer = message -> {							//SUB COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_SUB_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_SUB_COUNT,subCountConsumer,true));
		
		Consumer<OSCMessage> presetCountConsumer = message -> {							//PRESET COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_PRESET_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_PRESET_COUNT,presetCountConsumer,true));
		
		Consumer<OSCMessage> intensityPaletteCountConsumer = message -> {				//INTENSITY PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_INTENSITY_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_INTENSITY_PALETTE_COUNT,intensityPaletteCountConsumer,true));
		
		Consumer<OSCMessage> focusPaletteCountConsumer = message -> {					//FOCUS PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_FOCUS_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_FOCUS_PALETTE_COUNT,focusPaletteCountConsumer,true));
		
		Consumer<OSCMessage> colorPaletteCountConsumer = message -> {					//COLOR PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_COLOR_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_COLOR_PALETTE_COUNT,colorPaletteCountConsumer,true));
		
		Consumer<OSCMessage> beamPaletteCountConsumer = message -> {					//BEAM PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_BEAM_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_BEAM_PALETTE_COUNT,beamPaletteCountConsumer,true));
		
		Consumer<OSCMessage> curveCountConsumer = message -> {							//CURVE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_CURVE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_CURVE_COUNT,curveCountConsumer,true));
		
		Consumer<OSCMessage> effectCountConsumer = message -> {							//EFFECT COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_EFFECT_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_EFFECT_COUNT,effectCountConsumer,true));
		
		Consumer<OSCMessage> snapshotCountConsumer = message -> {						//SNAPSHOT COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_SNAPSHOT_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_SNAPSHOT_COUNT,snapshotCountConsumer,true));
		
		Consumer<OSCMessage> pixelmapCountConsumer = message -> {						//PIXELMAP COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_PIXELMAP_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_PIXELMAP_COUNT,pixelmapCountConsumer,true));
		
		Consumer<OSCMessage> magicSheetCountConsumer = message -> {						//MAGIC SHEET COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_MAGIC_SHEET_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_MAGIC_SHEET_COUNT,magicSheetCountConsumer,true));
		
	}
	
	
	/*
	 * METHODS - INFO CONSUMERS
	 */
	private void createInfoListeners() {
		
		
		
		
		
		
	}
	
	
	/*
	 * METHODS - REGISTRAR
	 */
	private void createConsumers() {
		createVersionListener();
		createCountListeners();
		createInfoListeners();
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