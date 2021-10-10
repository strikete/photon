package com.strikete.photon.objects;

import java.util.ArrayList;

import com.strikete.photon.Main;
import com.strikete.photon.events.CuelistUpdateEvent;
import com.strikete.photon.events.IntensityPaletteUpdateEvent;

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
	
	private ArrayList<Cue> cues = new ArrayList<Cue>();
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
	public int getCueSize() {
		return cues.size();
	}
	public Cue getCue(int index) {
		return cues.get(index);
	}
	public void addCue(Cue cueIn) {
		cues.add(cueIn);
	}
	public int addCueReturnIndex(Cue cueIn) {
		cues.add(cueIn);
		return(cues.size()-1);
	}
	public void modifyCue(Cue cueIn, int indexNum) {
		cues.set(indexNum, cueIn);
	}
	
	public int cueNumberIndexReturn(float number) {
		for(int x = 0; x < cues.size(); x++) {
			if(cues.get(x).getCueNumber() == number) {
				return x;
			}
		}
		Main.log.error("CUELIST OBJ: Could not find Cue with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	
	public int cueUidIndexReturn(String UID) {
		for(int x = 0; x < cues.size(); x++) {
			if(cues.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("CUELIST OBJ: Could not find Cue with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	
	public void addReplaceCue(final Cue cueIn) {
		boolean matchFlag = false;
		for(int x = 0; x < cues.size(); x++) {
			if(cues.get(x).getUID().equals(cueIn.getUID())) {
				matchFlag = true;
				cues.set(x, cueIn);
			}
		}
		if(!matchFlag) {
			cues.add(cueIn);
		}
	}
	public int addReplaceCueReturnIndex(final Cue cueIn) {
		for(int x = 0; x < cues.size(); x++) {
			if(cues.get(x).getUID().equals(cueIn.getUID())) {
				cues.set(x, cueIn);
				return x;
			}
		}
		cues.add(cueIn);
		return (cues.size()-1);
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
