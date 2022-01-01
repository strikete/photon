package com.strikete.photon.photonosc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.illposed.osc.OSCMessage;
import com.strikete.photon.Photon;
import com.strikete.photon.exceptions.ObjectNotFoundException;
import com.strikete.photon.objects.BeamPalette;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.objects.ColorPalette;
import com.strikete.photon.objects.Cue;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.objects.Curve;
import com.strikete.photon.objects.Effect;
import com.strikete.photon.objects.FocusPalette;
import com.strikete.photon.objects.Group;
import com.strikete.photon.objects.IntensityPalette;
import com.strikete.photon.objects.Macro;
import com.strikete.photon.objects.MagicSheet;
import com.strikete.photon.objects.Pixelmap;
import com.strikete.photon.objects.Preset;
import com.strikete.photon.objects.Snapshot;
import com.strikete.photon.objects.Sub;
import com.strikete.photon.osc.OscIncoming;
import com.strikete.photon.osc.OscListener;
import com.strikete.photon.osc.OscOutgoing;
import com.strikete.photon.utils.OscNumberInterpreter;

public class PhotonOscRoutines {

	/*
	 * VARIABLES
	 */
	private Photon photon;
	private ArrayList<OscListener> listeners = new ArrayList<OscListener>();
	private int delay;
	
	/*
	 * METHODS - CREATE ROUTINES
	 */
	private void createVersionListener() {
		Consumer<OSCMessage> versionConsumer = message -> {
			List<Object> argList = message.getArguments();
			photon.setEosVersion(OscNumberInterpreter.oscToString(argList.get(0)));
		};
		OscListener versionListener = new OscListener(photon, OscIncoming.RETURN_VERSION, versionConsumer, true);
		listeners.add(versionListener);
	}
	
	/*
	 * METHODS - COUNT CONSUMERS
	 */
	
