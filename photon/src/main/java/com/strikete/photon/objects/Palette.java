package com.strikete.photon.objects;

import java.util.ArrayList;

public class Palette implements CsvLevelDataSetter {

	/*
	 * VARIABLES
	 */
	private float paletteNum;
	private int index;
	private String uid;
	private String label;
	private boolean absolute;
	private boolean locked;
	private ArrayList<Integer> channelList = new ArrayList<Integer>();
	private ArrayList<Integer> byTypeChannelList = new ArrayList<Integer>();
	private ArrayList<Integer> channels = new ArrayList<Integer>();
	private ArrayList<String> parameters = new ArrayList<String>();
	private ArrayList<Float> levels = new ArrayList<Float>();
	
	
	/*
	 * METHODS - GETTERS
	 */
	public float getPaletteNumber() {
		return paletteNum;
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
	public boolean getAbsolute() {
		return absolute;
	}
	public boolean getLocked() {
		return locked;
	}
	public ArrayList<Integer> getChannelList() {
		return channelList;
	}
	public ArrayList<Integer> getByTypeChannelList() {
		return byTypeChannelList;
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
	public void setAbsolute(boolean absoluteIn) {
		this.absolute = absoluteIn;
	}
	public void setLocked(boolean lockedIn) {
		this.locked = lockedIn;
	}
	public void setChannelList(ArrayList<Integer> channelListIn) {
		this.channelList = channelListIn;
	}
	public void setByTypeChannelList(ArrayList<Integer> byTypeChannelListIn) {
		this.byTypeChannelList = byTypeChannelListIn;
	}
	public void setChannels(ArrayList<Integer> channelsIn) {
		this.channels = channelsIn;
	}
	public void setParamters(ArrayList<String> parametersIn) {
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
	public Palette(float paletteNumIn, int indexIn, String uidIn) {
		this.paletteNum = paletteNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}