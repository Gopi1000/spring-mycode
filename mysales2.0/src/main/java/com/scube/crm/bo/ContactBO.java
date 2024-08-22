package com.scube.crm.bo;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ContactBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private long contactid;
	
	private String primaryaddress;
	
	private String permanentaddress;
	
	@NotEmpty
	@Range(min = 1111111111, message = "Mobile Number must be a 10 Digits")
	private String primarycontactnumber;
	
	@NotEmpty
	@Range(min = 1111111111, message = "Mobile Number must be a 10 Digits")
	private String permanentcontactnumber;
	
	private String customerownername;

	private ClientBO clientbo;
	
	private int sNo;
	
	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	//private List<ContactBO> contactlist;
	
	
	/*public List<ContactBO> getContactlist() {
		return contactlist;
	}

	public void setContactlist(List<ContactBO> contactlist) {
		this.contactlist = contactlist;
	}*/

	public String getCustomerownername() {
		return customerownername;
	}

	public void setCustomerownername(String customerownername) {
		this.customerownername = customerownername;
	}
	
	

	public ClientBO getClientbo() {
		return clientbo;
	}

	public void setClientbo(ClientBO clientbo) {
		this.clientbo = clientbo;
	}

	/*public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}*/

	public String getPrimaryaddress() {
		return primaryaddress;
	}

	public long getContactid() {
		return contactid;
	}

	public void setContactid(long contactid) {
		this.contactid = contactid;
	}

	public void setPrimaryaddress(String primaryaddress) {
		this.primaryaddress = primaryaddress;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getPrimarycontactnumber() {
		return primarycontactnumber;
	}

	public void setPrimarycontactnumber(String primarycontactnumber) {
		this.primarycontactnumber = primarycontactnumber;
	}

	public String getPermanentcontactnumber() {
		return permanentcontactnumber;
	}

	public void setPermanentcontactnumber(String permanentcontactnumber) {
		this.permanentcontactnumber = permanentcontactnumber;
	}

	

}
