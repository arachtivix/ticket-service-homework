package com.walmart.ticketservice;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.walmart.ticketservice.entities.SeatHold;
import com.walmart.ticketservice.exceptions.TicketServiceException;

@Component
public class InteractiveCommandLine implements Runnable {
	
	@Value("${ticket-service.shell.greetings}")
	private String welcome;
	
	@Value("${ticket-service.shell.command-prompt}")
	private String commandPrompt;
	
	@Value("${ticket-service.shell.cmd.num-seats-available}")
	private String numSeatsAvailableCommand;
	
	@Value("${ticket-service.shell.cmd.find-and-hold-seats}")
	private String findAndHoldSeatsCommand;
	
	@Value("${ticket-service.shell.cmd.reserve-seats}")
	private String reserveSeatsCommand;
	
	@Value("${ticket-service.shell.cmd.exit}")
	private String exitCommand;
	
	@Value("${ticket-service.shell.cmd.num-seats-available.description}")
	private String numSeatsAvailableCommandDescription;
	
	@Value("${ticket-service.shell.cmd.find-and-hold-seats.description}")
	private String findAndHoldSeatsCommandDescription;
	
	@Value("${ticket-service.shell.cmd.find-and-hold-seats.results}")
	private String findAndHoldSeatsCommandResults;
	
	@Value("${ticket-service.shell.cmd.reserve-seats.description}")
	private String reserveSeatsCommandDescription;
	
	@Value("${ticket-service.shell.cmd.exit.description}")
	private String exitDescription;

	@Autowired
	private Scanner scanner; // This class could drive integration tests via a mock scanner
	
	@Autowired
	private TicketService ticketService;

	@Override
	public void run() {
		System.out.println(welcome);
		System.out.println(String.format(numSeatsAvailableCommandDescription,numSeatsAvailableCommand));
		System.out.println(String.format(findAndHoldSeatsCommandDescription,findAndHoldSeatsCommand));
		System.out.println(String.format(reserveSeatsCommandDescription,reserveSeatsCommand));
		System.out.println(String.format(exitDescription,exitCommand));
		System.out.println(commandPrompt);
		boolean keepRunning = true;
		while( keepRunning ){
			System.out.print(":");
			String command = scanner.next();
			try{
				if( command.equalsIgnoreCase(numSeatsAvailableCommand) ){
					System.out.println(ticketService.numSeatsAvailable());
				} else if ( command.equalsIgnoreCase(findAndHoldSeatsCommand) ) {
					int numSeats = scanner.nextInt();
					String email = scanner.next();
					SeatHold seatHold = ticketService.findAndHoldSeats(numSeats, email);
					int holdId = seatHold.getId();
					System.out.println(String.format(findAndHoldSeatsCommandResults, holdId));
				} else if ( command.equalsIgnoreCase(reserveSeatsCommand) ) {
					int holdId = scanner.nextInt();
					String email = scanner.next();
					String reserveResults = ticketService.reserveSeats(holdId, email);
					System.out.println(reserveResults);
				} else if ( command.equalsIgnoreCase(exitCommand) ) {
					keepRunning = false;
				}
			} catch( TicketServiceException e ) {
				System.out.println(e.getMessage());
			}
		}
	}

}
