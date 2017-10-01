package com.walmart.ticketservice;

import static org.mockito.Mockito.*;

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
		Venue mockVenue = mock(Venue.class);
		when(mockVenue.getSeatingCapacity()).thenReturn(1000);
		
		return mockVenue;
	}
	
}
