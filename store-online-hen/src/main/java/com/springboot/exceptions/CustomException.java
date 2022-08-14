package com.springboot.exceptions;

public class CustomException extends IllegalArgumentException {
   
	private static final long serialVersionUID = 8983130986770435329L;

	public CustomException(String msg) {
        super(msg);
    }
}