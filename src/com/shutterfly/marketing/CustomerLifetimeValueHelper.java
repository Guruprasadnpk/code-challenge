package com.shutterfly.marketing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gkuppuswamy CustomerLifetimeValueHelper is a helper class for
 *         CustomerLifetimeValue with the major payload of calculating the
 *         lifetime value of each customer.
 *
 *         The output data if available, will be written to the output file
 *         specified.
 */
public class CustomerLifetimeValueHelper {
	private int LIFESPAN = 10;
	private static List<LifeTimeValue> lifetimevalues = new ArrayList<LifeTimeValue>();
	int no_of_weeks = 0;
	HashMap<String, Customer> customers = null;
	
	/*
	 * ingest method takes the input file with the list of events,
	 * returns the weekly transactions of each customer with required business metrics.
	 */
	HashMap<String, HashMap<Integer, WeeklyTransaction>> ingest(String filename)
			throws ParseException {
		EventReader eventreader = new EventReader();
		eventreader.ingest_file(filename);
		no_of_weeks = eventreader.getNoOfWeeks();
		customers = eventreader.getCustomers();
		return eventreader.getCustomersTransactions();
	}

	/*summarizeAvgWeeklyExp calculates major metrics like total no of visits per week,
	 * expenditures per visit per week and the lifetime values of each customer.
	 * The result is stored in-memory which will be used by 
	 */
	void summarizeAvgWeeklyExp(String customer_id,
			HashMap<Integer, WeeklyTransaction> weeklyTransactions) {
		int no_of_visits_per_week = 0;
		Double revenue_per_visit = 0.0;
		BigDecimal total_no_of_visits = BigDecimal.ZERO;
		BigDecimal total_weekly_rev_per_visit = BigDecimal.ZERO;
		BigDecimal total_exp = BigDecimal.ZERO;
		Iterator transactionIterator = weeklyTransactions.entrySet().iterator();
		while (transactionIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) transactionIterator.next();
			no_of_visits_per_week = ((WeeklyTransaction) pair.getValue())
					.getNoOfVisits();
			revenue_per_visit = ((WeeklyTransaction) pair.getValue())
					.getExpenditurePerVisit();
			total_exp = total_exp.add(new BigDecimal(
					((WeeklyTransaction) pair.getValue()).getExpenditure()));
			BigDecimal bd_no_of_visits_per_week = new BigDecimal(
					no_of_visits_per_week, MathContext.DECIMAL64);
			total_no_of_visits = total_no_of_visits
					.add(bd_no_of_visits_per_week);
			BigDecimal bd_rev_per_visit = new BigDecimal(revenue_per_visit,
					MathContext.DECIMAL64);
			total_weekly_rev_per_visit = total_weekly_rev_per_visit
					.add(bd_rev_per_visit);
		}
		LifeTimeValue lifetimevalue = new LifeTimeValue(
				customers.get(customer_id), total_no_of_visits, LIFESPAN,
				total_weekly_rev_per_visit, total_exp, no_of_weeks);
		lifetimevalues.add(lifetimevalue);
	}

	/*
	 * TopXSimpleLTVCustomers takes the number of customers to be returned,
	 * input data (weekly transactions of the customer) and the directory where the output file 
	 * will be placed, ranks the customers with top N high lifetime values
	 * and writes it to a csv file
	 * 
	 * Note: The filename is a constant with a timestamp attached as follows:
	 * top_n_lifetime_values_yyyyMMddHHmmss.csvs
	 */
	void TopXSimpleLTVCustomers(int n, Object obj, String out_dir)
			throws ParseException, FileNotFoundException {
		HashMap<String, HashMap<Integer, WeeklyTransaction>> customersTransactions = (HashMap<String, HashMap<Integer, WeeklyTransaction>>) obj;

		Iterator transactionIterator = customersTransactions.entrySet()
				.iterator();
		while (transactionIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) transactionIterator.next();
			String customer_id = (String) pair.getKey();
			HashMap<Integer, WeeklyTransaction> weeklyTransactions = (HashMap<Integer, WeeklyTransaction>) pair
					.getValue();
			summarizeAvgWeeklyExp(customer_id, weeklyTransactions);
		}
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date today = Calendar.getInstance().getTime();
		String formatDate = sdf.format(today);
		PrintWriter pw = new PrintWriter(new File(out_dir + "top_" + n
				+ "_lifetime_values_" + formatDate + "test.csv"));
		pw.write("customer_id,last_name,state,lifetime_value\n");
		CustomerLifetimeValueHelper cltv = new CustomerLifetimeValueHelper();

		Collections.sort(lifetimevalues, new Comparator<LifeTimeValue>() {
			public int compare(LifeTimeValue o1, LifeTimeValue o2) {
				return o1.getLTV().compareTo(o2.getLTV());
			}
		});
		Collections.reverse(lifetimevalues);

		if (n > lifetimevalues.size())
			n = lifetimevalues.size();
		for (int i = 0; i < n; i++) {
			LifeTimeValue ltv = lifetimevalues.get(i);
			sb.append('"');
			sb.append(ltv.getCustomer().getId());
			sb.append('"');
			sb.append(',');
			sb.append('"');
			sb.append(ltv.getCustomer().getLastName());
			sb.append('"');
			sb.append(',');
			sb.append('"');
			sb.append(ltv.getCustomer().getState());
			sb.append('"');
			sb.append(',');
			sb.append(ltv.getLTV());
			sb.append('\n');
		}
		pw.write(sb.toString());
		pw.close();
	}
}
