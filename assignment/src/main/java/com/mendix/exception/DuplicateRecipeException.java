package com.mendix.exception;

public class DuplicateRecipeException extends Exception{
	private static final long serialVersionUID = -976676802051046124L;

	public DuplicateRecipeException(String error) {
		super(error);
	}
}
