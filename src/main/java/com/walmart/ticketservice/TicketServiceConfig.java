package com.walmart.ticketservice;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class TicketServiceConfig {

	@Bean
	public Venue getVenue() {
		return new Venue(1000);
	}
	
}
