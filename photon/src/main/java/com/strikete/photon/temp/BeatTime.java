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
		float modifier = (float) 8000.00;
		this.startSeconds = startSampleNumIn / modifier;
		this.endSeconds = endSampleNumIn / modifier;
	}
}
