package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.osc.OscInstance;

public class CueCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private Cuelist cuelist;
	private int cueNum;
	
	/*
	 * METHOD
	 */
	public Cuelist getCuelist() {
		return this.cuelist;
	}
	public int getNumberOfCues() {
		return this.cueNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CueCountUpdateEvent(OscInstance oscInstanceIn, Cuelist cuelistIn, int cueNumIn) {
		super(oscInstanceIn);
		this.cuelist = cuelistIn;
		this.cueNum = cueNumIn;
		Main.log.debug("EVENT TRIGGERED: CueCountUpdateEvent at " + this.getTime());
	}
}