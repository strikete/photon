package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.osc.OscInstance;

public class CuelistUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Cuelist cuelist;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public Cuelist getCuelist() {
		return this.cuelist;
	}
	public int getIndexNumber() {
		return this.indexNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CuelistUpdateEvent(OscInstance oscInstanceIn, Cuelist cuelistIn, int indexNumIn) {
		super(oscInstanceIn);
		this.cuelist = cuelistIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: CuelistUpdateEvent at " + this.getTime());
	}

}
