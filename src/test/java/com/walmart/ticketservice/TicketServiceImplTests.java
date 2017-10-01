package com.walmart.ticketservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TicketServiceImplTestsConfig.class, loader=AnnotationConfigContextLoader.class)
@SpringBootTest
public class TicketServiceImplTests {
	
	@Autowired
	private Venue venue;
	
	@Autowired
	private TicketServiceImpl ticketServiceImpl;
	
	@Before
	public void setup(){
		
	}

	@Test
	public void testGetSeatsAvailableReturnsZeroOrPositiveNumber(){
		int seatsAvailable = ticketServiceImpl.numSeatsAvailable();
		assertThat(seatsAvailable, greaterThanOrEqualTo(0));
	}
	
	@Test
	public void testSeatsAvailableEqualsSeatsInVenueWhenNoAssignmentsMadeYet(){
		int seatsAvailable = ticketServiceImpl.numSeatsAvailable();
		assertThat(seatsAvailable, equalTo(venue.getNumSeats()));
	}
	
	@Test
	public void testServiceAsksVenueToHoldSeatsWhenFindAndHoldSeatsIsCalled(){
		ticketServiceImpl.findAndHoldSeats(2, "test@test.com");
		Mockito.verify(venue, Mockito.times(1)).holdSeats(2);
	}
	
}
