package com.strikete.photon.objects;

import java.util.ArrayList;

import com.strikete.photon.Main;

public class Cue {

	/*
	 * VARIABLES
	 */
	private float cueNumber;
	private String UID;
	private String name;
	private int upTimeDuration;
	private int upTimeDelay;
	private int downTimeDuration;
	private int downTimeDelay;
	private int focusTimeDuration;
	private int focusTimeDelay;
	private int colorTimeDuration;
	private int colorTimeDelay;
	private int beamTimeDuration;
	private int beamTimeDelay;
	private boolean preheat;
	private String curve; //Osc Number? (As described in the EOS ShowControl Guide)
	private int rate;
	private String mark;
	private String block;
	private String assertString;
	private String linkString; //Osc Number? OR String?
	private int followTime; //In milliseconds
	private int hangTime; //In milliseconds
	private boolean allFade;
	private int loop;
	private boolean solo;
	private String timecode;
	
	//private Cue[] cueParts = new Cue[65535];
	private ArrayList<Cue> cueParts = new ArrayList<Cue>();
	
	private Cuelist parentCuelist;
	
	private ArrayList<Channel> channels = new ArrayList<Channel>();
	private ArrayList<Effect> effects = new ArrayList<Effect>();
	private ArrayList<Preset> presets = new ArrayList<Preset>();
	private ArrayList<IntensityPalette> intensityPalettes = new ArrayList<IntensityPalette>();
	private ArrayList<FocusPalette> focusPalettes = new ArrayList<FocusPalette>();
	private ArrayList<ColorPalette> colorPalettes = new ArrayList<ColorPalette>();
	private ArrayList<BeamPalette> beamPalettes = new ArrayList<BeamPalette>();
	
	
	/*
	 * METHODS - RELATING TO PARENT CUELIST
	 */
	public Cuelist getParentCuelist() {
		return this.parentCuelist;
	}
	public void setParentCuelist(Cuelist cuelistIn) {
		this.parentCuelist = cuelistIn;
	}
	
	/*
	 * METHODS - ADDING ATTACHED OBJECTS
	 */
	public void addChannel(Channel channelIn) {
		channels.add(channelIn);
	}
	public void addEffect(Effect effectIn) {
		effects.add(effectIn);
	}
	public void addPreset(Preset presetIn) {
		presets.add(presetIn);
	}
	public void addIntensityPalette(IntensityPalette ipIn) {
		intensityPalettes.add(ipIn);
	}
	public void addFocusPalette(FocusPalette fpIn) {
		focusPalettes.add(fpIn);
	}
	public void addColorPalette(ColorPalette cpIn) {
		colorPalettes.add(cpIn);
	}
	public void addBeamPalette(BeamPalette bpIn) {
		beamPalettes.add(bpIn);
	}
	
	/*
	 * METHODS - ATTACHED OBJECT GETTERS
	 */
	public Channel getChannel(int index) {
		return channels.get(index);
	}
	public Preset getPreset(int index) {
		return presets.get(index);
	}
	public IntensityPalette getIntensityPalette(int index) {
		return intensityPalettes.get(index);
	}
	public FocusPalette getFocusPalette(int index) {
		return focusPalettes.get(index);
	}
	public ColorPalette getColorPalette(int index) {
		return colorPalettes.get(index);
	}
	public BeamPalette getBeamPalette(int index) {
		return beamPalettes.get(index);
	}
	
	/*
	 * METHODS - ATTACHED OBJECT COUNT GETTERS
	 */
	public int getChannelSize() {
		return channels.size();
	}
	public int getPresetSize() {
		return presets.size();
	}
	public int getIntensityPaletteSize() {
		return intensityPalettes.size();
	}
	public int getFocusPaletteSize() {
		return focusPalettes.size();
	}
	public int getColorPaletteSize() {
		return colorPalettes.size();
	}
	public int getBeamPaletteSize() {
		return beamPalettes.size();
	}
	
