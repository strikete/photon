package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Sub;
import com.strikete.photon.osc.OscInstance;

public class SubUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Sub sub;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public Sub getSub() {
		return this.sub;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public SubUpdateEvent(OscInstance oscInstanceIn, Sub subIn, int indexNumIn) {
		super(oscInstanceIn);
		this.sub = subIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: SubUpdateEvent with Submaster " + subIn.getSubNum() + " at " + this.getTime());
	}
}