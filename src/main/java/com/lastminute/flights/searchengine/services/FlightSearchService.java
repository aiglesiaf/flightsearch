package com.lastminute.flights.searchengine.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lastminute.flights.searchengine.db.DataProvider;
import com.lastminute.flights.searchengine.model.Criteria;
import com.lastminute.flights.searchengine.model.Flight;
import com.lastminute.flights.searchengine.model.FlightResult;
import com.lastminute.flights.searchengine.model.PassengerType;

public class FlightSearchService {

	public List<FlightResult> search(Criteria criteria) {

		// Find the flights
		List<Flight> resultFlights = DataProvider.query(criteria.getOrigin(), criteria.getDestination());
		// Calculate prices
		List<FlightResult> resultOffers = calculateTotalPrice(criteria, resultFlights);
		// Order by price
		orderListByPriceAsc(resultOffers);
		return resultOffers;
	}

	private List<FlightResult> calculateTotalPrice(Criteria criteria, List<Flight> flightsToCalculate) {
		List<FlightResult> resultOffers = new ArrayList<FlightResult>();
		// Prices calculation
		for (Flight flight : flightsToCalculate) {
			BigDecimal basePrice = getPriceWithDateDiscount(flight.getBasePrice(), criteria.getDate());
			BigDecimal totalPrice = BigDecimal.ZERO;
			for (String type : criteria.getPassengers().keySet()) {
				BigDecimal passengersOfType = new BigDecimal(criteria.getPassengers().get(type));
				switch (PassengerType.valueOf(type)) {
				case INFANT:
					String airlineCode = flight.getFlightCode().substring(0, 2);
					BigDecimal infantsPrice = DataProvider.infantPrices.get(airlineCode);
					infantsPrice = infantsPrice.multiply(passengersOfType);
					totalPrice = totalPrice.add(infantsPrice);
					break;
				case CHILD:
					BigDecimal childrenPrice = basePrice.multiply(new BigDecimal(0.67));
					childrenPrice = childrenPrice.multiply(passengersOfType);
					totalPrice = totalPrice.add(childrenPrice);
					break;
				default:
					BigDecimal adultsPrice = basePrice.multiply(passengersOfType);
					totalPrice = totalPrice.add(adultsPrice);
				}
			}

			resultOffers.add(new FlightResult(flight.getFlightCode(), totalPrice));
		}

		return resultOffers;
	}

	private BigDecimal getPriceWithDateDiscount(int basePrice, Calendar date) {

		Calendar serverTime = Calendar.getInstance(date.getTimeZone());
		// If we are comparing dates with different Summer/Winter time the
		// number of milliseconds have to be taken into account
		long diff = (date.getTimeInMillis() + date.get(Calendar.DST_OFFSET))
				- (serverTime.getTimeInMillis() + serverTime.get(Calendar.DST_OFFSET));

		// Adding 5 min of extra time in case the request takes more than 1
		// millisecond
		diff += 5 * 60 * 1000;

		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		BigDecimal multiplier = new BigDecimal(1);

		if (days >= 31) {
			multiplier = BigDecimal.valueOf(0.8);
		} else if (days <= 15 && days >= 3) {
			multiplier = BigDecimal.valueOf(1.2);
		} else if (days <= 2) {
			multiplier = BigDecimal.valueOf(1.5);
		}

		return BigDecimal.valueOf(basePrice).multiply(multiplier);

	}

	private void orderListByPriceAsc(List<FlightResult> result) {

		Comparator<FlightResult> cmp = new Comparator<FlightResult>() {
			public int compare(FlightResult f1, FlightResult f2) {
				return f1.getTotalPrice().compareTo(f2.getTotalPrice());
			}
		};
		Collections.sort(result, cmp);
	}

}
