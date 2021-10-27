package com.strikete.photon.utils;

import java.io.IOException;
import java.util.ArrayList;
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
	
	private OSCMessage message;
	
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Sub> subs = new ArrayList<Sub>();
	private ArrayList<Channel> channels = new ArrayList<Channel>();
	private ArrayList<Cuelist> cuelists = new ArrayList<Cuelist>();
	private ArrayList<Macro> macros = new ArrayList<Macro>();
	private ArrayList<Effect> effects = new ArrayList<Effect>();
	private ArrayList<Preset> presets = new ArrayList<Preset>();
	private ArrayList<IntensityPalette> intensityPalettes = new ArrayList<IntensityPalette>();
	private ArrayList<FocusPalette> focusPalettes = new ArrayList<FocusPalette>();
	private ArrayList<ColorPalette> colorPalettes = new ArrayList<ColorPalette>();
	private ArrayList<BeamPalette> beamPalettes = new ArrayList<BeamPalette>();
	
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
	
	private ArrayList<Channel> activeChannels = new ArrayList<Channel>();
	
	private boolean singleChannelSelected;
	
	/*
	 * METHODS - GETTERS FOR TOP INDEX
	 */
	public int getGroupSize() {
		return groups.size();
	}
	public int getSubSize() {
		return subs.size();
	}
	public int getChannelSize() {
		return channels.size();
	}
	public int getCuelistSize() {
		return cuelists.size();
	}
	public int getMacroSize() {
		return macros.size();
	}
	public int getEffectSize() {
		return effects.size();
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
	public ArrayList<Group> getGroups() {
		return this.groups;
	}
	public ArrayList<Sub> getSubs() {
		return this.subs;
	}
	public ArrayList<Channel> getChannels() {
		return this.channels;
	}
	public ArrayList<Cuelist> getCuelists() {
		return this.cuelists;
	}
	public ArrayList<Macro> getMacros() {
		return this.macros;
	}
	public ArrayList<Effect> getEffects() {
		return this.effects;
	}
	public ArrayList<Preset> getPresets() {
		return this.presets;
	}
	public ArrayList<IntensityPalette> getIntensityPalettes() {
		return this.intensityPalettes;
	}
	public ArrayList<FocusPalette> getFocusPalettes() {
		return this.focusPalettes;
	}
	public ArrayList<ColorPalette> getColorPalettes() {
		return this.colorPalettes;
	}
	public ArrayList<BeamPalette> getBeamPalettes() {
		return this.beamPalettes;
	}
	
	public Group getGroupFromIndex(int index) {
		return groups.get(index);
	}
	public Sub getSubFromIndex(int index) {
		return subs.get(index);
	}
	public Channel getChannelFromIndex(int index) {
		return channels.get(index);
	}
	public Cuelist getCuelistFromIndex(int index) {
		return cuelists.get(index);
	}
	public Macro getMacroFromIndex(int index) {
		return macros.get(index);
	}
	public Effect getEffectFromIndex(int index) {
		return effects.get(index);
	}
	public Preset getPresetFromIndex(int index) {
		return presets.get(index);
	}
	public IntensityPalette getIntensityPaletteFromIndex(int index) {
		return intensityPalettes.get(index);
	}
	public FocusPalette getFocusPaletteFromIndex(int index) {
		return focusPalettes.get(index);
	}
	public ColorPalette getColorPaletteFromIndex(int index) {
		return colorPalettes.get(index);
	}
	public BeamPalette getBeamPaletteFromIndex(int index) {
		return beamPalettes.get(index);
	}
	
	/*
	 * SET DATA TYPES
	 */
	public void addReplaceGroup(final Group groupIn) {
		boolean matchFlag = false;
		for(int x = 0; x < groups.size(); x++) {
			if(groups.get(x).getUID().equals(groupIn.getUID())) {
				matchFlag = true;
				groups.set(x, groupIn);
			}
		}
		if(!matchFlag) {
			groups.add(groupIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new GroupUpdateEvent(oscInstance, groupIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceSub(final Sub subIn) {
		boolean matchFlag = false;
		for(int x = 0; x < subs.size(); x++) {
			if(subs.get(x).getUID().equals(subIn.getUID())) {
				matchFlag = true;
				subs.set(x, subIn);
			}
		}
		if(!matchFlag) {
			subs.add(subIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new SubUpdateEvent(oscInstance, subIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceChannel(final Channel channelIn) {
		boolean matchFlag = false;
		for(int x = 0; x < channels.size(); x++) {
			if(channels.get(x).getUID().equals(channelIn.getUID())) {
				matchFlag = true;
				channels.set(x, channelIn);
			}
		}
		if(!matchFlag) {
			channels.add(channelIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new ChannelUpdateEvent(oscInstance, channelIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceCuelist(final Cuelist cuelistIn) {
		boolean matchFlag = false;
		for(int x = 0; x < cuelists.size(); x++) {
			if(cuelists.get(x).getUID().equals(cuelistIn.getUID())) {
				matchFlag = true;
				cuelists.set(x, cuelistIn);
			}
		}
		if(!matchFlag) {
			cuelists.add(cuelistIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new CuelistUpdateEvent(oscInstance, cuelistIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceMacro(final Macro macroIn) {
		boolean matchFlag = false;
		for(int x = 0; x < macros.size(); x++) {
			if(macros.get(x).getUID().equals(macroIn.getUID())) {
				matchFlag = true;
				macros.set(x, macroIn);
			}
		}
		if(!matchFlag) {
			macros.add(macroIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new MacroUpdateEvent(oscInstance, macroIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceEffect(final Effect effectIn) {
		boolean matchFlag = false;
		for(int x = 0; x < effects.size(); x++) {
			if(effects.get(x).getUID().equals(effectIn.getUID())) {
				matchFlag = true;
				effects.set(x, effectIn);
			}
		}
		if(!matchFlag) {
			effects.add(effectIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new EffectUpdateEvent(oscInstance, effectIn));
		    }
		  };
		  thread.start();
	}
	public void addReplacePreset(final Preset presetIn) {
		boolean matchFlag = false;
		for(int x = 0; x < presets.size(); x++) {
			if(presets.get(x).getUID().equals(presetIn.getUID())) {
				matchFlag = true;
				presets.set(x, presetIn);
			}
		}
		if(!matchFlag) {
			presets.add(presetIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new PresetUpdateEvent(oscInstance, presetIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceIntensityPalette(final IntensityPalette intensityPaletteIn) {
		boolean matchFlag = false;
		for(int x = 0; x < intensityPalettes.size(); x++) {
			if(intensityPalettes.get(x).getUID().equals(intensityPaletteIn.getUID())) {
				matchFlag = true;
				intensityPalettes.set(x, intensityPaletteIn);
			}
		}
		if(!matchFlag) {
			intensityPalettes.add(intensityPaletteIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new IntensityPaletteUpdateEvent(oscInstance, intensityPaletteIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceFocusPalette(final FocusPalette focusPaletteIn) {
		boolean matchFlag = false;
		for(int x = 0; x < focusPalettes.size(); x++) {
			if(focusPalettes.get(x).getUID().equals(focusPaletteIn.getUID())) {
				matchFlag = true;
				focusPalettes.set(x, focusPaletteIn);
			}
		}
		if(!matchFlag) {
			focusPalettes.add(focusPaletteIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new FocusPaletteUpdateEvent(oscInstance, focusPaletteIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceColorPalette(final ColorPalette colorPaletteIn) {
		boolean matchFlag = false;
		for(int x = 0; x < colorPalettes.size(); x++) {
			if(colorPalettes.get(x).getUID().equals(colorPaletteIn.getUID())) {
				matchFlag = true;
				colorPalettes.add(colorPaletteIn);
			}
		}
		if(!matchFlag) {
			colorPalettes.add(colorPaletteIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new ColorPaletteUpdateEvent(oscInstance, colorPaletteIn));
		    }
		  };
		  thread.start();
	}
	public void addReplaceBeamPalette(final BeamPalette beamPaletteIn) {
		boolean matchFlag = false;
		for(int x = 0; x < beamPalettes.size(); x++) {
			if(beamPalettes.get(x).getUID().equals(beamPaletteIn.getUID())) {
				matchFlag = true;
				beamPalettes.add(beamPaletteIn);
			}
		}
		if(!matchFlag) {
			beamPalettes.add(beamPaletteIn);
		}
		Thread thread = new Thread(){
		    public void run(){
		    	eventBus.post(new BeamPaletteUpdateEvent(oscInstance, beamPaletteIn));
		    }
		  };
		  thread.start();
	}
	
	/*
	 * METHODS - MATCH UID then INDEX RETURN
	 */
	public int groupUidIndexReturn(String UID) {
		for(int x = 0; x < groups.size(); x++) {
			if(groups.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Group with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int subUidIndexReturn(String UID) {
		for(int x = 0; x < subs.size(); x++) {
			if(subs.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Sub with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int channelUidIndexReturn(String UID) {
		for(int x = 0; x < channels.size(); x++) {
			if(channels.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Channel with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int cuelistUidIndexReturn(String UID) {
		for(int x = 0; x < cuelists.size(); x++) {
			if(cuelists.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Cuelist with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int macroUidIndexReturn(String UID) {
		for(int x = 0; x < macros.size(); x++) {
			if(macros.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Macro with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int effectUidIndexReturn(String UID) {
		for(int x = 0; x < effects.size(); x++) {
			if(effects.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Effect with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int presetUidIndexReturn(String UID) {
		for(int x = 0; x < presets.size(); x++) {
			if(presets.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Preset with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int intensityPaletteUidIndexReturn(String UID) {
		for(int x = 0; x < intensityPalettes.size(); x++) {
			if(intensityPalettes.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Intensity Palette with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int focusPaletteUidIndexReturn(String UID) {
		for(int x = 0; x < focusPalettes.size(); x++) {
			if(focusPalettes.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Focus Palette with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int colorPaletteUidIndexReturn(String UID) {
		for(int x = 0; x < colorPalettes.size(); x++) {
			if(colorPalettes.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Color Palette with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	public int beamPaletteUidIndexReturn(String UID) {
		for(int x = 0; x < beamPalettes.size(); x++) {
			if(beamPalettes.get(x).getUID().equals(UID)) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Beam Palette with UID: " + UID + ", RETURNING ZERO!");
		return 0;
	}
	
	/*
	 * METHODS - MATCH NUMBER then INDEX RETURN
	 */
	
	public int groupNumberIndexReturn(float number) {
		for(int x = 0; x < groups.size(); x++) {
			if(groups.get(x).getGroupNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Group with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int subNumberIndexReturn(float number) {
		for(int x = 0; x < subs.size(); x++) {
			if(subs.get(x).getSubNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Sub with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int channelNumberIndexReturn(int number) {
		for(int x = 0; x < channels.size(); x++) {
			if(channels.get(x).getChannelNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Channel with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int cuelistNumberIndexReturn(int number) {
		for(int x = 0; x < cuelists.size(); x++) {
			if(cuelists.get(x).getCuelistNumber() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Cuelist with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int macroNumberIndexReturn(int number) {
		for(int x = 0; x < macros.size(); x++) {
			if(macros.get(x).getMacroNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Macro with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int effectNumberIndexReturn(float number) {
		for(int x = 0; x < effects.size(); x++) {
			if(effects.get(x).getEffectNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Effect with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int presetNumberIndexReturn(float number) {
		for(int x = 0; x < presets.size(); x++) {
			if(presets.get(x).getPresetNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Preset with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int intensityPaletteNumberIndexReturn(float number) {
		for(int x = 0; x < intensityPalettes.size(); x++) {
			if(intensityPalettes.get(x).getPaletteNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Intensity Palette with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int focusPaletteNumberIndexReturn(float number) {
		for(int x = 0; x < focusPalettes.size(); x++) {
			if(focusPalettes.get(x).getPaletteNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Focus Palette with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int colorPaletteNumberIndexReturn(float number) {
		for(int x = 0; x < colorPalettes.size(); x++) {
			if(colorPalettes.get(x).getPaletteNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Color Palette with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	public int beamPaletteNumberIndexReturn(float number) {
		for(int x = 0; x < beamPalettes.size(); x++) {
			if(beamPalettes.get(x).getPaletteNum() == number) {
				return x;
			}
		}
		Main.log.error("OSCPARSER: Could not find Beam Palette with Number: " + number + ", RETURNING ZERO!");
		return 0;
	}
	
	/*
	 * METHODS - SearchMatch™ Old Methods
	 */
	
	
	/*
	 * METHODS - CLEAR ARRAYLISTS
	 */
	
	public void clearGroups() {
		groups.clear();
	}
	public void clearSubs() {
		subs.clear();
	}
	public void clearChannels() {
		channels.clear();
	}
	public void clearCuelists() {
		cuelists.clear();
	}
	public void clearMacros() {
		macros.clear();
	}
	public void clearEffects() {
		effects.clear();
	}
	public void clearPresets() {
		presets.clear();
	}
	public void clearIntensityPalettes() {
		intensityPalettes.clear();
	}
	public void clearFocusPalettes() {
		focusPalettes.clear();
	}
	public void clearColorPalettes() {
		colorPalettes.clear();
	}
	public void clearBeamPalettes() {
		beamPalettes.clear();
	}

	/*
	 * METHODS - INTERFACES
	 */
	
	public void acceptMessage(OSCMessageEvent event) {
		message = event.getMessage();
		OscInstance.processingWait = true; //Lock osc sending
		try {
			processMessage();
		}catch(IndexOutOfBoundsException e){
			Main.log.error("OSCPARSER(1/2): Message triggered an index out of bounds error! Printing address, message & print stack.");
			e.printStackTrace();
			Main.log.error("OSCPARSER(2/2): Error report complete.");
		}
		OscInstance.processingWait = false; //Unlock OscSending
	}
	
	public void processMessage() {
		String command = message.getAddress();
		List<Object> argList = message.getArguments();
		
		//For debug
				//System.out.println(message.getAddress());
				//System.out.println(message.getArguments());
		
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
			final int cuelistNum = Integer.parseInt(postIndex[5]);
			System.out.println(message.getAddress());
			final int cueNum = (Integer) (argList.get(0));
			final int cuelistIndex = cuelistNumberIndexReturn(cuelistNum);
			Thread thread = new Thread() {
				public void run() {
					eventBus.post(new CueCountUpdateEvent(oscInstance, cuelists.get(cuelistIndex), cueNum));
				}
			};
			thread.start();
		} else if (command.contains(oscmap.RETURN_PATCH_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("list") && (argList.size() > 5)) {		//RETURN_PATCH_INFO for ETC_EOS only
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int channelNumTemp = Integer.parseInt(postIndex[5]);
			System.out.println(command);
			System.out.println(argList.size());
			int addressNum = (Integer) argList.get(5);
			int level = (Integer) argList.get(7);
			String manufacturer = (String) argList.get(3);
			String type = (String) argList.get(4);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			addReplaceChannel(new Channel(channelNumTemp, addressNum, level, manufacturer, type, name, UID));
		} else if (command.contains(oscmap.RETURN_CUELIST_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("links")) {	//RETURN_CUELIST_INFO for ETC_EOS only
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
			addReplaceCuelist(new Cuelist(cuelistNumTemp, UID, name, playbackMode, faderMode, faderModeIndependence, HTP, assertBool, block, background, soloMode, timecodeList, OOSsync));
		} else if (command.contains(oscmap.RETURN_GROUP_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("channel")) { 	//RETURN_GROUP_INFO for ETC_EOS only --->PART 1<---
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float groupNum = Float.parseFloat(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			addReplaceGroup(new Group(UID, name, groupNum)); //Get group number from console	
		} else if (command.contains(oscmap.RETURN_GROUP_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("channel")) {		//RETURN_GROUP_INFO for ETC_EOS only --->PART 2<---
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					groups.get(groupUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn((Integer) argList.get(x))));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Group " + groups.get(groupUidIndexReturn(UID)).getGroupNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							groups.get(groupUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Group " + groups.get(groupUidIndexReturn(UID)).getGroupNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							groups.get(groupUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Group " + groups.get(groupUidIndexReturn(UID)).getGroupNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_MACRO_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("text")) {		//RETURN_MACRO_INFO for ETC_EOS only --->PART 1<---
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float macroNum = Float.parseFloat(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			String mode = (String) argList.get(3);
			addReplaceMacro(new Macro(macroNum, UID, name, mode));
		} else if (command.contains(oscmap.RETURN_MACRO_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("text")) {		//RETURN_MACRO_INFO for ETC_EOS only --->PART 2<---
			String UID = (String) argList.get(1);
			macros.get(macroUidIndexReturn(UID)).addCommandText((String) argList.get(2));
		} else if (command.contains(oscmap.RETURN_EFFECT_INFO) && format.equals(OscFormat.ETC_EOS)) {									//RETURN_EFFECT_INFO for ETC_EOS only
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float fxNum = Float.parseFloat(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			String effectType = (String) argList.get(3);
			String entry = (String) argList.get(3);
			String exit = (String) argList.get(4);
			String duration = (String) argList.get(5);
			String scale = (String) argList.get(6);
			addReplaceEffect(new Effect(fxNum, UID, name, effectType, entry, exit, duration, scale));
		} else if (command.contains(oscmap.RETURN_SUB_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("fx")) {			//RETURN_SUB_INFO for ETC_EOS only --->PART 1<---
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
			addReplaceSub(new Sub(subNum, UID, name, mode, faderMode, HTP, exclusive, background, restore, priority, upTime, dwellTime, downTime));
		} else if (command.contains(oscmap.RETURN_SUB_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("fx")) {			//RETURN_SUB_INFO for ETC_EOS only --->PART 2<---
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					subs.get(subUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Sub " + subs.get(subUidIndexReturn(UID)).getSubNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							subs.get(subUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Sub " + subs.get(subUidIndexReturn(UID)).getSubNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							subs.get(subUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Sub " + subs.get(subUidIndexReturn(UID)).getSubNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_PRESET_INFO) && format.equals(OscFormat.ETC_EOS) && 
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {								//RETURN_PRESET_INFO for ETC_EOS only --->PART 1<---
			
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float presetNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			addReplacePreset(new Preset(presetNum, UID, name, absolute, locked));
		} else if (command.contains(oscmap.RETURN_PRESET_INFO) && format.equals(OscFormat.ETC_EOS) && 
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {								//RETURN_PRESET_INFO for ETC_EOS only --->PART 2<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					presets.get(presetUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Preset " + presets.get(presetUidIndexReturn(UID)).getPresetNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							presets.get(presetUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Preset " + presets.get(presetUidIndexReturn(UID)).getPresetNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							presets.get(presetUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Preset " + presets.get(presetUidIndexReturn(UID)).getPresetNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_PRESET_INFO) && format.equals(OscFormat.ETC_EOS) && 
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {								//RETURN_PRESET_INFO for ETC_EOS only --->PART 3<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					presets.get(presetUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Preset " + presets.get(presetUidIndexReturn(UID)).getPresetNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							presets.get(presetUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Preset " + presets.get(presetUidIndexReturn(UID)).getPresetNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							presets.get(presetUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Preset " + presets.get(presetUidIndexReturn(UID)).getPresetNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_INTENSITY_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float ipNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			addReplaceIntensityPalette(new IntensityPalette(ipNum, UID, name, absolute, locked));
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_INTENSITY_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Intensity Palette " + intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Intensity Palette " + intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Intensity Palette " + intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_INTENSITY_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_INTENSITY_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Focus Palette " + intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + intensityPalettes.get(intensityPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		}  else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_FOCUS_PALETTE_INFO for ETC_EOS only --->PART 1<---
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float fpNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			addReplaceFocusPalette(new FocusPalette(fpNum, UID, name, absolute, locked));
		} else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_FOCUS_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					focusPalettes.get(focusPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Focus Palette " + focusPalettes.get(focusPaletteUidIndexReturn(UID)).getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							focusPalettes.get(focusPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Focus Palette " + focusPalettes.get(focusPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							focusPalettes.get(focusPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Focus Palette " + focusPalettes.get(focusPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_FOCUS_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_FOCUS_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					focusPalettes.get(focusPaletteUidIndexReturn(UID)).addEffect(effects.get(channelNumberIndexReturn(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Focus Palette " + focusPalettes.get(focusPaletteUidIndexReturn(UID)).getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							focusPalettes.get(focusPaletteUidIndexReturn(UID)).addEffect(effects.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + focusPalettes.get(focusPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							focusPalettes.get(focusPaletteUidIndexReturn(UID)).addEffect(effects.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Focus Palette " + focusPalettes.get(focusPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_COLOR_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float cpNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			addReplaceColorPalette(new ColorPalette(cpNum, UID, name, absolute, locked));
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_COLOR_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					colorPalettes.get(colorPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Color Palette " + colorPalettes.get(colorPaletteUidIndexReturn(UID)).getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							colorPalettes.get(colorPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Color Palette " + colorPalettes.get(colorPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							colorPalettes.get(colorPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Color Palette " + colorPalettes.get(colorPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_COLOR_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_COLOR_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					colorPalettes.get(colorPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Color Palette " + colorPalettes.get(colorPaletteUidIndexReturn(UID)).getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							colorPalettes.get(colorPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Color Palette " + colorPalettes.get(colorPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							colorPalettes.get(colorPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Color Palette " + colorPalettes.get(colorPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_BEAM_PALETTE_INFO for ETC_EOS only --->PART 1<---
			
			int indexNum = (Integer) argList.get(0);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float bpNum = Integer.parseInt(postIndex[5]);
			String UID = (String) argList.get(1);
			String name = (String) argList.get(2);
			boolean absolute = (Boolean) argList.get(3);
			boolean locked = (Boolean) argList.get(4);
			addReplaceBeamPalette(new BeamPalette(bpNum, UID, name, absolute, locked));
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				command.contains("channels") && !command.contains("byType") && !command.contains("fx")) {					//RETURN_BEAM_PALETTE_INFO for ETC_EOS only --->PART 2<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					beamPalettes.get(beamPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(x)));
					Main.log.debug("OscParser added Channel " + (Integer) argList.get(x) + " to Beam Palette " + beamPalettes.get(beamPaletteUidIndexReturn(UID)).getPaletteNum());
				}else { //Split the string if needed
					String[] chanSplit = temp.split("-");
					int firstNum = Integer.parseInt(chanSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(chanSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							beamPalettes.get(beamPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Beam Palette " + beamPalettes.get(beamPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							beamPalettes.get(beamPaletteUidIndexReturn(UID)).addChannel(channels.get(channelNumberIndexReturn(i)));
							Main.log.debug("OscParser added Channel " + i + " to Beam Palette " + beamPalettes.get(beamPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if (command.contains(oscmap.RETURN_BEAM_PALETTE_INFO) && format.equals(OscFormat.ETC_EOS) &&
				!command.contains("channels") && !command.contains("byType") && command.contains("fx")) {					//RETURN_BEAM_PALETTE_INFO for ETC_EOS only --->PART 3<---
			
			String UID = (String) argList.get(1);
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			int varLength = Integer.parseInt(postIndex[9]);
			for(int x = 2; x < varLength; x++) {
				String temp = argList.get(x).toString();
				if(!temp.contains("-")) { 
					beamPalettes.get(beamPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(x)));
					Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Beam Palette " + beamPalettes.get(beamPaletteUidIndexReturn(UID)).getPaletteNum());
				} else {
					String[] fxSplit = temp.split("-");
					int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
					int secondNum = Integer.parseInt(fxSplit[1]);
					int newNum = secondNum - firstNum;
					if(newNum > 0) { //If the number is positive
						for(int i = firstNum; i <= secondNum; i++) {
							beamPalettes.get(beamPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Beam Palette " + beamPalettes.get(beamPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					} else if (newNum < 0) { //If the number is negative
						for(int i = secondNum; i <= firstNum; i++) {
							beamPalettes.get(beamPaletteUidIndexReturn(UID)).addEffect(effects.get(effectNumberIndexReturn(i)));
							Main.log.debug("OscParser added Effect " + i + " to Beam Palette " + beamPalettes.get(beamPaletteUidIndexReturn(UID)).getPaletteNum());
						}
					}
				}
			}
		} else if(command.contains(oscmap.RETURN_CUE_INFO) && format.equals(OscFormat.ETC_EOS) && !command.contains("fx")
				&& !command.contains("links") && !command.contains("actions")) {											//RETURN_CUE_INFO for ETC_EOS only --->PART 1<---
			
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float cueNum = Float.parseFloat(postIndex[6]);
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cuePart = Float.parseFloat(postIndex[7]);
			int cuelistIndex = cuelistNumberIndexReturn(cuelistNum);
			
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
				Cue tempCue = new Cue(cuePart, cuelists.get(cuelistIndex), UID, name, upTimeDuration, upTimeDelay, downTimeDuration, downTimeDelay,
						focusTimeDuration, focusTimeDelay, colorTimeDuration, colorTimeDelay, beamTimeDuration, beamTimeDelay, preheat,
						curve, rate, mark, block, assertString, link, followTime, hangTime, allFade, loop, solo, timecode);
				int cuelistIndexNum = cuelists.get(cuelistIndex).cueUidIndexReturn(UID);
				cuelists.get(cuelistIndex).getCue(cuelistIndexNum).addReplaceCuePart(tempCue); //Adds the cue and returns the indexNum all in one fell-swoop
				eventBus.post(new CuePartUpdateEvent(this.oscInstance, cuelists.get(cuelistIndex).getCue(cuelistIndexNum), cuelists.get(cuelistIndex), tempCue));
			}else { //If the Cue is a base cue
				Cue tempCue = new Cue(cueNum, cuelists.get(cuelistIndex), UID, name, upTimeDuration, upTimeDelay, downTimeDuration, downTimeDelay,
						focusTimeDuration, focusTimeDelay, colorTimeDuration, colorTimeDelay, beamTimeDuration, beamTimeDelay, preheat,
						curve, rate, mark, block, assertString, link, followTime, hangTime, allFade, loop, solo, timecode);
				cuelists.get(cuelistIndex).addReplaceCue(tempCue);
				eventBus.post(new CueUpdateEvent(this.oscInstance, tempCue, cuelists.get(cuelistIndex)));
			}
		} else if(command.contains(oscmap.RETURN_CUE_INFO) && format.equals(OscFormat.ETC_EOS) && command.contains("fx")
				&& !command.contains("links") && !command.contains("actions")) {											//RETURN_CUE_INFO for ETC_EOS only --->PART 2<---
		
			String[] postIndex = command.split("/"); //See EosFamily_ShowControl_UserGuide_RevC.pdf, page 70, for more details on this whole section.
			float cueNum = Float.parseFloat(postIndex[6]);
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cuePart = Float.parseFloat(postIndex[7]);
			int varLength = Integer.parseInt(postIndex[11]);
			int cuelistIndex = cuelistNumberIndexReturn(cuelistNum);
			int parentCueIndex = cuelists.get(cuelistIndex).cueNumberIndexReturn(cueNum);
			
			if(cuePart != 0) { //If it's a partCue
				int cuePartIndex = cuelists.get(cuelistIndex).getCue(parentCueIndex).cuePartNumberIndexReturn(cuePart);
				
				for(int x = 2; x < varLength; x++) {
					String temp = argList.get(x).toString();
					if(!temp.contains("-")) { 
						cuelists.get(cuelistIndex).getCue(parentCueIndex).getPart(cuePartIndex).addEffect(effects.get(effectNumberIndexReturn((Integer) argList.get(x))));
						Main.log.debug("OscParser added Effect " + (Integer) argList.get(x) + " to Cue " + cueNum);
					} else {
						String[] fxSplit = temp.split("-");
						int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
						int secondNum = Integer.parseInt(fxSplit[1]);
						int newNum = secondNum - firstNum;
						if(newNum > 0) { //If the number is positive
							for(int i = firstNum; i <= secondNum; i++) {
								cuelists.get(cuelistIndex).getCue(parentCueIndex).getPart(cuePartIndex).addEffect(effects.get(effectNumberIndexReturn(i)));;
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cueNum);
							}
						} else if (newNum < 0) { //If the number is negative
							for(int i = secondNum; i <= firstNum; i++) {
								cuelists.get(cuelistIndex).getCue(parentCueIndex).getPart(cuePartIndex).addEffect(effects.get(effectNumberIndexReturn(i)));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cueNum);
							}
						}
					}
				}
			}else { //If it's a baseCue only
				
				for(int x = 2; x < varLength; x++) {
					String temp = argList.get(x).toString();
					if(!temp.contains("-")) { 
						cuelists.get(cuelistIndex).getCue(parentCueIndex).addEffect(effects.get(effectNumberIndexReturn(Float.parseFloat(argList.get(x).toString()))));
						Main.log.debug("OscParser added Effect " + Float.parseFloat(argList.get(x).toString()) + " to Cue " + cueNum);
					} else {
						String[] fxSplit = temp.split("-");
						int firstNum = Integer.parseInt(fxSplit[0]); //Calculate the size of the channel array and get channels from the patch list.
						int secondNum = Integer.parseInt(fxSplit[1]);
						int newNum = secondNum - firstNum;
						if(newNum > 0) { //If the number is positive
							for(int i = firstNum; i <= secondNum; i++) {
								System.out.println(effects.size());
								cuelists.get(cuelistIndex).getCue(parentCueIndex).addEffect(effects.get(effectNumberIndexReturn(i)));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cueNum);
							}
						} else if (newNum < 0) { //If the number is negative
							for(int i = secondNum; i <= firstNum; i++) {
								cuelists.get(cuelistIndex).getCue(parentCueIndex).addEffect(effects.get(effectNumberIndexReturn(i)));
								Main.log.debug("OscParser added Effect " + i + " to Cue " + cueNum);
							}
						}
					}
				}
			}
			//TODO: Add Cue links and Cue external actions
		} else if(command.contains(oscmap.RETURN_ACTIVE_CHAN) && format.equals(OscFormat.ETC_EOS)) {						//ACTIVE CHANNEL PROCESSING FOR ETC_EOS ONLY
			String[] postIndex = ((String)argList.get(0)).split(" "); //Split via the break, giving us the active channel numbers only.
			if(postIndex[0].contains(",")) {
				//this.activeChannelCount = 0; //Set to 0- we'll add to this throughout this section
				activeChannels.clear();
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
								activeChannels.add(channels.get(channelNumberIndexReturn(i)));
								eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels.get(x), x, this.singleChannelSelected));
							}
						} else if (newNum < 0) { //If the number is negative
							for(int i = secondNum; i <= firstNum; i++) {
								activeChannels.add(channels.get(channelNumberIndexReturn(i)));
								eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels.get(x), x, this.singleChannelSelected));
							}
						}
					}
				}
			}else if(postIndex[0].contains("-")) {
				//this.activeChannelCount = 0; //Set to 0- we'll add to this throughout this section
				activeChannels.clear();
				this.singleChannelSelected = false;
				String[] chanSplit = postIndex[0].split("-");
				int firstNum = Integer.parseInt(chanSplit[0]);
				int secondNum = Integer.parseInt(chanSplit[1]);
				int newNum = secondNum - firstNum;
				if(newNum > 0 ) { //If the number is positive
					for(int i = firstNum; i <= secondNum; i++) {
						activeChannels.add(channels.get(channelNumberIndexReturn(i)));
						eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels.get(activeChannels.size()-1), activeChannels.size(), this.singleChannelSelected));
					}
				} else if (newNum < 0) { //If the number is negative
					for(int i = secondNum; i <= firstNum; i++) {
						activeChannels.add(channels.get(channelNumberIndexReturn(i)));
						eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels.get(activeChannels.size()-1), activeChannels.size(), this.singleChannelSelected));
					}
				}
			} else if(postIndex[0].isBlank()) { //No channels selected
				activeChannels.clear();
				singleChannelSelected = false;
			} else {
				activeChannels.clear();
				int activeChannelNum = Integer.parseInt(postIndex[0]);
				activeChannels.add(channels.get(channelNumberIndexReturn(activeChannelNum)));
				singleChannelSelected = true;
				try {
					eventBus.post(new ChannelSelectionEvent(this.oscInstance, activeChannels.get(0), activeChannels.size(), this.singleChannelSelected));
				}catch(NullPointerException e) {
					Main.log.error("OscParser ERROR: Tried to select a non-patched channel! CHANNEL NUM: " + activeChannelNum);
				}
			}
			
		}
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
