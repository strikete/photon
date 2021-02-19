package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class GroupCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int groupNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfGroups() {
		return this.groupNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public GroupCountUpdateEvent(OscInstance oscInstanceIn, int groupNumIn) {
		super(oscInstanceIn);
		this.groupNum = groupNumIn;
		Main.log.debug("EVENT TRIGGERED: GroupCountUpdateEvent at " + this.getTime());
	}
}