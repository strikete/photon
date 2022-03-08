package com.strikete.photon.events;

public class ObjectDataGatheredEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private String type;
	private int count;
	
	/*
	 * METHODS
	 */
	public String getType() {
		return type;
	}
	public int getCount() {
		return count;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ObjectDataGatheredEvent(String type, int count) {
		super();
		this.type = type;
		this.count = count;
	}
}