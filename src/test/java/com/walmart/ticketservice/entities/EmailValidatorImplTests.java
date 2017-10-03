package com.walmart.ticketservice.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.walmart.ticketservice.EmailValidatorImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EmailValidatorImplTestsConfig.class, loader=AnnotationConfigContextLoader.class)
public class EmailValidatorImplTests {

	@Autowired
	EmailValidatorImpl validator;
	
	@Test
	public void testValidEmailPasses() {
		Assert.assertTrue(validator.validate("test@test.com"));
	}
	
	@Test
	public void testHasNoAtSymbolFailsValidator() {
		Assert.assertFalse(validator.validate("not an email"));
	}
	
	@Test
	public void testNoUsernameFailsValidator() {
		Assert.assertFalse(validator.validate("@test.com"));
	}
	
	@Test
	public void testNoDomainlFailsValidator() {
		Assert.assertFalse(validator.validate("test@"));
	}
	
}
