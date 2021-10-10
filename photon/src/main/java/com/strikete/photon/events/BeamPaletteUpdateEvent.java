package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.BeamPalette;
import com.strikete.photon.osc.OscInstance;

public class BeamPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private BeamPalette bp;
	
	/*
	 * METHODS
	 */
	public BeamPalette getBeamPalette() {
		return this.bp;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public BeamPaletteUpdateEvent(OscInstance oscInstanceIn, BeamPalette bpIn) {
		super(oscInstanceIn);
		this.bp = bpIn;
		Main.log.debug("EVENT TRIGGERED: BeamPaletteUpdateEvent with Beam Palette " + bpIn.getPaletteNum() + " at " + this.getTime());
	}
}
