package com.scube.techboot.vo;

import java.util.Date;

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



@Entity
@Table(name="campaign")
public class CampaignVO extends BasicEntity {

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int campaignId;
	private String campaignName;
	private String category;
	private String message;
	private Date startedTime;
	private Date endTime;
	private ProductServiceVO productService;
	private CourseVO course;
	private CompanyVO companyVO;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="campaignId")
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	@Column(name="campaign_Name" , nullable=false, length=50)
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	@Column(name="category" , nullable=false)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name="message" , nullable=false ,columnDefinition="Text")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name="started_Time" , nullable=false ,columnDefinition="DATETIME")
	public Date getStartedTime() {
		return startedTime;
	}
	public void setStartedTime(Date startedTime) {
		this.startedTime = startedTime;
	}
	@Column(name="end_Time" , nullable=false ,columnDefinition="DATETIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@OneToOne
	@JoinColumn(name="productServiceId")
	public ProductServiceVO getProductService() {
		return productService;
	}
	public void setProductService(ProductServiceVO productService) {
		this.productService = productService;
	}
	@OneToOne
	@JoinColumn(name="company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	@OneToOne
	@JoinColumn(name="courseId")
	public CourseVO getCourse() {
		return course;
	}
	public void setCourse(CourseVO course) {
		this.course = course;
	}
}
