package com.walmart.ticketservice;

public class Venue {
	
	private int seatingCapacity;
	private int numHeldSeats;

	public Venue(int numSeats){
		this.seatingCapacity = numSeats;
		numHeldSeats = 0;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
	public void holdSeats(int numSeatsRequested) throws SeatsUnavailableException {
		if( numSeatsRequested > seatingCapacity) {
			throw new SeatsUnavailableException("User asked for quantity of seats greater than number of seats in the venue");
		} else if( numSeatsRequested > seatingCapacity - numHeldSeats ) {
			throw new SeatsUnavailableException("User asked for more seats than there were available");
		} else {
			numHeldSeats += numSeatsRequested;
		}
	}
	
	public int getNumSeatsAvailable() {
		return seatingCapacity - numHeldSeats;
	}
	
}
