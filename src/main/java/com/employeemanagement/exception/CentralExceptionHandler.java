package com.employeemanagement.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralExceptionHandler {
	
	@ExceptionHandler(value = {NoDataFoundException.class})
	public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException e){
		// Create Payload caintaining exception details
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ExceptionPayload payload = new ExceptionPayload(
					e.getMessage(),
					badRequest);
			
		// Return actual response Entity
		return new ResponseEntity<>(payload,badRequest);
		 
	}

}
