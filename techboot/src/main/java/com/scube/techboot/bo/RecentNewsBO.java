package com.scube.techboot.bo;

import java.sql.Blob;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class RecentNewsBO extends BaseBo{
	
	private int newsId;
	
	@NotBlank(message="Title Name may not be empty")
	/*@Pattern(regexp="^[a-zA-Z0-9-\\.\\'\\s]*$", message = "Enter the Title Name valid format")*/
	private String titleName;
	
	@NotBlank(message="Descriptions may not be empty")
	/*@Pattern(regexp="^[a-zA-Z0-9-\\.\\'\\s]*$", message = "Enter the Descriptions valid format")*/
	private String descriptions;
	
	@NotNull(message = "Date can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;
	private Blob recentNewsLogo;
	private String imageName;
	
	@NotBlank(message="Author Name may not be empty")
	/*@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Author Name valid format")*/
	private String authorName;
	private String beginDate;
	
	
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Blob getRecentNewsLogo() {
		return recentNewsLogo;
	}
	public void setRecentNewsLogo(Blob recentNewsLogo) {
		this.recentNewsLogo = recentNewsLogo;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	
	
	

}
