package com.walmart.ticketservice.entities;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.walmart.ticketservice.EmailValidatorImpl;

@TestConfiguration
public class EmailValidatorImplTestsConfig {
	
	@Bean
	public EmailValidatorImpl getValidator(){
		return new EmailValidatorImpl();
	}
	
}
