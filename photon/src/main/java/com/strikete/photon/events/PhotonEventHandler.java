package com.strikete.photon.events;

import com.google.common.eventbus.EventBus;
import com.strikete.photon.osc.OscInstance;

public class PhotonEventHandler extends EventBus {
	/*
	 * VARIABLE
	 */
	private OscInstance oscinstance;
	
	/*
	 * METHOD
	 */
	public OscInstance getOscInstance() {
		return this.oscinstance;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public PhotonEventHandler(OscInstance oscinstanceIn) {
		super();
		this.oscinstance = oscinstanceIn;
	}
	
}
