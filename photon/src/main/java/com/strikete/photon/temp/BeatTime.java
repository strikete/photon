package com.strikete.photon.temp;

public class BeatTime {

	/*
	 * VARIABLES
	 */
	public int startSampleNumber;
	public int endSampleNumber;
	public float startSeconds;
	public float endSeconds;
	
	/*
	 * CONSTRUCTOR
	 */
	public BeatTime(int startSampleNumIn, int endSampleNumIn) {
		this.startSampleNumber = startSampleNumIn;
		this.endSampleNumber = endSampleNumIn;
		this.startSeconds = startSampleNumIn / 8000;
		this.endSeconds = endSampleNumIn / 8000;
	}
}
