package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class PresetCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int presetNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfPresets() {
		return this.presetNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public PresetCountUpdateEvent(OscInstance oscInstanceIn, int presetNumIn) {
		super(oscInstanceIn);
		this.presetNum = presetNumIn;
		Main.log.debug("EVENT TRIGGERED: PresetCountUpdateEvent at " + this.getTime());
	}
}