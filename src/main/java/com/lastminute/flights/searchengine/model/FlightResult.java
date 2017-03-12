package com.lastminute.flights.searchengine.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FlightResult {

	private String flightCode;
	private BigDecimal totalPrice;

	public FlightResult(String flightCode, BigDecimal totalPrice) {
		super();
		this.flightCode = flightCode;
		this.totalPrice = totalPrice;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public String getFormattedTotalPrice() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(totalPrice);
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "FlightResult [flightCode=" + flightCode + ", totalPrice=" + getFormattedTotalPrice() + "]";
	}

}
