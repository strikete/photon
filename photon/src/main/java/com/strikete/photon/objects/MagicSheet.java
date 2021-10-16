package com.strikete.photon.objects;

public class MagicSheet {

	/*
	 * VARIABLES
	 */
	private int magicSheetNum;
	private int index;
	private String uid;
	private String label;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getMagicSheetNumber() {
		return magicSheetNum;
	}
	public int getIndex() {
		return index;
	}
	public String getUid() {
		return uid;
	}
	public String getLabel() {
		return label;
	}
	
	
	/*
	 * METHODS - SETTER
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public MagicSheet(int magicSheetNumIn, int indexIn, String uidIn) {
		this.magicSheetNum = magicSheetNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}