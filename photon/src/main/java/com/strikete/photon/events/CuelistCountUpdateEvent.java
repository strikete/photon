package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class CuelistCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int cuelistNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfCuelists() {
		return this.cuelistNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CuelistCountUpdateEvent(OscInstance oscInstanceIn, int cuelistNumIn) {
		super(oscInstanceIn);
		this.cuelistNum = cuelistNumIn;
		Main.log.debug("EVENT TRIGGERED: CuelistCountUpdateEvent at " + this.getTime());
	}
}