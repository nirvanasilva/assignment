package com.nirvana.assignment;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class BinaryDataExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException ex, WebRequest request) {
		return new ResponseEntity<ErrorMessage>(createSingleErrorMessage(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	private ErrorMessage createSingleErrorMessage(MethodArgumentNotValidException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return errorMessage;
	}
	
}
