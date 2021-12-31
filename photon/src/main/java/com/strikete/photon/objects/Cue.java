package com.strikete.photon.objects;

import java.util.ArrayList;

public class Cue implements CsvLevelDataSetter {

	/*
	 * VARIABLES
	 */
	private float cueNum;
	private int index;
	private String uid;
	private int cuelistNum;
	private int part;
	
	private String label;
	private float upTimeDuration;
	private float upTimeDelay;
	private float downTimeDuration;
	private float downTimeDelay;
	private float focusTimeDuration;
	private float focusTimeDelay;
	private float colorTimeDuration;
	private float colorTimeDelay;
	private float beamTimeDuration;
	private float beamTimeDelay;
	private boolean preheat;
	private ArrayList<Integer> curve; //Does this need to be a different number type?
	private int rate;
	private String mark;
	private String blockVar;
	private String assertVar;
	private String link;
	private float followTime;
	private float hangTime;
	private boolean allFade;
	private int loop;
	private boolean solo;
	private String timecode;
	private int partCount;
	private ArrayList<Integer> effectList = new ArrayList<Integer>();
	private ArrayList<Integer> linkedCuelists = new ArrayList<Integer>();
	private String extLinkAction;
	
	private ArrayList<Integer> channels = new ArrayList<Integer>();
	private ArrayList<String> parameters = new ArrayList<String>();
	private ArrayList<Float> levels = new ArrayList<Float>();
	
