package com.strikete.photon.wtsahtsi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.strikete.photon.objects.Group;

public class CueSentence {

	/*
	 * VARIABLES
	 */
	private CueNumber cueNumberObj;
	private ArrayList<Integer> channelNumbers = new ArrayList<Integer>();
	public CueVariables cueVariable;
	
	
	/*
	 * METHODS - SETTERS
	 */
	
	public void addChannelNumber(int channelNumberIn) {
		if(channelNumberIn < Wtsahtsi.channelLimit) {
			channelNumbers.add(channelNumberIn);
		}
	}
	
	/*
	 * METHODS - GETTERS
	 */
	public float getCueNumber() {
		return cueNumberObj.getCueNumber();
	}
	public boolean getCuePartBoolean() {
		return cueNumberObj.getCuePartBoolean();
	}
	public int getCuePart() {
		return cueNumberObj.getCuePart();
	}
	public CueNumber getCueNumberObj() {
		return cueNumberObj;
	}
	
	/*
	 * METHODS
	 */
	
	private boolean doesContain(Integer[] outer, Integer[] inner) {
		return Arrays.asList(outer).containsAll(Arrays.asList(inner));
	}
	
	public String processString(ArrayList<Group> groups) {
		String theWords = "";
		
		ArrayList<Group> containerHits = new ArrayList<Group>();
		ArrayList<Integer> workingChannels = channelNumbers;
		
		//Sort channels in order
		Collections.sort(workingChannels);
		
		//Place the working channels into an array.
		int workChanArraySize = workingChannels.size();
		Integer[] workChanArray = new Integer[workChanArraySize];
		workChanArray = workingChannels.toArray(workChanArray);
		
		for(int a = 0; a < groups.size(); a++) {
			
			//Place the group working group's channels into an array.
			int groupArraySize = groups.get(a).getChannelSize();
			Integer[] tempGroupArray = new Integer[groupArraySize];
			tempGroupArray = groups.get(a).getChannelsAsInt().toArray(tempGroupArray);
			//See if the group is applicable to this move
			if(doesContain(workChanArray,tempGroupArray)) {
				containerHits.add(groups.get(a));
			}
		}
		
		//Sort groups by biggest
		containerHits.sort(Comparator.comparing(Group::getChannelSize));
		
		//Process into String
		
		//PROCESS GROUPS
		boolean firstListing = true;
		while(containerHits.size() > 0) {  
			
			//Process selected group into an array.
			int groupArraySize = containerHits.get(containerHits.size()-1).getChannelSize();
			Integer[] tempGroupArray = new Integer[groupArraySize];
			tempGroupArray = containerHits.get(containerHits.size()-1).getChannelsAsInt().toArray(tempGroupArray);
			if(doesContain(workChanArray,tempGroupArray)) {
				if(firstListing) {
					theWords = theWords + "Group " + containerHits.get(containerHits.size()-1).getGroupNum();
					firstListing = false;
				}else {
					theWords = theWords + " + Group " + containerHits.get(containerHits.size()-1).getGroupNum();
				}
				
				//Remove added groups channels from the workingChannels arraylist
				for(int b = 0; b < groupArraySize; b++) {
					int removeMe = tempGroupArray[b];
					for(int c = 0; c < workingChannels.size(); c++) {
						if(workingChannels.get(c) == removeMe) {
							workingChannels.remove(c);
						}
					}
				}
				//Remove group from the containerHits arraylist
				containerHits.remove(containerHits.size()-1);
			}else {
				//Remove no-longer needed groups from the containerHits arraylist
				containerHits.remove(containerHits.size()-1);
			}
		}
		
		//PROCESS CHANNELS
		boolean thruBool = false;
		int thruCounter = 0;
		int thruTopChan = 0;
		int thruBottomChan = 0;
		while(workingChannels.size() > 0) {
			//See if we're detecting a thru area, and if not try to detect one.
			if(thruBottomChan == 0 || !thruBool) {
				thruBottomChan = workingChannels.get(0);
				thruCounter = 0;
				thruBool = true;
			}
			
			//Get next variable
			int nextVariable = -2;
			try {
				nextVariable = workingChannels.get(thruCounter+1);
			}catch(IndexOutOfBoundsException e) {
				nextVariable = -2;
			}
			
			//If the thru is continuous...
			if((thruBottomChan+thruCounter+1) == nextVariable) {
				thruCounter++;
			}else {
				if(thruBool && thruCounter > 0) { //If a thru needs to be inserted
					thruTopChan = workingChannels.get(thruCounter);
					int loopNum = thruTopChan - thruBottomChan;
					System.out.println("TOP CHAN: " + thruTopChan + " - BOT CHAN: " + thruBottomChan);
					for(int c = 0; c < loopNum; c++) {
							workingChannels.remove(c);
					}
					//Add words
					if(firstListing) {
						theWords = theWords + thruBottomChan + " thru " + thruTopChan;
						firstListing = false;
					}else {
						theWords = theWords + " + " + thruBottomChan + " thru " + thruTopChan;
					}
				}else {//Individual channel add
					if(firstListing) {
						theWords = theWords + workingChannels.get(0);
						workingChannels.remove(0);
						firstListing = false;
					}else {
						theWords = theWords + " + " + workingChannels.get(0);
						workingChannels.remove(0);
					}
				}
				thruBool = false;
			}
		}
		
		//Add variable changes
		
		return theWords;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public CueSentence(CueNumber cnIn, CueVariables cvIn) {
		this.cueNumberObj = cnIn;
		cueVariable = cvIn;
	}
}
