package com.strikete.photon.objects;

public class Channel { //TODO: Add Part Support
	
	private int channelNum;
	private DmxAddress address;
	private String manufacturer;
	private String type;
	private String name;
	private String UID;
	private int level;
	
	/*
	 * METHODS
	 */
	
	//Getters
	public int getChannelNum() {
		return this.channelNum;
	}
	public DmxAddress getAddress() {
		return this.address;
	}
	public String getManufacturer() {
		return this.manufacturer;
	}
	public String getType() {
		return this.type;
	}
	public String getName() {
		return this.name;
	}
	public String getUID() {
		return this.UID;
	}
	public int getLevel() {
		return this.level;
	}
	
	//Setters
	public void setName(String nameIn) {
		this.name = nameIn;
	}
	public void setLevel(byte levelIn) {
		this.level = levelIn;
	}
	//TODO: Add address setters
	
	/*
	 * CONSTRUCTORS
	 */
	
	//TODO: Add more detailed constructors
	public Channel(int channelNumIn, int addressNumIn, int levelIn, String manufactuerIn, String typeIn, String nameIn, String UIDin) {
		this.channelNum = channelNumIn;
		this.address = new DmxAddress(addressNumIn);
		this.level = levelIn;
		this.manufacturer = manufactuerIn;
		this.type = typeIn;
		this.name = nameIn;
		this.UID = UIDin;
	}
	public Channel(int channelNumIn) {
		this.channelNum = channelNumIn;
	}
	
}
