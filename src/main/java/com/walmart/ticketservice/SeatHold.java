package com.walmart.ticketservice;

import java.util.Collections;
import java.util.List;

public class SeatHold {

	private final Integer id;
	private final List<Seat> seats;
	
	public SeatHold(Integer id, List<Seat> seats){
		this.id = id;
		this.seats = Collections.unmodifiableList(seats);
	}
	
	public Integer getId(){
		return id;
	}
	
	public List<Seat> getSeats(){
		return seats;
	}
	
}
