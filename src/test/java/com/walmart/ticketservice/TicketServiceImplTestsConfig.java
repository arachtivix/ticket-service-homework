package com.walmart.ticketservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketServiceImplTestsConfig {
	
	@Bean
	@Autowired
	public TicketServiceImpl getTicketService(Venue venue) {
		return new TicketServiceImpl(venue);
	}
	
	@Bean
	public Venue getVenue(){
		return new Venue(1000);
	}
	
}
