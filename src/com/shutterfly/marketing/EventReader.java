
package com.shutterfly.marketing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

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
	static Date timeframe_start =  null;
	static Date timeframe_end =  null;
	HashMap<String, Customer> ingest(File input_file) throws java.text.ParseException {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray events = (JSONArray) jsonParser.parse(new FileReader(input_file));
			events.sort(new EventComparator());
			EventParser eventParser = new EventParser();
			eventParser.parseEvents(events);
			timeframe_start = eventParser.getStartDateTime();
			timeframe_end = eventParser.getEndDateTime();

			HashMap<String, Customer> customers = eventParser.getCustomers();
			HashMap<String, HashSet<SiteVisit>> sitevisits = eventParser.getSiteVisits();
			HashMap<String, HashSet<Order>> orders = eventParser.getOrders();
			HashMap<String, HashSet<Image>> images = eventParser.getImages();

			Iterator it = customers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				((Customer) pair.getValue()).setSiteVisits(sitevisits.get((Customer) pair.getKey()));
				((Customer) pair.getValue()).setOrders(orders.get((Customer) pair.getKey()));
				((Customer) pair.getValue()).setImages(images.get((Customer) pair.getKey()));
				it.remove();
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

	public static void main(String args[]) throws java.text.ParseException {
		EventReader eventreader = new EventReader();
		final String dir = System.getProperty("user.dir");
		HashMap<String, Customer> data = eventreader.ingest(new File(dir + 
				"/sample_input/events.txt"));
	}
}
