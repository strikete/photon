package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class IntensityPaletteCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int intensityPaletteNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfIntensityPalettes() {
		return this.intensityPaletteNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public IntensityPaletteCountUpdateEvent(OscInstance oscInstanceIn, int intensityPaletteNumIn) {
		super(oscInstanceIn);
		this.intensityPaletteNum = intensityPaletteNumIn;
		Main.log.debug("EVENT TRIGGERED: IntensityPaletteCountUpdateEvent at " + this.getTime());
	}
}