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
public class Image {
	private String id;
	private String customer_id;
	private String camera_make;
	private String camera_model;
	private Date createdDateTime;
	private Date updatedDateTime;

	public Image(String id, String customer_id, String camera_make, String camera_model, Date date) {
		this.id = id;
		this.customer_id = customer_id;
		this.camera_make = camera_make;
		this.camera_model = camera_model;
		this.createdDateTime = date;
		this.updatedDateTime = date;
	}

	public void setCustomerId(String customer_id) {
		this.customer_id = customer_id;
	}

	public void setCameraMake(String camera_make) {
		this.camera_make = camera_make;
	}

	public void setCameraModel(String camera_model) {
		this.camera_model = camera_model;
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

	public String getCameraMake() {
		return this.camera_make;
	}
	
	public String getCameraModel() {
		return this.camera_model;
	}
	
	public Date getCreatedDate() {
		return this.createdDateTime;
	}

	public Date getUpdatedDate() {
		return this.updatedDateTime;
	}

}
