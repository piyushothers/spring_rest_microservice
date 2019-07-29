package com.example.microservices.currencyexchangeservice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class ExchangeValue {
	
	@Id
	private Long id ;
	private String fromCurrency ; 
	private String toCurrency ;
	private BigDecimal conversionMultiple ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency= fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.fromCurrency = from;
		this.toCurrency = to;
		this.conversionMultiple = conversionMultiple;
	}
	
	public ExchangeValue() {
		
	}
}
