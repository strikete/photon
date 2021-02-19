package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Preset;
import com.strikete.photon.osc.OscInstance;

public class PresetUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Preset preset;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public Preset getPreset() {
		return this.preset;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public PresetUpdateEvent(OscInstance oscInstanceIn, Preset presetIn, int indexNumIn) {
		super(oscInstanceIn);
		this.preset = presetIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: PresetUpdateEvent with Preset " + presetIn.getPresetNum() + " at " + this.getTime());
	}
}
