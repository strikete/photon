package com.strikete.photon.objects;

public class Sub {
	
	/*
	 * VARIABLES
	 */
	private float subNum;
	private String UID;
	private String name;
	private String mode;
	private String faderMode;
	private boolean HTP;
	private boolean exclusive;
	private boolean background;
	private boolean restore;
	private String priority;
	private String upTime;
	private String dwellTime;
	private String downTime;
	private Effect[] effects = new Effect[512];
	private int effectCount;
	
	/*
	 * METHODS
	 */
	public float getSubNum() {
		return this.subNum;
	}
	public String getUID() {
		return this.UID;
	}
	public String getName() {
		return this.name;
	}
	public String getMode() {
		return this.mode;
	}
	public String getFaderMode() {
		return this.faderMode;
	}
	public boolean getHTP() {
		return this.HTP;
	}
	public boolean getExclusive() {
		return this.exclusive;
	}
	public boolean getBackground() {
		return this.background;
	}
	public boolean getRestore() {
		return this.restore;
	}
	public String getPriority() {
		return this.priority;
	}
	public String getUpTime() {
		return this.upTime;
	}
	public String getDwellTime() {
		return this.dwellTime;
	}
	public String getDownTime() {
		return this.downTime;
	}
	public Effect getEffect(int indexNum) {
		return this.effects[indexNum];
	}
	public int getEffectCount() {
		return this.effectCount;
	}
	
	public void addEffect(Effect effectIn) {
		this.effects[effectCount] = effectIn;
		effectCount++;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Sub(float subNumIn, String UIDin, String nameIn, String modeIn, String faderModeIn, boolean HTPin, boolean exclusiveIn,
			boolean backgroundIn, boolean restoreIn, String priorityIn, String upTimeIn, String dwellTimeIn, String downTimeIn) {
		
		this.subNum = subNumIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.mode = modeIn;
		this.faderMode = faderModeIn;
		this.HTP = HTPin;
		this.exclusive = exclusiveIn;
		this.background = backgroundIn;
		this.restore = restoreIn;
		this.priority = priorityIn;
		this.upTime = upTimeIn;
		this.dwellTime = dwellTimeIn;
		this.downTime = downTimeIn;
		this.effectCount = 0;
	}

}
