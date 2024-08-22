package com.scube.techboot.bo;


import java.sql.Blob;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;



public class TestimonialBO extends BaseBo {
	
	
	private long testimonialId;
	
	@NotBlank(message="Name may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Name valid format")
	private String name;
	
	@NotBlank(message="Title Name may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Title Name valid format")
	private String titleName;
	
	@NotBlank(message="Message may not be empty")
	private String message;
	private Blob testimonialLogo;
	private String imageName;
	
	@NotBlank(message="Profession may not be empty")
	private String profession;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	/*public Blob getTestimonialLogo() {
		return testimonialLogo;
	}
	public void setTestimonialLogo(byte[] testimonialLogo) 
			throws SerialException, SQLException {
		
		if (null != testimonialLogo) {
			this.testimonialLogo = new SerialBlob(testimonialLogo);
		} else {
			this.testimonialLogo = null;
		}
	}*/
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Blob getTestimonialLogo() {
		return testimonialLogo;
	}
	public void setTestimonialLogo(Blob testimonialLogo) {
		this.testimonialLogo = testimonialLogo;
	}
	public long getTestimonialId() {
		return testimonialId;
	}
	public void setTestimonialId(long testimonialId) {
		this.testimonialId = testimonialId;
	}
	

}
