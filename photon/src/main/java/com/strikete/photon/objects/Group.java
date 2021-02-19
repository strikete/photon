package com.strikete.photon.objects;

public class Group {

	/*
	 * VARIABLES
	 */
	private String UID;
	private Channel[] channels = new Channel[5120];
    private int numOfChannels;
	private String name;
	private float groupNum;
	
	/*
	 * METHODS
	 */
	public String getUID() {
		return this.UID;
	}
	public Channel[] getChannels() {
		return this.channels;
	}
	public String getName() {
		return this.name;
	}
	public int getNumOfChannels() {
		return(this.numOfChannels + 1);
	}
	public float getGroupNum() {
		return this.groupNum;
	}
	
	
	public void addChannel(Channel channelIn) { //As of right now if you need to modify channels you'll just need to create a new group object.
		this.channels[this.numOfChannels] = channelIn;
		this.numOfChannels++;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Group(String UIDin, String nameIn, float groupNumIn) {
		this.UID = UIDin;
		this.name = nameIn;
		this.groupNum = groupNumIn;
		this.numOfChannels = 0;
	}
}
