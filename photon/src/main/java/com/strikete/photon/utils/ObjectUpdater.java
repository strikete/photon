package com.strikete.photon.utils;

import com.google.common.eventbus.Subscribe;
import com.strikete.photon.Main;
import com.strikete.photon.events.BeamPaletteCountUpdateEvent;
import com.strikete.photon.events.ChannelCountUpdateEvent;
import com.strikete.photon.events.ColorPaletteCountUpdateEvent;
import com.strikete.photon.events.CueCountUpdateEvent;
import com.strikete.photon.events.CuelistCountUpdateEvent;
import com.strikete.photon.events.CuelistUpdateEvent;
import com.strikete.photon.events.EffectCountUpdateEvent;
import com.strikete.photon.events.FocusPaletteCountUpdateEvent;
import com.strikete.photon.events.GroupCountUpdateEvent;
import com.strikete.photon.events.IntensityPaletteCountUpdateEvent;
import com.strikete.photon.events.MacroCountUpdateEvent;
import com.strikete.photon.events.PhotonEventHandler;
import com.strikete.photon.events.PresetCountUpdateEvent;
import com.strikete.photon.events.SubCountUpdateEvent;
import com.strikete.photon.objects.Sub;
import com.strikete.photon.osc.OscInstance;
import com.strikete.photon.osc.OscMap;
import com.strikete.photon.osc.OscSender;

public class ObjectUpdater {

	/*
	 * VARIABLES
	 */
	private PhotonEventHandler eventbus;
	private OscSender sender;
	private OscMap oscmap;
	private OscInstance oscInstance;
	
	private long patchSleep;
	private long groupSleep;
	private long macroSleep;
	private long effectSleep;
	private long subSleep;
	private long presetSleep;
	private long ipSleep;
	private long fpSleep;
	private long cpSleep;
	private long bpSleep;
	private long cuelistSleep;
	
	/*
	 * SET SLEEP TIMES
	 */
	public void setPatchSleep(long patchSleepIn) {
		this.patchSleep = patchSleepIn;
	}
	public void setGroupSleep(long groupSleepIn) {
		this.groupSleep = groupSleepIn;
	}
	public void setMacroSleep(long macroSleepIn) {
		this.macroSleep = macroSleepIn;
	}
	public void setEffectSleep(long effectSleepIn) {
		this.effectSleep = effectSleepIn;
	}
	public void setSubSleep(long subSleepIn) {
		this.subSleep = subSleepIn;
	}
	public void setPresetSleep(long presetSleepIn) {
		this.presetSleep = presetSleepIn;
	}
	public void setIntensityPaletteSleep(long ipSleepIn) {
		this.ipSleep = ipSleepIn;
	}
	public void setFocusPaletteSleep(long fpSleepIn) {
		this.fpSleep = fpSleepIn;
	}
	public void setColorPaletteSleep(long cpSleepIn) {
		this.cpSleep = cpSleepIn;
	}
	public void setBeamPaletteSleep(long bpSleepIn) {
		this.bpSleep = bpSleepIn;
	}
	
	/*
	 * BASIC EVENT METHODS
	 */
	@Subscribe
	private void onChannelCountUpdate(ChannelCountUpdateEvent e) {
		int patchCount = e.getNumberOfChannels();
		for(int x = 0; x < patchCount; x++) {
			sender.sendOscMessage(oscmap.GET_PATCH_INFO, x);
		}
	}
	
	@Subscribe
	private void onCuelistCountUpdate(CuelistCountUpdateEvent e) {
		int cuelistCount = e.getNumberOfCuelists();
		for(int x = 0; x < cuelistCount; x++) {
			sender.sendOscMessage(oscmap.GET_CUELIST_INFO, x);
		}
	}
	
	@Subscribe
	private void onGroupCountUpdate(GroupCountUpdateEvent e) {
		int groupCount = e.getNumberOfGroups();
		for(int x = 0; x < groupCount; x++) {
			sender.sendOscMessage(oscmap.GET_GROUP_INFO, x);
		}
	}
	
