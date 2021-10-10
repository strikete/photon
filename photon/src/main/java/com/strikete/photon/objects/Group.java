package com.strikete.photon.objects;

import java.util.ArrayList;

public class Group {

	/*
	 * VARIABLES
	 */
	private String UID;
	private ArrayList<Channel> channels = new ArrayList<Channel>();
	private String name;
	private float groupNum;
	
	/*
	 * METHODS
	 */
	public String getUID() {
		return this.UID;
	}
	public ArrayList<Channel> getChannels(){
		return channels;
	}
	public ArrayList<Integer> getChannelsAsInt(){
		ArrayList<Integer> channelsAsInt = new ArrayList<Integer>();
		for(int x = 0; x < channels.size(); x++) {
			channelsAsInt.add(channels.get(x).getChannelNum());
		}
		return channelsAsInt;
	}
	public Channel getChannel(int index) {
		return channels.get(index);
	}
	public String getName() {
		return this.name;
	}
	public int getChannelSize() {
		return channels.size();
	}
	public float getGroupNum() {
		return this.groupNum;
	}
	
	
	public void addChannel(Channel channelIn) { //As of right now if you need to modify channels you'll just need to create a new group object.
		channels.add(channelIn);
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Group(String UIDin, String nameIn, float groupNumIn) {
		this.UID = UIDin;
		this.name = nameIn;
		this.groupNum = groupNumIn;
	}
}
