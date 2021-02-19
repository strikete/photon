package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class EffectCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int effectNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfEffects() {
		return this.effectNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public EffectCountUpdateEvent(OscInstance oscInstanceIn, int effectNumIn) {
		super(oscInstanceIn);
		this.effectNum = effectNumIn;
		Main.log.debug("EVENT TRIGGERED: EffectCountUpdateEvent at " + this.getTime());
	}
}