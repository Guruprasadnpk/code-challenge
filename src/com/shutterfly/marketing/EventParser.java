package com.shutterfly.marketing;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author gkuppuswamy Class to parse the Event to corresponding object:
 *         Customer, Image, Site Visit or Order
 */
public class EventParser {

	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private void parseEvents(JSONArray events) {
		Iterator<?> eventsIterator = events.iterator(); // groups the events by
														// type, event_time
		System.out.println("####################");
		eventsIterator = events.iterator();

		while (eventsIterator.hasNext()) {
			JSONObject event = (JSONObject) eventsIterator.next();
			String event_type = (String) event.get("type");
			if (event_type == null) {
				System.out.println("Event Type not found");
			} else {
				switch (event_type) {
				case "CUSTOMER":
					parseCustomer(event);
					break;
				case "SITE_VISIT":
					parseSiteVisit(event);
					break;
				case "IMAGE":
					parseImage(event);
					break;
				case "ORDER":
					parseOrder(event);
					break;
				}
			}
		}
	}
	
	private void parseCustomer(JSONObject customer) {
		String verb = (String)customer.get("verb");
		if (verb.equalsIgnoreCase("NEW")) {
			
		}
	}

}
