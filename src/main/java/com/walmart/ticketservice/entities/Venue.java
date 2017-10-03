package com.walmart.ticketservice.entities;

import java.util.LinkedList;
import java.util.List;

import com.walmart.ticketservice.exceptions.SeatsUnavailableException;


public class Venue {
	
	private int seatingCapacity;
	private List<Seat> openSeats, nonOpenSeats;

	public Venue(int numSeats){
		openSeats = new LinkedList<Seat>();
		nonOpenSeats = new LinkedList<Seat>();
		
		for( int i = 0; i < numSeats; i++ ){
			openSeats.add(new Seat());
		}
		
		seatingCapacity = numSeats;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
	public List<Seat> holdSeats(int numSeatsRequested) throws SeatsUnavailableException {
		List<Seat> seatsHeld = new LinkedList<Seat>();
		if( numSeatsRequested > seatingCapacity) {
			throw new SeatsUnavailableException("User asked for quantity of seats greater than number of seats in the venue");
		} else if( numSeatsRequested > openSeats.size() ) {
			throw new SeatsUnavailableException("User asked for more seats than there were available");
		} else {
			for( int i = 0; i < numSeatsRequested; i++ ){
				Seat currentSeat = openSeats.remove(0);
				seatsHeld.add(currentSeat);
				nonOpenSeats.add(currentSeat);
			}
		}
		return seatsHeld;
	}
	
	public int getNumSeatsAvailable() {
		return openSeats.size();
	}
	
}
