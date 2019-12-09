package com.example.limits.service.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.limits.service.enitity.LimitConfiguration;
import com.example.limits.service.properties.Configuration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		return new LimitConfiguration(config.getMinimum(),
				config.getMaximum());
	}
}
