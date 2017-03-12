package com.lastminute.flights.searchengine.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lastminute.flights.searchengine.model.Flight;

public class DataProvider {

	public static List<Flight> flightsList;
	public static Map<String, BigDecimal> infantPrices;
	private static String resourcesPath = "./src/main/resources";

	static {
		initFlights();
		initInfantPrices();
	}

	private static void initFlights() {

		String flightsCSV = resourcesPath + "/flights.csv";
		String flightLine = "";
		String separator = ",";

		flightsList = new ArrayList<Flight>();

		try (BufferedReader br = new BufferedReader(new FileReader(flightsCSV))) {
			while ((flightLine = br.readLine()) != null) {

				String[] flightArray = flightLine.split(separator);
				Flight flight = new Flight(flightArray[0], flightArray[1], flightArray[2],
						Integer.parseInt(flightArray[3]));
				flightsList.add(flight);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void initInfantPrices() {

		String infantPricesCSV = resourcesPath + "/infantprices.csv";
		String line = "";
		String separator = ",";
		infantPrices = new HashMap<String, BigDecimal>();

		try (BufferedReader br = new BufferedReader(new FileReader(infantPricesCSV))) {
			while ((line = br.readLine()) != null) {

				String[] priceArray = line.split(separator);
				infantPrices.put(priceArray[0], new BigDecimal(priceArray[1]));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Flight> query(String origin, String destination) {

		if (origin != null && destination != null) {
			List<Flight> result = flightsList.stream()
					.filter(flight -> origin.equals(flight.getOrigin()) && destination.equals(flight.getDestination()))
					.collect(Collectors.toList());

			return result;
		}

		return null;
	}

}
