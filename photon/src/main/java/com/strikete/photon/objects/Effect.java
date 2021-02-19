package com.strikete.photon.objects;

public class Effect {

	/*
	 * VARIABLES
	 */
	private float fxNum;
	private String UID;
	private String name;
	private String effectType; //TODO: Make enumeration of types
	private String entry;
	private String exit;
	private String duration;
	private String scale;
	
	/*
	 * METHODS
	 */
	public float getEffectNum() {
		return this.fxNum;
	}
	public String getUID() {
		return this.UID;
	}
	public String getName() {
		return this.name;
	}
	public String getEffectType() {
		return this.effectType;
	}
	public String getEntry() {
		return this.entry;
	}
	public String getExit() {
		return this.exit;
	}
	public String getDuration() {
		return this.duration;
	}
	public String getScale() {
		return this.scale;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Effect(float fxNumIn, String UIDin, String nameIn, String effectTypeIn, String entryIn, String exitIn, String durationIn, String scaleIn) {
		this.fxNum = fxNumIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.effectType = effectTypeIn;
		this.entry = entryIn;
		this.exit = exitIn;
		this.duration = durationIn;
		this.scale = scaleIn;
	}
	
	
}
