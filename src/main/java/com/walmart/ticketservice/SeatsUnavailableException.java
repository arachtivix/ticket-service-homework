package com.walmart.ticketservice;

public class SeatsUnavailableException extends RuntimeException {

	public SeatsUnavailableException(String message){
		super(message);
	}
	
}
