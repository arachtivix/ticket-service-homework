package com.walmart.ticketservice;

public class InvalidEmailException extends RuntimeException {

	public InvalidEmailException(String message){
		super(message);
	}
	
}