	/*
	 * METHODS - GENERIC GETTERS
	 */
	public float getCueNumber() {
		return this.cueNumber;
	}
	public String getUID() {
		return this.UID;
	}
	public String getName() {
		return this.name;
	}
	public int getUpTimeDuration() {
		return this.upTimeDuration;
	}
	public int getUpTimeDelay() {
		return this.upTimeDelay;
	}
	public int getDownTimeDuration() {
		return this.downTimeDuration;
	}
	public int getDownTimeDelay() {
		return this.downTimeDelay;
	}
	public int getFocusTimeDuration() {
		return this.focusTimeDuration;
	}
	public int getFocusTimeDelay() {
		return this.focusTimeDelay;
	}
	public int getColorTimeDuration() {
		return this.colorTimeDuration;
	}
	public int getColorTimeDelay() {
		return this.colorTimeDelay;
	}
	public int getBeamTimeDuration() {
		return this.beamTimeDuration;
	}
	public int getBeamTimeDelay() {
		return this.beamTimeDelay;
	}
	public boolean getPreheat() {
		return this.preheat;
	}
	public String getCurve() {
		return this.curve;
	}
	public int getRate() {
		return this.rate;
	}
	public String getMark() {
		return this.mark;
	}
	public String getBlock() {
		return this.block;
	}
	public String getAssert() {
		return this.assertString;
	}
	public String getLinkString() {
		return this.linkString;
	}
	public int getFollowTime() {
		return this.followTime;
	}
	public int getHangTime() {
		return this.hangTime;
	}
	public boolean getAllFade() {
		return this.allFade;
	}
	public int getLoop() {
		return this.loop;
	}
	public boolean getSolo() {
		return this.solo;
	}
	public String getTimecode() {
		return this.timecode;
	}
	public int getPartCount() {
		return cueParts.size();
	}
	
	/*
	 * METHODS RELATING TO CUE PARTS
	 */
	public void addReplaceCuePart(final Cue cueIn) {
		boolean matchFlag = false;
		for(int x = 0; x < cueParts.size(); x++) {
			if(cueParts.get(x).getUID().equals(cueIn.getUID())) {
				matchFlag = true;
				cueParts.set(x, cueIn);
			}
		}
		if(!matchFlag) {
			cueParts.add(cueIn);
		}
	}
	public int addReplaceCuePartReturnIndex(final Cue cueIn) {
		for(int x = 0; x < cueParts.size(); x++) {
			if(cueParts.get(x).getUID().equals(cueIn.getUID())) {
				cueParts.set(x, cueIn);
				return x;
			}
		}
		cueParts.add(cueIn);
		return (cueParts.size()-1);
	}
	public int cuePartNumberIndexReturn(float number) {
		for(int x = 0; x < cueParts.size(); x++) {
			if(cueParts.get(x).getCueNumber() == number) {
				return x;
			}
		}
		Main.log.error("CUE OBJ: Could not find Cue with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	
	public int cuePartUidIndexReturn(String UID) {
		for(int x = 0; x < cueParts.size(); x++) {
			if(cueParts.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("CUE OBJ: Could not find Cue with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public Cue getPart(int index) {
		return cueParts.get(index);
	}
	
	/*
	 * CONSTRUCTORS
	 */
	public Cue(float cueNumberIn, Cuelist parentCuelistIn, String UIDin, String nameIn, int upTimeDurationIn, int upTimeDelayIn, int downTimeDurationIn, int downTimeDelayIn,
			int focusTimeDurationIn, int focusTimeDelayIn, int colorTimeDurationIn, int colorTimeDelayIn, int beamTimeDurationIn,
			int beamTimeDelayIn, boolean preheatIn, String curveIn, int rateIn, String markIn, String blockIn, String assertStringIn,
			String linkStringIn, int followTimeIn, int hangTimeIn, boolean allFadeIn, int loopIn, boolean soloIn, String timecodeIn) {
		
		this.cueNumber = cueNumberIn;
		this.UID = UIDin;
		this.name = nameIn;
		this.upTimeDuration = upTimeDurationIn;
		this.upTimeDelay = upTimeDelayIn;
		this.downTimeDuration = downTimeDurationIn;
		this.downTimeDelay = downTimeDelayIn;
		this.focusTimeDuration = focusTimeDurationIn;
		this.focusTimeDelay = focusTimeDelayIn;
		this.colorTimeDuration = colorTimeDurationIn;
		this.colorTimeDelay = colorTimeDelayIn;
		this.beamTimeDuration = beamTimeDurationIn;
		this.beamTimeDelay = beamTimeDelayIn;
		this.preheat = preheatIn;
		this.curve = curveIn;
		this.rate = rateIn;
		this.mark = markIn;
		this.block = blockIn;
		this.assertString = assertStringIn;
		this.linkString = linkStringIn;
		this.followTime = followTimeIn;
		this.hangTime = hangTimeIn;
		this.allFade = allFadeIn;
		this.loop = loopIn;
		this.solo = soloIn;
		this.timecode = timecodeIn;
		
		this.parentCuelist = parentCuelistIn;
	}
}