	private void performResponse(int count, String message) {
		for(int b = 0; b < count; b++) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Add generic Thread sleep error
				e.printStackTrace();
			}
			ArrayList<String> indexParameter = new ArrayList<String>();
			indexParameter.add(Integer.toString(b));
			photon.sender.sendOscMessage(photon.sender.parameterizeString(message, indexParameter));
		}
	}
	
	private void createCountListeners() {
		
		Consumer<OSCMessage> patchCountConsumer = message -> {							//PATCH COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_PATCH_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_PATCH_COUNT,patchCountConsumer,true));
		
		Consumer<OSCMessage> cuelistCountConsumer = message -> {						//CUELIST COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			photon.cuelistCount = count;
			performResponse(count,OscOutgoing.GET_CUELIST_INFO);
			
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_CUELIST_COUNT,cuelistCountConsumer,true));
		
		Consumer<OSCMessage> cueCountConsumer = message -> {							//CUE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			for(int b = 0; b < count; b++) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Add generic Thread sleep error
					e.printStackTrace();
				}
				ArrayList<String> indexParameter = new ArrayList<String>();
				String messageAddress = message.getAddress();
				String[] messageAddressArray = messageAddress.split("/");
				indexParameter.add(messageAddressArray[5]);
				indexParameter.add(Integer.toString(b));
				photon.sender.sendOscMessage(photon.sender.parameterizeString(OscOutgoing.GET_CUE_INFO, indexParameter));
			}
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_CUE_COUNT,cueCountConsumer,true));
		
		Consumer<OSCMessage> groupCountConsumer = message -> {							//GROUP COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_GROUP_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_GROUP_COUNT,groupCountConsumer,true));
		
		Consumer<OSCMessage> macroCountConsumer = message -> {							//MACRO COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_MACRO_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_MACRO_COUNT,macroCountConsumer,true));
		
		Consumer<OSCMessage> subCountConsumer = message -> {							//SUB COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_SUB_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_SUB_COUNT,subCountConsumer,true));
		
		Consumer<OSCMessage> presetCountConsumer = message -> {							//PRESET COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_PRESET_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_PRESET_COUNT,presetCountConsumer,true));
		
		Consumer<OSCMessage> intensityPaletteCountConsumer = message -> {				//INTENSITY PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_INTENSITY_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_INTENSITY_PALETTE_COUNT,intensityPaletteCountConsumer,true));
		
		Consumer<OSCMessage> focusPaletteCountConsumer = message -> {					//FOCUS PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_FOCUS_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_FOCUS_PALETTE_COUNT,focusPaletteCountConsumer,true));
		
		Consumer<OSCMessage> colorPaletteCountConsumer = message -> {					//COLOR PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_COLOR_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_COLOR_PALETTE_COUNT,colorPaletteCountConsumer,true));
		
		Consumer<OSCMessage> beamPaletteCountConsumer = message -> {					//BEAM PALETTE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_BEAM_PALETTE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_BEAM_PALETTE_COUNT,beamPaletteCountConsumer,true));
		
		Consumer<OSCMessage> curveCountConsumer = message -> {							//CURVE COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_CURVE_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_CURVE_COUNT,curveCountConsumer,true));
		
		Consumer<OSCMessage> effectCountConsumer = message -> {							//EFFECT COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_EFFECT_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_EFFECT_COUNT,effectCountConsumer,true));
		
		Consumer<OSCMessage> snapshotCountConsumer = message -> {						//SNAPSHOT COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_SNAPSHOT_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_SNAPSHOT_COUNT,snapshotCountConsumer,true));
		
		Consumer<OSCMessage> pixelmapCountConsumer = message -> {						//PIXELMAP COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_PIXELMAP_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_PIXELMAP_COUNT,pixelmapCountConsumer,true));
		
		Consumer<OSCMessage> magicSheetCountConsumer = message -> {						//MAGIC SHEET COUNT CONSUMER
			List<Object> argList = message.getArguments();
			int count = (Integer) argList.get(0);
			performResponse(count,OscOutgoing.GET_MAGIC_SHEET_INFO);
		};
		listeners.add(new OscListener(photon,OscIncoming.RETURN_MAGIC_SHEET_COUNT,magicSheetCountConsumer,true));
		
	}
	
	
	/*
	 * METHODS - INFO CONSUMERS
	 */
	private void createInfoListeners() {
		
		Consumer<OSCMessage> patchInfoConsumer = message -> {							//PATCH INFO CONSUMER (1/2)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int channelNum = Integer.parseInt(postIndex[5]);
			int partNum = Integer.parseInt(postIndex[6]);
			int index = OscNumberInterpreter.oscNumberToInt(argList.get(0));
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			
			Channel channel = null;
			boolean isNew = false;
			
			try {//Check to see if a channel already exists
				channel = photon.channels.get(photon.dataUtility.findChannel(channelNum, partNum, index, uid));
			
			}catch(ObjectNotFoundException e) {
				channel = new Channel(channelNum,partNum,index,uid);
				isNew = true;
				Photon.log.info("OBJ: New Channel Object created. CH "+ channelNum + " PT " + partNum);
			}
			
			try {
				channel.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
				channel.setManufacturer(OscNumberInterpreter.oscToString(argList.get(3)));
				channel.setModel(OscNumberInterpreter.oscToString(argList.get(4)));
				channel.setAddress((Integer) argList.get(5));
				channel.setIntensityAddress((Integer) argList.get(6));
				channel.setLevel((Integer) argList.get(7));
				channel.setGel(OscNumberInterpreter.oscToString(argList.get(8)));
				channel.setText1(OscNumberInterpreter.oscToString(argList.get(9)));
				channel.setText2(OscNumberInterpreter.oscToString(argList.get(10)));
				channel.setText3(OscNumberInterpreter.oscToString(argList.get(11)));
				channel.setText4(OscNumberInterpreter.oscToString(argList.get(12)));
				channel.setText5(OscNumberInterpreter.oscToString(argList.get(13)));
				channel.setText6(OscNumberInterpreter.oscToString(argList.get(14)));
				channel.setText7(OscNumberInterpreter.oscToString(argList.get(15)));
				channel.setText8(OscNumberInterpreter.oscToString(argList.get(16)));
				channel.setText9(OscNumberInterpreter.oscToString(argList.get(17)));
				channel.setText10(OscNumberInterpreter.oscToString(argList.get(18)));
				channel.setPartCount((Integer) argList.get(19));
			}catch(IndexOutOfBoundsException e) {
				//No data for you.
			}
				
			if(isNew) { //Add the channel to the register if it's new
				photon.channels.add(channel);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PATCH, patchInfoConsumer, true));
		
		Consumer<OSCMessage> patchNoteConsumer = message -> {								//PATCH INFO CONSUMER (2/2) notes
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int channelNum = Integer.parseInt(postIndex[5]);
			int partNum = Integer.parseInt(postIndex[6]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Channel channel = null;
			boolean isNew = false;
			
			try {//Check to see if a channel already exists
				channel = photon.channels.get(photon.dataUtility.findChannel(channelNum, partNum, index, uid));
			
			}catch(ObjectNotFoundException e) {
				channel = new Channel(channelNum,partNum,index,uid);
				isNew = true;
				Photon.log.info("OBJ: New Channel Object created. CH "+ channelNum + " PT " + partNum);
			}
			
			channel.setNotes(OscNumberInterpreter.oscToString(argList.get(2)));
			
			if(isNew) { //Add the channel to the register if it's new
				photon.channels.add(channel);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PATCH_NOTES, patchNoteConsumer, true));
		
		Consumer<OSCMessage> cuelistInfoConsumer = message -> {								//CUELIST INFO CONSUMER (1/2)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int cuelistNum = Integer.parseInt(postIndex[5]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Cuelist cuelist = null;
			boolean isNew = false;
			
			try {
				cuelist = photon.cuelists.get(photon.dataUtility.findCuelist(cuelistNum, index, uid));
			}catch(ObjectNotFoundException e) {
				cuelist = new Cuelist(cuelistNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Cuelist Object created. Cuelist #" + cuelistNum);
			}
			
			cuelist.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			cuelist.setPlaybackMode(OscNumberInterpreter.oscToString(argList.get(3)));
			cuelist.setFaderMode(OscNumberInterpreter.oscToString(argList.get(4)));
			cuelist.setIndependent((Boolean) argList.get(5));
			cuelist.setHtp((Boolean) argList.get(6));
			cuelist.setAssert((Boolean) argList.get(7));
			cuelist.setBlock((Boolean) argList.get(8));
			cuelist.setBackground((Boolean) argList.get(9));
			cuelist.setSoloMode((Boolean) argList.get(10));
			cuelist.setTimecodeList((Integer) argList.get(11));
			cuelist.setOosSync((Boolean) argList.get(12));
			
			if(isNew) { //Add the cuelist to the register if it's new
				photon.cuelists.add(cuelist);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CUELIST, cuelistInfoConsumer, true));
		
		Consumer<OSCMessage> cuelistLinkedConsumer = message -> {							//CUELIST INFO CONSUMER (2/2) linked
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int cuelistNum = Integer.parseInt(postIndex[5]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Cuelist cuelist = null;
			boolean isNew = false;
			
			try {
				cuelist = photon.cuelists.get(photon.dataUtility.findCuelist(cuelistNum, index, uid));
			}catch(ObjectNotFoundException e) {
				cuelist = new Cuelist(cuelistNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Cuelist Object created. Cuelist #" + cuelistNum);
			}
			
			try {
				cuelist.setLinkedCueLists(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				cuelist.setLinkedCueLists(null);
			}
			
			
			if(isNew) {
				photon.cuelists.add(cuelist);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CUELIST_LINKS, cuelistLinkedConsumer, true));
		
		Consumer<OSCMessage> cueInfoConsumer = message -> {									//CUE INFO CONSUMER (1/4)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cueNum = Float.parseFloat(postIndex[6]);
			int partNum = Integer.parseInt(postIndex[7]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Cue cue = null;
			boolean isNew = false;
			
			try {
				cue = photon.cues.get(photon.dataUtility.findCue(cueNum, index, uid, cuelistNum, partNum));
			}catch(ObjectNotFoundException e) {
				cue = new Cue(cueNum, index, uid, cuelistNum, partNum);
				isNew = true;
				Photon.log.info("OBJ: New Cue Object created. Cue #" + cueNum);
			}
			
			cue.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			cue.setUpTimeDuration(OscNumberInterpreter.oscNumberToFloat(argList.get(3)));
			cue.setUpTimeDelay(OscNumberInterpreter.oscNumberToFloat(argList.get(4)));
			cue.setDownTimeDuration(OscNumberInterpreter.oscNumberToFloat(argList.get(5)));
			cue.setDownTimeDelay(OscNumberInterpreter.oscNumberToFloat(argList.get(6)));
			cue.setFocusTimeDuration(OscNumberInterpreter.oscNumberToFloat(argList.get(7)));
			cue.setFocusTimeDelay(OscNumberInterpreter.oscNumberToFloat(argList.get(8)));
			cue.setColorTimeDuration(OscNumberInterpreter.oscNumberToFloat(argList.get(9)));
			cue.setColorTimeDelay(OscNumberInterpreter.oscNumberToFloat(argList.get(10)));
			cue.setBeamTimeDuration(OscNumberInterpreter.oscNumberToFloat(argList.get(11)));
			cue.setBeamTimeDelay(OscNumberInterpreter.oscNumberToFloat(argList.get(12)));
			cue.setPreheat((boolean) argList.get(13));
			cue.setCurve(OscNumberInterpreter.oscNumberToArray(argList.get(14)));
			cue.setRate((int) argList.get(15));
			cue.setMark(OscNumberInterpreter.oscToString(argList.get(16)));
			cue.setBlock(OscNumberInterpreter.oscToString(argList.get(17)));
			cue.setAssert(OscNumberInterpreter.oscToString(argList.get(18)));
			cue.setLink(String.valueOf(argList.get(19)));
			cue.setFollowTime(OscNumberInterpreter.oscNumberToFloat(argList.get(20)));
			cue.setHangTime(OscNumberInterpreter.oscNumberToFloat(argList.get(21)));
			cue.setAllFade((boolean) argList.get(22));
			cue.setLoop((int) argList.get(23));
			cue.setSolo((boolean) argList.get(24));
			cue.setTimecode(OscNumberInterpreter.oscToString(argList.get(25)));
			cue.setPartCount((int) argList.get(26));
			
			if(isNew) {
				photon.cues.add(cue);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CUE, cueInfoConsumer, true));
		
		Consumer<OSCMessage> cueEffectListConsumer = message -> {							//CUE INFO CONSUMER (2/4) effect list
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cueNum = Float.parseFloat(postIndex[6]);
			int partNum = Integer.parseInt(postIndex[7]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Cue cue = null;
			boolean isNew = false;
			
			try {
				cue = photon.cues.get(photon.dataUtility.findCue(cueNum, index, uid, cuelistNum, partNum));
			}catch(ObjectNotFoundException e) {
				cue = new Cue(cueNum, index, uid, cuelistNum, partNum);
				isNew = true;
				Photon.log.info("OBJ: New Cue Object created. Cue #" + cueNum);
			}
			
			try {
				cue.setEffectList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				cue.setEffectList(null);
			}
				
			if(isNew) {
				photon.cues.add(cue);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CUE_EFFECTS, cueEffectListConsumer, true));
		
		Consumer<OSCMessage> cueLinkedCuelistsConsumer = message -> {						//CUE INFO CONSUMER (3/4) linked cuelists
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cueNum = Float.parseFloat(postIndex[6]);
			int partNum = Integer.parseInt(postIndex[7]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Cue cue = null;
			boolean isNew = false;
			
			try {
				cue = photon.cues.get(photon.dataUtility.findCue(cueNum, index, uid, cuelistNum, partNum));
			}catch(ObjectNotFoundException e) {
				cue = new Cue(cueNum, index, uid, cuelistNum, partNum);
				isNew = true;
				Photon.log.info("OBJ: New Cue Object created. Cue #" + cueNum);
			}
			
			try {
				cue.setLinkedCueLists(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				cue.setLinkedCueLists(null);
			}
			
			if(isNew) {
				photon.cues.add(cue);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CUE_LINKS, cueLinkedCuelistsConsumer, true));
		
		Consumer<OSCMessage> cueActionConsumer = message -> {								//CUE INFO CONSUMER (4/4) linked action
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int cuelistNum = Integer.parseInt(postIndex[5]);
			float cueNum = Float.parseFloat(postIndex[6]);
			int partNum = Integer.parseInt(postIndex[7]);
			int index = (Integer) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Cue cue = null;
			boolean isNew = false;
			
			try {
				cue = photon.cues.get(photon.dataUtility.findCue(cueNum, index, uid, cuelistNum, partNum));
			}catch(ObjectNotFoundException e) {
				cue = new Cue(cueNum, index, uid, cuelistNum, partNum);
				isNew = true;
				Photon.log.info("OBJ: New Cue Object created. Cue #" + cueNum);
			}
			
			try {
				cue.setExternalLinkAction(OscNumberInterpreter.oscToString(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				cue.setExternalLinkAction(null);
			}
			
			if(isNew) {
				photon.cues.add(cue);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CUE_ACTIONS, cueActionConsumer, true));
		
		Consumer<OSCMessage> groupInfoConsumer = message -> {								//GROUP INFO CONSUMER (1/2)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			float groupNum = Float.parseFloat(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Group group = null;
			boolean isNew = false;
			
			try {
				group = photon.groups.get(photon.dataUtility.findGroup(groupNum, index, uid));
			}catch(ObjectNotFoundException e) {
				group = new Group(groupNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Group Object created. Group #" + groupNum);
			}
			group.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			
			if(isNew) {
				photon.groups.add(group);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_GROUP, groupInfoConsumer, true));
		
		Consumer<OSCMessage> groupChannelsConsumer = message -> {							//GROUP INFO CONSUMER (2/2) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			float groupNum = Float.parseFloat(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Group group = null;
			boolean isNew = false;
			
			try {
				group = photon.groups.get(photon.dataUtility.findGroup(groupNum, index, uid));
			}catch(ObjectNotFoundException e) {
				group = new Group(groupNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Group Object created. Group #" + groupNum);
			}
			group.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			
			ArrayList<Integer> groupChannels = new ArrayList<Integer>();
			for(int x = 3; x < (argList.size()); x++) {  //Interprets various channels in the argument list into one list.
				try {
					groupChannels.addAll(OscNumberInterpreter.oscNumberToArray(argList.get(x)));
				}catch(NullPointerException e) {
					//Don't add anything...
				}
			}
			group.setChannels(groupChannels);
			
			if(isNew) {
				photon.groups.add(group);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_GROUP_CHANNELS, groupChannelsConsumer, true));
		
		Consumer<OSCMessage> macroInfoConsumer = message -> {								//MACRO INFO CONSUMER (1/2)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int macroNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Macro macro = null;
			boolean isNew = false;
			
			try {
				macro = photon.macros.get(photon.dataUtility.findMacro(macroNum, index, uid));
			}catch(ObjectNotFoundException e) {
				macro = new Macro(macroNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Macro Object created. Macro #" + macroNum);
			}
			macro.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			macro.setMode(OscNumberInterpreter.oscToString(argList.get(3)));
			
			if(isNew) {
				photon.macros.add(macro);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_MACRO, macroInfoConsumer, true));
		
		Consumer<OSCMessage> macroCommandConsumer = message -> {							//MACRO INFO CONSUMER (2/2) command text
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int macroNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Macro macro = null;
			boolean isNew = false;
			
			try {
				macro = photon.macros.get(photon.dataUtility.findMacro(macroNum, index, uid));
			}catch(ObjectNotFoundException e) {
				macro = new Macro(macroNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Macro Object created. Macro #" + macroNum);
			}
			
			String macroCommand = ""; //Compiles multiple String messages (if necessary) and puts them together
			for(int x = 2; x < argList.size(); x++) {
				macroCommand.concat(OscNumberInterpreter.oscToString(argList.get(x)));
			}
			macro.setCommandText(macroCommand);
			
			if(isNew) {
				photon.macros.add(macro);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_MACRO_COMMAND, macroCommandConsumer, true));
		
		Consumer<OSCMessage> subInfoConsumer = message -> {									//SUB INFO CONSUMER (1/2)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int subNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Sub sub = null;
			boolean isNew = false;
			
			try {
				sub = photon.subs.get(photon.dataUtility.findSub(subNum, index, uid));
			}catch(ObjectNotFoundException e) {
				sub = new Sub(subNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Sub Object created. Sub #" + subNum);
			}
			
			sub.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			sub.setMode(OscNumberInterpreter.oscToString(argList.get(3)));
			sub.setFaderMode(OscNumberInterpreter.oscToString(argList.get(4)));
			sub.setHtp((boolean) argList.get(5));
			sub.setExclusive((boolean) argList.get(6));
			sub.setBackground((boolean) argList.get(7));
			sub.setRestore((boolean) argList.get(8));
			sub.setPriority(OscNumberInterpreter.oscToString(argList.get(9)));
			sub.setUpTime(OscNumberInterpreter.oscToString(argList.get(10)));
			sub.setDwellTime(OscNumberInterpreter.oscToString(argList.get(11)));
			sub.setDownTime(OscNumberInterpreter.oscToString(argList.get(12)));
			
			if(isNew) {
				photon.subs.add(sub);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_SUB, subInfoConsumer, true));
		
		Consumer<OSCMessage> subEffectConsumer = message -> {								//SUB INFO CONSUMER (2/2) effect list
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int subNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Sub sub = null;
			boolean isNew = false;
			
			try {
				sub = photon.subs.get(photon.dataUtility.findSub(subNum, index, uid));
			}catch(ObjectNotFoundException e) {
				sub = new Sub(subNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Sub Object created. Sub #" + subNum);
			}
			
			try {
				sub.setEffectList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				sub.setEffectList(null);
			}
				
			if(isNew) {
				photon.subs.add(sub);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_SUB_EFFECTS, subEffectConsumer, true));
		
		Consumer<OSCMessage> presetInfoConsumer = message -> {								//PRESET INFO CONSUMER (1/4)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int presetNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Preset preset = null;
			boolean isNew = false;
			
			try {
				preset = photon.presets.get(photon.dataUtility.findPreset(presetNum, index, uid));
			}catch(ObjectNotFoundException e) {
				preset = new Preset(presetNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Preset Object created. Preset #" + presetNum);
			}
			
			preset.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			preset.setAbsolute((boolean) argList.get(3));
			preset.setLocked((boolean) argList.get(4));
			
			if(isNew) {
				photon.presets.add(preset);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PRESET, presetInfoConsumer, true));
		
		Consumer<OSCMessage> presetChannelConsumer = message -> {							//PRESET INFO CONSUMER (2/4) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int presetNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Preset preset = null;
			boolean isNew = false;
			
			try {
				preset = photon.presets.get(photon.dataUtility.findPreset(presetNum, index, uid));
			}catch(ObjectNotFoundException e) {
				preset = new Preset(presetNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Preset Object created. Preset #" + presetNum);
			}
			
			try {
				preset.setChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				preset.setChannelList(null);
			}
				
			if(isNew) {
				photon.presets.add(preset);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PRESET_CHANNELS, presetChannelConsumer, true));
		
		Consumer<OSCMessage> presetChannelByTypeConsumer = message -> {						//PRESET INFO CONSUMER (3/4) channels by type
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int presetNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Preset preset = null;
			boolean isNew = false;
			
			try {
				preset = photon.presets.get(photon.dataUtility.findPreset(presetNum, index, uid));
			}catch(ObjectNotFoundException e) {
				preset = new Preset(presetNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Preset Object created. Preset #" + presetNum);
			}
			
			try {
				preset.setByTypeChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				preset.setByTypeChannelList(null);
			}
				
			if(isNew) {
				photon.presets.add(preset);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PRESET_CHANNELS_BY_TYPE, presetChannelByTypeConsumer, true));
		
		Consumer<OSCMessage> presetEffectConsumer = message -> {							//PRESET INFO CONSUMER (4/4) effects
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int presetNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Preset preset = null;
			boolean isNew = false;
			
			try {
				preset = photon.presets.get(photon.dataUtility.findPreset(presetNum, index, uid));
			}catch(ObjectNotFoundException e) {
				preset = new Preset(presetNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Preset Object created. Preset #" + presetNum);
			}
			
			try {
				preset.setEffectList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				preset.setEffectList(null);
			}
			
			if(isNew) {
				photon.presets.add(preset);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PRESET_EFFECTS, presetEffectConsumer, true));
		
		Consumer<OSCMessage> intensityPaletteInfoConsumer = message -> {					//INTENSITY PALETTE INFO CONSUMER (1/3)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			IntensityPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.intensityPalettes.get(photon.dataUtility.findIntensityPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new IntensityPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Intensity Palette Object created. IP #" + paletteNum);
			}
			
			palette.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			palette.setAbsolute((boolean) argList.get(3));
			palette.setLocked((boolean) argList.get(4));
			
			if(isNew) {
				photon.intensityPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_INTENSITY_PALETTE, intensityPaletteInfoConsumer, true));
		
		Consumer<OSCMessage> intensityPaletteChannelConsumer = message -> {					//INTENSITY PALETTE INFO CONSUMER (2/3) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			IntensityPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.intensityPalettes.get(photon.dataUtility.findIntensityPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new IntensityPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Intensity Palette Object created. IP #" + paletteNum);
			}
			
			palette.setChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			
			if(isNew) {
				photon.intensityPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_INTENSITY_PALETTE_CHANNELS, intensityPaletteChannelConsumer, true));
		
		Consumer<OSCMessage> intensityPaletteChannelByTypeConsumer = message -> {			//INTENSITY PALETTE INFO CONSUMER (3/3) channels by type
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			IntensityPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.intensityPalettes.get(photon.dataUtility.findIntensityPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new IntensityPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Intensity Palette Object created. IP #" + paletteNum);
			}
			
			try {
				palette.setByTypeChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setByTypeChannelList(null);
			}
			
			if(isNew) {
				photon.intensityPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_INTENSITY_PALETTE_CHANNELS_BY_TYPE, intensityPaletteChannelByTypeConsumer, true));
		
		Consumer<OSCMessage> focusPaletteInfoConsumer = message -> {						//FOCUS PALETTE INFO CONSUMER (1/3)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			FocusPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.focusPalettes.get(photon.dataUtility.findFocusPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new FocusPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Focus Palette Object created. FP #" + paletteNum);
			}
			
			palette.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			palette.setAbsolute((boolean) argList.get(3));
			palette.setLocked((boolean) argList.get(4));
			
			if(isNew) {
				photon.focusPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_FOCUS_PALETTE, focusPaletteInfoConsumer, true));
		
		Consumer<OSCMessage> focusPaletteChannelConsumer = message -> {						//FOCUS PALETTE INFO CONSUMER (2/3) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			FocusPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.focusPalettes.get(photon.dataUtility.findFocusPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new FocusPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Focus Palette Object created. FP #" + paletteNum);
			}
			
			try {
				palette.setChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setChannelList(null);
			}
			
			if(isNew) {
				photon.focusPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_FOCUS_PALETTE_CHANNELS, focusPaletteChannelConsumer, true));
		
		Consumer<OSCMessage> focusPaletteChannelByTypeConsumer = message -> {				//FOCUS PALETTE INFO CONSUMER (3/3) channels by type
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			FocusPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.focusPalettes.get(photon.dataUtility.findFocusPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new FocusPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Focus Palette Object created. FP #" + paletteNum);
			}
			
			try {
				palette.setByTypeChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setByTypeChannelList(null);
			}
			
			if(isNew) {
				photon.focusPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_FOCUS_PALETTE_CHANNELS_BY_TYPE, focusPaletteChannelByTypeConsumer, true));
		
		Consumer<OSCMessage> colorPaletteInfoConsumer = message -> {						//COLOR PALETTE INFO CONSUMER (1/3)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			ColorPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.colorPalettes.get(photon.dataUtility.findColorPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new ColorPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Color Palette Object created. CP #" + paletteNum);
			}
			
			palette.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			palette.setAbsolute((boolean) argList.get(3));
			palette.setLocked((boolean) argList.get(4));
			
			if(isNew) {
				photon.colorPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_COLOR_PALETTE, colorPaletteInfoConsumer, true));
		
		Consumer<OSCMessage> colorPaletteChannelConsumer = message -> {						//COLOR PALETTE INFO CONSUMER (2/3) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			ColorPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.colorPalettes.get(photon.dataUtility.findColorPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new ColorPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Color Palette Object created. CP #" + paletteNum);
			}
			
			try {
				palette.setChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setChannelList(null);
			}
			
			if(isNew) {
				photon.colorPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_COLOR_PALETTE_CHANNELS, colorPaletteChannelConsumer, true));
		
		Consumer<OSCMessage> colorPaletteChannelByTypeConsumer = message -> {				//COLOR PALETTE INFO CONSUMER (3/3) channels by type
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			ColorPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.colorPalettes.get(photon.dataUtility.findColorPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new ColorPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Color Palette Object created. CP #" + paletteNum);
			}
			
			try {
				palette.setByTypeChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setByTypeChannelList(null);
			}
			
			if(isNew) {
				photon.colorPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_COLOR_PALETTE_CHANNELS_BY_TYPE, colorPaletteChannelByTypeConsumer, true));
		
		Consumer<OSCMessage> beamPaletteInfoConsumer = message -> {							//BEAM PALETTE INFO CONSUMER (1/3)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			BeamPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.beamPalettes.get(photon.dataUtility.findBeamPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new BeamPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Beam Palette Object created. BP #" + paletteNum);
			}
			
			palette.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			palette.setAbsolute((boolean) argList.get(3));
			palette.setLocked((boolean) argList.get(4));
			
			if(isNew) {
				photon.beamPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_BEAM_PALETTE, beamPaletteInfoConsumer, true));
		
		Consumer<OSCMessage> beamPaletteChannelConsumer = message -> {						//BEAM PALETTE INFO CONSUMER (2/3) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			BeamPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.beamPalettes.get(photon.dataUtility.findBeamPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new BeamPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Beam Palette Object created. BP #" + paletteNum);
			}
			
			try {
				palette.setChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setChannelList(null);
			}
			
			if(isNew) {
				photon.beamPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_BEAM_PALETTE_CHANNELS, beamPaletteChannelConsumer, true));
		
		Consumer<OSCMessage> beamPaletteChannelByTypeConsumer = message -> {				//BEAM PALETTE INFO CONSUMER (3/3) channels by type
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int paletteNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			BeamPalette palette = null;
			boolean isNew = false;
			
			try {
				palette = photon.beamPalettes.get(photon.dataUtility.findBeamPalette(paletteNum, index, uid));
			}catch(ObjectNotFoundException e) {
				palette = new BeamPalette(paletteNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Beam Palette Object created. BP #" + paletteNum);
			}
			
			try {
				palette.setByTypeChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			}catch(IndexOutOfBoundsException e) {
				palette.setByTypeChannelList(null);
			}
			
			if(isNew) {
				photon.beamPalettes.add(palette);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_BEAM_PALETTE_CHANNELS_BY_TYPE, beamPaletteChannelByTypeConsumer, true));
		
		Consumer<OSCMessage> curveInfoConsumer = message -> {								//CURVE INFO CONSUMER (1/1)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int curveNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Curve curve = null;
			boolean isNew = false;
			
			try {
				curve = photon.curves.get(photon.dataUtility.findCurve(curveNum, index, uid));
			}catch(ObjectNotFoundException e) {
				curve = new Curve(curveNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Curve Object created. Curve #" + curveNum);
			}
			
			curve.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			
			if(isNew) {
				photon.curves.add(curve);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_CURVE, curveInfoConsumer, true));
		
		Consumer<OSCMessage> effectInfoConsumer = message -> {								//EFFECT INFO CONSUMER (1/1)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			float effectNum = Float.parseFloat(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Effect effect = null;
			boolean isNew = false;
			
			try {
				effect = photon.effects.get(photon.dataUtility.findEffect(effectNum, index, uid));
			}catch(ObjectNotFoundException e) {
				effect = new Effect(effectNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Effect Object created. Effect #" + effectNum);
			}
			
			effect.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			effect.setEffectType(OscNumberInterpreter.oscToString(argList.get(3)));
			effect.setEntry(OscNumberInterpreter.oscToString(argList.get(4)));
			effect.setExit(OscNumberInterpreter.oscToString(argList.get(5)));
			effect.setDuration(OscNumberInterpreter.oscToString(argList.get(6)));
			effect.setScale((int) argList.get(7));
			
			if(isNew) {
				photon.effects.add(effect);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_EFFECT, effectInfoConsumer, true));
		
		Consumer<OSCMessage> snapshotInfoConsumer = message -> {							//SNAPSHOT INFO CONSUMER (1/1)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int snapshotNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Snapshot snapshot = null;
			boolean isNew = false;
			
			try {
				snapshot = photon.snapshots.get(photon.dataUtility.findSnapshot(snapshotNum, index, uid));
			}catch(ObjectNotFoundException e) {
				snapshot = new Snapshot(snapshotNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Snapshot Object created. Snapshot #" + snapshotNum);
			}
			
			snapshot.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			
			if(isNew) {
				photon.snapshots.add(snapshot);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_SNAPSHOT, snapshotInfoConsumer, true));
		
		Consumer<OSCMessage> pixelmapInfoConsumer = message -> {							//PIXELMAP INFO CONSUMER (1/2)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int pixelmapNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Pixelmap pixelmap = null;
			boolean isNew = false;
			
			try {
				pixelmap = photon.pixelmaps.get(photon.dataUtility.findPixelmap(pixelmapNum, index, uid));
			}catch(ObjectNotFoundException e) {
				pixelmap = new Pixelmap(pixelmapNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Pixelmap Object created. Pixelmap #" + pixelmapNum);
			}
			
			pixelmap.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			pixelmap.setServerChannel((int) argList.get(3));
			pixelmap.setInterface(OscNumberInterpreter.oscToString(argList.get(4)));
			pixelmap.setWidth((int) argList.get(5));
			pixelmap.setHeight((int) argList.get(6));
			pixelmap.setPixelCount((int) argList.get(7));
			pixelmap.setFixtureCount((int) argList.get(8));
			
			if(isNew) {
				photon.pixelmaps.add(pixelmap);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PIXELMAP, pixelmapInfoConsumer, true));
		
		Consumer<OSCMessage> pixelmapChannelConsumer = message -> {							//PIXELMAP INFO CONSUMER (2/2) channels
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int pixelmapNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			Pixelmap pixelmap = null;
			boolean isNew = false;
			
			try {
				pixelmap = photon.pixelmaps.get(photon.dataUtility.findPixelmap(pixelmapNum, index, uid));
			}catch(ObjectNotFoundException e) {
				pixelmap = new Pixelmap(pixelmapNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Pixelmap Object created. Pixelmap #" + pixelmapNum);
			}
			
			pixelmap.setLayerChannelList(OscNumberInterpreter.oscNumberToArray(argList.get(2)));
			
			if(isNew) {
				photon.pixelmaps.add(pixelmap);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_PIXELMAP_CHANNELS, pixelmapChannelConsumer, true));
		
		Consumer<OSCMessage> magicSheetInfoConsumer = message -> {							//MAGIC SHEET INFO CONSUMER (1/1)
			List<Object> argList = message.getArguments();
			String command = message.getAddress();
			String[] postIndex = command.split("/");
			
			int magicSheetNum = Integer.parseInt(postIndex[5]);
			int index = (int) argList.get(0);
			String uid = OscNumberInterpreter.oscToString(argList.get(1));
			
			MagicSheet magicSheet = null;
			boolean isNew = false;
			
			try {
				magicSheet = photon.magicSheets.get(photon.dataUtility.findMagicSheet(magicSheetNum, index, uid));
			}catch(ObjectNotFoundException e) {
				magicSheet = new MagicSheet(magicSheetNum, index, uid);
				isNew = true;
				Photon.log.info("OBJ: New Magic Sheet Object created. Magic Sheet #" + magicSheetNum);
			}
			
			magicSheet.setLabel(OscNumberInterpreter.oscToString(argList.get(2)));
			
			if(isNew) {
				photon.magicSheets.add(magicSheet);
			}
		};
		listeners.add(new OscListener(photon, OscIncoming.RETURN_MAGIC_SHEET, magicSheetInfoConsumer, true));
		
	}
	
	
	/*
	 * METHODS - REGISTRAR
	 */
	private void createConsumers() {
		createVersionListener();
		createCountListeners();
		createInfoListeners();
	}
	
	private void registerListeners() {
		for(int x = 0; x < listeners.size(); x++) {
			photon.interpreter.addOscListener(listeners.get(x));
		}
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public PhotonOscRoutines(Photon photonIn, int delayIn) {
		this.photon = photonIn;
		this.delay = delayIn;
		createConsumers();
		registerListeners();
	}
}