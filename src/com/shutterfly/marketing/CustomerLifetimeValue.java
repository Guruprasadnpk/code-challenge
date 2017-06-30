package com.shutterfly.marketing;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomerLifetimeValue {
	private int LIFESPAN = 10;
	List<LifeTimeValue> lifetimevalues = new ArrayList<LifeTimeValue>();
	int no_of_weeks = 0;
	HashMap<String, Customer> customers = null;
	
	void calculateRevenue() throws ParseException{
		EventReader eventreader = new EventReader();
		eventreader.ingest("/sample_input/events.txt");
		HashMap<String, HashMap<Integer, WeeklyTransaction>> customersTransactions = 
				eventreader.getCustomersTransactions();
		no_of_weeks = eventreader.getNoOfWeeks();
		customers = eventreader.getCustomers(); 
		Iterator transactionIterator = customersTransactions.entrySet().iterator();
		while(transactionIterator.hasNext()) {
			Map.Entry pair = (Map.Entry)transactionIterator.next();
			String customer_id = (String)pair.getKey();
			HashMap<Integer, WeeklyTransaction> weeklyTransactions = 
					(HashMap<Integer, WeeklyTransaction>)pair.getValue();
			summarizeAvgWeeklyExp(customer_id, weeklyTransactions);
		}
	}
	
	void summarizeAvgWeeklyExp(String customer_id, HashMap<Integer, WeeklyTransaction> weeklyTransactions) {
		int no_of_visits_per_week =  0;
		Double revenue_per_visit =0.0;
		
		int total_no_of_visits = 0;
		Double total_weekly_rev_per_visit = 0.0;
		Iterator transactionIterator = weeklyTransactions.entrySet().iterator();
		while(transactionIterator.hasNext()) {
			Map.Entry pair = (Map.Entry)transactionIterator.next();
			no_of_visits_per_week = ((WeeklyTransaction)pair.getValue()).getNoOfVisits();
			revenue_per_visit = ((WeeklyTransaction)pair.getValue()).getExpenditurePerVisit();
			total_no_of_visits += no_of_visits_per_week;
			total_weekly_rev_per_visit += revenue_per_visit;
		}
		LifeTimeValue lifetimevalue = new LifeTimeValue(customers.get(customer_id),
				total_no_of_visits/no_of_weeks,  LIFESPAN, 
				total_weekly_rev_per_visit/no_of_weeks);
		lifetimevalues.add(lifetimevalue);
	}
	
	public static void main(String args[]) {
		
	}
}
