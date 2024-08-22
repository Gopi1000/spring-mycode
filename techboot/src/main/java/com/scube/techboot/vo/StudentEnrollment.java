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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Persistent;

@Entity
@Table(name="student_enrollment")
public class StudentEnrollment extends BasicEntity {

	private static final long serialVersionUID = 7067379026592302528L;

	

	private long studentEnrollmentId;	
    private CourseVO courseVO;
	private StudentRegisterVO student;
	private Date enrollmentDate;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="enrollment_id")
	public long getStudentEnrollmentId() {
		return studentEnrollmentId;
	}
	public void setStudentEnrollmentId(long studentEnrollmentId) {
		this.studentEnrollmentId = studentEnrollmentId;
	}
	
	@OneToOne
	//@ManyToMany
	//@JoinTable(name="inverse_table_one")
	@JoinColumn(name = "course_id")
	public CourseVO getCourseVO() {
		return courseVO;
	}
	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}
		
	//@OneToOne
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="inverse_table")
	public StudentRegisterVO getStudent() {
		return student;
	}
	public void setStudent(StudentRegisterVO student) {
		this.student = student;
	}
	
	@Column(name="enrollment_date")
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	
	

}
