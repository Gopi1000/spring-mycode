package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
    private int customerId;
	
	private String customerName;
	
	private String emailid;

	private String address;
	
    private String mobileNo;

    private String password;
    
    public Customer() {
    	
    }
	public Customer(int customerId, String customerName, String emailid, String address, String mobileNo,
			String password) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.emailid = emailid;
		this.address = address;
		this.mobileNo = mobileNo;
		this.password = password;
		
	}
	@Id
	@GeneratedValue
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
