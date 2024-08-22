package com.example.StudentCrud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
//@Table(name = "course")
public class Student {
@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String studentname;
    private String course;
    private int fee;
    
	public Student() {
    }
    public Student(Long id, String studentname, String course, int fee) {
		this.id = id;
		this.studentname = studentname;
		this.course = course;
		this.fee = fee;
	}
	 
	 
@Id
@GeneratedValue
//@Column(name = "Id")
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
//@Column(name = "Student_name")
public String getStudentname() {
return studentname;
}
public void setStudentname(String studentname) {
this.studentname = studentname;
}
//@Column(name = "Student_course")
public String getCourse() {
return course;
}
public void setCourse(String course) {
this.course = course;
}
//@Column(name = "Student_fee")
public int getFee() {
return fee;
}
public void setFee(int fee) {
this.fee = fee;
}
 
}