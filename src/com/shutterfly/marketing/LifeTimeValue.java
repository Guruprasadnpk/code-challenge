package com.shutterfly.marketing;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author gkuppuswamy LifeTimeValue consolidates the customer, his/her no of
 *         visits per week, expenditures per visit, total expenditure, lifespan,
 *         lifetime value of the customer
 */
public class LifeTimeValue {
	private Customer customer;
	private BigDecimal total_no_of_visits_per_week;
	private BigDecimal total_expenditures_per_visit;
	private BigDecimal total_expenditure;
	private int lifespan;
	private BigDecimal lifetimevalue;
	private int no_of_weeks;

	/*
	 * creates a LifeTimeValue
	 * 
	 */
	public LifeTimeValue(Customer customer, BigDecimal no_of_visits_per_week,
			int lifespan, BigDecimal expenditures_per_visit,
			BigDecimal total_exp, int no_of_weeks) {
		this.customer = customer;
		this.total_no_of_visits_per_week = no_of_visits_per_week;
		this.lifespan = lifespan;
		this.total_expenditures_per_visit = expenditures_per_visit;
		this.no_of_weeks = no_of_weeks;
		this.total_expenditure = total_exp;
		calculateLTV();
	}

	/*
	 * calculates the lifetime values LifeTime Value = 52 * (customer
	 * expenditures per visit (USD) * number of site visits per week) * lifespan
	 */
	void calculateLTV() {
		this.lifetimevalue = (total_no_of_visits_per_week
				.divide(new BigDecimal(this.no_of_weeks), 5,
						RoundingMode.HALF_UP)
				.multiply(total_expenditures_per_visit)
				.divide(new BigDecimal(this.no_of_weeks), 5,
						RoundingMode.HALF_UP)
				.multiply(new BigDecimal(this.no_of_weeks))
				.multiply(new BigDecimal(52 * lifespan)));
	}

	/* sets number of visits and calculates LifeTime Value */
	public void setNoOfVisits(BigDecimal no_of_visits_per_week) {
		this.total_no_of_visits_per_week = no_of_visits_per_week;
		calculateLTV();
	}

	/* sets weekly expenditure and calculates LifeTime Value */
	public void setTotalWeeklyExp(BigDecimal expenditures_per_visit) {
		this.total_expenditures_per_visit = expenditures_per_visit;
		calculateLTV();
	}

	// sets lifespan
	public void setLifeSpan(int lifespan) {
		this.lifespan = lifespan;
		calculateLTV();
	}

	// returns number of visits
	public BigDecimal getNoOfVisits() {
		return this.total_no_of_visits_per_week;
	}

	// returns number of expenditure per visit
	public BigDecimal getExpPerVisit() {
		return this.total_expenditures_per_visit;
	}

	// returns lifetime value
	public BigDecimal getLTV() {
		return this.lifetimevalue;
	}

	// returns lifespan
	public int getLifeSpan() {
		return this.lifespan;
	}

	// returns total expenditure
	public BigDecimal getTotalExpenditure() {
		return this.total_expenditure;
	}

	// returns customer
	public Customer getCustomer() {
		return this.customer;
	}
}
