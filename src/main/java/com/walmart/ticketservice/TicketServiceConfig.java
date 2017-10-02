package com.walmart.ticketservice;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class TicketServiceConfig {
	
	@Value("${ticket-service.seating-capacity}")
	private int seatingCapacity;

	@Bean
	public Venue getVenue() {
		return new Venue(seatingCapacity);
	}
	
	@Bean
	@Autowired
	public Scanner getConsole(InputStream commands) {
		return new Scanner(commands);
	}
	
	@Bean
	public InputStream getCommands() {
		return System.in;
	}
	
}
