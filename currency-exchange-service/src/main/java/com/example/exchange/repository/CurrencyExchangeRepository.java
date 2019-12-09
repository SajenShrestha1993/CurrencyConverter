package com.example.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exchange.entity.ExchangeValue;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Integer> {

	public ExchangeValue findByFromAndTo(String from, String to);
}
