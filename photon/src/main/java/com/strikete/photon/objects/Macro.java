package com.strikete.photon.objects;

public class Macro {
	
	/*
	 * VARIABLES
	 */
	private int macroNum;
	private int index;
	private String uid;
	private String label;
	private String mode;
	private String commandText;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getMacroNumber() {
		return macroNum;
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
	public String getMode() {
		return mode;
	}
	public String getCommandText() {
		return commandText;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setMode(String modeIn) {
		this.mode = modeIn;
	}
	public void setCommandText(String commandTextIn) {
		this.commandText = commandTextIn;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Macro(int macroNumIn, int indexIn, String uidIn) {
		this.macroNum = macroNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}