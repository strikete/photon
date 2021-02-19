package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Macro;
import com.strikete.photon.osc.OscInstance;

public class MacroUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Macro macro;
	private int indexNum;
	
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public Macro getMacro() {
		return this.macro;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public MacroUpdateEvent(OscInstance oscInstanceIn, Macro macroIn, int indexNumIn) {
		super(oscInstanceIn);
		this.macro = macroIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: MacroUpdateEvent with Macro " + macroIn.getMacroNum() + " at " + this.getTime());
	}
}
