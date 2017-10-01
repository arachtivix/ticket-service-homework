package com.walmart.ticketservice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TicketServiceImplTestsConfig {
	
	@Bean
	@Autowired
	public TicketServiceImpl getTicketService(Venue venue) {
		return new TicketServiceImpl(venue);
	}
	
	@Bean
	public Venue getVenue(){
		Venue mockVenue = mock(Venue.class);
		when(mockVenue.getSeatingCapacity()).thenReturn(1000);
		
		return mockVenue;
	}
	
}
