package com.strikete.photon.objects;

public class Effect {

	/*
	 * VARIABLES
	 */
	private float effectNum;
	private int index;
	private String uid;
	private String label;
	private String effectType;
	private String entry;
	private String exit;
	private String duration;
	private String scale;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public float getEffectNumber() {
		return effectNum;
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
	public String getEffectType() {
		return effectType;
	}
	public String getEntry() {
		return entry;
	}
	public String getExit() {
		return exit;
	}
	public String getDuration() {
		return duration;
	}
	public String getScale() {
		return scale;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setEffectType(String effectTypeIn) {
		this.effectType = effectTypeIn;
	}
	public void setEntry(String entryIn) {
		this.entry = entryIn;
	}
	public void setExit(String exitIn) {
		this.exit = exitIn;
	}
	public void setDuration(String durationIn) {
		this.duration = durationIn;
	}
	public void setScale(String scaleIn) {
		this.scale = scaleIn;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Effect(float effectNumIn, int indexIn, String uidIn) {
		this.effectNum = effectNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}