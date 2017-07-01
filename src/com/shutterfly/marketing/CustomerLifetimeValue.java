package com.shutterfly.marketing;
/*
 * input_2_customers - main function which calls the ingest
 * to ingest the data and TopXSimpleLTVCustomers - calculate the Lifetime 
 * values of the customers and writes the top n customers to the output file
 * 
 * * ******** External jar required: json-simple-1.1.1 *********
 */
import java.io.FileNotFoundException;
import java.text.ParseException;

public class CustomerLifeTimeValue {
	public static void main(String[] args) throws FileNotFoundException {
		try {
		CustomerLifetimeValueHelper cltv = new CustomerLifetimeValueHelper();
		// events are provided in input file which are ingested into data
		Object data = cltv.ingest("/input/input_3_customers.txt");
		final String dir = System.getProperty("user.dir");
		String out_dir = dir + "/output/";
		// Call TopXSimpleLTVCustomers to get top N customers with highest LTV
		cltv.TopXSimpleLTVCustomers(3, data, out_dir);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
