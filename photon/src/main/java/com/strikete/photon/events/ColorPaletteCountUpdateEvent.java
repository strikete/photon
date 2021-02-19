package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.osc.OscInstance;

public class ColorPaletteCountUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLE
	 */
	private int colorPaletteNum;
	
	/*
	 * METHOD
	 */
	public int getNumberOfColorPalettes() {
		return this.colorPaletteNum;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ColorPaletteCountUpdateEvent(OscInstance oscInstanceIn, int colorPaletteNumIn) {
		super(oscInstanceIn);
		this.colorPaletteNum = colorPaletteNumIn;
		Main.log.debug("EVENT TRIGGERED: ColorPaletteCountUpdateEvent at " + this.getTime());
	}
}