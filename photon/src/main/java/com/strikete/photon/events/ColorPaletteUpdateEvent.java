package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.ColorPalette;
import com.strikete.photon.osc.OscInstance;

public class ColorPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private ColorPalette cp;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public ColorPalette getColorPalette() {
		return this.cp;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ColorPaletteUpdateEvent(OscInstance oscInstanceIn, ColorPalette cpIn, int indexNumIn) {
		super(oscInstanceIn);
		this.cp = cpIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: ColorPaletteUpdateEvent with Color Palette " + cpIn.getPaletteNum() + " at " + this.getTime());
	}
}
