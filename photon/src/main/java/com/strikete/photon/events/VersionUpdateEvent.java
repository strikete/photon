package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class VersionUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private String version;
	
	/*
	 * METHOD
	 */
	public String getVersion() {
		return this.version;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public VersionUpdateEvent(OscInstance oscInstanceIn, String versionIn) {
		super(oscInstanceIn);
		this.version = versionIn;
		Main.log.debug("EVENT TRIGGERED: VersionUpdateEvent at " + this.getTime());
	}

}