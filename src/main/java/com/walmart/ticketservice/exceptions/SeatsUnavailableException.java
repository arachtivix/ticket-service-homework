package com.walmart.ticketservice.exceptions;


public class SeatsUnavailableException extends TicketServiceException {

	public SeatsUnavailableException(String message){
		super(message);
	}
	
}
