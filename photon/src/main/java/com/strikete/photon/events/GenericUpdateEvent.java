package com.strikete.photon.events;

import com.strikete.photon.osc.OscInstance;

public class GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private OscInstance oscInstance;
	private long time;
	
	/*
	 * METHODS
	 */
	public OscInstance getOscInstance() {
		return this.oscInstance;
	}
	public long getTime() {
		return this.time;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public GenericUpdateEvent(OscInstance oscInstanceIn) {
		this.oscInstance = oscInstanceIn;
		this.time = System.currentTimeMillis();
	}
}