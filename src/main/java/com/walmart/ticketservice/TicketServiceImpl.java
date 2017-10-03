package com.walmart.ticketservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketServiceImpl implements TicketService {
	
	private final Venue venue;
	private final EmailValidator emailValidator;
	private int holdIndex = 0;
	
	@Autowired
	public TicketServiceImpl(Venue venue, EmailValidator emailValidator){
		this.venue = venue;
		this.emailValidator = emailValidator;
	}

	@Override
	public int numSeatsAvailable() {
		return venue.getSeatingCapacity();
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		if( !emailValidator.validate(customerEmail) ){
			throw new InvalidEmailException("Email supplied was invalid");
		}
		List<Seat> seatsHeld = venue.holdSeats(numSeats);
		SeatHold hold = new SeatHold(holdIndex++,seatsHeld);
		return hold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEamil) {
		// TODO Auto-generated method stub
		return null;
	}

}
