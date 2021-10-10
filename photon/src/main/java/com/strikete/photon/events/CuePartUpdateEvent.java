package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Cue;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.osc.OscInstance;

public class CuePartUpdateEvent extends CueUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Cue partCue;
	
	/*
	 * METHODS
	 */
	public Cue getPartCue() {
		return this.partCue;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CuePartUpdateEvent(OscInstance oscInstanceIn, Cue cueIn, Cuelist parentCuelistIn, Cue partCueIn) {
		super(oscInstanceIn, cueIn, parentCuelistIn);
		this.partCue = partCueIn;
		Main.log.debug("EVENT TRIGGERED: CuePartUpdateEvent with Part " + partCueIn.getCueNumber() + " of Cue " + cueIn.getCueNumber() + " from Cuelist " + parentCuelistIn.getCuelistNumber() +" at " + this.getTime());
	}
}