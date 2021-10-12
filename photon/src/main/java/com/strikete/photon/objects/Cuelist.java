package com.strikete.photon.objects;

import java.util.ArrayList;

public class Cuelist {

	/*
	 * VARIABLES
	 */
	private int cuelistNum;
	private int index;
	private String uid;
	private String label;
	private String playbackMode;
	private String faderMode;
	private boolean independent;
	private boolean htp;
	private boolean assertBool;
	private boolean blockBool;
	private boolean background;
	private boolean soloMode;
	private int timecodeList;
	private boolean oosSync;
	private ArrayList<Integer> linkedCueLists = new ArrayList<Integer>();
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getCuelistNumber() {
		return cuelistNum;
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
	public String getPlaybackMode() {
		return playbackMode;
	}
	public String getFaderMode() {
		return faderMode;
	}
	public boolean getIndependent() {
		return independent;
	}
	public boolean getHtp() {
		return htp;
	}
	public boolean getAssert() {
		return assertBool;
	}
	public boolean getBlock() {
		return blockBool;
	}
	public boolean getBackground() {
		return background;
	}
	public boolean getSoloMode() {
		return soloMode;
	}
	public int getTimecodeList() {
		return timecodeList;
	}
	public boolean getOosSync() {
		return oosSync;
	}
	public ArrayList<Integer> getLinkedCuelists(){
		return linkedCueLists;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setPlaybackMode(String playbackModeIn) {
		this.playbackMode = playbackModeIn;
	}
	public void setFaderMode(String faderModeIn) {
		this.faderMode = faderModeIn;
	}
	public void setIndependent(boolean independentIn) {
		this.independent = independentIn;
	}
	public void setHtp(boolean htpIn) {
		this.htp = htpIn;
	}
	public void setAssert(boolean assertIn) {
		this.assertBool = assertIn;
	}
	public void setBlock(boolean blockIn) {
		this.blockBool = blockIn;
	}
	public void setBackground(boolean backgroundIn) {
		this.background = backgroundIn;
	}
	public void setSoloMode(boolean soloModeIn) {
		this.soloMode = soloModeIn;
	}
	public void setTimecodeList(int timecodeListIn) {
		this.timecodeList = timecodeListIn;
	}
	public void setOosSync(boolean oosSyncIn) {
		this.oosSync = oosSyncIn;
	}
	public void setLinkedCueLists(ArrayList<Integer> linkedCueListsIn) {
		this.linkedCueLists = linkedCueListsIn;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public Cuelist(int cuelistNumIn, int indexIn, String uidIn) {
		this.cuelistNum = cuelistNumIn;
		this.index = indexIn;
		this.uid = uidIn;
	}
}