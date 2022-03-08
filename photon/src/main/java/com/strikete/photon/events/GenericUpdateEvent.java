package com.strikete.photon.events;

public class GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	long timestamp;
	
	/*
	 * METHODS
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public GenericUpdateEvent() {
		this.timestamp = System.nanoTime();
	}
}