package com.strikete.photon.objects;

import java.util.ArrayList;

public class Group {

	/*
	 * VARIABLES
	 */
	private float groupNum;
	private int index;
	private String uid;
	private String label;
	private ArrayList<Integer> channels = new ArrayList<Integer>();
	
	
	/*
	 * METHODS - GETTERS
	 */
	public float getGroupNumber() {
		return groupNum;
	}
	public float getIndex() {
		return index;
	}
	public String getUid() {
		return uid;
	}
	public String getLabel() {
		return label;
	}
	public ArrayList<Integer> getChannels(){
		return channels;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setChannels(ArrayList<Integer> channelsIn) {
		this.channels = channelsIn;
	}
	public void addChannel(int channelIn) {
		this.channels.add(channelIn);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Group(float groupNumIn, int indexIn, String uidIn) {
		this.groupNum = groupNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}