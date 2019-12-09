package com.example.exvhange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExchageRateNotFoundException extends RuntimeException {

	public ExchageRateNotFoundException (String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