	private float timeData;
	private float duration;
	private String mode;
	private String cueNotes;
	private String sceneText;
	private String sceneEnd;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public float getCueNumber() {
		return cueNum;
	}
	public int getIndex() {
		return index;
	}
	public String getUid() {
		return uid;
	}
	public int getCuelistNumber() {
		return cuelistNum;
	}
	public int getPart() {
		return part;
	}
	public String getLabel() {
		return label;
	}
	public float getUpTimeDuration() {
		return upTimeDuration;
	}
	public float getUpTimeDelay() {
		return upTimeDelay;
	}
	public float getDownTimeDuration() {
		return downTimeDuration;
	}
	public float getDownTimeDelay() {
		return downTimeDelay;
	}
	public float getFocusTimeDuration() {
		return focusTimeDuration;
	}
	public float getFocusTimeDelay() {
		return focusTimeDelay;
	}
	public float getColorTimeDuration() {
		return colorTimeDuration;
	}
	public float getColorTimeDelay() {
		return colorTimeDelay;
	}
	public float getBeamTimeDuration() {
		return beamTimeDuration;
	}
	public float getBeamTimeDelay() {
		return beamTimeDelay;
	}
	public boolean getPreheat() {
		return preheat;
	}
	public ArrayList<Integer> getCurve() {
		return curve;
	}
	public int getRate() {
		return rate;
	}
	public String getMark() {
		return mark;
	}
	public String getBlock() {
		return blockVar;
	}
	public String getAssert() {
		return assertVar;
	}
	public String getLink() {
		return link;
	}
	public float getFollowTime() {
		return followTime;
	}
	public float getHangTime() {
		return hangTime;
	}
	public boolean getAllFade() {
		return allFade;
	}
	public int getLoop() {
		return loop;
	}
	public boolean getSolo() {
		return solo;
	}
	public String getTimecode() {
		return timecode;
	}
	public int getPartCount() {
		return partCount;
	}
	public ArrayList<Integer> getEffectList() {
		return effectList;
	}
	public ArrayList<Integer> getLinkedCuelists() {
		return linkedCuelists;
	}
	public String getExternalLinkAction() {
		return extLinkAction;
	}
	public ArrayList<Integer> getChannels() {
		return channels;
	}
	public ArrayList<String> getParameters() {
		return parameters;
	}
	public ArrayList<Float> getLevels(){
		return levels;
	}
	public float getTimeData() {
		return timeData;
	}
	public float getDuration() {
		return duration;
	}
	public String getMode() {
		return mode;
	}
	public String getCueNotes() {
		return cueNotes;
	}
	public String getSceneText() {
		return sceneText;
	}
	public String getSceneEnd() {
		return sceneEnd;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setLabel(String labelIn) {
		this.label = labelIn;
	}
	public void setUpTimeDuration(float upTimeDurationIn) {
		this.upTimeDuration = upTimeDurationIn;
	}
	public void setUpTimeDelay(float upTimeDelayIn) {
		this.upTimeDelay = upTimeDelayIn;
	}
	public void setDownTimeDuration(float downTimeDurationIn) {
		this.downTimeDuration = downTimeDurationIn;
	}
	public void setDownTimeDelay(float downTimeDelayIn) {
		this.downTimeDelay = downTimeDelayIn;
	}
	public void setFocusTimeDuration(float focusTimeDurationIn) {
		this.focusTimeDuration = focusTimeDurationIn;
	}
	public void setFocusTimeDelay(float focusTimeDelayIn) {
		this.focusTimeDelay = focusTimeDelayIn;
	}
	public void setColorTimeDuration(float colorTimeDurationIn) {
		this.colorTimeDuration = colorTimeDurationIn;
	}
	public void setColorTimeDelay(float colorTimeDelayIn) {
		this.colorTimeDelay = colorTimeDelayIn;
	}
	public void setBeamTimeDuration(float beamTimeDurationIn) {
		this.beamTimeDuration = beamTimeDurationIn;
	}
	public void setBeamTimeDelay(float beamTimeDelayIn) {
		this.beamTimeDelay = beamTimeDelayIn;
	}
	public void setPreheat(boolean preheatIn) {
		this.preheat = preheatIn;
	}
	public void setCurve(ArrayList<Integer> curveIn) {
		this.curve = curveIn;
	}
	public void setRate(int rateIn) {
		this.rate = rateIn;
	}
	public void setMark(String markIn) {
		this.mark = markIn;
	}
	public void setBlock(String blockIn) {
		this.blockVar = blockIn;
	}
	public void setAssert(String assertIn) {
		this.assertVar = assertIn;
	}
	public void setLink(String linkIn) {
		this.link = linkIn;
	}
	public void setFollowTime(float followTimeIn) {
		this.followTime = followTimeIn;
	}
	public void setHangTime(float hangTimeIn) {
		this.hangTime = hangTimeIn;
	}
	public void setAllFade(boolean allFadeIn) {
		this.allFade = allFadeIn;
	}
	public void setLoop(int loopIn) {
		this.loop = loopIn;
	}
	public void setSolo(boolean soloIn) {
		this.solo = soloIn;
	}
	public void setTimecode(String timecodeIn) {
		this.timecode = timecodeIn;
	}
	public void setPartCount(int partCountIn) {
		this.partCount = partCountIn;
	}
	public void setEffectList(ArrayList<Integer> effectListIn) {
		this.effectList = effectListIn;
	}
	public void setLinkedCueLists(ArrayList<Integer> linkedCuelistsIn) {
		this.linkedCuelists = linkedCuelistsIn;
	}
	public void setExternalLinkAction(String extLinkActionIn) {
		this.extLinkAction = extLinkActionIn;
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
	public void setTimeData(float timeDataIn) {
		this.timeData = timeDataIn;
	}
	public void setDuration(float durationIn) {
		this.duration = durationIn;
	}
	public void setMode(String modeIn) {
		this.mode = modeIn;
	}
	public void setCueNotes(String cueNotesIn) {
		this.cueNotes = cueNotesIn;
	}
	public void setSceneText(String sceneTextIn) {
		this.sceneText = sceneTextIn;
	}
	public void setSceneEnd(String sceneEndIn) {
		this.sceneEnd = sceneEndIn;
	}
	
	
	/*
	 * METHODS - INTERFACES
	 */
	@Override
	public void addCsvLevelData(int channelIn, String parameterIn, float levelIn) {
		channels.add(channelIn);
		parameters.add(parameterIn);
		levels.add(levelIn);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Cue(float cueNumberIn, int indexIn, String uidIn, int cuelistNumberIn, int partIn) {
		this.cueNum = cueNumberIn;
		this.index = indexIn;
		this.uid = uidIn;
		this.cuelistNum = cuelistNumberIn;
		this.part = partIn;
	}
}