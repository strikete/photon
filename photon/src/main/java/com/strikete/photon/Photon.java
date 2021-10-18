package com.strikete.photon;

import java.util.ArrayList;

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

public class Photon {

	/*
	 * VARIABLES - PROGRAM SPECIFIC
	 */
	public static Logger log;
	public static final String version = "SNAPSHOT 0.9.0";
	
	/*
	 * VARIABLES - OBJECTS
	 */
	private ArrayList<BeamPalette> beamPalettes;
	private ArrayList<Channel> channels;
	private ArrayList<ColorPalette> colorPalettes;
	private ArrayList<Cue> cues;
	private ArrayList<Cuelist> cuelists;
	private ArrayList<Curve> curves;
	private ArrayList<Effect> effects;
	private ArrayList<Fixture> fixtures;
	private ArrayList<FocusPalette> focusPalettes;
	private ArrayList<Group> groups;
	private ArrayList<IntensityPalette>intensityPalettes;
	private ArrayList<Macro> macros;
	private ArrayList<MagicSheet> magicSheets;
	private ArrayList<Pixelmap> pixelmaps;
	private ArrayList<Preset> presets;
	private ArrayList<Snapshot> snapshots;
	private ArrayList<Sub> subs;
	
	
	/*
	 * METHODS - GETTERS
	 */
	
	
	
	/*
	 * METHODS - SETTERS
	 */
	
	
	
	/*
	 * METHODS - PROGRAM SPECIFIC
	 */
	private static void printWelcomeMessage() { //Only to be called after the logger object has been configured
		log.info("Welcome to Photon " + version + " .");
		log.info("Photon is authored by Benji Arrigo in conjunction with Strike Theatre Electronics.");	
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
	}
	
	public void initializeOsc() {
		
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Photon() {
		initializeVariables();
		
	}
}
