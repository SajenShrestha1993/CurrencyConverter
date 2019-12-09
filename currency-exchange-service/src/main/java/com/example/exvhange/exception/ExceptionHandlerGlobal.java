package com.example.exvhange.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.entity.ErrorResponse;

@RestController
@ControllerAdvice
public class ExceptionHandlerGlobal {

	@ExceptionHandler
	public ResponseEntity<?> userNotFoundHandler(ExchageRateNotFoundException e){
		ErrorResponse error = new ErrorResponse();
		error.setMessage("User Not Found");
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(new Date());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
}
