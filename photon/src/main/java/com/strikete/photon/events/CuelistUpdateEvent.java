package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.osc.OscInstance;

public class CuelistUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Cuelist cuelist;
	
	/*
	 * METHODS
	 */
	public Cuelist getCuelist() {
		return this.cuelist;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CuelistUpdateEvent(OscInstance oscInstanceIn, Cuelist cuelistIn) {
		super(oscInstanceIn);
		this.cuelist = cuelistIn;
		Main.log.debug("EVENT TRIGGERED: CuelistUpdateEvent at " + this.getTime());
	}

}
