package com.shutterfly.marketing;

import java.util.Date;

/**
 * @author gkuppuswamy
 * The Customer class represents a customer of Shutterfly.
 * A customer will be created if an event type is Customer and the 
 * action of the event is NEW. The object will be updated if the
 * action is UPDATE. 
 * 
 */
public class Order {
	private String id;
	private String customer_id;
	private Double total_amount;
	private String currency;
	private Date createdDateTime;
	private Date updatedDateTime;

	public Order(String id, String customer_id, Double total_amount, String currency, Date date) {
		this.id = id;
		this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.currency = currency;
		this.createdDateTime = date;
		this.updatedDateTime = date;
	}

	public void setCustomerId(String customer_id) {
		this.customer_id = customer_id;
	}

	public void setTotalAmount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCreatedDate(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = createdDateTime;
	}

	public String getId() {
		return this.id;
	}

	public String getCustomerId() {
		return this.customer_id;
	}

	public Double getTotalAmount() {
		return this.total_amount;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public Date getCreatedDate() {
		return this.createdDateTime;
	}

	public Date getUpdatedDate() {
		return this.updatedDateTime;
	}
}

