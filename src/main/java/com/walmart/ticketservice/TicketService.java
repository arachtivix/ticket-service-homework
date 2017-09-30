package com.walmart.ticketservice;

public interface TicketService {

	int numSeatsAvailable();
	
	SeatHold findAndHoldSeats(int numSeats, String customerEmail);
	
	String reserveSeats(int seatHoldId, String customerEamil);
	
}
