package com.scube.techboot.bo;

import java.sql.Blob;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
public class EventsBO extends BaseBo {
	
	private long eventId;
	
	@NotBlank(message="Title Name may not be empty")
	private String titleName;
	
	@NotBlank(message="Title Name may not be empty")
	private String timing;
	
	@NotBlank(message="Title Name may not be empty")
	
	private String address;
	private String eventDate;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;
	private Blob eventsLogo;
	private String imageName;
	private String day;
	private String month;
	private String dayDate;
	private String eventsName;
    private CompanyBO companyBO;

	
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Blob getEventsLogo() {
		return eventsLogo;
	}
	public void setEventsLogo(Blob eventsLogo) {
		this.eventsLogo = eventsLogo;
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
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	public Date getDate() {
		return date;
	}
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDayDate() {
		return dayDate;
	}
	public void setDayDate(String dayDate) {
		this.dayDate = dayDate;
	}
	public String getEventsName() {
		return eventsName;
	}
	public void setEventsName(String eventsName) {
		this.eventsName = eventsName;
	}
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	
}
