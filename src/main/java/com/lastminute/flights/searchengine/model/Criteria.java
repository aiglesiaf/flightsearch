package com.lastminute.flights.searchengine.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Criteria {

	private String origin;
	private String destination;
	private Calendar date;
	private Map<String, Integer> passengers = new HashMap<String, Integer>();

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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setAdults(int adults) {
		this.passengers.put(PassengerType.ADULT.name(), adults);
	}

	public void setChildren(int children) {
		this.passengers.put(PassengerType.CHILD.name(), children);
	}

	public void setInfants(int infants) {
		this.passengers.put(PassengerType.INFANT.name(), infants);
	}

	public Map<String, Integer> getPassengers() {
		return passengers;
	}

}
