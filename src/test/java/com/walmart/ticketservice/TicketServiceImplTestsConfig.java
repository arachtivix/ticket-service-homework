package com.walmart.ticketservice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.walmart.ticketservice.entities.Venue;

@TestConfiguration
public class TicketServiceImplTestsConfig {
	
	@Bean
	@Autowired
	public TicketServiceImpl getTicketService(Venue venue, EmailValidator emailValidator) {
		return new TicketServiceImpl(venue,emailValidator);
	}
	
	@Bean
	public Venue getVenue() {
		Venue mockVenue = mock(Venue.class);
		when(mockVenue.getSeatingCapacity()).thenReturn(1000);
		
		return mockVenue;
	}
	
	@Bean
	public EmailValidator getEmailValidator() {
		return new EmailValidatorImpl();
	}
	
}
