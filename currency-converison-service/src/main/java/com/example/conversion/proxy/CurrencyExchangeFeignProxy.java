package com.example.conversion.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.conversion.entity.CurrencyConversion;

//Feign Client Allows easy connect to external services
//RibbonClient is for loadbalancing

//@FeignClient(name="currency-exchange-service",url="localhost:8001")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeFeignProxy {

	//Endpoint defination from "currency-exchange-service"
	@GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<CurrencyConversion> retrieveExchangeValue(@PathVariable("from") String from,
																	@PathVariable("to") String to);
}
