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
		if( numSeatsRequested <= seatingCapacity){
			numHeldSeats += numSeatsRequested;
		} else {
			throw new SeatsUnavailableException("User asked for quantity of seats greater than number of seats in the venue");
		}
	}
	
	public int getNumSeatsAvailable() {
		return seatingCapacity - numHeldSeats;
	}
	
}
