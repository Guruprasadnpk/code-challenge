package com.shutterfly.marketing;

import java.util.Date;

/**
 * @author gkuppuswamy The Customer class represents a customer of Shutterfly. A
 *         customer will be created if an event type is Customer and the action
 *         of the event is NEW. The object will be updated if the action is
 *         UPDATE.
 * 
 */
public class Image {
	private String id;
	private String customer_id;
	private String camera_make;
	private String camera_model;
	private Date createdDateTime;
	private Date updatedDateTime;

	// Creates an Image
	public Image(String id, String customer_id, String camera_make,
			String camera_model, Date date) {
		this.id = id;
		this.customer_id = customer_id;
		this.camera_make = camera_make;
		this.camera_model = camera_model;
		this.createdDateTime = date;
		this.updatedDateTime = date;
	}

	// sets customer id
	public void setCustomerId(String customer_id) {
		this.customer_id = customer_id;
	}

	// sets camera make
	public void setCameraMake(String camera_make) {
		this.camera_make = camera_make;
	}

	// sets camera model
	public void setCameraModel(String camera_model) {
		this.camera_model = camera_model;
	}

	// sets created date & updated date
	public void setCreatedDate(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = createdDateTime;
	}

	// returns customer image id
	public String getId() {
		return this.id;
	}

	// returns customer id
	public String getCustomerId() {
		return this.customer_id;
	}

	// returns camera make
	public String getCameraMake() {
		return this.camera_make;
	}

	// returns camera model
	public String getCameraModel() {
		return this.camera_model;
	}

	// returns created date
	public Date getCreatedDate() {
		return this.createdDateTime;
	}

	// returns updated date
	public Date getUpdatedDate() {
		return this.updatedDateTime;
	}

	// returns hashcode value for customer id and id.
	@Override
	public int hashCode() {
		return (id + customer_id).hashCode();
	}

	// Compares the Images
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Image) {
			Image image = (Image) obj;
			return (image.id.equals(this.id)
					&& image.customer_id.equals(this.customer_id));
		} else {
			return false;
		}
	}

}
