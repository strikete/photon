package com.strikete.photon.wtsahtsi;

public class CueNumber {

	/*
	 * VARIABLES
	 */
	private float cueNumber;
	private boolean cuePartBool;
	private int cuePartNumber;
	
	/*
	 * METHODS
	 */
	public float getCueNumber() {
		return cueNumber;
	}
	public boolean getCuePartBoolean() {
		return cuePartBool;
	}
	public int getCuePart() {
		return cuePartNumber;
	}
	public void setCuePart(int cuePartIn) {
		this.cuePartBool = true;
		this.cuePartNumber = cuePartIn;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CueNumber(float cueNumberIn) {
		this.cueNumber = cueNumberIn;
		this.cuePartBool = false;
	}
	public CueNumber(float cueNumberIn, int cuePartIn) {
		this.cueNumber = cueNumberIn;
		this.cuePartBool = true;
		this.cuePartNumber = cuePartIn;
	}
}
