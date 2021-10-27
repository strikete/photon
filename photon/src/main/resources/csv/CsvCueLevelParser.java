package com.strikete.photon.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.strikete.photon.Main;
import com.strikete.photon.wtsahtsi.CueLevel;
import com.strikete.photon.wtsahtsi.CueNumber;

public class CsvCueLevelParser {

	/*
	 * VARIABLES
	 */
	private Reader reader;
	public ArrayList<CueLevel> cueLevels = new ArrayList<CueLevel>();
	
	/*
	 * METHODS
	 */
	
	public int getCueSize() {
		ArrayList<Float> cueNumberList = new ArrayList<Float>();
		for(int x = 0; x < cueLevels.size(); x++) {
			if(!cueNumberList.contains(cueLevels.get(x).getCueNumber())) {
				cueNumberList.add(cueLevels.get(x).getCueNumber());
			}
		}
		return cueNumberList.size();
	}
	public ArrayList<CueNumber> getCueNumberList(){
		ArrayList<CueNumber> cueNumberList = new ArrayList<CueNumber>();
		cueNumberList.add(cueLevels.get(0).getCueNumberObj());
		for(int x = 1; x < cueLevels.size(); x++) {
			boolean needToAdd = true;
			for(int y = 0; y < cueNumberList.size(); y++) {
				if(cueLevels.get(x).getCueNumber() == cueNumberList.get(y).getCueNumber() && cueLevels.get(x).getCuePart() == cueNumberList.get(y).getCuePart()) {
					needToAdd = false;
				}
			}
			if(needToAdd) {
				cueNumberList.add(cueLevels.get(x).getCueNumberObj());
			}
		}
		return cueNumberList;
	}
	public boolean doesCueLevelExist(float cueNumber, int channelNumber) {
		for(int x = 0; x < cueLevels.size(); x++) {
			if(cueNumber == cueLevels.get(x).getCueNumber() && channelNumber == cueLevels.get(x).getChannelNumber()) {
				return true;
			}
		}
		return false;
	}
	public boolean doesCueLevelWithPartExist(float cueNumber, int cuePart, int channelNumber) {
		for(int x = 0; x < cueLevels.size(); x++) {
			if(cueNumber == cueLevels.get(x).getCueNumber() && channelNumber == cueLevels.get(x).getChannelNumber() && cuePart == cueLevels.get(x).getCuePart()) {
				return true;
			}
		}
		return false;
	}
	public int getCueLevelIndex(float cueNumber, int channelNumber) {
		for(int x = 0; x < cueLevels.size(); x++) {
			if(cueNumber == cueLevels.get(x).getCueNumber() && channelNumber == cueLevels.get(x).getChannelNumber()) {
				return x;
			}
		}
		//Main.log.error("CSV PARSER: Could not find corresponding CueLevel, RETURNING ZERO!");
		return 0;
	}
	public int getCueLevelWithPartIndex(float cueNumber, int cuePart, int channelNumber) {
		for(int x = 0; x < cueLevels.size(); x++) {
			if(cueNumber == cueLevels.get(x).getCueNumber() && channelNumber == cueLevels.get(x).getChannelNumber() && cuePart == cueLevels.get(x).getCuePart()) {
				return x;
			}
		}
		//Main.log.error("CSV PARSER: Could not find corresponding CueLevel, RETURNING ZERO!");
		return 0;
	}
	
	/*
	 * CONSTRUCTOR
	 */
	@SuppressWarnings("deprecation")
	public CsvCueLevelParser(String path, int cuelistNumber) {
		try {
			this.reader = new FileReader(path);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
			
			for (CSVRecord record : records) {
			
			int targetCuelistNumber = Integer.parseInt(record.get(CsvCueLevelHeaders.TARGET_LIST_NUMBER));	
			if(cuelistNumber == targetCuelistNumber) {
		        float cueNumber = Float.parseFloat(record.get(CsvCueLevelHeaders.TARGET_ID));
		        int channelNumber = Integer.parseInt(record.get(CsvCueLevelHeaders.CHANNEL));
		        if(channelNumber > 10000) { //Get out of here part channels
		        	channelNumber = channelNumber / 10000;
		        }
		        String cuePartText = record.get(CsvCueLevelHeaders.TARGET_PART_NUMBER);
		        
		        int index;
		        
		        if(cuePartText.isEmpty()) {//If theres no cue part...
		        	if(!doesCueLevelExist(cueNumber, channelNumber)) {
		        		cueLevels.add(new CueLevel(cueNumber, channelNumber));
		        	}
		        	index = getCueLevelIndex(cueNumber,channelNumber);
		        }else { //If there IS a cue part
		        	int cuePartNumber = Integer.parseInt(cuePartText);
		        	if(!doesCueLevelWithPartExist(cueNumber,cuePartNumber,channelNumber)) {
		        		CueLevel cuelevelTemp = new CueLevel(cueNumber, channelNumber);
		        		cuelevelTemp.setCuePart(cuePartNumber);
		        		cueLevels.add(cuelevelTemp);
		        	}
		        	index = getCueLevelWithPartIndex(cueNumber,cuePartNumber,channelNumber);
		        }
		        
		        //Find intensity parameter
		        String parameter = record.get(CsvCueLevelHeaders.PARAMETER_TYPE_AS_TEXT);
		        String levelString = record.get(CsvCueLevelHeaders.LEVEL);
		        if(parameter.equals("Intens") && !levelString.isEmpty()) {
		        	int level = Integer.parseInt(levelString);
		        	cueLevels.get(index).cueVariable.setIntensityLevel(level);
		        }
		        
		        //Find level references
		        String levelReferenceType = record.get(CsvCueLevelHeaders.LEVEL_REFERENCE_TYPE_AS_TEXT);
		        if(!levelReferenceType.isEmpty()) {
		        	float levelReferenceId = Float.parseFloat(record.get(CsvCueLevelHeaders.LEVEL_REFERENCE_ID));
		        	
		        	if(levelReferenceType.contains("Preset")) {
		        		cueLevels.get(index).cueVariable.setPresetId(levelReferenceId);
		        	}else if(levelReferenceType.contains("Intens")) {
		        		cueLevels.get(index).cueVariable.setIntensityPaletteId(levelReferenceId);
		        	}else if(levelReferenceType.contains("Focus")) {
		        		cueLevels.get(index).cueVariable.setFocusPaletteId(levelReferenceId);
		        	}else if(levelReferenceType.contains("Color")) {
		        		cueLevels.get(index).cueVariable.setColorPaletteId(levelReferenceId);
		        	}else if(levelReferenceType.contains("Beam")) {
		        		cueLevels.get(index).cueVariable.setBeamPaletteId(levelReferenceId);
		        	}
		        }
			}  
		    }
			
		} catch (FileNotFoundException e) {
			//Main.log.error("CSV PARSER: File not found!",e);
		} catch (IOException e) {
			//Main.log.error("CSV PARSER: IO Exception!",e);
		}
		
	}
}
