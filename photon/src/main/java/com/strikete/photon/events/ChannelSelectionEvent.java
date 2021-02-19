package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.osc.OscInstance;

public class ChannelSelectionEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private Channel channel;
	private int indexNum;
	private boolean singleChannelSelection;
	
	/*
	 * METHODS
	 */
	public boolean getSingleChannelSelectionBoolean() {
		return this.singleChannelSelection;
	}
	public int getIndexNum() {
		return this.indexNum;
	}
	public Channel getChannel() {
		return this.channel;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ChannelSelectionEvent(OscInstance oscInstanceIn, Channel channelIn, int indexNumIn, boolean singleChannelSelectionIn) {
		super(oscInstanceIn);
		this.channel = channelIn;
		this.indexNum = indexNumIn;
		this.singleChannelSelection = singleChannelSelectionIn;
		Main.log.debug("EVENT TRIGGERED: ChannelSelectionEvent with Channel " + channelIn.getChannelNum() + " at " + this.getTime());
	}
}
