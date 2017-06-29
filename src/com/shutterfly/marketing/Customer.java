package com.shutterfly.marketing;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author gkuppuswamy The Customer class represents a customer of Shutterfly. A
 *         customer will be created if an event type is Customer and the action
 *         of the event is NEW. The object will be updated if the action is
 *         UPDATE.
 * 
 */
public class Customer {
	private String id;
	private String last_name;
	private String city;
	private String state;
	private Date createdDateTime;
	private Date updatedDateTime;
	private ArrayList<Order> orders;
	private ArrayList<SiteVisit> site_visits;
	private ArrayList<Image> images;

	public Customer(String id, String last_name, String city, String state, Date date) {
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

	public String getLastName() {
		return this.last_name;
	}

	public String getId() {
		return this.id;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public Date getCreatedDate() {
		return this.createdDateTime;
	}

	public Date getUpdatedDate() {
		return this.updatedDateTime;
	}

	public ArrayList<Order> getOrders() {
		return this.orders;
	}

	public ArrayList<SiteVisit> getSiteVisits() {
		return this.site_visits;
	}

	public ArrayList<Image> getImages() {
		return this.images;
	}

}
