package com.walmart.ticketservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InteractiveCommandLine implements Runnable {
	
	@Value("${ticket-service.shell.greetings}")
	private String welcome;

	@Override
	public void run() {
		boolean keepRunning = true;
		while( keepRunning ){
			System.out.println(welcome);
			keepRunning = false;
		}
	}

}
