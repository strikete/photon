package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.IntensityPalette;
import com.strikete.photon.osc.OscInstance;

public class IntensityPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private IntensityPalette ip;
	
	/*
	 * METHODS
	 */
	public IntensityPalette getIntensityPalette() {
		return this.ip;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public IntensityPaletteUpdateEvent(OscInstance oscInstanceIn, IntensityPalette ipIn) {
		super(oscInstanceIn);
		this.ip = ipIn;
		Main.log.debug("EVENT TRIGGERED: IntensityPaletteUpdateEvent with Intensity Palette " + ipIn.getPaletteNum() + " at " + this.getTime());
	}
}
