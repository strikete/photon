package com.strikete.photon.utils;

import java.io.IOException;
import java.util.List;

import com.illposed.osc.MessageSelector;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCMessageEvent;
import com.illposed.osc.OSCMessageListener;
import com.illposed.osc.messageselector.OSCPatternAddressMessageSelector;
import com.illposed.osc.transport.udp.OSCPortIn;
import com.strikete.photon.Main;
import com.strikete.photon.events.BeamPaletteCountUpdateEvent;
import com.strikete.photon.events.BeamPaletteUpdateEvent;
import com.strikete.photon.events.ChannelCountUpdateEvent;
import com.strikete.photon.events.ChannelSelectionEvent;
import com.strikete.photon.events.ChannelUpdateEvent;
import com.strikete.photon.events.ColorPaletteCountUpdateEvent;
import com.strikete.photon.events.ColorPaletteUpdateEvent;
import com.strikete.photon.events.CueCountUpdateEvent;
import com.strikete.photon.events.CuePartUpdateEvent;
import com.strikete.photon.events.CueUpdateEvent;
import com.strikete.photon.events.CuelistCountUpdateEvent;
import com.strikete.photon.events.CuelistUpdateEvent;
import com.strikete.photon.events.EffectCountUpdateEvent;
import com.strikete.photon.events.EffectUpdateEvent;
import com.strikete.photon.events.FocusPaletteCountUpdateEvent;
import com.strikete.photon.events.FocusPaletteUpdateEvent;
import com.strikete.photon.events.GroupCountUpdateEvent;
import com.strikete.photon.events.GroupUpdateEvent;
import com.strikete.photon.events.IntensityPaletteCountUpdateEvent;
import com.strikete.photon.events.IntensityPaletteUpdateEvent;
import com.strikete.photon.events.MacroCountUpdateEvent;
import com.strikete.photon.events.MacroUpdateEvent;
import com.strikete.photon.events.PhotonEventHandler;
import com.strikete.photon.events.PresetCountUpdateEvent;
import com.strikete.photon.events.PresetUpdateEvent;
import com.strikete.photon.events.SubCountUpdateEvent;
import com.strikete.photon.events.SubUpdateEvent;
import com.strikete.photon.events.VersionUpdateEvent;
import com.strikete.photon.objects.BeamPalette;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.objects.ColorPalette;
import com.strikete.photon.objects.Cue;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.objects.Effect;
import com.strikete.photon.objects.FocusPalette;
import com.strikete.photon.objects.Group;
import com.strikete.photon.objects.IntensityPalette;
import com.strikete.photon.objects.Macro;
import com.strikete.photon.objects.Preset;
import com.strikete.photon.objects.Sub;
import com.strikete.photon.osc.OscInstance;
import com.strikete.photon.osc.OscInstance.OscFormat;
import com.strikete.photon.osc.OscMap;

public class OscParser implements OSCMessageListener {
	
	/*
	 * VARIABLES
	 */
	
	private OscInstance oscInstance;
	private PhotonEventHandler eventBus;
	private OSCPortIn receiver;
	private ObjectUpdater objectUpdater;
	private int receiverPort;
	private OscFormat format;
	private OscMap oscmap;
	private String version;
	
	//TODO: Research ETC object limits and set array size accordingly
	private Group[] groups = new Group[65535];
	private Sub[] subs = new Sub[65535];
	private Channel[] channels = new Channel[65535];
	private Cuelist[] cuelists = new Cuelist[65535];
	private Macro[] macros = new Macro[512];
	private Effect[] effects = new Effect[65535];
	private Preset[] presets = new Preset[65535];
	private IntensityPalette[] intensityPalettes = new IntensityPalette[65535];
	private FocusPalette[] focusPalettes = new FocusPalette[65535];
	private ColorPalette[] colorPalettes = new ColorPalette[65535];
	private BeamPalette[] beamPalettes = new BeamPalette[65535];
	
	private int groupCount;
	private int subCount;
	private int channelCount;
	private int cuelistCount;
	private int macroCount;
	private int effectCount;
	private int presetCount;
	private int intensityPaletteCount;
	private int focusPaletteCount;
	private int colorPaletteCount;
	private int beamPaletteCount;
	
