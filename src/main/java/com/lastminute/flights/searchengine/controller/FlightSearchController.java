package com.lastminute.flights.searchengine.controller;

import java.util.List;

import com.lastminute.flights.searchengine.exception.LastMinuteException;
import com.lastminute.flights.searchengine.exception.ValidationException;
import com.lastminute.flights.searchengine.model.Criteria;
import com.lastminute.flights.searchengine.model.FlightResult;
import com.lastminute.flights.searchengine.services.FlightSearchService;

public class FlightSearchController {

	public List<FlightResult> search(Criteria criteria) throws LastMinuteException{
		validateCriteria(criteria);
		return new FlightSearchService().search(criteria);
	}
	
	private void validateCriteria(Criteria criteria) throws ValidationException
	{
		if(criteria.getOrigin() == null)
		{
			throw new ValidationException("Origin must be filled out");
		}
		
		if(criteria.getDestination() == null)
		{
			throw new ValidationException("Destination must be filled out");
		}
		
		if(criteria.getPassengers().isEmpty())
		{
			throw new ValidationException("Passengers must be included");
		}
		
		if(criteria.getDate() == null)
		{
			throw new ValidationException("The date of your flight must be included");
		}
	}

}
