package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.ColorPalette;
import com.strikete.photon.osc.OscInstance;

public class ColorPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private ColorPalette cp;
	
	/*
	 * METHODS
	 */
	public ColorPalette getColorPalette() {
		return this.cp;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ColorPaletteUpdateEvent(OscInstance oscInstanceIn, ColorPalette cpIn) {
		super(oscInstanceIn);
		this.cp = cpIn;
		Main.log.debug("EVENT TRIGGERED: ColorPaletteUpdateEvent with Color Palette " + cpIn.getPaletteNum() + " at " + this.getTime());
	}
}
