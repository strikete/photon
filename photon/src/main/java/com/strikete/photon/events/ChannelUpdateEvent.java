package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.osc.OscInstance;

public class ChannelUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Channel channel;
	
	/*
	 * METHODS
	 */
	public Channel getChannel() {
		return this.channel;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ChannelUpdateEvent(OscInstance oscInstanceIn, Channel channelIn) {
		super(oscInstanceIn);
		this.channel = channelIn;
		Main.log.debug("EVENT TRIGGERED: ChannelUpdateEvent with Channel " + channelIn.getChannelNum() + " at " + this.getTime());
	}

}
