package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class MacroCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int macroNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfMacros() {
		return this.macroNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public MacroCountUpdateEvent(OscInstance oscInstanceIn, int macroNumIn) {
		super(oscInstanceIn);
		this.macroNum = macroNumIn;
		Main.log.debug("EVENT TRIGGERED: MacroCountUpdateEvent at " + this.getTime());
	}
}