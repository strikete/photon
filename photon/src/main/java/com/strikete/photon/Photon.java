package com.strikete.photon;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.strikete.photon.objects.BeamPalette;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.objects.ColorPalette;
import com.strikete.photon.objects.Cue;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.objects.Curve;
import com.strikete.photon.objects.Effect;
import com.strikete.photon.objects.Fixture;
import com.strikete.photon.objects.FocusPalette;
import com.strikete.photon.objects.Group;
import com.strikete.photon.objects.IntensityPalette;
import com.strikete.photon.objects.Macro;
import com.strikete.photon.objects.MagicSheet;
import com.strikete.photon.objects.Pixelmap;
import com.strikete.photon.objects.Preset;
import com.strikete.photon.objects.Snapshot;
import com.strikete.photon.objects.Sub;
import com.strikete.photon.osc.OscInterpreter;
import com.strikete.photon.osc.OscSender;
import com.strikete.photon.photonosc.OscSenderRoutines;
import com.strikete.photon.photonosc.PhotonOscRoutines;

public class Photon {

	/*
	 * VARIABLES - PROGRAM SPECIFIC
	 */
	public static Logger log;
	public static final String version = "SNAPSHOT 0.9.0";
	public OscSender sender;
	public OscInterpreter interpreter;
	public PhotonDataUtilities dataUtility;
	public OscSenderRoutines senderRoutines;
	
	
	/*
	 * VARIABLES - EOS SPECIFIC
	 */
	public String eosVersion;
	
	
	/*
	 * VARIABLES - OBJECTS
	 */
	public ArrayList<BeamPalette> beamPalettes;
	public ArrayList<Channel> channels;
	public ArrayList<ColorPalette> colorPalettes;
	public ArrayList<Cue> cues;
	public ArrayList<Cuelist> cuelists;
	public ArrayList<Curve> curves;
	public ArrayList<Effect> effects;
	public ArrayList<Fixture> fixtures;
	public ArrayList<FocusPalette> focusPalettes;
	public ArrayList<Group> groups;
	public ArrayList<IntensityPalette>intensityPalettes;
	public ArrayList<Macro> macros;
	public ArrayList<MagicSheet> magicSheets;
	public ArrayList<Pixelmap> pixelmaps;
	public ArrayList<Preset> presets;
	public ArrayList<Snapshot> snapshots;
	public ArrayList<Sub> subs;
	
	public int cuelistCount;
	
	/*
	 * METHODS - GETTERS
	 */
	public String getEosVersion() {
		return eosVersion;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setEosVersion(String versionIn) {
		this.eosVersion = versionIn;
		System.out.println(eosVersion);
	}
	
	
	/*
	 * METHODS - PROGRAM SPECIFIC
	 */
	private static void printWelcomeMessage() { //Only to be called after the logger object has been configured
		log.info("Welcome to Photon " + version + " .");
		log.info("Photon is authored by Benji Arrigo in conjunction with Strike Theatre Electronics.");	
	}
	
	
	/*
	 * METHODS - CORE FUNCTIONS
	 */
	
	public void updateOscObjects() {
		log.debug("PHOTON: Updating OSC Objects.");
		
		senderRoutines.getPatchCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getCuelistCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getGroupCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getMacroCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getSubCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getPresetCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getIntensityPaletteCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getFocusPaletteCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getColorPaletteCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getBeamPaletteCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getCurveCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getEffectCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getSnapshotCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getPixelmapCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		senderRoutines.getMagicSheetCount();
		waitForPause(1000000000L); //Wait 1 second after last message
		
		log.debug("PHOTON: Updating Cue Objects.");
		for(int x = 0; x < cuelists.size(); x++) {
			senderRoutines.getCueCount(cuelists.get(x).getCuelistNumber());
		}
	}
	
	public void waitForPause(long pauseTimeNano) { //Wait a set amount of time after receiving the last OSC message.
		boolean working = true;
		while(working) {
			
			try {
				Thread.sleep(50); //Take a break
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.nanoTime() > (pauseTimeNano + interpreter.getLastTime())) { //Check to see the time since the last message was received
				working = false;
			}
		}
	}
	
	
	/*
	 * METHODS - INITIALIZATION
	 */
	private void initializeVariables() {
		beamPalettes = new ArrayList<BeamPalette>();
		channels = new ArrayList<Channel>();
		colorPalettes = new ArrayList<ColorPalette>();
		cues = new ArrayList<Cue>();
		cuelists = new ArrayList<Cuelist>();
		curves = new ArrayList<Curve>();
		effects = new ArrayList<Effect>();
		fixtures = new ArrayList<Fixture>();
		focusPalettes = new ArrayList<FocusPalette>();
		groups = new ArrayList<Group>();
		intensityPalettes = new ArrayList<IntensityPalette>();
		macros = new ArrayList<Macro>();
		magicSheets = new ArrayList<MagicSheet>();
		pixelmaps = new ArrayList<Pixelmap>();
		presets = new ArrayList<Preset>();
		snapshots = new ArrayList<Snapshot>();
		subs = new ArrayList<Sub>();
		
		
		cuelistCount = 0;
	}
	
	@SuppressWarnings("unused")
	public void initializeOsc(String targetIp,int oscClientPort, int oscIncomingPort) {
		this.sender = new OscSender(targetIp,oscClientPort);
		try {
			this.interpreter = new OscInterpreter(this, oscIncomingPort);
		} catch (IOException e) {
			log.error("PHOTON ERROR: COULD NOT ESTABLISH LISTENER AT PORT: " + oscIncomingPort);
		}
		
		PhotonOscRoutines photonOscRoutines = new PhotonOscRoutines(this, 100);
		this.senderRoutines = new OscSenderRoutines(sender);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Photon() {
		log = Logger.getLogger(Photon.class);
		BasicConfigurator.configure();
		initializeVariables();
		this.dataUtility = new PhotonDataUtilities(this);
		
		printWelcomeMessage();
	}
}
