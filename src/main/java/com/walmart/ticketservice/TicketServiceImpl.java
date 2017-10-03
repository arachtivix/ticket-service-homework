package com.walmart.ticketservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketServiceImpl implements TicketService {
	
	private Venue venue;
	
	@Autowired
	public TicketServiceImpl(Venue venue){
		this.venue = venue;
	}

	@Override
	public int numSeatsAvailable() {
		return venue.getSeatingCapacity();
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		venue.holdSeats(numSeats);
		return new SeatHold(0);
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEamil) {
		// TODO Auto-generated method stub
		return null;
	}

}
