package com.strikete.photon.utils;

import com.strikete.photon.Main;
import com.strikete.photon.objects.Channel;
import com.strikete.photon.objects.Cue;
import com.strikete.photon.objects.Cuelist;
import com.strikete.photon.objects.Effect;

public class SearchMatch {

	
	public static Channel findChannel(Channel[] channels, int channelCount, int channelNum) {				//FIND CHANNEL FROM NUMBER
		int limit = channelCount;
		try {
			for(int x = 0; x < limit; x++) {
				if(channels[x].getChannelNum() == channelNum) {
					return(channels[x]);
				}
			}
		} catch(NullPointerException e) {
			
		}
		Main.log.error("SearchMatch ERROR: No Channel found matching that number! CHANNEL NUM: " + channelNum);
		return null;
	}
	
	public static Effect findEffect(Effect[] effects, int effectCount, float f) {					//FIND EFFECT FROM NUMBER
		int limit = effectCount;
		try {
			for(int x = 0; x < limit; x++) {
				if(effects[x].getEffectNum() == f) {
					return(effects[x]);
				}
			}
		} catch(NullPointerException e) {
			
		}
		Main.log.error("SearchMatch ERROR: No Effect found matching that number! EFFECT NUM: " + f);
		return null;
	}
	
	public static int findCuelistIndex(Cuelist[] cuelists, int cuelistCount, int cuelistNum) {
		int limit = cuelistCount;
		try {
			for(int x = 0; x < limit; x++) {
				if(cuelists[x].getCuelistNumber() == cuelistNum) {
					return x;
				}
			}
		} catch(NullPointerException e) {
			
		}
		Main.log.error("SearchMatch ERROR: No Cuelist found matching that number! CUELIST NUM: " + cuelistNum);
		return 0;
	}
	
	public static Cue findCueFromCuelist(Cuelist cuelist, float cueNum) {					//FIND CUE FROM NUMBER & CUELIST
		Cue[] cues = cuelist.getCues();
		int limit = cuelist.getCueCount();
		try {
			for(int x = 0; x < limit; x++) {
				if(cues[x].getCueNumber() == cueNum) {
					return(cues[x]);
				}
			}
		} catch(NullPointerException e) {
			
		}
		Main.log.error("SearchMatch ERROR: No Cue found matching that number! CUE NUM: " + cueNum + " IN CUELIST: " + cuelist.getCuelistNumber());
		return null;
	}
	
	public static int findCueIndexFromCuelist(Cuelist cuelist, float cueNum) {				//FIND CUE INDEX NUMBER FROM CUELIST
		Cue[] cues = cuelist.getCues();
		int limit = cuelist.getCueCount();
		try {
			for(int x = 0; x < limit; x++) {
				if(cues[x].getCueNumber() == cueNum) {
					return x;
				}
			}
		} catch(NullPointerException e) {
			
		}
		Main.log.error("SearchMatch ERROR: No Cue found matching that number! CUE NUM: " + cueNum + " IN CUELIST: " + cuelist.getCuelistNumber());
		return 0;
	}
	
	public static Cue findCuePartFromCue(Cue cue, float cuePartNum) {						//FIND CUE PART FROM CUE USING NUMBER
		Cue[] partCues = cue.getCueParts();
		int limit = cue.getPartCount();
		try {
			for(int x = 0; x < limit; x++) {
				if(partCues[x].getCueNumber() == cuePartNum) {
					return partCues[x];
				}
			}
		} catch(NullPointerException e) {
			
		}
		Main.log.error("SearchMatch ERROR: No Cue Part found matching that number! CUE PART NUM: " + cuePartNum + " CUE NUM: " + cue.getCueNumber() + " IN CUELIST: " + cue.getParentCuelist().getCuelistNumber());
		return null;
	}
	
	public static int findCuePartIndexFromCue(Cue cue, float cuePartNum) {					//FIND CUE PART INDEX FROM CUE
		Cue[] partCues = cue.getCueParts();
		int limit = cue.getPartCount();
		try {
			for(int x = 0; x < limit; x++) {
				if(partCues[x].getCueNumber() == cuePartNum) {
					return x;
				}
			}
		} catch(NullPointerException e) {
		 
		}
		Main.log.error("SearchMatch ERROR: No Cue Part found matching that number! CUE PART NUM: " + cuePartNum + " CUE NUM: " + cue.getCueNumber() + " IN CUELIST: " + cue.getParentCuelist().getCuelistNumber());
		return 0;
	}
	
	//MISC METHODS
	
	
	
}
