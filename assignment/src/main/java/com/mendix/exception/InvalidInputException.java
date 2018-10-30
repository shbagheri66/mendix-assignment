package com.mendix.exception;

public class InvalidInputException extends Exception {
	private static final long serialVersionUID = -1275966445183481019L;

	public InvalidInputException(String error) {
		super(error);
	}
	
	public InvalidInputException(String field,String error) {
		super(String.format("Error in field %s : %s",field,error));
	}
}
