package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class FocusPaletteCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int focusPaletteNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfFocusPalettes() {
		return this.focusPaletteNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public FocusPaletteCountUpdateEvent(OscInstance oscInstanceIn, int focusPaletteNumIn) {
		super(oscInstanceIn);
		this.focusPaletteNum = focusPaletteNumIn;
		Main.log.debug("EVENT TRIGGERED: FocusPaletteCountUpdateEvent at " + this.getTime());
	}
}