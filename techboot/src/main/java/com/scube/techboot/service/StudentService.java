package com.scube.techboot.service;

import java.util.List;

import com.scube.techboot.bo.FeedbackBO;
import com.scube.techboot.bo.StudentCouresBO;

import com.scube.techboot.bo.StudentRegisterBO;


public interface StudentService {

	StudentRegisterBO saveStudent(StudentRegisterBO studentRegisterBO) throws Exception;

	boolean emailAddressValidation(String emailAddress);

	long saveCourseEnrollment(StudentCouresBO studentCouresBO, long studentId);

	List<StudentCouresBO> getStudentCouresList(long studentId);

	 StudentRegisterBO getStudentProfile(long studentLoginId);

	 StudentCouresBO getEditCourseEnrollement(StudentCouresBO studentCouresBO);

	boolean editSaveCourseEnrollment(StudentCouresBO studentCouresBO, long studentId);

	boolean deleteCourseEnrollemt(StudentCouresBO studentCouresBO);
	
	List<StudentRegisterBO> studentList(StudentRegisterBO student);

	long saveCourseEnrollment(StudentCouresBO studentCouresBO);

	List<StudentCouresBO> getEntrollmentList(StudentCouresBO student);

	//List<StudentEnrollmentBO> getStudentReporting(StudentCouresBO student);

	//List<StudentEnrollmentBO> getStudentReporting(StudentEnrollmentBO student);


  boolean isCourseExist(StudentCouresBO studentCouresBO,long studId);
  List<StudentCouresBO> searchCoures(StudentCouresBO studentCouresBO,long studentId);

long retrieveCourseCount(StudentCouresBO courseBo);

List<FeedbackBO> viewFeedback();

FeedbackBO editFeedback(long id);

boolean updateFeedback(FeedbackBO feedbackBO);

boolean deletefeedback(FeedbackBO feedbackBO);

long saveFeedback(FeedbackBO feedbackBO);

List<StudentRegisterBO> listOfPageStudent(StudentRegisterBO studentBO);

List<StudentRegisterBO> totalSearchStudent(StudentRegisterBO studentBo);

List<StudentRegisterBO> searchStudent(StudentRegisterBO studentBo);

boolean updateStudent(StudentRegisterBO studentRegisterBO);

boolean deleteStudent(StudentRegisterBO studentRegisterBO);

boolean mobileValidation(long mobilenumber);

int getCompanyLogin(int companyId);
	
}
