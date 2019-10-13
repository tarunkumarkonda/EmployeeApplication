package com.employeemanagement.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionPayload {

	private final String message;
	private final HttpStatus httpStatus;
	
	public ExceptionPayload(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	
}
