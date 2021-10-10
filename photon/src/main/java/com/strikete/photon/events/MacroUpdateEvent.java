package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Macro;
import com.strikete.photon.osc.OscInstance;

public class MacroUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Macro macro;
	
	
	/*
	 * METHODS
	 */
	public Macro getMacro() {
		return this.macro;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public MacroUpdateEvent(OscInstance oscInstanceIn, Macro macroIn) {
		super(oscInstanceIn);
		this.macro = macroIn;
		Main.log.debug("EVENT TRIGGERED: MacroUpdateEvent with Macro " + macroIn.getMacroNum() + " at " + this.getTime());
	}
}
