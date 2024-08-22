package com.scube.techboot.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class FeedbackVO{
	

	@Id
	@GeneratedValue
	@Column(name="feedback_Id")
	private long feedbackId;
	/*
	@OneToOne
	@JoinColumn(name="studentEnrollment_id",nullable = false)
	private StudentEnrollment studentEnrollment;*/
	
	@OneToOne
	@JoinColumn(name="course_id")
	private CourseVO courseVO;
	
	@Column(name="starrating")
	private String starRating;
	
	@Column(name="command")
	private String command;
	@OneToOne
	@JoinColumn(name="student_Id")
	private StudentRegisterVO studentRegisterVO;
	
	
	public long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getStarRating() {
		return starRating;
	}
	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}
	public CourseVO getCourseVO() {
		return courseVO;
	}
	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}
	public StudentRegisterVO getStudentRegisterVO() {
		return studentRegisterVO;
	}
	public void setStudentRegisterVO(StudentRegisterVO studentRegisterVO) {
		this.studentRegisterVO = studentRegisterVO;
	}
	
	
	

}