	@Subscribe
	private void onMacroCountUpdate(MacroCountUpdateEvent e) {
		int macroCount = e.getNumberOfMacros();
		for(int x = 0; x < macroCount; x++) {
			sender.sendOscMessage(oscmap.GET_MACRO_INFO, x);
		}
	}
	
	@Subscribe
	private void onSubCountUpdate(SubCountUpdateEvent e) {
		int subCount = e.getNumberOfSubs();
		for(int x = 0; x < subCount; x++) {
			sender.sendOscMessage(oscmap.GET_SUB_INFO, x);
		}
	}
	
	@Subscribe
	private void onPresetCountUpdate(PresetCountUpdateEvent e) {
		int presetCount = e.getNumberOfPresets();
		for(int x = 0; x < presetCount; x++) {
			sender.sendOscMessage(oscmap.GET_PRESET_INFO, x);
		}
	}
	
	@Subscribe
	private void onIntensityPaletteCountUpdate(IntensityPaletteCountUpdateEvent e) {
		int ipCount = e.getNumberOfIntensityPalettes();
		for(int x = 0; x < ipCount; x++) {
			sender.sendOscMessage(oscmap.GET_INTENSITY_PALETTE_INFO, x);
		}
	}
	
	@Subscribe
	private void onFocusPaletteCountUpdate(FocusPaletteCountUpdateEvent e) {
		int fpCount = e.getNumberOfFocusPalettes();
		for(int x = 0; x < fpCount; x++) {
			sender.sendOscMessage(oscmap.GET_FOCUS_PALETTE_INFO, x);
		}
	}
	
	@Subscribe
	private void onColorPaletteCountUpdate(ColorPaletteCountUpdateEvent e) {
		int cpCount = e.getNumberOfColorPalettes();
		for(int x = 0; x < cpCount; x++) {
			sender.sendOscMessage(oscmap.GET_COLOR_PALETTE_INFO, x);
		}
	}
	
	@Subscribe
	private void onBeamPaletteCountUpdate(BeamPaletteCountUpdateEvent e) {
		int bpCount = e.getNumberOfBeamPalettes();
		for(int x = 0; x < bpCount; x++) {
			sender.sendOscMessage(oscmap.GET_BEAM_PALETTE_INFO, x);
		}
	}
	
	@Subscribe
	private void onEffectCountUpdate(EffectCountUpdateEvent e) {
		int fxCount = e.getNumberOfEffects();
		for(int x = 0; x < fxCount; x++) {
			sender.sendOscMessage(oscmap.GET_EFFECT_INFO, x);
		}
	}
	
	@Subscribe
	private void onCueCountUpdate(final CueCountUpdateEvent e) {
	    int cueCount = e.getNumberOfCues();
		int cuelistNum = e.getCuelist().getCuelistNumber();
		for(int x = 0; x < cueCount; x++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sender.sendOscMessage(oscmap.GET_CUE_INFO + cuelistNum + "/index/", x);
		}
	
	}
	
	/*
	 * ENHANCED EVENT UPDATE METHODS
	 */
	@Subscribe
	private void onCuelistUpdateEvent(CuelistUpdateEvent e) {
		int cuelistNum = e.getCuelist().getCuelistNumber();
		sender.sendOscMessage(oscmap.GET_CUE_COUNT + cuelistNum +"/count");
	}
	
