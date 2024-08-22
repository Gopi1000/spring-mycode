package com.scube.crm.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name = "role")
public class RoleVO extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long roleid;

	private String rolename;

	private List<PrivilegeVO> privilegelist;

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	@Column(name = "role_name")
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@ManyToMany/*(fetch = FetchType.EAGER)*/
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "role_privilege", 
	joinColumns = { @JoinColumn(name = "role_id") }, 
    inverseJoinColumns = {@JoinColumn(name = "privilege_id") })
	
	public List<PrivilegeVO> getPrivilegelist() {
		return privilegelist;
	}

	public void setPrivilegelist(List<PrivilegeVO> privilegelist) {
		this.privilegelist = privilegelist;
	}

}
