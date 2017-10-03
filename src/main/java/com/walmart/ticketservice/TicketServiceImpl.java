package com.walmart.ticketservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TicketServiceImpl implements TicketService {
	
	private final Venue venue;
	private final EmailValidator emailValidator;
	private Map<Integer,SeatHold> holds;
	private int holdIndex = 0;
	
	@Value("${ticket-service.shell.cmd.reserve-seats.results}")
	private String reserveSuccessMessage;
	
	@Autowired
	public TicketServiceImpl(Venue venue, EmailValidator emailValidator){
		this.venue = venue;
		this.emailValidator = emailValidator;
		holds = new HashMap<Integer,SeatHold>();
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
		
		return String.format(reserveSuccessMessage, hold.getId());
	}

}
