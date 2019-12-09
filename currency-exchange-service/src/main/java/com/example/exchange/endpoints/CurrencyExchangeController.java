package com.example.exchange.endpoints;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.entity.ExchangeValue;
import com.example.exchange.service.CurrencyExchangeService;
import com.example.exvhange.exception.ExchageRateNotFoundException;

@RestController
public class CurrencyExchangeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//Needed to get the current port where the application is running
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeService service;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<?> retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue= service.findRateFromTo(from, to);
		
		if(exchangeValue == null) {
			throw new RuntimeException("Requested Exchange Rate Not Found");
		}
		//Port where the application is running
		String port = environment.getProperty("local.server.port");
		
		exchangeValue.setPort(Integer.parseInt(port));
		logger.info("{}",exchangeValue);
		return new ResponseEntity<>(exchangeValue,HttpStatus.OK);
	}
}
