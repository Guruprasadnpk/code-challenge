package com.shutterfly.marketing;

import java.util.Date;
import java.util.HashMap;

/**
 * @author gkuppuswamy The Customer class represents a customer of Shutterfly. A
 *         customer will be created if an event type is Customer and the action
 *         of the event is NEW. The object will be updated if the action is
 *         UPDATE.
 * 
 */
public class SiteVisit {
	private String id;
	private String customer_id;
	private HashMap<String, String> tags;
	private Date createdDateTime;
	private Date updatedDateTime;

	public SiteVisit(String id, String customer_id, HashMap<String, String> tags, Date date) {
		this.id = id;
		this.customer_id = customer_id;
		this.tags = tags;
		this.createdDateTime = date;
		this.updatedDateTime = date;
	}

	public void setCustomerId(String customer_id) {
		this.customer_id = customer_id;
	}

	public void setTags(HashMap<String, String> tags) {
		this.tags = tags;
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

	public HashMap<String, String> getTags() {
		return this.tags;
	}

	public Date getCreatedDate() {
		return this.createdDateTime;
	}

	public Date getUpdatedDate() {
		return this.updatedDateTime;
	}
	
    @Override
    public int hashCode() {
    	return (id+customer_id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SiteVisit) {
        	SiteVisit pp = (SiteVisit) obj;
            return (pp.id.equals(this.id) && pp.customer_id.equals(this.customer_id));
        } else {
            return false;
        }
    }

}