	private Channel[] activeChannels = new Channel[65535];
	
	private int activeChannelCount;
	private boolean singleChannelSelected;
	
	/*
	 * METHODS - GETTERS FOR TOP INDEX
	 */
	public int getGroupCount() {
		return this.groupCount;
	}
	public int getSubCount() {
		return this.subCount;
	}
	public int getChannelCount() {
		return this.channelCount;
	}
	public int getCuelistCount() {
		return this.cuelistCount;
	}
	public int getMacroCount() {
		return this.macroCount;
	}
	public int getEffectCount() {
		return this.effectCount;
	}
	public int getPresetCount() {
		return this.presetCount;
	}
	public int getIntensityPaletteCount() {
		return this.intensityPaletteCount;
	}
	public int getFocusPaletteCount() {
		return this.focusPaletteCount;
	}
	public int getColorPaletteCount() {
		return this.colorPaletteCount;
	}
	public int getBeamPaletteCount() {
		return this.beamPaletteCount;
	}
	
	/*
	 * RETURN GENERIC DATA TYPES
	 */
	public String getVersion() {
		return this.version;
	}
	public OscFormat getOscFormat() {
		return this.format;
	}
	public OscMap getOscMap() {
		return this.oscmap;
	}
	
	/*
	 * RETURN DATA TYPES
	 */
	public Group[] getGroups() {
		return this.groups;
	}
	public Sub[] getSubs() {
		return this.subs;
	}
	public Channel[] getChannels() {
		return this.channels;
	}
	public Cuelist[] getCuelists() {
		return this.cuelists;
	}
	public Macro[] getMacros() {
		return this.macros;
	}
	public Effect[] getEffects() {
		return this.effects;
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
	
	public Group getGroupFromIndex(int indexNum) {
		return this.groups[indexNum];
	}
	public Sub getSubFromIndex(int indexNum) {
		return this.subs[indexNum];
	}
	public Channel getChannelFromIndex(int indexNum) {
		return this.channels[indexNum];
	}
	public Cuelist getCuelistFromIndex(int indexNum) {
		return this.cuelists[indexNum];
	}
	public Macro getMacroFromIndex(int indexNum) {
		return this.macros[indexNum];
	}
	public Effect getEffectFromIndex(int indexNum) {
		return this.effects[indexNum];
	}
	public Preset getPresetFromIndex(int indexNum) {
		return this.presets[indexNum];
	}
	public IntensityPalette getIntensityPaletteFromIndex(int indexNum) {
		return this.intensityPalettes[indexNum];
	}
	public FocusPalette getFocusPaletteFromIndex(int indexNum) {
		return this.focusPalettes[indexNum];
	}
	public ColorPalette getColorPaletteFromIndex(int indexNum) {
		return this.colorPalettes[indexNum];
	}
	public BeamPalette getBeamPaletteFromIndex(int indexNum) {
		return this.beamPalettes[indexNum];
	}
	
	/*
	 * SET DATA TYPES
	 */
	public synchronized void setGroupByIndex(Group groupIn, int indexNum) {
		this.groups[indexNum] = groupIn;
	}
	public synchronized void setSubByIndex(Sub subIn, int indexNum) {
		this.subs[indexNum] = subIn;
	}
	public synchronized void setChannelByIndex(Channel channelIn, int indexNum) {
		this.channels[indexNum] = channelIn;
	}
	public synchronized void setCuelistByIndex(Cuelist cuelistIn, int indexNum) {
		this.cuelists[indexNum] = cuelistIn;
	}
	public synchronized void setMacroByIndex(Macro macroIn, int indexNum) {
		this.macros[indexNum] = macroIn;
	}
	public synchronized void setEffectByIndex(Effect effectIn, int indexNum) {
		this.effects[indexNum] = effectIn;
	}
	public synchronized void setPresetByIndex(Preset presetIn, int indexNum) {
		this.presets[indexNum] = presetIn;
	}
	public synchronized void setIntensityPaletteByIndex(IntensityPalette ipIn, int indexNum) {
		this.intensityPalettes[indexNum] = ipIn;
	}
	public synchronized void setFocusPaletteByIndex(FocusPalette fpIn, int indexNum) {
		this.focusPalettes[indexNum] = fpIn;
	}
	public synchronized void setColorPaletteByIndex(ColorPalette cpIn, int indexNum) {
		this.colorPalettes[indexNum] = cpIn;
	}
	public synchronized void setBeamPaletteByIndex(BeamPalette bpIn, int indexNum) {
		this.beamPalettes[indexNum] = bpIn;
	}

	/*
	 * METHODS - INTERFACES
	 */
	public void acceptMessage(OSCMessageEvent event) {
		OSCMessage message = event.getMessage();
		String command = message.getAddress();
		List<Object> argList = message.getArguments();
		
		if(command.contains(oscmap.RETURN_VERSION)){								//RETURN_VERSION
			final String versionText = (String) argList.get(0);
			this.version = versionText;
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new VersionUpdateEvent(oscInstance,versionText));
			    }
			  };
			thread.start();
		} else if (command.contains(oscmap.RETURN_PATCH_COUNT)) {					//RETURN_PATCH_COUNT
			this.channelCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new ChannelCountUpdateEvent(oscInstance, channelCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_CUELIST_COUNT)) {					//RETURN_CUELIST_COUNT
			this.cuelistCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new CuelistCountUpdateEvent(oscInstance, cuelistCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_GROUP_COUNT)) {					//RETURN_GROUP_COUNT
			this.groupCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new GroupCountUpdateEvent(oscInstance, groupCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_MACRO_COUNT)) {					//RETURN_MACRO_COUNT
			this.macroCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new MacroCountUpdateEvent(oscInstance, macroCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_SUB_COUNT)) {						//RETURN_SUB_COUNT
			this.subCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new SubCountUpdateEvent(oscInstance, subCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_PRESET_COUNT)) {					//RETURN_PRESET_COUNT
			this.presetCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new PresetCountUpdateEvent(oscInstance, presetCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_COUNT)) {		//RETURN_INTENSITY_PALETTE_COUNT
			this.intensityPaletteCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new IntensityPaletteCountUpdateEvent(oscInstance, intensityPaletteCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_COUNT)) {			//RETURN_FOCUS_PALETTE_COUNT
			this.focusPaletteCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new FocusPaletteCountUpdateEvent(oscInstance, focusPaletteCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_COUNT)) {			//RETURN_COLOR_PALETTE_COUNT
			this.colorPaletteCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new ColorPaletteCountUpdateEvent(oscInstance, colorPaletteCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_COUNT)) {			//RETURN_BEAM_PALETTE_COUNT
			this.beamPaletteCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new BeamPaletteCountUpdateEvent(oscInstance, beamPaletteCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_EFFECT_COUNT)) {					//RETURN_EFFECT_COUNT
			this.effectCount = (Integer) argList.get(0);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new EffectCountUpdateEvent(oscInstance, effectCount));
			    }
			  };
			  thread.start();
		} else if (command.contains(oscmap.RETURN_CUE_COUNT) && format.equals(OscFormat.ETC_EOS) && command.contains("count")) { 		//RETURN_CUE_COUNT for ETC_EOS only
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int cuelistNum = Integer.parseInt(postIndex[5]);
			System.out.println(message.getAddress());
			final int cuelistIndex = SearchMatch.findCuelistIndex(cuelists, cuelistCount, cuelistNum);
			final int cueCount = (Integer) argList.get(0);
			final OscInstance oscTemp = this.oscInstance;
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new CueCountUpdateEvent(oscTemp, cuelists[cuelistIndex], cueCount));
			    }
			  };
			thread.start();
		} else if (command.contains(oscmap.RETURN_PATCH_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("list")) {		//RETURN_PATCH_INFO for ETC_EOS only
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int channelNumTemp = Integer.parseInt(postIndex[5]);
			int addressNum = (Integer) argList.get(5);
			int level = (Integer) argList.get(7);
			String manufacturer = (String) argList.get(3);
			String type = (String) argList.get(4);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			channels[indexNum] = new Channel(channelNumTemp, addressNum, level, manufacturer, type, name, UID);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new ChannelUpdateEvent(oscInstance,channels[indexNum], indexNum));
			    }
			  };
			thread.start();
		} else if (command.contains(oscmap.RETURN_CUELIST_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("links")) {	//RETURN_CUELIST_INFO for ETC_EOS only
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int cuelistNumTemp = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			String playbackMode = (String) argList.get(3);
			String faderMode = (String) argList.get(4);
			boolean faderModeIndependence = (Boolean) argList.get(5);
			boolean HTP = (Boolean) argList.get(6);
			boolean assertBool = (Boolean) argList.get(7);
			boolean block = (Boolean) argList.get(8);
			boolean background = (Boolean) argList.get(9);
			boolean soloMode = (Boolean) argList.get(10);
			int timecodeList = (Integer) argList.get(11);
			boolean OOSsync = (Boolean) argList.get(12);
			cuelists[indexNum] = new Cuelist(cuelistNumTemp, UID, name, playbackMode, faderMode, faderModeIndependence, HTP, assertBool, block, background, soloMode, timecodeList, OOSsync);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new CuelistUpdateEvent(oscInstance,cuelists[indexNum], indexNum));
			    }
			  };
			thread.start();	
		} else if (command.contains(oscmap.RETURN_GROUP_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("channel")) { 	//RETURN_GROUP_INFO for ETC_EOS only --->PART 1<---
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float groupNum = Float.parseFloat(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			groups[indexNum] = new Group(UID, name, groupNum); //Get group number from console
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new GroupUpdateEvent(oscInstance, groups[indexNum], indexNum)); //Post group update event
			    }
			  };
			thread.start();	
		} else if (command.contains(oscmap.RETURN_GROUP_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("channel")) {		//RETURN_GROUP_INFO for ETC_EOS only --->PART 2<---
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					groups[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Group " + groups[indexNum].getGroupNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							groups[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Group " + groups[indexNum].getGroupNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							groups[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Group " + groups[indexNum].getGroupNum());
						}
					}
				}
			}
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new GroupUpdateEvent(oscInstance, groups[indexNum], indexNum)); //Post group update event
			    }
			  };
			thread.start();	
		} else if (command.contains(oscmap.RETURN_MACRO_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("text")) {		//RETURN_MACRO_INFO for ETC_EOS only --->PART 1<---
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float macroNum = Float.parseFloat(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			String mode = (String) argList.get(3);
			macros[indexNum] = new Macro(macroNum, UID, name, mode);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new MacroUpdateEvent(oscInstance, macros[indexNum], indexNum));
			    }
			  };
			thread.start();	
		} else if (command.contains(oscmap.RETURN_MACRO_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("text")) {		//RETURN_MACRO_INFO for ETC_EOS only --->PART 2<---
			final int indexNum = (Integer) argList.get(0);
			macros[indexNum].addCommandText((String) argList.get(2));
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new MacroUpdateEvent(oscInstance, macros[indexNum], indexNum));
			    }
			  };
			thread.start();	
		} else if (command.contains(oscmap.RETURN_EFFECT_INFO) && format.equals(OscFormat.ETC_EOS)) {									//RETURN_EFFECT_INFO for ETC_EOS only
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float fxNum = Float.parseFloat(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			String effectType = (String) argList.get(3);
			String entry = (String) argList.get(3);
			String exit = (String) argList.get(4);
			String duration = (String) argList.get(5);
			String scale = (String) argList.get(6);
			effects[indexNum] = new Effect(fxNum, UID, name, effectType, entry, exit, duration, scale);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new EffectUpdateEvent(oscInstance, effects[indexNum], indexNum));
			    }
			  };
			thread.start();
		} else if (command.contains(oscmap.RETURN_SUB_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("fx")) {			//RETURN_SUB_INFO for ETC_EOS only --->PART 1<---
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float subNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			String mode = (String) argList.get(3);
			String faderMode = (String) argList.get(4);
			boolean HTP = (Boolean) argList.get(5);
			boolean exclusive = (Boolean) argList.get(6);
			boolean background = (Boolean) argList.get(7);
			boolean restore = (Boolean) argList.get(8);
			String priority = (String) argList.get(9);
			String upTime = (String) argList.get(10);
			String dwellTime = (String) argList.get(11);
			String downTime = (String) argList.get(12);
			subs[indexNum] = new Sub(subNum, UID, name, mode, faderMode, HTP, exclusive, background, restore, priority, upTime, dwellTime, downTime);
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new SubUpdateEvent(oscInstance, subs[indexNum], indexNum));
			    }
			  };
			thread.start();
		} else if (command.contains(oscmap.RETURN_SUB_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("fx")) {			//RETURN_SUB_INFO for ETC_EOS only --->PART 2<---
			final int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					subs[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Sub " + subs[indexNum].getSubNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							subs[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Sub " + subs[indexNum].getSubNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							subs[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Sub " + subs[indexNum].getSubNum());
						}
					}
				}
			}
			Thread thread = new Thread(){
			    public void run(){
			    	eventBus.post(new SubUpdateEvent(oscInstance, subs[indexNum], indexNum));
			    }
			  };
			thread.start();
		} else if (command.contains(oscmap.RETURN_PRESET_INFO) && format.equals(OscFormat.ETC_EOS) && 
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {								//RETURN_PRESET_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float presetNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			presets[indexNum] = new Preset(presetNum, UID, name, absolute, locked);
			eventBus.post(new PresetUpdateEvent(this.oscInstance, presets[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_PRESET_INFO) && format.equals(OscFormat.ETC_EOS) && 
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {								//RETURN_PRESET_INFO for ETC_EOS only --->PART 2<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					presets[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Preset " + presets[indexNum].getPresetNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							presets[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Preset " + presets[indexNum].getPresetNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							presets[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Preset " + presets[indexNum].getPresetNum());
						}
					}
				}
			}
			eventBus.post(new PresetUpdateEvent(this.oscInstance, presets[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_PRESET_INFO) && format.equals(OscFormat.ETC_EOS) && 
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {								//RETURN_PRESET_INFO for ETC_EOS only --->PART 3<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					presets[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Preset " + presets[indexNum].getPresetNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							presets[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Preset " + presets[indexNum].getPresetNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							presets[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Preset " + presets[indexNum].getPresetNum());
						}
					}
				}
			}
			eventBus.post(new PresetUpdateEvent(this.oscInstance, presets[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_INTENSITY_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float ipNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			intensityPalettes[indexNum] = new IntensityPalette(ipNum, UID, name, absolute, locked);
			eventBus.post(new IntensityPaletteUpdateEvent(this.oscInstance, intensityPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_INTENSITY_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					intensityPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Intensity Palette " + intensityPalettes[indexNum].getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							intensityPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Intensity Palette " + intensityPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							intensityPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Intensity Palette " + intensityPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new IntensityPaletteUpdateEvent(this.oscInstance, intensityPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_INTENSITY_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					intensityPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Focus Palette " + intensityPalettes[indexNum].getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							intensityPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + intensityPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							intensityPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + intensityPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new IntensityPaletteUpdateEvent(this.oscInstance, intensityPalettes[indexNum], indexNum));
		}  else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_FOCUS_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float fpNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			focusPalettes[indexNum] = new FocusPalette(fpNum, UID, name, absolute, locked);
			eventBus.post(new FocusPaletteUpdateEvent(this.oscInstance, focusPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_FOCUS_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					focusPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Focus Palette " + focusPalettes[indexNum].getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							focusPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Focus Palette " + focusPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							focusPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Focus Palette " + focusPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new FocusPaletteUpdateEvent(this.oscInstance, focusPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_FOCUS_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					focusPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Focus Palette " + focusPalettes[indexNum].getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							focusPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + focusPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							focusPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + focusPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new FocusPaletteUpdateEvent(this.oscInstance, focusPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_COLOR_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float cpNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			colorPalettes[indexNum] = new ColorPalette(cpNum, UID, name, absolute, locked);
			eventBus.post(new ColorPaletteUpdateEvent(this.oscInstance, colorPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_COLOR_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					colorPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Color Palette " + colorPalettes[indexNum].getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							colorPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Color Palette " + colorPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							colorPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Color Palette " + colorPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new ColorPaletteUpdateEvent(this.oscInstance, colorPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_COLOR_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					colorPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Color Palette " + colorPalettes[indexNum].getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							colorPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Color Palette " + colorPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							colorPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Color Palette " + colorPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new ColorPaletteUpdateEvent(this.oscInstance, colorPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_BEAM_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float bpNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			beamPalettes[indexNum] = new BeamPalette(bpNum, UID, name, absolute, locked);
			eventBus.post(new BeamPaletteUpdateEvent(this.oscInstance, beamPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_BEAM_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					beamPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Beam Palette " + beamPalettes[indexNum].getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							beamPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Beam Palette " + beamPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							beamPalettes[indexNum].addChannel(SearchMatch.findChannel(channels, channelCount, i));
							Main.log.debug("OscParser added Channel " + i + " to Beam Palette " + beamPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new BeamPaletteUpdateEvent(this.oscInstance, beamPalettes[indexNum], indexNum));
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_BEAM_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					beamPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Beam Palette " + beamPalettes[indexNum].getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							beamPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Beam Palette " + beamPalettes[indexNum].getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							beamPalettes[indexNum].addEffect(SearchMatch.findEffect(effects, effectCount, i));
							Main.log.debug("OscParser added Effect " + i + " to Beam Palette " + beamPalettes[indexNum].getPaletteNum());
						}
					}
				}
			}
			eventBus.post(new BeamPaletteUpdateEvent(this.oscInstance, beamPalettes[indexNum], indexNum));
		} else if(command.contains(oscmap.RETURN_CUE_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("fx")
				&& !command.contains("links") && !command.contains("actions")) {											//RETURN_CUE_INFO for ETC_EOS only --->PART 1<---
			
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float cueNum = Float.parseFloat(postIndex[6]);
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cuePart = Float.parseFloat(postIndex[7]);
			int cuelistIndex = SearchMatch.findCuelistIndex(cuelists, cuelistCount, cuelistNum);
			
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			int upTimeDuration = (Integer) argList.get(3);
			int upTimeDelay = (Integer) argList.get(4);
			int downTimeDuration = (Integer) argList.get(5);
			int downTimeDelay = (Integer) argList.get(6);
			int focusTimeDuration = (Integer) argList.get(7);
			int focusTimeDelay = (Integer) argList.get(8);
			int colorTimeDuration = (Integer) argList.get(9);
			int colorTimeDelay = (Integer) argList.get(10);
			int beamTimeDuration = (Integer) argList.get(11);
			int beamTimeDelay = (Integer) argList.get(12);
			boolean preheat = (Boolean) argList.get(13);
			String curve = (String) argList.get(14);
			int rate = (Integer) argList.get(15);
			String mark = (String) argList.get(16);
			String block = (String) argList.get(17);
			String assertString = (String) argList.get(18);
		    String link = String.valueOf(argList.get(19));
			int followTime = (Integer) argList.get(20);
			int hangTime = (Integer) argList.get(21);
			boolean allFade = (Boolean) argList.get(22);
			int loop = (Integer) argList.get(23);
			boolean solo = (Boolean) argList.get(24);
			String timecode = (String) argList.get(25);
			int partCount = (Integer) argList.get(26);
			
			if(cuePart != 0) { //If the cue is a Part
				Cue tempCue = new Cue(cuePart, cuelists[cuelistIndex], UID, name, upTimeDuration, upTimeDelay, downTimeDuration, downTimeDelay,
						focusTimeDuration, focusTimeDelay, colorTimeDuration, colorTimeDelay, beamTimeDuration, beamTimeDelay, preheat,
						curve, rate, mark, block, assertString, link, followTime, hangTime, allFade, loop, solo, timecode);
				int cuelistIndexNum = SearchMatch.findCueIndexFromCuelist(cuelists[cuelistIndex], cueNum);
				int partIndexNum = cuelists[cuelistIndex].getCueFromIndex(cuelistIndexNum).addCuePartReturnIndex(tempCue); //Adds the cue and returns the indexNum all in one fell-swoop
				eventBus.post(new CuePartUpdateEvent(this.oscInstance, cuelists[cuelistIndex].getCueFromIndex(cuelistIndexNum), cuelists[cuelistIndex], cuelistIndexNum, tempCue, partIndexNum));
			}else { //If the Cue is a base cue
				Cue tempCue = new Cue(cueNum, cuelists[cuelistIndex], UID, name, upTimeDuration, upTimeDelay, downTimeDuration, downTimeDelay,
						focusTimeDuration, focusTimeDelay, colorTimeDuration, colorTimeDelay, beamTimeDuration, beamTimeDelay, preheat,
						curve, rate, mark, block, assertString, link, followTime, hangTime, allFade, loop, solo, timecode);
				int indexNum = cuelists[cuelistIndex].addCueReturnIndex(tempCue);
				eventBus.post(new CueUpdateEvent(this.oscInstance, tempCue, cuelists[cuelistIndex], indexNum));
			}
		} else if(command.contains(oscmap.RETURN_CUE_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("fx")
				&& !command.contains("links") && !command.contains("actions")) {											//RETURN_CUE_INFO for ETC_EOS only --->PART 2<---
		
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float cueNum = Float.parseFloat(postIndex[6]);
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cuePart = Float.parseFloat(postIndex[7]);
			int varLength = Integer.parseInt(postIndex[11]);
			int cuelistIndex = SearchMatch.findCuelistIndex(cuelists, cuelistCount, cuelistNum);
			
			if(cuePart != 0) { //If it's a partCue
				int parentCueIndex = SearchMatch.findCueIndexFromCuelist(cuelists[cuelistIndex], cueNum);
				int cuePartIndex = SearchMatch.findCuePartIndexFromCue(cuelists[cuelistIndex].getCueFromIndex(parentCueIndex), cuePart);
				
				for(int x = 2; x < varLength; x++) {
					String temp = argList.get(x).toString();
					if(!temp.contains("-")) { 
						cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getPartFromIndex(cuePartIndex).addEffect(SearchMatch.findEffect(effects, effectCount,(Integer) argList.get(x)));
						Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Cue " + cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getPartFromIndex(cuePartIndex).getCueNumber());
					} else {
						String[] fxSplit = temp.split("-");
						int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
						int secondNum = Integer.parseInt(fxSplit[1]);
						int newNum = secondNum - firstNum;
						if(newNum > 0) { //If the number is positive
							for(int i = firstNum; i <= secondNum; i++) {
								cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getPartFromIndex(cuePartIndex).addEffect(SearchMatch.findEffect(effects, effectCount, i));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getPartFromIndex(cuePartIndex).getCueNumber());
							}
						} else if (newNum < 0) { //If the number is negative
							for(int i = secondNum; i <= firstNum; i++) {
								cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getPartFromIndex(cuePartIndex).addEffect(SearchMatch.findEffect(effects, effectCount, i));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getPartFromIndex(cuePartIndex).getCueNumber());
							}
						}
					}
				}
			}else { //If it's a baseCue only
				int parentCueIndex = SearchMatch.findCueIndexFromCuelist(cuelists[cuelistIndex], cueNum);
				
				for(int x = 2; x < varLength; x++) {
					String temp = argList.get(x).toString();
					if(!temp.contains("-")) { 
						cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).addEffect(SearchMatch.findEffect(effects, effectCount, Float.parseFloat(argList.get(x).toString())));
						Main.log.debug("OscParser added Effect " + Float.parseFloat(argList.get(x).toString()) + " to Cue " + cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getCueNumber());
					} else {
						String[] fxSplit = temp.split("-");
						int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
						int secondNum = Integer.parseInt(fxSplit[1]);
						int newNum = secondNum - firstNum;
						if(newNum > 0) { //If the number is positive
							for(int i = firstNum; i <= secondNum; i++) {
								cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).addEffect(SearchMatch.findEffect(effects, effectCount, i));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getCueNumber());
							}
						} else if (newNum < 0) { //If the number is negative
							for(int i = secondNum; i <= firstNum; i++) {
								cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).addEffect(SearchMatch.findEffect(effects, effectCount, i));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cuelists[cuelistIndex].getCueFromIndex(parentCueIndex).getCueNumber());
							}
						}
					}
				}
			}
			//TODO: Add Cue links and Cue external actions
		} else if(command.contains(oscmap.RETURN_ACTIVE_CHAN) && format.equals(OscFormat.ETC_EOS)) {						//ACTIVE CHANNEL PROCESSING FOR ETC_EOS ONLY
			String[] postIndex = ((String)argList.get(0)).split(" "); //Split via the break, giving us the active channel numbers only.
			if(postIndex[0].contains(",")) {
				this.activeChannelCount = 0; //Set to 0- we'll add to this throughout this section
				this.singleChannelSelected = false;
				String[] commaSplit = postIndex[0].split(","); //Split in-case of commas
				for(int x = 0; x < commaSplit.length; x++) {
					if(commaSplit[x].contains("-")) {
						String[] chanSplit = commaSplit[x].split("-");
						int firstNum = Integer.parseInt(chanSplit[0]);
						int secondNum = Integer.parseInt(chanSplit[1]);
						int newNum = secondNum - firstNum;
						if(newNum > 0 ) { //If the number is positive
							for(int i = firstNum; i <= secondNum; i++) {
								activeChannels[x] = SearchMatch.findChannel(channels, channelCount, i);
								activeChannelCount++;
								eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels[x], x, this.singleChannelSelected));
							}
						} else if (newNum < 0) { //If the number is negative
							for(int i = secondNum; i <= firstNum; i++) {
								activeChannels[x] = SearchMatch.findChannel(channels, channelCount, i);
								activeChannelCount++;
								eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels[x], x, this.singleChannelSelected));
							}
						}
					}
				}
			}else if(postIndex[0].contains("-")) {
				this.activeChannelCount = 0; //Set to 0- we'll add to this throughout this section
				this.singleChannelSelected = false;
				String[] chanSplit = postIndex[0].split("-");
				int firstNum = Integer.parseInt(chanSplit[0]);
				int secondNum = Integer.parseInt(chanSplit[1]);
				int newNum = secondNum - firstNum;
				if(newNum > 0 ) { //If the number is positive
					for(int i = firstNum; i <= secondNum; i++) {
						activeChannels[activeChannelCount] = SearchMatch.findChannel(channels, channelCount, i);
						eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels[activeChannelCount], activeChannelCount, this.singleChannelSelected));
						activeChannelCount++;
					}
				} else if (newNum < 0) { //If the number is negative
					for(int i = secondNum; i <= firstNum; i++) {
						activeChannels[activeChannelCount] = SearchMatch.findChannel(channels, channelCount, i);
						eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels[activeChannelCount], activeChannelCount, this.singleChannelSelected));
						activeChannelCount++;
					}
				}
			} else if(postIndex[0].isBlank()) { //No channels selected
				activeChannelCount = 0;
				singleChannelSelected = false;
			} else {
				int activeChannelNum = Integer.parseInt(postIndex[0]);
				activeChannels[0] = SearchMatch.findChannel(channels, channelCount, activeChannelNum);
				activeChannelCount = 1;
				singleChannelSelected = true;
				try {
					eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels[0], activeChannelCount, this.singleChannelSelected));
				}catch(NullPointerException e) {
					Main.log.error("OscParser ERROR: Tried to select a non-patched channel! CHANNEL NUM: " + activeChannelNum);
				}
			}
			
		}
		
		//For debug
		System.out.println(message.getAddress());
		System.out.println(message.getArguments());
	}
	
	/*
	 * CONSTRUCTORS
	 */
	
	public OscParser(OscInstance oscInstanceIn, PhotonEventHandler eventBusIn, ObjectUpdater objectUpdaterIn, OscMap oscmapIn, OscFormat formatIn, int receiverPortIn) {
		this.oscInstance = oscInstanceIn;
		this.receiverPort = receiverPortIn;
		this.oscmap = oscmapIn;
		this.format = formatIn;
		this.eventBus = eventBusIn;
		this.objectUpdater = objectUpdaterIn;
		
		this.groupCount = 0;
		this.subCount = 0;
		this.channelCount = 0;
		this.cuelistCount = 0;
		this.macroCount = 0;
		this.effectCount = 0;
		this.presetCount = 0;
		this.intensityPaletteCount = 0;
		this.focusPaletteCount = 0;
		this.colorPaletteCount = 0;
		this.beamPaletteCount = 0;
		
		this.activeChannelCount = 0;
		
		this.singleChannelSelected = false;
		
		try {
			this.receiver = new OSCPortIn(this.receiverPort);
		} catch (IOException e) {
			Main.log.error("OscParser ERROR: IOException on setting incoming port listener. Maybe the port is already in use?",e);
		}
		
		MessageSelector selector = new OSCPatternAddressMessageSelector("//"); //Selects and listens to all incoming messages
		this.receiver.getDispatcher().addListener(selector,this);
		this.receiver.startListening();

	}
}
