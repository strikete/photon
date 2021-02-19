package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.IntensityPalette;
import com.strikete.photon.osc.OscInstance;

public class IntensityPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private IntensityPalette ip;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public IntensityPalette getIntensityPalette() {
		return this.ip;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public IntensityPaletteUpdateEvent(OscInstance oscInstanceIn, IntensityPalette ipIn, int indexNumIn) {
		super(oscInstanceIn);
		this.ip = ipIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: IntensityPaletteUpdateEvent with Intensity Palette " + ipIn.getPaletteNum() + " at " + this.getTime());
	}
}
