package com.strikete.photon.osc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCSerializeException;
import com.illposed.osc.transport.udp.OSCPortOut;
import com.strikete.photon.Main;
import com.strikete.photon.objects.Channel;

public class OscSender {
	//Object used to send variable Osc Messages
	
	private OscMap oscmap;
	private OSCPortOut sender;
	private InetAddress targetIp;
	private int targetPort;
	private int messagesSent = 0;
	
	/*
	 * METHODS - COMMON TASKS - GET COUNTS
	 */
	public void getVersion() {
		sendOscMessage(oscmap.GET_VERSION);
	}
	public void getPatchCount() {
		sendOscMessage(oscmap.GET_PATCH_COUNT);
	}
	public void getCuelistCount() {
		sendOscMessage(oscmap.GET_CUELIST_COUNT);
	}
	public void getGroupCount() {
		sendOscMessage(oscmap.GET_GROUP_COUNT);
	}
	public void getMacroCount() {
		sendOscMessage(oscmap.GET_MACRO_COUNT);
	}
	public void getSubCount() {
		sendOscMessage(oscmap.GET_SUB_COUNT);
	}
	public void getPresetCount() {
		sendOscMessage(oscmap.GET_PRESET_COUNT);
	}
	public void getIntensityPaletteCount() {
		sendOscMessage(oscmap.GET_INTENSITY_PALETTE_COUNT);
	}
	public void getFocusPaletteCount() {
		sendOscMessage(oscmap.GET_FOCUS_PALETTE_COUNT);
	}
	public void getColorPaletteCount() {
		sendOscMessage(oscmap.GET_COLOR_PALETTE_COUNT);
	}
	public void getBeamPaletteCount() {
		sendOscMessage(oscmap.GET_BEAM_PALETTE_COUNT);
	}
	public void getEffectcount() {
		sendOscMessage(oscmap.GET_EFFECT_COUNT);
	}
	
	/*
	 * METHODS - COMMON TASKS - GET INFO
	 */
	public void getPatchInfo(int indexNumber) {
		sendOscMessage(oscmap.GET_PATCH_INFO, indexNumber);
	}
	
	/*
	 * METHODS - COMMON TASKS - UPDATE CHANNEL
	 */
	public void selectChannel(int channelNum) {
		sendOscMessage(oscmap.SELECT_CHANNEL + channelNum);
		sendOscMessage(oscmap.KEY_ENTER);
	}
	public void selectChannel(Channel channelIn) {
		sendOscMessage(oscmap.SELECT_CHANNEL + channelIn.getChannelNum());
		sendOscMessage(oscmap.KEY_ENTER);
	}
	
	
	/*
	 * METHODS - GETTERS
	 */
	public int getMessageSentCount() {
		return this.messagesSent;
	}
	public InetAddress getTargetIp() {
		return this.targetIp;
	}
	public int getTargetPort() {
		return this.targetPort;
	}
	
	/*
	 * METHODS - INITALIZATION
	 */
	
	private void initalizeOscSender() {
		try {
			this.sender = new OSCPortOut(this.targetIp,this.targetPort);
		} catch (IOException e) {
			Main.log.error("OscSender ERROR: IOException on OSCPortOut Object initalization.",e);
		}
	}
	
	/*
	 * METHODS - LOWER LEVEL OSC MESSAGE SENDING
	 */
	
	public synchronized void sendOscMessage(OSCMessage message) {
		try {
			this.sender.send(message);
		} catch (IOException e) {
			Main.log.error("OscSender ERROR: IOException encountered on sendOscMessage.",e);
		} catch (OSCSerializeException e) {
			Main.log.error("OscSender ERROR: OSCSerializeException on sendOscMessage.",e);
		}
		this.messagesSent++; //Count another message sent
	}
	public void sendOscMessage(String address, List<Object> arguments) { //For sending an address & List<Object>
		OSCMessage message = new OSCMessage(address,arguments);
		this.sendOscMessage(message);
	}
	public void sendOscMessage(String address, String argument) { //For sending an address & String argument
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		OSCMessage message = new OSCMessage(address,arguments);
		this.sendOscMessage(message);
	}
	public void sendOscMessage(String address, int argument) { //For sending an address & integer argument
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(argument);
		OSCMessage message = new OSCMessage(address,arguments);
		this.sendOscMessage(message);
	}
	public void sendOscMessage(String address) { //For sending just an address
		OSCMessage message = new OSCMessage(address);
		this.sendOscMessage(message);
	}
	
	/*
	 * CONSTRUCTORS
	 */
	
	public OscSender(OscMap oscmapIn, InetAddress targetIpIn, int targetPortIn) {
		this.oscmap = oscmapIn;
		this.targetIp = targetIpIn;
		this.targetPort = targetPortIn;
		initalizeOscSender();
	}
	public OscSender(OscMap oscmapIn, String targetIpStringIn, int targetPortIn) {
		this.oscmap = oscmapIn;
		this.targetPort = targetPortIn;
		
		//Catch block for Unknown Host Exception
		try {
			this.targetIp = InetAddress.getByName(targetIpStringIn);
		} catch (UnknownHostException e) {
			Main.log.error("OscSender ERROR: UnknownHostException on object creation. Use a valid IP address.",e);
		}
		initalizeOscSender();
	}
	
}
