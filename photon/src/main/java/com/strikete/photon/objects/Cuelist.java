package com.strikete.photon.objects;

public class Cuelist {

	/*
	 * VARIABLES
	 */
	private int cuelistNum;
	private String UID;
	private String name;
	private String playbackMode;
	private String faderMode;
	private boolean faderModeIndependence;
	private boolean HTP;
	private boolean assertBool;
	private boolean block;
	private boolean background;
	private boolean soloMode;
	private int timecodeList;
	private boolean OOSsync;
	
	private Cue[] cues = new Cue[65535];
	private int cueCount;
	
	/*
	 * METHODS
	 */
	public int getCuelistNumber() {
		return this.cuelistNum;
	}
	public String getUID() {
		return this.UID;
	}
	public String getName() {
		return this.name;
	}
	public String getPlaybackMode() {
		return this.playbackMode;
	}
	public String getFaderMode() {
		return this.faderMode;
	}
	public boolean getFaderModeIndependence() {
		return this.faderModeIndependence;
	}
	public boolean getHTP() {
		return this.HTP;
	}
	public boolean getAssert() {
		return this.assertBool;
	}
	public boolean getBlock() {
		return this.block;
	}
	public boolean getBackground() {
		return this.background;
	}
	public boolean getSoloMode() {
		return this.soloMode;
	}
	public int getTimecodeList(){
		return this.timecodeList;
	}
	public boolean getOOSsync() {
		return this.OOSsync;
	}
	public int getCueCount() {
		return this.cueCount;
	}
	public Cue[] getCues() {
		return this.cues;
	}
	public Cue getCueFromIndex(int indexNum) {
		return this.cues[indexNum];
	}
	public void addCue(Cue cueIn) {
		this.cues[cueCount] = cueIn;
		cueCount++;
	}
	public int addCueReturnIndex(Cue cueIn) {
		int tempIndex = cueCount;
		this.cues[cueCount] = cueIn;
		cueCount++;
		return(tempIndex);
	}
	public void modifyCue(Cue cueIn, int indexNum) {
		this.cues[indexNum] = cueIn;
	}
	
	/*
	 * CONSTRUCTORS
	 */
	public Cuelist(int cuelistNumIn, String UIDin, String nameIn, String playbackModeIn, String faderModeIn, boolean faderModeIndependenceIn, boolean HTPin, boolean assertIn,
			boolean blockIn, boolean backgroundIn, boolean soloModeIn, int timecodeListIn, boolean OOSsyncIn) {
		
		this.cuelistNum = cuelistNumIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.playbackMode = playbackModeIn;
		this.faderMode = faderModeIn;
		this.faderModeIndependence = faderModeIndependenceIn;
		this.HTP = HTPin;
		this.assertBool = assertIn;
		this.block = blockIn;
		this.background = backgroundIn;
		this.soloMode = soloModeIn;
		this.timecodeList = timecodeListIn;
		this.OOSsync = OOSsyncIn;
		
		this.cueCount = 0;
	}
	//TODO: Add non-ETC Cuelist Constructors
}
