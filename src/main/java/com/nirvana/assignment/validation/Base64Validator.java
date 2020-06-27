package com.nirvana.assignment.validation;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
