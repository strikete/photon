package com.strikete.photon.events;

import com.strikete.photon.Main;
import com.strikete.photon.objects.FocusPalette;
import com.strikete.photon.osc.OscInstance;

public class FocusPaletteUpdateEvent extends GenericUpdateEvent {

	/*
	 * VARIABLES
	 */
	private FocusPalette fp;
	private int indexNum;
	
	/*
	 * METHODS
	 */
	public int getIndexNum() {
		return this.indexNum;
	}
	public FocusPalette getFocusPalette() {
		return this.fp;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public FocusPaletteUpdateEvent(OscInstance oscInstanceIn, FocusPalette fpIn, int indexNumIn) {
		super(oscInstanceIn);
		this.fp = fpIn;
		this.indexNum = indexNumIn;
		Main.log.debug("EVENT TRIGGERED: FocusPaletteUpdateEvent with Focus Palette " + fpIn.getPaletteNum() + " at " + this.getTime());
	}
}
