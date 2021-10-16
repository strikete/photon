package com.strikete.photon.objects;

public class Snapshot {

	/*
	 * VARIABLES
	 */
	private int snapshotNum;
	private int index;
	private String uid;
	private String label;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getSnapshotNumber() {
		return snapshotNum;
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
	public Snapshot(int snapshotNumIn, int indexIn, String uidIn) {
		this.snapshotNum = snapshotNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}