package com.strikete.photon.objects;

import java.util.ArrayList;

public class Pixelmap {

	/*
	 * VARIABLES
	 */
	private int pixelmapNum;
	private int index;
	private String uid;
	private String label;
	private int serverChannel;
	private String interfaceVar;
	private int width;
	private int height;
	private int pixelCount;
	private int fixtureCount;
	private ArrayList<Integer> layerChannelList = new ArrayList<Integer>();
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getPixelmapNumber() {
		return pixelmapNum;
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
	public int getServerChannel() {
		return serverChannel;
	}
	public String getInterface() {
		return interfaceVar;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPixelCount() {
		return pixelCount;
	}
	public int getFixtureCount() {
		return fixtureCount;
	}
	public ArrayList<Integer> getLayerChannelList() {
		return layerChannelList;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setServerChannel(int serverChannelIn) {
		this.serverChannel = serverChannelIn;
	}
	public void setInterface(String interfaceIn) {
		this.interfaceVar = interfaceIn;
	}
	public void setWidth(int widthIn) {
		this.width = widthIn;
	}
	public void setHeight(int heightIn) {
		this.height = heightIn;
	}
	public void setPixelCount(int pixelCountIn) {
		this.pixelCount = pixelCountIn;
	}
	public void setFixtureCount(int fixtureCountIn) {
		this.fixtureCount = fixtureCountIn;
	}
	public void setLayerChannelList(ArrayList<Integer> layerChannelListIn) {
		this.layerChannelList = layerChannelListIn;
	}
	public void addLayerChannel(int layerChannelIn) {
		this.layerChannelList.add(layerChannelIn);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Pixelmap(int pixelmapNumIn, int indexIn, String uidIn) {
		this.pixelmapNum = pixelmapNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}