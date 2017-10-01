package com.walmart.ticketservice;

import org.springframework.stereotype.Component;

@Component
public class TicketServiceImpl implements TicketService {

	@Override
	public int numSeatsAvailable() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEamil) {
		// TODO Auto-generated method stub
		return null;
	}

}
