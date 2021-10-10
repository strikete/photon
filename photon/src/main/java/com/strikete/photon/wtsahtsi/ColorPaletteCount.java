package com.strikete.photon.wtsahtsi;

import com.strikete.photon.osc.OscInstance;

public class ColorPaletteCount {

	/*
	 * VARIABLES
	 */
	private float colorPaletteId;
	private int hitCounter;
	private String name;
	
	/*
	 * METHODS
	 */
	public void print() {
		System.out.println("Color Palette " + colorPaletteId + " - " + name + " - " + hitCounter + " hits.");
	}
	public int getHits() {
		return hitCounter;
	}
	public void incrementHits() {
		this.hitCounter++;
	}
	public float getColorPaletteId() {
		return colorPaletteId;
	}
	public void findName(OscInstance osc) {
		this.name = osc.getOscParser().getColorPaletteFromIndex(osc.getOscParser().colorPaletteNumberIndexReturn(colorPaletteId)).getName();
	}
	public String getName() {
		return name;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public ColorPaletteCount(float cpIdIn) {
		this.colorPaletteId = cpIdIn;
		this.hitCounter = 1;
		this.name = "";
	}
}
