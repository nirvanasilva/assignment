package com.nirvana.assignment.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Base64ValidatorTest {

	@InjectMocks
	private Base64Validator validator;
	
	@Mock
	private ConstraintValidatorContext mockContext;
	
	@Test
	public void shouldValidateNullValue() {
		boolean result = validator.isValid(null, mockContext);
		assertTrue(result);
	}

	@Test
	public void shouldValidateBase64Value() {
		boolean result = validator.isValid("test", mockContext);
		assertTrue(result);
	}
	
	@Test
	public void shouldNotValidateNonBase64Value() {
		boolean result = validator.isValid("test test", mockContext);
		assertFalse(result);
	}
}
