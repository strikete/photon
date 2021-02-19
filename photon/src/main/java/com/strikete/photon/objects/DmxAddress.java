package com.strikete.photon.objects;

import com.strikete.photon.objects.DmxUniverse.DmxOutput;

public class DmxAddress {
	
	private int addressNum = 0; //Address is an int. Addresses above 512 can be converted into universe/address
	private DmxUniverse universe; //Universe is a int based on the sACN and Art-Net protocols
	
	/*
	* METHODS
	*/
	
	private void pushAddress(int addressIn) { //Checks if an address is valid and then pushes it or throws an error
		if(addressIn <= 512) { //If the universe is provided, the address must be under 512. And not 0.
			this.addressNum = addressIn;
		}else {
			throw new IllegalArgumentException("DmxAddress Object Error: "
					+ "Addresses with a provided universe must not exceed 512.");
		}
	}
	
	public DmxUniverse getUniverse() {
		return this.universe;
	}
	public int getAddressNumber() {
		return this.addressNum;
	}
	
	//No setter methods. Address Objects should be left for garbage collection if you need to change either the universe or the address number.
	
	/*
	* CONSTRUCTORS
	*/
	public DmxAddress(DmxUniverse universeIn, int addressIn) {
		this.universe = universeIn;
		pushAddress(addressIn);
	}
	
	public DmxAddress(int universeNum, DmxOutput dmxOutput, int addressIn) {
		this.universe = new DmxUniverse(universeNum, dmxOutput);
		pushAddress(addressIn);
	}
	
	public DmxAddress(int universeNum, int addressIn) {
		this.universe = new DmxUniverse(universeNum);
		pushAddress(addressIn);
	}
	public DmxAddress(int addressIn) {
		if(addressIn > 512) { //If the address is over 512
			int universeNum;
			for (universeNum = 1; addressIn >= 512;universeNum++) { //Find the universe and address
				addressIn = addressIn - 512;
			}
			this.addressNum = addressIn;
			this.universe = new DmxUniverse(universeNum);
		}else { //Otherwise just record it as the default
			this.universe = new DmxUniverse(1);
			this.addressNum = addressIn;
		}
	}
}
