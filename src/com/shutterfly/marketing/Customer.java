package com.shutterfly.marketing;

import java.util.Date;
import java.util.HashSet;

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
	private HashSet<Order> orders;
	private HashSet<SiteVisit> site_visits;
	private HashSet<Image> images;

	// Constructor to create a new customer object.
	public Customer(String id, String last_name, String city, String state, Date date) {
		this.id = id;
		this.last_name = last_name;
		this.city = city;
		this.state = state;
		this.createdDateTime = date;
		this.updatedDateTime = date;
	}

	// sets last name
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	// sets city
	public void setCity(String city) {
		this.city = city;
	}

	// sets state
	public void setState(String state) {
		this.state = state;
	}

	// sets created date
	public void setCreatedDate(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	// sets updated date
	public void setUpdatedDate(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	// sets orders of the customer
	public void setOrders(HashSet<Order> orders) {
		this.orders = orders;
	}

	// sets site visits
	public void setSiteVisits(HashSet<SiteVisit> site_visits) {
		this.site_visits = site_visits;
	}

	// sets images
	public void setImages(HashSet<Image> images) {
		this.images = images;
	}

	// returns customer last name
	public String getLastName() {
		return this.last_name;
	}

	// returns customer Id
	public String getId() {
		return this.id;
	}

	// returns customer city
	public String getCity() {
		return this.city;
	}

	// returns customer state
	public String getState() {
		return this.state;
	}

	// returns createddate
	public Date getCreatedDate() {
		return this.createdDateTime;
	}

	// returns updateddate
	public Date getUpdatedDate() {
		return this.updatedDateTime;
	}

	// returns orders
	public HashSet<Order> getOrders() {
		return this.orders;
	}

	// returns site visits
	public HashSet<SiteVisit> getSiteVisits() {
		return this.site_visits;
	}

	// returns images
	public HashSet<Image> getImages() {
		return this.images;
	}

}
