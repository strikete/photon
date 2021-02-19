package com.strikete.photon.objects;

import com.strikete.photon.Main;

public class DmxUniverse {

	/*
	 * VARIABLES
	 */
	
	public enum DmxOutput {
		ARTNET_UNICAST,ARTNET_BROADCAST,SACN,RS485,OTHER,NULL
	}
	
	private DmxOutput dmxOutput = DmxOutput.NULL;
	private int universeNum = 0; //The universe number is a int based on the sACN and Art-Net protocols.
	
	/*
	 * METHODS
	 */
	
	public DmxOutput getDmxOutput() {
		return this.dmxOutput;
	}
	public int getUniverseNum() {
		return this.universeNum;
	}
	
	public void setDmxOutput(DmxOutput output) {
		this.dmxOutput = output;
	}
	public void setUniverseNum(int universeNumIn) {
		this.universeNum = universeNumIn;
	}
	
	
	/*
	 * CONSTRUCTORS
	 */
	
	public DmxUniverse(int universeNumIn, DmxOutput outputIn) {
		this.dmxOutput = outputIn;
		this.universeNum = universeNumIn;
	}
	public DmxUniverse(int universeNumIn) {
		this.dmxOutput = DmxOutput.NULL;
		this.universeNum = universeNumIn;
	}
	public DmxUniverse() {
		this.dmxOutput = DmxOutput.NULL;
		this.universeNum = 1;
		Main.log.warn("No args on DmxUniverse Object creation. Defaulting to 1.");
	}
}
