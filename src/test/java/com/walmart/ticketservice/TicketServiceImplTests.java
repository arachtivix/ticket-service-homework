package com.walmart.ticketservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import com.walmart.ticketservice.entities.Seat;
import com.walmart.ticketservice.entities.SeatHold;
import com.walmart.ticketservice.entities.Venue;
import com.walmart.ticketservice.exceptions.InvalidEmailException;
import com.walmart.ticketservice.exceptions.SeatsUnavailableException;

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
		List<Seat> seats = getSeatsList(3);
		Mockito.when(venue.holdSeats(3)).thenReturn(seats);
		
		SeatHold result = ticketServiceImpl.findAndHoldSeats(3, "test@test.com");
		assertThat(result.getSeats().size(), equalTo(3));
	}
	
	@Test
	public void testServiceReturnsDifferentIdsEachTimeSeatsRequested() {
		List<Seat> seats3 = getSeatsList(3);
		List<Seat> seats2 = getSeatsList(2);
		Mockito.when(venue.holdSeats(3)).thenReturn(seats3);
		Mockito.when(venue.holdSeats(2)).thenReturn(seats2);

		SeatHold result3 = ticketServiceImpl.findAndHoldSeats(3, "test@test.com");
		SeatHold result2 = ticketServiceImpl.findAndHoldSeats(2, "test@test.com");
		assertThat(result3.getId(), Matchers.not(Matchers.equalTo(result2.getId())));
	}
	
	@Test(expected = InvalidEmailException.class)
	public void testServiceThrowsInvalidEmailExceptionIfEmailIsInvalid() {
		ticketServiceImpl.findAndHoldSeats(3, "INVALID");
	}
	
	@Test(expected = SeatsUnavailableException.class)
	public void testReserveSeatsThrowsSeatsUnavailableExceptionIfSeatHoldIdInvalid() {
		ticketServiceImpl.reserveSeats(99, "test@test.com");
	}
	
	@Test
	public void testReserveSeatsReturnsSuccessMessageIfProperSeatHoldEmailComboPresented() {
		List<Seat> seats3 = getSeatsList(3);
		List<Seat> seats2 = getSeatsList(2);
		Mockito.when(venue.holdSeats(3)).thenReturn(seats3);
		Mockito.when(venue.holdSeats(2)).thenReturn(seats2);
		
		ticketServiceImpl.findAndHoldSeats(3, "testalso@test.com");
		SeatHold result2 = ticketServiceImpl.findAndHoldSeats(2, "test@test.com");
		
		String response = ticketServiceImpl.reserveSeats(result2.getId(), "test@test.com");
		Assert.assertNotNull(response);
	}
	
	@Test(expected = InvalidEmailException.class)
	public void testReserveSeatsThrowsInvalidEmailExceptionIfEmailSuppliedDoesNotMatchStored() {
		List<Seat> seats3 = getSeatsList(3);
		List<Seat> seats2 = getSeatsList(2);
		Mockito.when(venue.holdSeats(3)).thenReturn(seats3);
		Mockito.when(venue.holdSeats(2)).thenReturn(seats2);
		
		ticketServiceImpl.findAndHoldSeats(3, "test@test.com");
		SeatHold result2 = ticketServiceImpl.findAndHoldSeats(2, "unique@test.com");
		
		ticketServiceImpl.reserveSeats(result2.getId(), "test@test.com");
	}
	
	@Test(expected = SeatsUnavailableException.class)
	public void testReserveSeatsThrowsSeatsUnavailableExceptionIfReserveSeatsAlreadyCalledOnce() {
		List<Seat> seats3 = getSeatsList(3);
		Mockito.when(venue.holdSeats(3)).thenReturn(seats3);
		SeatHold result3 = ticketServiceImpl.findAndHoldSeats(3, "test@test.com");

		ticketServiceImpl.reserveSeats(result3.getId(), "test@test.com");
		ticketServiceImpl.reserveSeats(result3.getId(), "test@test.com");
	}

	private List<Seat> getSeatsList(int numSeats) {
		List<Seat> seats = new LinkedList<Seat>();
		for( int i = 0; i < numSeats; i++ ){
			seats.add(new Seat());
		}
		return seats;
	}
	
}
