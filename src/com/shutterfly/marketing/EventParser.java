package com.shutterfly.marketing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author gkuppuswamy Class to parse the Event to corresponding object:
 *         Customer, Image, Site Visit or Order
 */
public class EventParser {

	private static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private static HashMap<String, HashSet<SiteVisit>> site_visits = new HashMap<String, HashSet<SiteVisit>>();
	private static HashMap<String, HashSet<Order>> orders = new HashMap<String, HashSet<Order>>();
	private static HashMap<String, HashSet<Image>> images = new HashMap<String, HashSet<Image>>();

	void parseEvents(JSONArray events) throws ParseException {
		Iterator<?> eventsIterator = events.iterator(); // groups the events by
		// type, event_time
		eventsIterator = events.iterator();

		while (eventsIterator.hasNext()) {
			JSONObject event = (JSONObject) eventsIterator.next();
			String event_type = (String) event.get("type");
			if (event_type != null) {
				switch (event_type) {
				case "CUSTOMER":
					parseCustomer(event);
					break;
				case "SITE_VISIT":
					parseSiteVisit(event);
					break;
				/*
				 * case "IMAGE": parseImage(event); break; case "ORDER":
				 * parseOrder(event); break;
				 */
				}
			}
		}
		System.out.println(site_visits.size());
	}

	// Method to parse customer event
	private void parseCustomer(JSONObject customerEvent) throws ParseException {
		Customer customer = null;
		String verb = (String) customerEvent.get("verb");
		String customer_id = (String) customerEvent.get("key");
		String last_name = (String) customerEvent.get("last_name");
		String city = (String) customerEvent.get("adr_city");
		String state = (String) customerEvent.get("adr_state");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		Date date = dateFormat.parse((String) customerEvent.get("event_time"));
		if ("NEW".equalsIgnoreCase(verb) && customer_id != null && !customers.containsKey("customer_id")) {
			customer = new Customer(customer_id, last_name, city, state, date);
		} else if ("UPLOAD".equalsIgnoreCase(verb) && customer_id != null && customers.containsKey("customer_id")) {
			customer = customers.get("customer_id");
			customer.setCity(city);
			customer.setLastName(last_name);
			customer.setState(state);
			customer.setUpdatedDate(date);
		}
		if (customer_id != null)
			customers.put(customer_id, customer);
	}
// Method to parse site visit
	private void parseSiteVisit(JSONObject siteVisitEvent) throws ParseException {
		SiteVisit site_visit = null;
		String verb = (String) siteVisitEvent.get("verb");
		String site_visit_id = (String) siteVisitEvent.get("key");
		String customer_id = (String) siteVisitEvent.get("customer_id");
		JSONArray tagsJson = (JSONArray) siteVisitEvent.get("tags");
		HashMap<String, String> tags = new HashMap<String, String>();
		Iterator<?> it = tagsJson.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			Iterator pair = jsonObject.entrySet().iterator();
			Entry entry = null;
			while (pair.hasNext()) {
				entry = (Entry) pair.next();
				tags.put((String) entry.getKey(), (String) entry.getValue());
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		Date date = dateFormat.parse((String) siteVisitEvent.get("event_time"));
		if ("NEW".equalsIgnoreCase(verb) && site_visit_id != null && !site_visits.containsKey("site_visit_id")) {
			site_visit = new SiteVisit(site_visit_id, customer_id, tags, date);
		}
		if (customer_id != null && site_visits.containsKey(customer_id)) {
			site_visits.get(customer_id).add(site_visit);
		} else {
			HashSet<SiteVisit> customer_site_visits = new HashSet<SiteVisit>();
			customer_site_visits.add(site_visit);
			site_visits.put(customer_id, customer_site_visits);
		}
	}

	// Method to parse order
	private void parseOrder(JSONObject orderEvent) throws ParseException {
		Order order = null;
		String verb = (String) orderEvent.get("verb");
		String order_id = (String) orderEvent.get("key");
		String customer_id = (String) orderEvent.get("customer_id");
		String total_amount_str = (String)orderEvent.get("total_amount");
		HashMap<String, String> tags = new HashMap<String, String>();
		Iterator<?> it = tagsJson.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			Iterator pair = jsonObject.entrySet().iterator();
			Entry entry = null;
			while (pair.hasNext()) {
				entry = (Entry) pair.next();
				tags.put((String) entry.getKey(), (String) entry.getValue());
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		Date date = dateFormat.parse((String) orderEvent.get("event_time"));
		if ("NEW".equalsIgnoreCase(verb) && order_id != null && !orders.containsKey("order_id")) {
			order = new Order(order_id, customer_id, total_amount, currency, date);
		}
		if (customer_id != null && orders.containsKey(customer_id)) {
			orders.get(customer_id).add(order);
		} else {
			HashSet<Order> customer_orders = new HashSet<Order>();
			customer_orders.add(order);
			orders.put(customer_id, customer_orders);
		}
	}
	

}
