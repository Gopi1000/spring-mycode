package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.StudentRegisterBO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.FeedbackVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.StudentEnrollment;
import com.scube.techboot.vo.StudentRegisterVO;

public interface StudentDao {

	

	StudentRegisterBO saveStudent(StudentRegisterVO studentRegisterVO);

	LoginVO loginStudent(LoginVO loginVO);

	boolean emailAddressValidation(StudentRegisterVO studentRegisterVO);



	long saveStudentCourse(StudentEnrollment studentEnrollment);

	//List<StudentEnrollment> getStudentCouresList(long studentId);

	StudentRegisterVO getStudentProfile(long studentLoginId);

	

	StudentEnrollment getEditCourseEnrollement(StudentEnrollment studentEnrollment);

	boolean editSaveStudentCourse(StudentEnrollment studentEnrollment);

	boolean deleteCourseEnrollemt(StudentEnrollment studentCouresVo);

	List<StudentEnrollment> getStudentCouresList(StudentEnrollment studentEnrollment);

	List<StudentRegisterVO> studentList(StudentRegisterVO registerVo);

	//List<StudentEnrollment> getStudentReporting(StudentEnrollment studentEnrollment);

	boolean isExist(StudentEnrollment studentenrollmentVO);
	
	List<StudentEnrollment> searchCourseDao(StudentEnrollment studentEnrollmentVO);

	long retrieveCourseCount(StudentEnrollment enrollment);

	List<FeedbackVO> viewfeedback();

	FeedbackVO editFeedback(long id);

	boolean updateFeedback(FeedbackVO feedbackVO);

	boolean deletefeedback(FeedbackVO feedbackVO);

	long saveFeedback(FeedbackVO feedbackVO);

	List<StudentRegisterVO> listOfPageStudent(StudentRegisterVO registerVo);

	List<StudentRegisterVO> totalSearchStudent(StudentRegisterVO studentVO);

	List<StudentRegisterVO> searchStudent(StudentRegisterVO studentVO);

	boolean updateStudent(StudentRegisterVO studentRegisterVO);

	boolean deleteStudent(StudentRegisterVO studentRegisterVO);

	boolean mobileValidation(StudentRegisterVO studentRegisterVO);
	
  
	int getCompanyLogin(int companyId);

	/*CourseVO getCompanyId(int courseId);*/
	



	

}
