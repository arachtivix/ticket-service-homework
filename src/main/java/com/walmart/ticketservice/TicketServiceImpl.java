package com.walmart.ticketservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.walmart.ticketservice.entities.Seat;
import com.walmart.ticketservice.entities.SeatHold;
import com.walmart.ticketservice.entities.Venue;
import com.walmart.ticketservice.exceptions.InvalidEmailException;
import com.walmart.ticketservice.exceptions.SeatsUnavailableException;

@Component
public class TicketServiceImpl implements TicketService {
	
	private final Venue venue;
	private final EmailValidator emailValidator;
	private Map<Integer,SeatHold> holds;
	private Map<Integer,SeatHold> reservations;
	private int holdIndex = 0;
	
	@Value("${ticket-service.shell.cmd.reserve-seats.results}")
	private String reserveSuccessMessage;
	
	@Autowired
	public TicketServiceImpl(Venue venue, EmailValidator emailValidator){
		this.venue = venue;
		this.emailValidator = emailValidator;
		holds = new HashMap<Integer,SeatHold>();
		reservations = new HashMap<Integer,SeatHold>();
	}

	@Override
	public int numSeatsAvailable() {
		return venue.getNumSeatsAvailable();
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		if( !emailValidator.validate(customerEmail) ){
			throw new InvalidEmailException("Email supplied was invalid");
		} else if( numSeats <= 0 ) {
			throw new SeatsUnavailableException("Number of seats requested less than or equal to zero");
		}
		List<Seat> seatsHeld = venue.holdSeats(numSeats);
		SeatHold hold = new SeatHold(holdIndex,seatsHeld,customerEmail);
		holds.put(holdIndex++, hold);
		return hold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		SeatHold hold = holds.get(seatHoldId);
		
		if( hold == null ){
			throw new SeatsUnavailableException("Seat hold ID does not match any found in the system");
		} else if ( !hold.getEmail().equalsIgnoreCase(customerEmail) ){
			throw new InvalidEmailException("Email supplied does not match the one given for the hold");
		}
		
		reservations.put(seatHoldId, holds.remove(seatHoldId));
		
		return String.format(reserveSuccessMessage, hold.getSeats().size(), hold.getEmail());
	}

}
