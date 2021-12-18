package com.strikete.photon.temp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.strikete.photon.Photon;

public class BeatProcessor {

	/*
	 * VARIABLES
	 */
	private static ArrayList<BeatTime> beatTimes = new ArrayList<BeatTime>();
	private Photon photon;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	/*
	 * METHODS
	 */
	
	public static void readFile() throws IOException {
		String path = "C:\\Users\\Rusettsten\\Desktop\\sample-data.txt";
		
		//Open File
		FileInputStream fstream = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		String strLine;
		int currentSample = 0;
		float amplitudeThreshold = (float) -3.000;
		float amplitudeFloor = (float) -49.000;
		boolean activeBeat = false;
		int activeBeatStartingSample = 0;
		
		while((strLine = br.readLine()) != null) {
			currentSample++;
			
			float lineAmplitude = Float.parseFloat(strLine);
			if(activeBeat) {
				if(lineAmplitude < amplitudeFloor) {
					beatTimes.add(new BeatTime(activeBeatStartingSample, currentSample));
					activeBeat = false;
				}
			}else {
				if(lineAmplitude >= amplitudeThreshold) {
					activeBeatStartingSample = currentSample;
					activeBeat = true;
				}
			}
		}
		System.out.println(beatTimes.size());
	}
	
	
	public void setupCueRecording() {
		photon.sender.sendOscMessage("/eos/key/blind");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recordCues() {
		photon.sender.sendOscMessage("/eos/cmd","Cue 1 ##");
		photon.sender.sendOscMessage("/eos/newcmd", "Chan 1 Thru 3 @ 0 #");
		photon.sender.sendOscMessage("/eos/newcmd","Cue 1 Time 0 #");
		
		int cueCount = 1;
		float oldCueTime = 0;
		
		for(int x = 0; x < beatTimes.size(); x++) {
			String oldCueNum = Integer.toString(cueCount);
			cueCount++;
			float oldFollowTime = (beatTimes.get(x).startSeconds-oldCueTime);
			String senderMessageOldFollow = "Cue " + oldCueNum + " Follow " + df.format(oldFollowTime) + " #";
			photon.sender.sendOscMessage("/eos/newcmd",senderMessageOldFollow);
			
			String cueNum = Integer.toString(cueCount); //New Cue Creation
			String senderMessage = "Cue " + cueNum + " ##";
			photon.sender.sendOscMessage("/eos/newcmd",senderMessage);
			photon.sender.sendOscMessage("/eos/newcmd","Chan 1 Thru 3 @ 100 #");
			String senderMessage2 = "Cue " + cueNum + " Time 0.0 #";
			photon.sender.sendOscMessage("/eos/newcmd", senderMessage2);
			float followTime = (beatTimes.get(x).endSeconds - beatTimes.get(x).startSeconds);
			String senderMessage3 = "Cue " + cueNum + " Follow " + df.format(followTime) + " #";
			photon.sender.sendOscMessage("/eos/newcmd", senderMessage3);
			
			cueCount++;
			cueNum = Integer.toString(cueCount); //New Cue Creation
			senderMessage = "Cue " + cueNum + " ##";
			photon.sender.sendOscMessage("/eos/newcmd",senderMessage);
			photon.sender.sendOscMessage("/eos/newcmd","");
			photon.sender.sendOscMessage("/eos/newcmd","Chan 1 Thru 3 @ 0 #");
			senderMessage2 = "Cue " + cueNum + " Time 0.6 #";
			oldCueTime = beatTimes.get(x).endSeconds;
			photon.sender.sendOscMessage("/eos/newcmd",senderMessage2);
			
		}
		
	}
	
	public float findAverageBeatTime() {
		ArrayList<Float> averageList = new ArrayList<Float>();
		for(int x = 1; x < beatTimes.size(); x++) {
			float time = beatTimes.get(x).startSeconds-beatTimes.get(x-1).startSeconds;
			if(time <= 1) {
				averageList.add(time);
			}
		}
		double averageBig = 0;
		for(int y = 0; y < averageList.size(); y++) {
			averageBig = averageBig + averageList.get(y);
		}
		return (float) (averageBig / averageList.size());
	}
	
	/*
	 * CONSTRUCTOR
	 */ 
	public BeatProcessor(Photon photonIn) {
		this.photon = photonIn;
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setupCueRecording();
		System.out.println(findAverageBeatTime());
		
		recordCues();
		
	}
	
}
