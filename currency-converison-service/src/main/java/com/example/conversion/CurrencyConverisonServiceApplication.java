
package com.example.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableFeignClients("com.example.conversion") //Package to be scanned for FeignClient
@EnableDiscoveryClient //Enabling the service to register with the Eureka Server
public class CurrencyConverisonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverisonServiceApplication.class, args);
	}


	
}
