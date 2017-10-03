package com.walmart.ticketservice;

import com.walmart.ticketservice.entities.SeatHold;

public interface TicketService {

	int numSeatsAvailable();
	
	SeatHold findAndHoldSeats(int numSeats, String customerEmail);
	
	String reserveSeats(int seatHoldId, String customerEamil);
	
}
