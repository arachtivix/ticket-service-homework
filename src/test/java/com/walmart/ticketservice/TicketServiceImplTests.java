package com.walmart.ticketservice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TicketServiceImplTestsConfig.class, loader=AnnotationConfigContextLoader.class)
public class TicketServiceImplTests {
	
	private TicketServiceImpl ticketServiceImpl;
	
	@Before
	public void setup(){
		ticketServiceImpl = new TicketServiceImpl();
	}

	@Test
	public void testGetSeatsAvailableReturnsZeroOrPositiveNumber(){
		int seatsAvailable = ticketServiceImpl.numSeatsAvailable();
		assertThat(seatsAvailable, is(greaterThanOrEqualTo(0)));
	}
	
}
