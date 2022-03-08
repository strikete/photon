package com.strikete.photon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.strikete.photon.events.CompleteDataModelEvent;
import com.strikete.photon.events.ObjectDataGatheredEvent;
import com.strikete.photon.events.UpdateOscObjectsEvent;
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
	public static final String version = "1.17";
	public OscSender sender;
	public OscInterpreter interpreter;
	public PhotonDataUtilities dataUtility;
	public OscSenderRoutines senderRoutines;
	public AsyncEventBus photonEventBus;
	public ExecutorService executor;
	
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
	public boolean workingBool;
	public boolean threadsAllowed;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public String getEosVersion() {
		return eosVersion;
	}
	
	/*
	 * METHODS - USER
	 */
	
	public void stopUpdating() {
		workingBool = false;
	}
	public boolean isUpdating() {
		return workingBool;
	}
	public void closeAllThreads() {
		executor.shutdownNow();
		threadsAllowed = false;
	}
	public void allowThreads() {
		threadsAllowed = true;
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
	
	@Subscribe
	public void updateOscObjectsViaEvent(UpdateOscObjectsEvent e) {
		updateOscObjects();
	}
	
	public void updateOscObjects() {
		log.debug("PHOTON: Updating OSC Objects.");
		workingBool = true;
		
		while(workingBool) {
			senderRoutines.getPatchCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Channels",channels.size()));
			
			senderRoutines.getCuelistCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Cuelists",cuelists.size()));
			
			senderRoutines.getGroupCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Groups",groups.size()));
			
			senderRoutines.getMacroCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Macros",macros.size()));
			
			senderRoutines.getSubCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Subs",subs.size()));
			
			senderRoutines.getPresetCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Presets",presets.size()));
			
			senderRoutines.getIntensityPaletteCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Intensity Palettes",intensityPalettes.size()));
			
			senderRoutines.getFocusPaletteCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Focus Palettes",focusPalettes.size()));
			
			senderRoutines.getColorPaletteCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Color Palettes",colorPalettes.size()));
			
			senderRoutines.getBeamPaletteCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Beam Palettes",beamPalettes.size()));
			
			senderRoutines.getCurveCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Curves",curves.size()));
			
			senderRoutines.getEffectCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Effects",effects.size()));
			
			senderRoutines.getSnapshotCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Snapshots",effects.size()));
			
			senderRoutines.getPixelmapCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Pixelmaps",effects.size()));
		
			senderRoutines.getMagicSheetCount();
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new ObjectDataGatheredEvent("Magic Sheets",magicSheets.size()));
		
			log.debug("PHOTON: Updating Cue Objects.");
			for(int x = 0; x < cuelists.size(); x++) {
				senderRoutines.getCueCount(cuelists.get(x).getCuelistNumber());
			}
			waitForPause(1000000000L); //Wait 1 second after last message
			photonEventBus.post(new CompleteDataModelEvent());
			workingBool = false;
			break;
		}
		workingBool = false;
		
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
		this.executor = Executors.newFixedThreadPool(40);
		this.threadsAllowed = true;
		
		this.photonEventBus = new AsyncEventBus(executor);
		photonEventBus.register(this);
		
		printWelcomeMessage();
	}
}
