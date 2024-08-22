package com.demo.springboot.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "Customer")
public class CustomersBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -409940941234187977L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "customer_name")
	private String customername;

	@Column(name = "Email")
	private String email;

	@Column(name = "customer_details")
	private String customerdetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Event event;

	public CustomersBo() {

	}

	public CustomersBo(Long id, String customername, String email, String customerdetails) {
		super();
		this.id = id;
		this.customername = customername;
		this.email = email;
		this.customerdetails = customerdetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerdetails() {
		return customerdetails;
	}

	public void setCustomerdetails(String customerdetails) {
		this.customerdetails = customerdetails;
	}

}
