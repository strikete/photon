package com.strikete.photon.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * VARIABLES
	 */
	private static final long serialVersionUID = 7112843241263143043L;

	
	/*
	 * CONSTRUCTOR
	 */
	public ObjectNotFoundException(String message) {
		super(message);
	}
}