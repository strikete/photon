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
	private int parentIndexNum;
	
	/*
	 * METHODS
	 */
	public int getParentIndexNum() {
		return this.parentIndexNum;
	}
	public Cue getPartCue() {
		return this.partCue;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CuePartUpdateEvent(OscInstance oscInstanceIn, Cue cueIn, Cuelist parentCuelistIn, int indexNumIn, Cue partCueIn, int parentIndexNumIn) {
		super(oscInstanceIn, cueIn, parentCuelistIn, indexNumIn);
		this.partCue = partCueIn;
		this.parentIndexNum = parentIndexNumIn;
		Main.log.debug("EVENT TRIGGERED: CuePartUpdateEvent with Part " + partCueIn.getCueNumber() + " of Cue " + cueIn.getCueNumber() + " from Cuelist " + parentCuelistIn.getCuelistNumber() +" at " + this.getTime());
	}
}