package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Effect;
import com.strikete.photon.osc.OscInstance;

public class EffectUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Effect fx;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public Effect getEffect() {
		return this.fx;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public EffectUpdateEvent(OscInstance oscInstanceIn, Effect effectIn, int indexNumIn) {
		super(oscInstanceIn);
		this.fx = effectIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: EffectUpdateEvent with Effect " + effectIn.getEffectNum() + " at " + this.getTime());
	}
}
