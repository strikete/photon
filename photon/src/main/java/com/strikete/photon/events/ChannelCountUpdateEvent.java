package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class ChannelCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int channelNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfChannels() {
		return this.channelNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ChannelCountUpdateEvent(OscInstance oscInstanceIn, int channelNumIn) {
		super(oscInstanceIn);
		this.channelNum = channelNumIn;
		Main.log.debug("EVENT TRIGGERED: ChannelCountUpdateEvent at " + this.getTime());
	}
}