	/*
	 * BASIC UPDATE STARTING METHODS
	 */
	public synchronized void updatePatch() {																//UPDATE PATCH
		sender.sendOscMessage(oscmap.GET_PATCH_COUNT);
		try {
			Thread.sleep(patchSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Patch.");
			e.printStackTrace();
		}
	}
	public synchronized void updateGroups() {																//UPDATE GROUPS
		sender.sendOscMessage(oscmap.GET_GROUP_COUNT);
		try {
			Thread.sleep(groupSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Groups.");
			e.printStackTrace();
		}
	}
	public synchronized void updateMacros() {																//UPDATE MACROS
		sender.sendOscMessage(oscmap.GET_MACRO_COUNT);
		try {
			Thread.sleep(macroSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Macros.");
			e.printStackTrace();
		}
	}
	public synchronized void updateEffects() {																//UPDATE EFFECTS
		sender.sendOscMessage(oscmap.GET_EFFECT_COUNT);
		try {
			Thread.sleep(effectSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Effects.");
			e.printStackTrace();
		}
	}
	public synchronized void updateSubs() {																	//UPDATE SUBMASTERS
		sender.sendOscMessage(oscmap.GET_SUB_COUNT);
		try {
			Thread.sleep(subSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Subs.");
			e.printStackTrace();
		}
	}
	public synchronized void updatePresets() {																//UPDATE PRESETS
		sender.sendOscMessage(oscmap.GET_PRESET_COUNT);
		try {
			Thread.sleep(presetSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Presets.");
			e.printStackTrace();
		}
	}
	public synchronized void updateIntensityPalettes() {													//UPDATE INTENSITY PALETTES
		sender.sendOscMessage(oscmap.GET_INTENSITY_PALETTE_COUNT);
		try {
			Thread.sleep(ipSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Intensity Palettes.");
			e.printStackTrace();
		}
	}
	public synchronized void updateFocusPalettes() {														//UPDATE FOCUS PALETTES
		sender.sendOscMessage(oscmap.GET_FOCUS_PALETTE_COUNT);
		try {
			Thread.sleep(fpSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Focus Palettes.");
			e.printStackTrace();
		}
	}
	public synchronized void updateColorPalettes() {														//UPDATE COLOR PALETTES
		sender.sendOscMessage(oscmap.GET_COLOR_PALETTE_COUNT);
		try {
			Thread.sleep(cpSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Color Palettes.");
			e.printStackTrace();
		}
	}
	public synchronized void updateBeamPalettes() {															//UPDATE BEAM PALETTES
		sender.sendOscMessage(oscmap.GET_BEAM_PALETTE_COUNT);
		try {
			Thread.sleep(bpSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Beam Palettes.");
			e.printStackTrace();
		}
	}
	public synchronized void updateCuelists() {																//UPDATE CUELISTS
		sender.sendOscMessage(oscmap.GET_CUELIST_COUNT);
		try {
			Thread.sleep(cuelistSleep);
		} catch (InterruptedException e) {
			Main.log.error("ObjectUpdater ERROR: Interrupted Exception when attempting to update Cuelists.");
			e.printStackTrace();
		}
	}
	
	/*
	 * ADVANCED EVENT UPDATE STARTERS
	 */
	public synchronized void doAdvancedSubUpdate() {
		int channelCount = oscInstance.getOscParser().getChannelCount(); //Get channel count
		int subCount = oscInstance.getOscParser().getSubCount(); //Get sub count
		
		sender.sendOscMessage(oscmap.GO_BLIND); //Put the console into blind mode
		sender.sendOscMessage(oscmap.KEY_RESET_COMMAND_LINE); //Clear the command line
		
		for(int x = 0; x < subCount; x++) {
			Sub inputSub = oscInstance.getOscParser().getSubFromIndex(x);
			
		}
	}
	
	/*
	 * HIGH LEVEL UPDATE METHODS
	 */
	
	public synchronized void doBasicUpdate() {
		updatePatch();
		updateGroups();
		updateMacros();
		updateEffects();
		updateSubs();
		updatePresets();
		updateIntensityPalettes();
		updateFocusPalettes();
		updateColorPalettes();
		updateBeamPalettes();
		updateCuelists();
	}
	
	public synchronized void doComprehensiveUpdate() {
		doBasicUpdate();
		
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public ObjectUpdater(OscInstance oscInstanceIn, PhotonEventHandler eventbusIn, OscSender senderIn, OscMap oscmapIn) {
		this.eventbus = eventbusIn;
		this.sender = senderIn;
		this.oscmap = oscmapIn;
		this.oscInstance = oscInstanceIn;
		
		this.patchSleep = 1000;
		this.groupSleep = 10;
		this.macroSleep = 10;
		this.effectSleep = 10;
		this.subSleep = 10;
		this.presetSleep = 10;
		this.ipSleep = 10;
		this.fpSleep = 10;
		this.cpSleep = 10;
		this.bpSleep = 10;
		this.cuelistSleep = 1000;
		
		eventbus.register(this);
	}
	
}
