package com.shutterfly.marketing;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author gkuppuswamy
 * The Customer class represents a customer of Shutterfly.
 * A customer will be created if an event type is Customer and the 
 * action of the event is NEW. The object will be updated if the
 * action is UPDATE. 
 * 
 */
public class Customer {
	private int id;
	private String last_name;
	private String city;
	private String state;
	private Date createdDateTime;
	private Date updatedDateTime;
	// Need to create 3 more classes ***
	//private ArrayList<Order> orders;
	//private ArrayList<SiteVisit> site_visits;
	//private ArrayList<Image> images;

	public Customer(String type, int id, String last_name, String city, String state, Date date) {
		this.id = id;
		this.last_name = last_name;
		this.city = city;
		this.state = state;
		this.createdDateTime = date;
		this.updatedDateTime = date;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCreatedDate(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public void setUpdatedDate(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String setLastName() {
		return this.last_name;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public Date setCreatedDate() {
		return this.createdDateTime;
	}

	public Date setUpdatedDate() {
		return this.updatedDateTime;
	}

}
