
package com.shutterfly.marketing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author gkuppuswamy EventReader Class implements the ingest method which
 *         loads the events into im-memory data structure which can be later
 *         used to calculate LifeTime Value of each customer.
 * 
 */
public class EventReader {
	static Date timeframe_start = null;
	static Date timeframe_end = null;
	static HashMap<String, HashMap<Integer, WeeklyTransaction>> customerTransactions = new HashMap<String, HashMap<Integer, WeeklyTransaction>>();
	static NavigableMap<Date, Integer> ranges = null;
    static int no_of_weeks = 0;
    static HashMap<String, Customer> customers = null;
	HashMap<String, Customer> readEvents(File input_file) throws java.text.ParseException {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray events = (JSONArray) jsonParser.parse(new FileReader(input_file));
			events.sort(new EventComparator());
			EventParser eventParser = new EventParser();
			eventParser.parseEvents(events);
			timeframe_start = eventParser.getStartDateTime();
			timeframe_end = eventParser.getEndDateTime();

			customers = eventParser.getCustomers();
			HashMap<String, HashSet<SiteVisit>> sitevisits = eventParser.getSiteVisits();
			HashMap<String, HashSet<Order>> orders = eventParser.getOrders();
			HashMap<String, HashSet<Image>> images = eventParser.getImages();
			TimeFrame timeframe = new TimeFrame();
			ranges = timeframe.generateWeeks(timeframe_start, timeframe_end);
			no_of_weeks = ranges.lastEntry().getValue();

			Iterator it = customers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				Customer customer_obj = (Customer) pair.getValue();
				String customer_id = (String) pair.getKey();

				customer_obj.setSiteVisits(sitevisits.get(customer_id));
				customer_obj.setOrders(orders.get(customer_id));
				customer_obj.setImages(images.get(customer_id));
			}
			return customers;
		} catch (ParseException parse_Ex) {
			parse_Ex.printStackTrace();
		} catch (FileNotFoundException fileNotFound_Ex) {
			fileNotFound_Ex.printStackTrace();
		} catch (IOException io_Ex) {
			io_Ex.printStackTrace();
		}
		return null;
	}

    void ingest(String file) throws java.text.ParseException {
		EventReader eventreader = new EventReader();
		final String dir = System.getProperty("user.dir");
		HashMap<String, Customer> data = eventreader.readEvents(new File(dir + file));
		Iterator dataIterator = data.entrySet().iterator();
		while (dataIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) dataIterator.next();
			String customer_id = (String) pair.getKey();
			HashSet<SiteVisit> sitevisits = ((Customer) pair.getValue()).getSiteVisits();
			eventreader.summarizeWeeklyCustomerSiteVisits(customer_id, sitevisits);
			HashSet<Order> orders = ((Customer) pair.getValue()).getOrders();
		}
	}

	/*
	 * Summarizes the visits and expenditure by week per customer
	 */
	 void summarizeWeeklyCustomerSiteVisits(String customer_id, HashSet<SiteVisit> site_visits) {
		Iterator sitevisitIterator = site_visits.iterator();
		while (sitevisitIterator.hasNext()) {
			SiteVisit sitevisit = (SiteVisit) sitevisitIterator.next();
			int week = ranges.floorEntry(sitevisit.getUpdatedDate()).getValue();
			Date week_start = ranges.floorEntry(sitevisit.getUpdatedDate()).getKey();
			Calendar cal = Calendar.getInstance();
			cal.setTime(week_start);
			cal.add(Calendar.DAY_OF_YEAR, 6);
			Date week_end = cal.getTime();
			if (customerTransactions.containsKey(customer_id)) {
				if (customerTransactions.get(customer_id).containsKey(week))
					customerTransactions.get(customer_id).get(week).addVisit(); // Test
																				// this
				else {
					WeeklyTransaction weekly_transaction = new WeeklyTransaction(customer_id, week, week_start,
							week_end, 1);
					customerTransactions.get(customer_id).put(week, weekly_transaction);
				}
			} else {
				WeeklyTransaction weekly_transaction = new WeeklyTransaction(customer_id, week, week_start, week_end,
						1);
				HashMap<Integer, WeeklyTransaction> weeklytransactions = new HashMap<Integer, WeeklyTransaction>();
				weeklytransactions.put(week, weekly_transaction);
				customerTransactions.put(customer_id, weeklytransactions);
			}

		}
	}

	/*
	 * Summarizes the visits and expenditure by week per customer
	 */

	 void summarizeWeeklyOrders(String customer_id, HashSet<Order> orders) {
		Iterator orderIterator = orders.iterator();
		while (orderIterator.hasNext()) {
			Order order = (Order) orderIterator.next();
			int week = ranges.floorEntry(order.getUpdatedDate()).getValue();
			Date week_start = ranges.floorEntry(order.getUpdatedDate()).getKey();
			Calendar cal = Calendar.getInstance();
			cal.setTime(week_start);
			cal.add(Calendar.DAY_OF_YEAR, 6);
			Date week_end = cal.getTime();
			if (customerTransactions.containsKey(customer_id)) {
				if (customerTransactions.get(customer_id).containsKey(week))
					customerTransactions.get(customer_id).get(week).addExpenditure(order.getTotalAmount()); // Test
				// this
				else {
					WeeklyTransaction weekly_transaction = new WeeklyTransaction(customer_id, week, week_start,
							week_end, order.getTotalAmount());
					customerTransactions.get(customer_id).put(week, weekly_transaction);
				}
			} else {
				WeeklyTransaction weekly_transaction = new WeeklyTransaction(customer_id, week, week_start, week_end,
						order.getTotalAmount());
				HashMap<Integer, WeeklyTransaction> weeklytransactions = new HashMap<Integer, WeeklyTransaction>();
				weeklytransactions.put(week, weekly_transaction);
				customerTransactions.put(customer_id, weeklytransactions);
			}
		}
	}
	
	public HashMap<String, HashMap<Integer, WeeklyTransaction>> getCustomersTransactions() {
		return this.customerTransactions;
	}
	
	
	public int getNoOfWeeks() {
		return this.no_of_weeks;
	}
	
	public HashMap<String, Customer> getCustomers() {
		return customers;
	}
}
