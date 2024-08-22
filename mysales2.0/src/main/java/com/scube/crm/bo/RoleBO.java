package com.scube.crm.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class RoleBO {
	
	
	private long roleid;
	
	@NotBlank(message="Role Name can not be empty")
	private String rolename;
	
	private int sno;
	
	private List<PrivilegeBO> privilegelist;
	
	private String[] privilegenames;
	
	//private String privilegenames;
	
	

	

	public String[] getPrivilegenames() {
		return privilegenames;
	}

	public void setPrivilegenames(String[] privilegenames) {
		this.privilegenames = privilegenames;
	}

	
	/*public String getPrivilegenames() {
		return privilegenames;
	}

	public void setPrivilegenames(String privilegenames) {
		this.privilegenames = privilegenames;
	}*/

	public List<PrivilegeBO> getPrivilegelist() {
		return privilegelist;
	}

	public void setPrivilegelist(List<PrivilegeBO> privilegelist) {
		this.privilegelist = privilegelist;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	


}
