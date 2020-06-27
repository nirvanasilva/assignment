package com.nirvana.assignment.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = Base64Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Constraint {
	
	String message() default "Data is not Base64 encoded";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
