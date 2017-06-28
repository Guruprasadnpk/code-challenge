package com.shutterfly.marketing;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.json.simple.JSONObject;

/**
 * @author gkuppuswamy The EventTmeComparator Class implements Comparator
 *         interface to compare two JSONObjects by event time. The compare
 *         method has been overridden to compare the elements by datetime.
 * 
 *         This comparator is used to sort the Events stored in JSONArray by
 *         event_time.
 */
public class EventTimeComparator implements Comparator<JSONObject> {
	private static final String KEY_NAME = "event_time";

	@Override
	public int compare(JSONObject o1, JSONObject o2) {
		Date event_time_o1 = null;
		Date event_time_o2 = null;
		SimpleDateFormat simpleDateFormat = new 
				SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		try {
			event_time_o1 = simpleDateFormat.parse((String) o1.get(KEY_NAME));
			event_time_o2 = simpleDateFormat.parse((String) o2.get(KEY_NAME));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		if (event_time_o1.before(event_time_o2))
			return -1;
		else if (event_time_o1.after(event_time_o2))
			return 1;
		else
			return 0;
	}
}