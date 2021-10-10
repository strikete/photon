package com.strikete.photon.wtsahtsi;

public class CueVariables {

	/*
	 * VARIABLES
	 */
	private boolean intensityBool;
	private int intensityLevel;
	
	private boolean presetBool;
	private boolean ipBool;
	private boolean fpBool;
	private boolean cpBool;
	private boolean bpBool;
	
	private float presetId;
	private float intensityPaletteId;
	private float focusPaletteId;
	private float colorPaletteId;
	private float beamPaletteId;
	
	/*
	 * METHODS
	 */
	public int getIntensityLevel() {
		return intensityLevel;
	}

	public boolean getIntensityBoolean() {
		return intensityBool;
	}
	public boolean getPresetBool() {
		return presetBool;
	}
	public boolean getIpBool() {
		return ipBool;
	}
	public boolean getFpBool() {
		return fpBool;
	}
	public boolean getCpBool() {
		return cpBool;
	}
	public boolean getBpBool() {
		return bpBool;
	}
	public float getPresetId() {
		return presetId;
	}
	public float getIpId() {
		return intensityPaletteId;
	}
	public float getFpId() {
		return focusPaletteId;
	}
	public float getCpId() {
		return colorPaletteId;
	}
	public float getBpId() {
		return beamPaletteId;
	}
	public void setIntensityLevel(int value) {
		intensityBool = true;
		intensityLevel = value;
	}
	public void setPresetId(float value) {
		presetBool = true;
		presetId = value;
	}
	public void setIntensityPaletteId(float value) {
		intensityBool = false;
		ipBool = true;
		intensityPaletteId = value;
	}
	public void setFocusPaletteId(float value) {
		fpBool = true;
		focusPaletteId = value;
	}
	public void setColorPaletteId(float value) {
		cpBool = true;
		colorPaletteId = value;
	}
	public void setBeamPaletteId(float value) {
		bpBool = true;
		beamPaletteId = value;
	}
	/*
	 * CONSTRUCTOR
	 */
	public CueVariables() {
		intensityBool = false;
		ipBool = false;
		fpBool = false;
		cpBool = false;
		bpBool = false;
	}
	
}
