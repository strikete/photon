package com.strikete.photon.objects;

public class Palette {

	/*
	 * VARIABLES
	 */
	private float paletteNum;
	private String UID;
	private String name;
	private boolean absolute;
	private boolean locked;
	private Channel[] channels = new Channel[65535];
	private int numOfChannels;
	private Effect[] effects = new Effect[65535];
	private int numOfEffects;
	
	/*
	 * METHODS
	 */
	
	public float getPaletteNum() {
		return this.paletteNum;
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
	public int getNumOfEffects() {
		return this.numOfEffects;
	}
	
	public void addChannel(Channel channelIn) {
		channels[numOfChannels] = channelIn;
		numOfChannels++;
	}
	public void addEffect(Effect effectIn) {
		effects[numOfEffects] = effectIn;
		numOfEffects++;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Palette(float paletteNumIn, String UIDin, String nameIn, boolean absoluteIn, boolean lockedIn) {
		this.paletteNum = paletteNumIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.absolute = absoluteIn;
		this.locked = lockedIn;
		this.numOfChannels = 0;
		this.numOfEffects = 0;
	}
}
