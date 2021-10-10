package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Effect;
import com.strikete.photon.osc.OscInstance;

public class EffectUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Effect fx;
	
	/*
	 * METHODS
	 */
	public Effect getEffect() {
		return this.fx;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public EffectUpdateEvent(OscInstance oscInstanceIn, Effect effectIn) {
		super(oscInstanceIn);
		this.fx = effectIn;
		Main.log.debug("EVENT TRIGGERED: EffectUpdateEvent with Effect " + effectIn.getEffectNum() + " at " + this.getTime());
	}
}
