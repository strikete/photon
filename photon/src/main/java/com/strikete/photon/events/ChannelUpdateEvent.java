package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.osc.OscInstance;

public class ChannelUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Channel channel;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public Channel getChannel() {
		return this.channel;
	}
	public int getIndexNumber() {
		return this.indexNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ChannelUpdateEvent(OscInstance oscInstanceIn, Channel channelIn, int indexNumIn) {
		super(oscInstanceIn);
		this.channel = channelIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: ChannelUpdateEvent with Channel " + channelIn.getChannelNum() + " at " + this.getTime());
	}

}
