package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.FocusPalette;
import com.strikete.photon.osc.OscInstance;

public class FocusPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private FocusPalette fp;
	
	/*
	 * METHODS
	 */
	public FocusPalette getFocusPalette() {
		return this.fp;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public FocusPaletteUpdateEvent(OscInstance oscInstanceIn, FocusPalette fpIn) {
		super(oscInstanceIn);
		this.fp = fpIn;
		Main.log.debug("EVENT TRIGGERED: FocusPaletteUpdateEvent with Focus Palette " + fpIn.getPaletteNum() + " at " + this.getTime());
	}
}
