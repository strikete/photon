package com.strikete.photon.objects;

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
	private int partCount;
	
	private Cue[] cueParts = new Cue[65535];
	
	private Cuelist parentCuelist;
	
	private Channel[] channels = new Channel[65535];
	private Effect[] effects = new Effect[65535];
	private Preset[] presets = new Preset[65535];
	private IntensityPalette[] intensityPalettes = new IntensityPalette[65535];
	private FocusPalette[] focusPalettes = new FocusPalette[65535];
	private ColorPalette[] colorPalettes = new ColorPalette[65535];
	private BeamPalette[] beamPalettes = new BeamPalette[65535];
	
	private int channelCount;
	private int effectCount;
	private int presetCount;
	private int ipCount;
	private int fpCount;
	private int cpCount;
	private int bpCount;
	
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
		channels[channelCount] = channelIn;
		channelCount++;
	}
	public void modifyChannel(Channel channelIn, int indexNum) {
		channels[indexNum] = channelIn;
	}
	public void addEffect(Effect effectIn) {
		effects[effectCount] = effectIn;
		effectCount++;
	}
	public void modifyEffect(Effect effectIn, int indexNum) {
		effects[indexNum] = effectIn;
	}
	public void addPreset(Preset presetIn) {
		presets[presetCount] = presetIn;
		presetCount++;
	}
	public void modifyPreset(Preset presetIn, int indexNum) {
		presets[indexNum] = presetIn;
	}
	public void addIntensityPalette(IntensityPalette ipIn) {
		intensityPalettes[ipCount] = ipIn;
		ipCount++;
	}
	public void modifyIntensityPalette(IntensityPalette ipIn, int indexNum) {
		intensityPalettes[indexNum] = ipIn;
	}
	public void addFocusPalette(FocusPalette fpIn) {
		focusPalettes[fpCount] = fpIn;
		fpCount++;
	}
	public void modifyFocusPalette(FocusPalette fpIn, int indexNum) {
		focusPalettes[indexNum] = fpIn;
	}
	public void addColorPalette(ColorPalette cpIn) {
		colorPalettes[cpCount] = cpIn;
		cpCount++;
	}
	public void modifyColorPalette(ColorPalette cpIn, int indexNum) {
		colorPalettes[indexNum] = cpIn;
	}
	public void addBeamPalette(BeamPalette bpIn) {
		beamPalettes[bpCount] = bpIn;
		bpCount++;
	}
	public void modifyBeamPalette(BeamPalette bpIn, int indexNum) {
		beamPalettes[indexNum] = bpIn;
	}
	
	/*
	 * METHODS - ATTACHED OBJECT GETTERS
	 */
	public Channel[] getChannels() {
		return this.channels;
	}
	public Preset[] getPresets() {
		return this.presets;
	}
	public IntensityPalette[] getIntensityPalettes() {
		return this.intensityPalettes;
	}
	public FocusPalette[] getFocusPalettes() {
		return this.focusPalettes;
	}
	public ColorPalette[] getColorPalettes() {
		return this.colorPalettes;
	}
	public BeamPalette[] getBeamPalettes() {
		return this.beamPalettes;
	}
	
	/*
	 * METHODS - ATTACHED OBJECT COUNT GETTERS
	 */
	public int getChannelCount() {
		return this.channelCount;
	}
	public int getPresetCount() {
		return this.presetCount;
	}
	public int getIntensityPaletteCount() {
		return this.ipCount;
	}
	public int getFocusPaletteCount() {
		return this.fpCount;
	}
	public int getColorPaletteCount() {
		return this.cpCount;
	}
	public int getBeamPaletteCount() {
		return this.bpCount;
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
		return this.partCount;
	}
	
	/*
	 * METHODS RELATING TO CUE PARTS
	 */
	public void addCuePart(Cue cueIn) {
		cueParts[partCount] = cueIn;
		partCount++;
	}
	public int addCuePartReturnIndex(Cue cueIn) {
		int tempIndex = partCount;
		cueParts[partCount] = cueIn;
		partCount++;
		return tempIndex;
	}
	public void modifyCuePart(Cue cueIn, int indexNum) {
		cueParts[indexNum] = cueIn;
	}
	public Cue[] getCueParts() {
		return cueParts;
	}
	public Cue getPartFromIndex(int indexNum) {
		return cueParts[indexNum];
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
		this.partCount = 0;
		
		this.parentCuelist = parentCuelistIn;

		this.channelCount = 0;
		this.effectCount = 0;
		this.presetCount = 0;
		this.ipCount = 0;
		this.fpCount = 0;
		this.cpCount = 0;
		this.bpCount = 0;
	}
}
