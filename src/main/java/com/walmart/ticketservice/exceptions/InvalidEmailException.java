package com.walmart.ticketservice.exceptions;


public class InvalidEmailException extends TicketServiceException {

	public InvalidEmailException(String message){
		super(message);
	}
	
}
