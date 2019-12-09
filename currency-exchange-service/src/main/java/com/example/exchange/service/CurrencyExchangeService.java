package com.example.exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchange.entity.ExchangeValue;
import com.example.exchange.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository repository;
	
	//Method to find currency exchange rate {from} and {to}
	
	public ExchangeValue findRateFromTo(String from, String to) {
		 return repository.findByFromAndTo(from, to);
	}
}
