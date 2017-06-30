package com.shutterfly.marketing;

import java.util.Comparator;

public class LifeTimeValue implements Comparator<LifeTimeValue>{
	private Customer customer;
	private int no_of_visits_per_week;
	private Double expenditures_per_visit;
	private Double avg_cust_value_per_week;
	private int lifespan;
	private Double lifetimevalue;

	public LifeTimeValue(Customer customer, int no_of_visits_per_week, int lifespan, 
			Double expenditures_per_visit) {
		this.customer = customer;
		this.no_of_visits_per_week = no_of_visits_per_week;
		this.lifespan = lifespan;
		this.expenditures_per_visit = expenditures_per_visit;
		this.lifetimevalue = 52 * (no_of_visits_per_week * expenditures_per_visit) * lifespan;
	}

	public void setNoOfVisits(int no_of_visits_per_week) {
		this.no_of_visits_per_week = no_of_visits_per_week;
	}

	public void setTotalWeeklyExp(Double expenditures_per_visit) {
		this.expenditures_per_visit = expenditures_per_visit;
	}

	public void setLifeSpan(int lifespan) {
		this.lifespan = lifespan;
	}

	public int getNoOfVisits() {
		return this.no_of_visits_per_week;
	}
	
	public Double getExpPerVisit() {
		return this.expenditures_per_visit;
	}
	
	public Double getAvgCustVal() {
		return this.avg_cust_value_per_week;
	}
	
	public Double getLTV() {
		return this.lifetimevalue;
	}
	
	public int getLifeSpan() {
		return this.lifespan;
	}
	
	@Override
	public int compare(LifeTimeValue o1, LifeTimeValue o2) {
		Double ltv1 = o1.getLTV();
		Double ltv2 = o2.getLTV();
		return ltv1.compareTo(ltv2);
	}
}
