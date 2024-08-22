package com.scube.techboot.bo;

public class FeedbackBO {
	private long feedbackId;
	private String starRating;
	private String command;
	private CourseBO courseBO;
	
	private StudentRegisterBO studentRegisterBO;


public String getStarRating() {
	return starRating;
}

public void setStarRating(String straRating) {
	this.starRating = straRating;
}

public String getCommand() {
	return command;
}

public void setCommand(String command) {
	this.command = command;
}



public CourseBO getCourseBO() {
	return courseBO;
}

public void setCourseBO(CourseBO courseBO) {
	this.courseBO = courseBO;
}

public StudentRegisterBO getStudentRegisterBO() {
	return studentRegisterBO;
}

public void setStudentRegisterBO(StudentRegisterBO studentRegisterBO) {
	this.studentRegisterBO = studentRegisterBO;
}

public long getFeedbackId() {
	return feedbackId;
}

public void setFeedbackId(long feedbackId) {
	this.feedbackId = feedbackId;
}


}
