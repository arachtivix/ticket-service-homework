package com.walmart.ticketservice;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EmailValidatorImplTestsConfig {
	
	@Bean
	public EmailValidatorImpl getValidator(){
		return new EmailValidatorImpl();
	}
	
}
