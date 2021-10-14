package com.strikete.photon.objects;

import java.util.ArrayList;

public class Sub implements CsvLevelDataSetter {
	
	/*
	 * VARIABLES
	 */
	private int subNum;
	private int index;
	private String uid;
	private String label;
	private String mode;
	private String faderMode;
	private boolean htp;
	private boolean exclusive;
	private boolean background;
	private boolean restore;
	private String priority;
	private String upTime;
	private String dwellTime;
	private String downTime;
	private ArrayList<Integer> effectList = new ArrayList<Integer>();
	private ArrayList<Integer> channels = new ArrayList<Integer>();
	private ArrayList<String> parameters = new ArrayList<String>();
	private ArrayList<Float> levels = new ArrayList<Float>();
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getSubNumber() {
		return subNum;
	}
	public int getIndexNumber() {
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
	public String getFaderMode() {
		return faderMode;
	}
	public boolean getHtp() {
		return htp;
	}
	public boolean getExclusive() {
		return exclusive;
	}
	public boolean getBackground() {
		return background;
	}
	public boolean getRestore() {
		return restore;
	}
	public String getPriority() {
		return priority;
	}
	public String getUpTime() {
		return upTime;
	}
	public String getDwellTime() {
		return dwellTime;
	}
	public String getDownTime() {
		return downTime;
	}
	public ArrayList<Integer> getEffectList() {
		return effectList;
	}
	public ArrayList<Integer> getChannels() {
		return channels;
	}
	public ArrayList<String> getParameters() {
		return parameters;
	}
	public ArrayList<Float> getLevels() {
		return levels;
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
	public void setFaderMode(String faderModeIn) {
		this.faderMode = faderModeIn;
	}
	public void setHtp(boolean htpIn) {
		this.htp = htpIn;
	}
	public void setExclusive(boolean exclusiveIn) {
		this.exclusive = exclusiveIn;
	}
	public void setBackground(boolean backgroundIn) {
		this.background = backgroundIn;
	}
	public void setRestore(boolean restoreIn) {
		this.restore = restoreIn;
	}
	public void setPriority(String priorityIn) {
		this.priority = priorityIn;
	}
	public void setUpTime(String upTimeIn) {
		this.upTime = upTimeIn;
	}
	public void setDwellTime(String dwellTimeIn) {
		this.dwellTime = dwellTimeIn;
	}
	public void setDownTime(String downTimeIn) {
		this.downTime = downTimeIn;
	}
	public void setEffectList(ArrayList<Integer> effectListIn) {
		this.effectList = effectListIn;
	}
	public void addEffect(int effectIn) {
		this.effectList.add(effectIn);
	}
	public void setChannels(ArrayList<Integer> channelsIn) {
		this.channels = channelsIn;
	}
	public void setParameters(ArrayList<String> parametersIn) {
		this.parameters = parametersIn;
	}
	public void setLevels(ArrayList<Float> levelsIn) {
		this.levels = levelsIn;
	}
	
	
	/*
	 * METHODS - INTERFACES
	 */
	@Override
	public void addCsvLevelData(int channelIn, String parameterIn, float levelIn) {
		this.channels.add(channelIn);
		this.parameters.add(parameterIn);
		this.levels.add(levelIn);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Sub(int subNumIn, int indexIn, String uidIn) {
		this.subNum = subNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}