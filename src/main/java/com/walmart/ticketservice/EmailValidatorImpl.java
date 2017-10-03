package com.walmart.ticketservice;

import org.springframework.stereotype.Component;

@Component
public class EmailValidatorImpl implements EmailValidator {

	@Override
	public boolean validate(String email) {
		int index = email.indexOf('@');
		if( index == -1 || index == email.length() - 1 || index == 0 ){
			return false;
		} else {
			return true;
		}
	}

}
