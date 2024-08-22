package com.scube.crm.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
public class PrivilegeVO extends BasicEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "privilege_id")
	private long privilegeid;
	
	@Column(name = "privilege_name")
	private String privilegename;
	

	public long getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(long privilegeid) {
		this.privilegeid = privilegeid;
	}

	public String getPrivilegename() {
		return privilegename;
	}

	public void setPrivilegename(String privilegename) {
		this.privilegename = privilegename;
	}
	


}
