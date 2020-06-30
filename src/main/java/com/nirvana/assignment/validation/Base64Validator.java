package com.nirvana.assignment.validation;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates if a string is a valid Base64 encoded binary data by checking if the decode is successful.
 */
public class Base64Validator implements ConstraintValidator<Base64Constraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) return true;
		try {
			Base64.getDecoder().decode(value);
		} catch(IllegalArgumentException iae) {
		    return false;
		}
		return true;
	}

}
