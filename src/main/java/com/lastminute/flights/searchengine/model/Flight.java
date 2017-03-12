package com.lastminute.flights.searchengine.model;

public class Flight {

	private String origin;
	private String destination;
	private String flightCode;
	private int basePrice;

	public Flight(String origin, String destination, String airline, int basePrice) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightCode = airline;
		this.basePrice = basePrice;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", airline=" + flightCode + ", basePrice="
				+ basePrice + "]";
	}

}
