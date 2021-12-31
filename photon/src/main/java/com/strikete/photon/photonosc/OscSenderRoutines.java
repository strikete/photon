package com.strikete.photon.photonosc;

import java.util.ArrayList;

import com.strikete.photon.objects.Channel;
import com.strikete.photon.osc.OscOutgoing;
import com.strikete.photon.osc.OscSender;

public class OscSenderRoutines {
	
	/*
	 * VARIABLES
	 */
	private OscSender oscSender;
	
	
	/*
	 * METHODS - COMMON TASKS - GET VERSION
	 */
	public void getVersion() {
		oscSender.sendOscMessage(OscOutgoing.GET_VERSION);
	}
	
	/*
	 * METHODS - COMMON TASKS - GET COUNTS
	 */
	public void getPatchCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_PATCH_COUNT);
	}
	public void getCuelistCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_CUELIST_COUNT);
	}
	public void getCueCount(int cuelistNum) {
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add(Integer.toString(cuelistNum));
		oscSender.sendOscMessage(oscSender.parameterizeString(OscOutgoing.GET_CUE_COUNT, parameters));
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
	public void getCurveCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_CURVE_COUNT);
	}
	public void getEffectCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_EFFECT_COUNT);
	}
	public void getSnapshotCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_SNAPSHOT_COUNT);
	}
	public void getPixelmapCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_PIXELMAP_COUNT);
	}
	public void getMagicSheetCount() {
		oscSender.sendOscMessage(OscOutgoing.GET_MAGIC_SHEET_COUNT);
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