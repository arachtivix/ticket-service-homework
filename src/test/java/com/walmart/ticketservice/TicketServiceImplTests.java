package com.walmart.ticketservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TicketServiceImplTestsConfig.class, loader=AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
		assertThat(seatsAvailable, equalTo(venue.getSeatingCapacity()));
	}
	
	@Test
	public void testServiceAsksVenueToHoldSeatsWhenFindAndHoldSeatsIsCalled() throws SeatsUnavailableException{
		ticketServiceImpl.findAndHoldSeats(2, "test@test.com");
		Mockito.verify(venue, Mockito.times(1)).holdSeats(2);
	}
	
	@Test
	public void testServiceReturnsNonNullSeatHoldWhenFindAndHoldSeatsIsCalled() {
		SeatHold hold = ticketServiceImpl.findAndHoldSeats(2, "test@test.com");
		Assert.assertNotNull(hold);
	}
	
	@Test(expected = SeatsUnavailableException.class)
	public void testServiceThrowsSeatsUnavailableExceptionWhenUnableToFulfillFindAndHoldSeats() {
		Mockito.when(venue.holdSeats(Mockito.anyInt())).thenThrow(new SeatsUnavailableException("test"));
		ticketServiceImpl.findAndHoldSeats(venue.getNumSeatsAvailable() + 1, "test@test.com");
	}
	
	@Test
	public void testServiceReturnsSeatHoldObjectWithTheNumerRequested() {
		List<Seat> seats = new LinkedList<Seat>();
		seats.add(new Seat());
		seats.add(new Seat());
		seats.add(new Seat());
		Mockito.when(venue.holdSeats(3)).thenReturn(seats);
		
		SeatHold result = ticketServiceImpl.findAndHoldSeats(3, "test@test.com");
		assertThat(result.getSeats().size(), equalTo(3));
	}
	
}
