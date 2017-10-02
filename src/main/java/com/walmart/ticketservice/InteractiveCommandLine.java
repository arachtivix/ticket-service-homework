package com.walmart.ticketservice;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
			if( command.equalsIgnoreCase(numSeatsAvailableCommand) ){
				System.out.println(ticketService.numSeatsAvailable());
			} else if ( command.equalsIgnoreCase(findAndHoldSeatsCommand) ) {
				
			} else if ( command.equalsIgnoreCase(reserveSeatsCommand) ) {
				
			} else if ( command.equalsIgnoreCase(exitCommand) ) {
				keepRunning = false;
			}
		}
	}

}
