package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class SubCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int subNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfSubs() {
		return this.subNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public SubCountUpdateEvent(OscInstance oscInstanceIn, int subNumIn) {
		super(oscInstanceIn);
		this.subNum = subNumIn;
		Main.log.debug("EVENT TRIGGERED: SubCountUpdateEvent at " + this.getTime());
	}
}