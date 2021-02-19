package com.strikete.photon;

public class Table {

	/*
	 * VARIABLES
	 */
	private int numOfLegs;
	private String material;
	
	/*
	 * METHODS
	 */
	public int getNumberOfLegs() {
		return this.numOfLegs;
	}
	public String getMaterial() {
		return this.material;
	}
	
	public void setNumberOfLegs(int numOfLegsIn) {
		this.numOfLegs = numOfLegsIn;
	}
	public void setMaterial(String materialIn) {
		this.material = materialIn;
	}
	
	/*
	 * CONSTRUCTORS
	 */
	public Table() {
		this.numOfLegs = 0;
		this.material = "wood";
	}
	public Table(int numOfLegsIn) {
		this.numOfLegs = numOfLegsIn;
		this.material = "wood";
	}
	public Table(int numOfLegsIn, String materialIn) {
		this.numOfLegs = numOfLegsIn;
		this.material = materialIn;
	}
}