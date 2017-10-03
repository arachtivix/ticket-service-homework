package com.walmart.ticketservice;

import java.util.Collections;
import java.util.List;

public class SeatHold {

	private final Integer id;
	private final List<Seat> seats;
	private final String email;
	
	public SeatHold(Integer id, List<Seat> seats, String email){
		this.id = id;
		this.seats = Collections.unmodifiableList(seats);
		this.email = email;
	}
	
	public Integer getId(){
		return id;
	}
	
	public List<Seat> getSeats(){
		return seats;
	}
	
	public String getEmail(){
		return email;
	}
	
}
