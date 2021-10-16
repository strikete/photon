package com.strikete.photon.objects;

public class Curve {

	/*
	 * VARIABLES
	 */
	private int curveNum;
	private int index;
	private String uid;
	private String label;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getCurveNumber() {
		return curveNum;
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
	public Curve(int curveNumIn, int indexIn, String uidIn) {
		this.curveNum = curveNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}