package com.scube.techboot.bo;

public class StudentCouresBO extends  BaseBo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4397139485769442315L;

	private long studentCouresId;
	
	private LoginBO studentLoginId;
	
	private CourseBO courseBO;
	
	private StudentRegisterBO studentRegisterBO;
	
	
	
	
	
	public long getStudentCouresId() {
		return studentCouresId;
	}
	public void setStudentCouresId(long studentCouresId) {
		this.studentCouresId = studentCouresId;
	}
	public CourseBO getCourseBO() {
		return courseBO;
	}
	public void setCourseBO(CourseBO courseBO) {
		this.courseBO = courseBO;
	}
	public LoginBO getStudentLoginId() {
		return studentLoginId;
	}
	public void setStudentLoginId(LoginBO studentLoginId) {
		this.studentLoginId = studentLoginId;
	}
	public StudentRegisterBO getStudentRegisterBO() {
		return studentRegisterBO;
	}
	public void setStudentRegisterBO(StudentRegisterBO studentRegisterBO) {
		this.studentRegisterBO = studentRegisterBO;
	}
	

}
