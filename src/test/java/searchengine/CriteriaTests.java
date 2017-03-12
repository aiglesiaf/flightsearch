package searchengine;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lastminute.flights.searchengine.controller.FlightSearchController;
import com.lastminute.flights.searchengine.exception.LastMinuteException;
import com.lastminute.flights.searchengine.exception.ValidationException;
import com.lastminute.flights.searchengine.model.Criteria;
import com.lastminute.flights.searchengine.model.FlightResult;

public class CriteriaTests {

	private static FlightSearchController searchController;

	@BeforeClass
	public static void init() {
		searchController = new FlightSearchController();
	}

	@Test(expected = ValidationException.class)
	public void noDate() throws LastMinuteException {

		Criteria criteria = new Criteria();
		criteria.setOrigin("AMS");
		criteria.setDestination("FRA");
		criteria.setAdults(1);

		List<FlightResult> results = searchController.search(criteria);
		assertEquals(results.size(), 0);

	}
	
	@Test(expected = ValidationException.class)
	public void noPassengers() throws LastMinuteException {

		Criteria criteria = new Criteria();
		criteria.setOrigin("AMS");
		criteria.setDestination("FRA");
		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.add(Calendar.DATE, 31);
		criteria.setDate(gcDate);

		List<FlightResult> results = searchController.search(criteria);
		assertEquals(results.size(), 0);

	}
	
	@Test(expected = ValidationException.class)
	public void nullAirport() throws LastMinuteException {

		Criteria criteria = new Criteria();
		// criteria.setOrigin("AMS");
		criteria.setDestination("AMS");
		criteria.setAdults(1);
		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.add(Calendar.DATE, 31);
		criteria.setDate(gcDate);

		List<FlightResult> results = searchController.search(criteria);
		assertEquals(results.size(), 0);

	}

	@Test
	public void emptyAirport() throws Exception {

		Criteria criteria = new Criteria();
		criteria.setOrigin("AMS");
		criteria.setDestination("");
		criteria.setAdults(1);
		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.add(Calendar.DATE, 31);
		criteria.setDate(gcDate);

		List<FlightResult> results = searchController.search(criteria);
		assertEquals(results.size(), 0);

	}

}
