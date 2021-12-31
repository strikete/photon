package com.strikete.photon.utils;

import java.util.ArrayList;

public class OscNumberInterpreter {

	/*
	 * VARIABLES
	 */
	
	
	
	/*
	 * METHODS
	 */
	public static ArrayList<Integer> oscNumberToArray(Object input) {
		//Declare the array we plan on returning
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		
		try { //Attempt to parse as an individual integer
			int number = (Integer) input;
			intArray.add(number);
		}catch(NumberFormatException | ClassCastException e) {
			
			String rawString = (String) input; //Convert the input to a String
			if(rawString.contains("-") && !rawString.contains(",")) { //FOR ONLY HYPENATED SEPERATED VALUES
				String[] postIndex = rawString.split("-");
				int firstNum = Integer.parseInt(postIndex[0]);
				int secondNum = Integer.parseInt(postIndex[1]);
				
				//Find the highest number
				int higherNum;
				int lowerNum;
				if(firstNum >= secondNum) {
					higherNum = firstNum;
					lowerNum = secondNum;
				}else {
					higherNum = secondNum;
					lowerNum = firstNum;
				}
				
				//Calculate the space inbetween
				for(int x = lowerNum; x <= higherNum; x++) {
					intArray.add(x);
				}
				return intArray;
			}else if(rawString.contains(",")) {	//FOR COMMA SEPERATED VALUES
				String[] postIndex = rawString.split(",");
				for(int x = 0; x < postIndex.length; x++) {
					if(postIndex[x].contains("-")) { //FOR COMMA AND HYPHENATED SEPERATED VALUES
						String[] postIndex2 = rawString.split("-");
						int firstNum = Integer.parseInt(postIndex2[0]);
						int secondNum = Integer.parseInt(postIndex2[1]);
						
						//Find the highest number
						int higherNum;
						int lowerNum;
						if(firstNum >= secondNum) {
							higherNum = firstNum;
							lowerNum = secondNum;
						}else {
							higherNum = secondNum;
							lowerNum = firstNum;
						}
						
						//Calculate the space inbetween
						for(int y = lowerNum; y <= higherNum; y++) {
							intArray.add(y);
						}
					}else {
						intArray.add(Integer.parseInt(postIndex[x]));
					}
				}
				return intArray;
			}
		}
		return null;
	}	
	
	public static float oscNumberToFloat(Object input) {
		float returnValue;
		try {
			returnValue = (float) input;
		}catch(ClassCastException e) {
			int tempInt = (int) input;
			returnValue = tempInt;
		}
		return returnValue;
	}
	
	public static String oscToString(Object input) {
		String returnValue;
		try {
			returnValue = (String) input;
		}catch(ClassCastException e) { //Probably needs to handle floats, too
			int tempInt = (int) input;
			returnValue = String.valueOf(tempInt);
		}
		return returnValue;
	}
	
	public static int oscNumberToInt(Object input) {
		int returnValue;
		try {
			returnValue = (int) input;
		}catch(ClassCastException e) {
			String tempStr = (String) input;
			returnValue = Integer.valueOf(tempStr);
		}
		return returnValue;
	}
}