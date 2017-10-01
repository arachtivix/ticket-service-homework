package com.walmart.ticketservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VenueTests {

	@Test
	public void testGetNumSeatsGreaterThanOrEqualToOne() {
		Venue venue = new Venue(500);
		int numSeats = venue.getNumSeats();
		assertThat(numSeats,greaterThanOrEqualTo(1));
	}
	
}
