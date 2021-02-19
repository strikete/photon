package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class BeamPaletteCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int beamPaletteNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfBeamPalettes() {
		return this.beamPaletteNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public BeamPaletteCountUpdateEvent(OscInstance oscInstanceIn, int beamPaletteNumIn) {
		super(oscInstanceIn);
		this.beamPaletteNum = beamPaletteNumIn;
		Main.log.debug("EVENT TRIGGERED: BeamPaletteCountUpdateEvent at " + this.getTime());
	}
}