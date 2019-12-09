package com.example.conversion.endpoints;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.conversion.entity.CurrencyConversion;
import com.example.conversion.proxy.CurrencyExchangeFeignProxy;

@RestController

public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CurrencyExchangeFeignProxy proxy;
	
	//Accessing external service without using feign
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<?> convertCurrency(@PathVariable String from,
													@PathVariable String to,
													@PathVariable BigDecimal quantity) {
		
		//RestTemplate to make a Get request to the currency-exchange-service
		RestTemplate template = new RestTemplate();
		
		//URI source to currency-exchange-service
		String resourceUri = "http://localhost:8001/currency-exchange/from/{from}/to/{to}";
		
		//parameters for pathvariables
		Map<String,String> uriParameter = new HashMap<String,String>();
		uriParameter.put("from",from);
		uriParameter.put("to", to);
		
		//Gets the response as a ResponseEntity Obj
		ResponseEntity<CurrencyConversion> entityResponse = template.getForEntity(resourceUri, CurrencyConversion.class, uriParameter);
		
		//Mapping to CurrencyConversion Obj
		CurrencyConversion result = entityResponse.getBody();
	
		CurrencyConversion conversionResult = new CurrencyConversion(result.getId(), //from Response
																	result.getFrom(),
																	result.getTo(),
																	result.getConversionMultiple(),
																	quantity, //from pathvariable quantity
																	quantity.multiply(result.getConversionMultiple()), //calculation
																	result.getPort()); //port where the external service is running
		
		
		return new ResponseEntity<>(conversionResult,HttpStatus.OK);
		
		
	}
	
	//Accessing external service using feign
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<?> convertCurrencyFeign(@PathVariable String from,
													@PathVariable String to,
													@PathVariable BigDecimal quantity) {
		
		
		ResponseEntity<CurrencyConversion> entityResponse = proxy.retrieveExchangeValue(from, to);
		
		//Mapping to CurrencyConversion Obj
		CurrencyConversion result = entityResponse.getBody();
	
		CurrencyConversion conversionResult = new CurrencyConversion(result.getId(), //from Response
																	result.getFrom(),
																	result.getTo(),
																	result.getConversionMultiple(),
																	quantity, //from pathvariable quantity
																	quantity.multiply(result.getConversionMultiple()), //calculation
																	result.getPort()); //port where the external service is running
		
		logger.info("Inside CurrencyConversion Service");
		return new ResponseEntity<>(conversionResult,HttpStatus.OK);
		
		
	}
	
}
