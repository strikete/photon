package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Group;
import com.strikete.photon.osc.OscInstance;

public class GroupUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private int indexNum;
	private Group group;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public Group getGroup() {
		return this.group;
	}
	
	public GroupUpdateEvent(OscInstance oscInstanceIn, Group groupIn, int indexNumIn) {
		super(oscInstanceIn);
		this.indexNum = indexNumIn;
		this.group = groupIn;
		Main.log.debug("EVENT TRIGGERED: GroupUpdateEvent with Group " + groupIn.getGroupNum() + " at " + this.getTime());
	}
}
