package com.strikete.photon.photonosc;

import com.strikete.photon.objects.Channel;
import com.strikete.photon.osc.OscOutgoing;
import com.strikete.photon.osc.OscSender;

public class OscSenderRoutines {
	
	/*
	 * VARIABLES
	 */
	private OscSender oscSender;
	

	/*
	 * METHODS - COMMON TASKS - GET COUNTS
	 */
	public void getVersion() {
		oscSender.sendOscMessage(OscOutgoing.GET_VERSION);
	}
	public void getPatchCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_PATCH_COUNT);
	}
	public void getCuelistCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_CUELIST_COUNT);
	}
	public void getGroupCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_GROUP_COUNT);
	}
	public void getMacroCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_MACRO_COUNT);
	}
	public void getSubCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_SUB_COUNT);
	}
	public void getPresetCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_PRESET_COUNT);
	}
	public void getIntensityPaletteCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_INTENSITY_PALETTE_COUNT);
	}
	public void getFocusPaletteCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_FOCUS_PALETTE_COUNT);
	}
	public void getColorPaletteCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_COLOR_PALETTE_COUNT);
	}
	public void getBeamPaletteCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_BEAM_PALETTE_COUNT);
	}
	public void getEffectcount() {
		oscSender.sendOscMessage(OscOutgoing.GET_EFFECT_COUNT);
	}
	
	
	/*
	 * METHODS - COMMON TASKS - GET INFO
	 */
	public void getPatchInfo(int indexNumber) {
		oscSender.sendOscMessage(OscOutgoing.GET_PATCH_INFO, indexNumber);
	}
	
	
	/*
	 * METHODS - COMMON TASKS - UPDATE CHANNEL
	 */
	public void selectChannel(int channelNum) {
		oscSender.sendOscMessage(OscOutgoing.SELECT_CHANNEL + channelNum);
		oscSender.sendOscMessage(OscOutgoing.KEY_ENTER);
	}
	public void selectChannel(Channel channelIn) {
		//oscSender.sendOscMessage(OscOutgoing.SELECT_CHANNEL + channelIn.getChannelNum());
		oscSender.sendOscMessage(OscOutgoing.KEY_ENTER);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public OscSenderRoutines(OscSender oscSender) {
		this.oscSender = oscSender;
	}
}