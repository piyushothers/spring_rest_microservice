package com.example.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.example.microservices.currencyconversionservice.proxy.CurrencyExchangeService;

@RestController
public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversion(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
		Map<String, String> uriValues = new HashMap<>();
		uriValues.put("from", from);
		uriValues.put("to", to);
		CurrencyConversionBean response = new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
						CurrencyConversionBean.class, uriValues)
				.getBody();
		logger.info("{}",response);
		return new CurrencyConversionBean(1000L, from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), 0);
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversionFeign(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
		CurrencyConversionBean response = currencyExchangeService.retrieveExchangeValue(from, to);
		return new CurrencyConversionBean(1000L, from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), 0);
	}
}
