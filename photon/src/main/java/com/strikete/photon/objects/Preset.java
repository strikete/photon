package com.strikete.photon.objects;

public class Preset {
	
	/*
	 * VARIABLES
	 */
	private float presetNum;
	private String UID;
	private String name;
	private boolean absolute;
	private boolean locked;
	private Channel[] channels = new Channel[65535];
	private int numOfChannels;
	private Effect[] effects = new Effect[512];
	private int numOfEffects;
	
	/*
	 * METHODS
	 */
	public float getPresetNum() {
		return this.presetNum;
	}
	public String getUID() {
		return this.UID;
	}
	public String getName() {
		return this.name;
	}
	public boolean getAbsolute() {
		return this.absolute;
	}
	public boolean getLocked() {
		return this.locked;
	}
	public Channel getChannel(int indexNum) {
		return this.channels[indexNum];
	}
	public int getNumOfChannels() {
		return this.numOfChannels;
	}
	public Effect getEffect(int indexNum) {
		return this.effects[indexNum];
	}
	public int getNumOfEffects() {
		return this.numOfEffects;
	}
	
	public void addChannel(Channel channelIn) {
		this.channels[numOfChannels] = channelIn;
		numOfChannels++;
	}
	
	public void addEffect(Effect effectIn) {
		this.effects[numOfEffects] = effectIn;
		numOfEffects++;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Preset(float presetNumIn, String UIDin, String nameIn, boolean absoluteIn, boolean lockedIn) {
		this.presetNum = presetNumIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.absolute = absoluteIn;
		this.locked = lockedIn;
		numOfChannels = 0;
		numOfEffects = 0;
	}
}
