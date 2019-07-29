package com.example.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.limitsservice.model.LimitConfiguration;
import com.example.microservices.limitsservice.model.LimitsServiceConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsController {

	@Autowired
	private LimitsServiceConfiguration limitsServiceConfiguration;
	
	@GetMapping("/limits")
	public LimitConfiguration getLimits() {
		return new LimitConfiguration(limitsServiceConfiguration.getMaximum(),limitsServiceConfiguration.getMinimum());
	}
	
	@GetMapping("/limits-fault-tolerant")
	@HystrixCommand(fallbackMethod = "getLimits")
	public LimitConfiguration getFaultTolerantLimits() {
		throw new RuntimeException();
	}
}
