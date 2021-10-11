package com.strikete.photon.objects;

public class Channel {

	/*
	 * VARIABLES
	 */
	private int channelNum;
	private int part;
	private int index;
	
	private String uid;
	private String label;
	private String manufacturer;
	private String model;
	private int address;
	private int intensAddress;
	private int level;
	private String gel;
	private String text1;
	private String text2;
	private String text3;
	private String text4;
	private String text5;
	private String text6;
	private String text7;
	private String text8;
	private String text9;
	private String text10;
	private int partCount;
	private String notes;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getChannelNumber() {
		return channelNum;
	}
	public int getPart() {
		return part;
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
	public String getManufacturer() {
		return manufacturer;
	}
	public String getModel() {
		return model;
	}
	public int getAddress() {
		return address;
	}
	public int getIntensityAddress() {
		return intensAddress;
	}
	public int getLevel() {
		return level;
	}
	public String getGel() {
		return gel;
	}
	public String getText1() {
		return text1;
	}
	public String getText2() {
		return text2;
	}
	public String getText3() {
		return text3;
	}
	public String getText4() {
		return text4;
	}
	public String getText5() {
		return text5;
	}
	public String getText6() {
		return text6;
	}
	public String getText7() {
		return text7;
	}
	public String getText8() {
		return text8;
	}
	public String getText9() {
		return text9;
	}
	public String getText10() {
		return text10;
	}
	public int getPartCount() {
		return partCount;
	}
	public String getNotes() {
		return notes;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setManufacturer(String manufacturerIn) {
		this.manufacturer = manufacturerIn;
	}
	public void setModel(String modelIn) {
		this.model = modelIn;
	}
	public void setAddress(int addressIn) {
		this.address = addressIn;
	}
	public void setIntensityAddress(int intensityAddressIn) {
		this.intensAddress = intensityAddressIn;
	}
	public void setLevel(int levelIn) {
		this.level = levelIn;
	}
	public void setGel(String gelIn) {
		this.gel = gelIn;
	}
	public void setText1(String textIn) {
		this.text1 = textIn;
	}
	public void setText2(String textIn) {
		this.text2 = textIn;
	}
	public void setText3(String textIn) {
		this.text3 = textIn;
	}
	public void setText4(String textIn) {
		this.text4 = textIn;
	}
	public void setText5(String textIn) {
		this.text5 = textIn;
	}
	public void setText6(String textIn) {
		this.text6 = textIn;
	}
	public void setText7(String textIn) {
		this.text7 = textIn;
	}
	public void setText8(String textIn) {
		this.text8 = textIn;
	}
	public void setText9(String textIn) {
		this.text9 = textIn;
	}
	public void setText10(String textIn) {
		this.text10 = textIn;
	}
	public void setPartCount(int partCountIn) {
		this.partCount = partCountIn;
	}
	public void setNotes(String notesIn) {
		this.notes = notesIn;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Channel(int channelNumIn, int partIn, int indexIn, String uidIn) {
		this.channelNum = channelNumIn;
		this.part = partIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}