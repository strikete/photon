package com.strikete.photon.wtsahtsi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.strikete.photon.csv.CsvCueLevelParser;
import com.strikete.photon.objects.Group;
import com.strikete.photon.osc.OscInstance;

public class Wtsahtsi {

	/*
	 * VARIABLES
	 */
	private CsvCueLevelParser csv;
	private ArrayList<CueNumber> cueNumberList = new ArrayList<CueNumber>();
	private ArrayList<CueParagraph> cueParagraphs = new ArrayList<CueParagraph>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	public static int channelLimit;
	
	/*
	 * METHODS
	 */
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Wtsahtsi(String csvPath, int cuelistNum, OscInstance osc, int channelLimitIn) {
		groups = osc.getOscParser().getGroups();
		csv = new CsvCueLevelParser(csvPath, 1);
		
		//Remove numbers over the channel limit
		Wtsahtsi.channelLimit = channelLimitIn;
		
		cueNumberList = csv.getCueNumberList();
		
		for(int b = 0; b < cueNumberList.size(); b++) {
			System.out.println(cueNumberList.get(b).getCueNumber());
		}
		
		int cueSentCounter = 0; //TODO: delete temp for debug
		for(int x = 0; x < cueNumberList.size(); x++) {
			
			//Find cue levels matching the current cue number in processing
			ArrayList<CueLevel> tempCueLevels = new ArrayList<CueLevel>();
			for(int y = 0; y < csv.cueLevels.size(); y++) {
				if(csv.cueLevels.get(y).getCueNumber() == cueNumberList.get(x).getCueNumber() && csv.cueLevels.get(y).getCuePart() == cueNumberList.get(x).getCuePart()) {
					tempCueLevels.add(csv.cueLevels.get(y));
				}
			}
			
			ArrayList<CueSentence> tempCueSentences = new ArrayList<CueSentence>();
			//Process into cue sentences
			while(tempCueLevels.size() > 0) {
				CueSentence tempCS = new CueSentence(tempCueLevels.get(0).getCueNumberObj(),tempCueLevels.get(0).cueVariable);
				tempCS.addChannelNumber(tempCueLevels.get(0).getChannelNumber());
				tempCueLevels.remove(0);
				
				ArrayList<Integer> removeIndexi = new ArrayList<Integer>();
				//Find similar objects and add their channel levels to the temp cue sentence
				for(int z = 0; z < tempCueLevels.size(); z++) {
					if(tempCS.cueVariable.getIntensityLevel() == tempCueLevels.get(z).cueVariable.getIntensityLevel() &&
							tempCS.cueVariable.getIpId() == tempCueLevels.get(z).cueVariable.getIpId() &&
							tempCS.cueVariable.getFpId() == tempCueLevels.get(z).cueVariable.getFpId() &&
							tempCS.cueVariable.getCpId() == tempCueLevels.get(z).cueVariable.getCpId() &&
							tempCS.cueVariable.getBpId() == tempCueLevels.get(z).cueVariable.getBpId()) {
						tempCS.addChannelNumber(tempCueLevels.get(z).getChannelNumber());
						removeIndexi.add(z);
					}
				}
				//Remove cuelevels once added to a cue sentence
				for(int a = 0; a < removeIndexi.size(); a++) {
					tempCueLevels.remove(removeIndexi.get(a));
				}
				
				//add the cueSentence to the arraylist
				tempCueSentences.add(tempCS);
				cueSentCounter++;
				
			}//end while loop
			cueParagraphs.add(new CueParagraph(cueNumberList.get(x),tempCueSentences));
			System.out.println("Processed CueSentences for Cue: " + tempCueSentences.get(0).getCueNumber());
			System.out.println("Total Cue Sentences: " + cueSentCounter);
		}//End processing looop
		
		ArrayList<Float> colorPalettesUsed = new ArrayList<Float>();
		ArrayList<ColorPaletteCount> colorPaletteCounts = new ArrayList<ColorPaletteCount>();
		
		for(int j = 0; j < csv.cueLevels.size(); j++) {
			if(!colorPalettesUsed.contains(csv.cueLevels.get(j).cueVariable.getCpId())) {
				colorPalettesUsed.add(csv.cueLevels.get(j).cueVariable.getCpId());
				colorPaletteCounts.add(new ColorPaletteCount(csv.cueLevels.get(j).cueVariable.getCpId()));
			}else {
				for(int b = 0; b < colorPaletteCounts.size(); b++) {
					if(colorPaletteCounts.get(b).getColorPaletteId() == csv.cueLevels.get(j).cueVariable.getCpId()) {
						colorPaletteCounts.get(b).incrementHits();
					}
				}
			}
		}
		
		colorPaletteCounts.sort(Comparator.comparing(ColorPaletteCount::getColorPaletteId));
		for(int k = 0; k < colorPaletteCounts.size(); k++) {
			colorPaletteCounts.get(k).findName(osc);
			colorPaletteCounts.get(k).print();
		}
		
		System.out.println("wah");
		
	}
}
