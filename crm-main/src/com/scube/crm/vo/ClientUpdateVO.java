/**
 * 
 */
package com.scube.crm.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * @author User
 *
 */
@Entity
@Table(name="client_update_status")
public class ClientUpdateVO extends BasicEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long updateid;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	private String date;
	
	
	
	@ManyToOne
	@JoinColumn(name="id")
	private EmployerVO VO;
	
	
	/**
	 * @return the adminVO
	 */
	public AdminLoginVO getAdminVO() {
		return adminVO;
	}
	/**
	 * @param adminVO the adminVO to set
	 */
	public void setAdminVO(AdminLoginVO adminVO) {
		this.adminVO = adminVO;
	}
	@ManyToOne
	@JoinColumn(name="admin_id")
	private AdminLoginVO adminVO;
	
	
	/**
	 * @return the updateid
	 */
	public long getUpdateid() {
		return updateid;
	}
	/**
	 * @param updateid the updateid to set
	 */
	public void setUpdateid(long updateid) {
		this.updateid = updateid;
	}
	/**
	 * @return the vO
	 */
	public EmployerVO getVO() {
		return VO;
	}
	/**
	 * @param vO the vO to set
	 */
	public void setVO(EmployerVO vO) {
		VO = vO;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	/**
	 * @return the date
	 */
	
	
	
	
	
	
	

}
