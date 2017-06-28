
package com.shutterfly.marketing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author gkuppuswamy
 * EventReader Class implements the ingest method which loads the events
 * into im-memory data structure which can be later used to calculate 
 * LifeTime Value of each customer.
 * 
 */
public class EventReader {
	void ingest(File input_file) {
		JSONParser eventParser = new JSONParser();
		try {
			JSONArray events = (JSONArray) eventParser.parse(new FileReader(input_file));
			Iterator<?> jsonIterator = events.iterator();
			while (jsonIterator.hasNext()) {
				JSONObject event = (JSONObject) jsonIterator.next();
				String type = (String) event.get("type");
			}

		} catch (ParseException parse_Ex) {
			parse_Ex.printStackTrace();
		} catch (FileNotFoundException fileNotFound_Ex) {
			fileNotFound_Ex.printStackTrace();
		} catch (IOException io_Ex) {
			io_Ex.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		EventReader eventreader = new EventReader();
		final String dir = System.getProperty("user.dir");
		eventreader.ingest(new File(dir + "/sample_input/events.txt"));
	}
}
