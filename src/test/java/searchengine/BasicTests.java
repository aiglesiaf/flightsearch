package searchengine;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lastminute.flights.searchengine.controller.FlightSearchController;
import com.lastminute.flights.searchengine.model.Criteria;
import com.lastminute.flights.searchengine.model.FlightResult;

public class BasicTests {

	private static FlightSearchController searchController;

	@BeforeClass
	public static void init() {
		searchController = new FlightSearchController();
	}
	
	@Test
	public void basicCaseOne() throws Exception{
		
		Criteria criteria = new Criteria();
		criteria.setOrigin("AMS");
		criteria.setDestination("FRA");
		criteria.setAdults(1);
		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.add(Calendar.DATE, 31);
		criteria.setDate(gcDate);

		List<FlightResult> results = searchController.search(criteria);
		
		FlightResult r1 = results.get(0);
		assertEquals(r1.getFlightCode(), "LH5909");
		assertEquals(r1.getFormattedTotalPrice(), "90.40");
		
		FlightResult r2 = results.get(1);
		assertEquals(r2.getFlightCode(), "TK2372");
		assertEquals(r2.getFormattedTotalPrice(), "157.60");
		
		FlightResult r3 = results.get(2);
		assertEquals(r3.getFlightCode(), "TK2659");
		assertEquals(r3.getFormattedTotalPrice(), "198.40");
		
	}

	@Test
	public void basicCaseTwo() throws Exception{
		
		Criteria criteria = new Criteria();
		criteria.setOrigin("LHR");
		criteria.setDestination("IST");
		criteria.setAdults(2);
		criteria.setChildren(1);
		criteria.setInfants(1);
		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.add(Calendar.DATE, 15);
		criteria.setDate(gcDate);

		List<FlightResult> results = searchController.search(criteria);
		FlightResult r1 = results.get(0);
		assertEquals(r1.getFlightCode(), "LH1085");
		assertEquals(r1.getFormattedTotalPrice(), "481.19");
		
		FlightResult r2 = results.get(1);
		assertEquals(r2.getFlightCode(), "TK8891");
		assertEquals(r2.getFormattedTotalPrice(), "806.00");
		
	}
	
	@Test
	public void basicCaseThree() throws Exception{
		
		Criteria criteria = new Criteria();
		criteria.setOrigin("BCN");
		criteria.setDestination("MAD");
		criteria.setAdults(1);
		criteria.setChildren(2);
		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.add(Calendar.DATE, 2);
		criteria.setDate(gcDate);

		List<FlightResult> results = searchController.search(criteria);
		
		FlightResult r1 = results.get(0);
		assertEquals(r1.getFlightCode(), "IB2171");
		assertEquals(r1.getFormattedTotalPrice(), "909.09");
		
		FlightResult r2 = results.get(1);
		assertEquals(r2.getFlightCode(), "LH5496");
		assertEquals(r2.getFormattedTotalPrice(), "1,028.43");
		
	}

}
