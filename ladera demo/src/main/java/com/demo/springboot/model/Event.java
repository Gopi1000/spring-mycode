package com.demo.springboot.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "event")
public class Event implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 2364016244414004630L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventid;

	@Column(name = "event_name")
	private String eventname;

	@Column(name = "event_details")
	private String eventdetails;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private CustomersBo customerbo;

	public Event() {
	}

	public Event(long eventid, String eventname, String eventdetails, CustomersBo customerbo) {
		super();
		this.eventid = eventid;
		this.eventname = eventname;
		this.eventdetails = eventdetails;
		this.customerbo = customerbo;
	}

	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getEventdetails() {
		return eventdetails;
	}

	public void setEventdetails(String eventdetails) {
		this.eventdetails = eventdetails;
	}

	@JsonBackReference
	public CustomersBo getCustomerbo() {
		return customerbo;
	}

	public void setCustomerbo(CustomersBo customerbo) {
		this.customerbo = customerbo;
	}

}
