package com.strikete.photon.objects;

import java.util.ArrayList;

public class Palette {

	/*
	 * VARIABLES
	 */
	private float paletteNum;
	private String UID;
	private String name;
	private boolean absolute;
	private boolean locked;
	private ArrayList<Channel> channels = new ArrayList<Channel>();
	private ArrayList<Effect> effects = new ArrayList<Effect>();
	
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
		return channels.get(indexNum);
	}
	public int getChannelCount() {
		return channels.size();
	}
	public int getEffectCount() {
		return effects.size();
	}
	public void addChannel(Channel channelIn) {
		channels.add(channelIn);
	}
	public void addEffect(Effect effectIn) {
		effects.add(effectIn);
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
	}
}
