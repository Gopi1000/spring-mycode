package com.scube.techboot.bo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


public class CampaignBO extends BaseBo {

	
	private int campaignId;
	@NotBlank(message="Campaign Name can not be empty")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Campaign Name should be a character only")
	private String campaignName;
	
	@NotBlank(message="Category can not be empty")
    private String category;
	
	@NotBlank(message="Message can not be empty")
    private String message;
	
	
	@NotNull(message = "Start Date can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date startedTime;
	
	
	@NotNull(message = "End Date can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date endTime;
	
    private String stDate;
    private String edDate;
    private CompanyBO companyBO;
    
    @NotNull(message = "ProductServie can not be Empty")
    private ProductServiceBO productService;
    

    @NotNull(message = "Course can not be Empty")
    private CourseBO course;
    
   	
	 // private CompanyBO companyBO;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getStartedTime() {
		return startedTime;
	}
	public void setStartedTime(Date startedTime) {
		this.startedTime = startedTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
    public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ProductServiceBO getProductService() {
		return productService;
	}
	public void setProductService(ProductServiceBO productService) {
		this.productService = productService;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	/*public CompanyBO getCompanyBO() {
		return companyBO;
	}
	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}*/
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getEdDate() {
		return edDate;
	}
	public void setEdDate(String edDate) {
		this.edDate = edDate;
	}
	public CourseBO getCourse() {
		return course;
	}
	public void setCourse(CourseBO course) {
		this.course = course;
	}
	
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	
}
