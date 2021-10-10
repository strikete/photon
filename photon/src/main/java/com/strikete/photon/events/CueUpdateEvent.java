package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Cue;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.osc.OscInstance;

public class CueUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Cue cue;
	private Cuelist parentCuelist;
	
	/*
	 * METHODS
	 */
	public Cue getCue() {
		return this.cue;
	}
	public Cuelist getParentCuelist() {
		return this.parentCuelist;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CueUpdateEvent(OscInstance oscInstanceIn, Cue cueIn, Cuelist parentCuelistIn) {
		super(oscInstanceIn);
		this.cue = cueIn;
		this.parentCuelist = parentCuelistIn;
		Main.log.debug("EVENT TRIGGERED: CueUpdateEvent with Cue " + cueIn.getCueNumber() + " from Cuelist " + parentCuelistIn.getCuelistNumber() +" at " + this.getTime());
	}
}