package com.strikete.photon.events;

public class VersionResponseEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private String version;
	
	/*
	 * METHODS
	 */
	public String getVersion() {
		return version;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public VersionResponseEvent(String version) {
		super();
	}
}