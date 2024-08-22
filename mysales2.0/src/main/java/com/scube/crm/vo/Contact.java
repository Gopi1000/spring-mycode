package com.scube.crm.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "contact")
public class Contact extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long contactid;

	private String primaryaddress;

	private String permanentaddress;

	private String primarycontactnumber;

	private String permanentcontactnumber;

	private String customerownername;

	private Customer customerVO;

	@Id
	@GeneratedValue
	public long getContactid() {
		return contactid;
	}

	public void setContactid(long contactid) {
		this.contactid = contactid;
	}
	

	@Column(name = "primary_address")
	public String getPrimaryaddress() {
		return primaryaddress;
	}

	public void setPrimaryaddress(String primaryaddress) {
		this.primaryaddress = primaryaddress;
	}

	@Column(name = "permanent_address")
	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	@Column(name = "primary_contact_number")
	public String getPrimarycontactnumber() {
		return primarycontactnumber;
	}

	public void setPrimarycontactnumber(String primarycontactnumber) {
		this.primarycontactnumber = primarycontactnumber;
	}

	@Column(name = "permanent_contact_number")
	public String getPermanentcontactnumber() {
		return permanentcontactnumber;
	}

	public void setPermanentcontactnumber(String permanentcontactnumber) {
		this.permanentcontactnumber = permanentcontactnumber;

	}

	@Column(name = "customer_name")
	public String getCustomerownername() {
		return customerownername;
	}

	public void setCustomerownername(String customerownername) {
		this.customerownername = customerownername;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
    public Customer getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(Customer customerVO) {
		this.customerVO = customerVO;
	}

}
