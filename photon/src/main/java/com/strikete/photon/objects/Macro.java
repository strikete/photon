package com.strikete.photon.objects;

public class Macro {
	
	/*
	 * VARIABLES
	 */
	private float macroNum;
	private String UID;
	private String name;
	private String mode;
	private String commandText;
	
	/*
	 * METHODS
	 */
	public float getMacroNum() {
		return this.macroNum;
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
	public String getCommandText() {
		return this.commandText;
	}
	
	public void addCommandText(String commandIn) {
		 this.commandText = this.commandText + " " + commandIn;
	}
	
	/*
	 * CONSTRUCTORS
	 */
	public Macro(float macroNumIn, String UIDin, String nameIn, String modeIn) {
		this.macroNum = macroNumIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.mode = modeIn;
	}

}
