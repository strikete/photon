package com.strikete.photon.wtsahtsi;

public class CueLevel {

	/*
	 * VARIABLES
	 */
	private int channel;
	private CueNumber cueNumberObj;
	public CueVariables cueVariable;
	
	
	/*
	 * METHODS
	 */
	public float getCueNumber() {
		return cueNumberObj.getCueNumber();
	}
	public int getChannelNumber() {
		return channel;
	}
	public boolean getCuePartBool() {
		return cueNumberObj.getCuePartBoolean();
	}
	
	public int getCuePart() {
		return cueNumberObj.getCuePart();
	}
	public void setCuePart(int value) {
		cueNumberObj.setCuePart(value);
	}
	public CueNumber getCueNumberObj() {
		return cueNumberObj;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CueLevel(float cueIn, int channelIn) {
		this.cueNumberObj = new CueNumber(cueIn);
		this.channel = channelIn;
		this.cueVariable = new CueVariables();
	}
}
