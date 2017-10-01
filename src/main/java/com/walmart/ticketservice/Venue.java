package com.walmart.ticketservice;

public class Venue {
	
	private int numSeats;
	private int numHeldSeats;

	public Venue(int numSeats){
		this.numSeats = numSeats;
		numHeldSeats = 0;
	}

	public int getNumSeats() {
		return numSeats;
	}
	
	public void holdSeats(int numSeats) {
		numHeldSeats += numSeats;
	}
	
	public int getNumSeatsAvailable() {
		return numSeats - numHeldSeats;
	}
	
}
