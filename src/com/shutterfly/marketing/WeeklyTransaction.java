package com.shutterfly.marketing;

import java.util.Date;

public class WeeklyTransaction {
	private String customer_id;
	private Double expenditure=0;
	private int no_of_visits=0;
	int week;
	private Date week_start;
	private Date week_end;

	public WeeklyTransaction(String customer_id, int week, Date week_start, Date week_end) {
		this.customer_id = customer_id;
		this.week = week;
		this.week_start = week_start;
		this.week_end = week_end;
	}

	public WeeklyTransaction(String customer_id, int week, Date week_start, Date week_end, Double expenditure) {
		this.customer_id = customer_id;
		this.week = week;
		this.week_start = week_start;
		this.week_end = week_end;
		this.expenditure = expenditure;
		this.no_of_visits = 0;
	}

	public WeeklyTransaction(String customer_id, int week, Date week_start, Date week_end, int no_of_visits) {
		this.customer_id = customer_id;
		this.week = week;
		this.week_start = week_start;
		this.week_end = week_end;
		this.expenditure = 0.0;
		this.no_of_visits = no_of_visits;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public void setWeekStart(Date week_start) {
		this.week_start = week_start;
	}

	public void setWeekEnd(Date week_end) {
		this.week_start = week_end;
	}

	public void addVisit() {
		this.no_of_visits++;
	}

	public void addExpenditure(Double expenditure) {
		this.expenditure += expenditure;
	}

	public int getWeek() {
		return this.week;
	}

	public Date getWeekStart() {
		return this.week_start;
	}

	public Date getWeekEnd() {
		return this.week_start;
	}

	public int getNoOfVisits() {
		return this.no_of_visits;
	}

	public Double getExpenditure() {
		return this.expenditure;
	}

}
