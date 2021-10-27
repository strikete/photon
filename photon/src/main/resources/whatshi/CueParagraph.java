package com.strikete.photon.wtsahtsi;

import java.util.ArrayList;

public class CueParagraph {

	/*
	 * VARIABLES
	 */
	public ArrayList<CueSentence> cueSentences = new ArrayList<CueSentence>();
	private CueNumber cueNumberObj;
	
	/*
	 * METHODS
	 */
	public float getCueNumber() {
		return cueNumberObj.getCueNumber();
	}
	public boolean getCuePartBoolean() {
		return cueNumberObj.getCuePartBoolean();
	}
	public int getCuePart() {
		return cueNumberObj.getCuePart();
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CueParagraph(CueNumber cnIn, ArrayList<CueSentence> csIn) {
		this.cueNumberObj = cnIn;
		this.cueSentences = csIn;
	}
}
