package com.strikete.photon.objects;

import java.util.ArrayList;

public class Fixture implements ParameterFieldSetter {

	/*
	 * VARIABLES
	 */
	private String fixtureName;
	private String manufacturerName;
	private ArrayList<String> parameterShort = new ArrayList<String>();
	private ArrayList<String> parameterLong = new ArrayList<String>();
	private ArrayList<String> parameterCategory = new ArrayList<String>();
	
	
	/*
	 * METHODS - GETTERS
	 */
	public String getFixtureName() {
		return fixtureName;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public ArrayList<String> getParameterShort() {
		return parameterShort;
	}
	public ArrayList<String> getParameterLong(){
		return parameterLong;
	}
	public ArrayList<String> getParameterCategory(){
		return parameterCategory;
	}
	
	
	/*
	 * METHODS - SETTERS
	 */
	public void setParameterShort(ArrayList<String> parameterShortIn) {
		this.parameterShort = parameterShortIn;
	}
	public void setParameterLong(ArrayList<String> parameterLongIn) {
		this.parameterLong = parameterLongIn;
	}
	public void setParameterCategory(ArrayList<String> parameterCategoryIn) {
		this.parameterCategory = parameterCategoryIn;
	}
	
	
	/*
	 * METHODS - INTERFACES
	 */
	@Override
	public void setParameters(String parameterShortIn, String parameterLongIn, String parameterCategoryIn) {
		this.parameterShort.add(parameterShortIn);
		this.parameterLong.add(parameterLongIn);
		this.parameterCategory.add(parameterCategoryIn);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public Fixture(String fixtureNameIn, String manufacturerNameIn) {
		this.fixtureName = fixtureNameIn;
		this.manufacturerName = manufacturerNameIn;
	}
}