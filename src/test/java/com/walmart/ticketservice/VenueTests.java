package com.walmart.ticketservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VenueTests {
	
	private Venue venue;

	@Before
	public void setup() {
		venue = new Venue(500);
	}
	
	@Test
	public void testGetNumSeatsGreaterThanOrEqualToOne() {
		int numSeats = venue.getNumSeats();
		assertThat(numSeats,greaterThanOrEqualTo(1));
	}
	
	@Test
	public void testHoldSeatsReducesAvailableSeatCountByNumberRequested() {
		int numSeatsBefore = venue.getNumSeatsAvailable();
		venue.holdSeats(2);
		int numSeatsAfter = venue.getNumSeatsAvailable();
		assertThat(numSeatsBefore - numSeatsAfter, equalTo(2));
	}
	
}