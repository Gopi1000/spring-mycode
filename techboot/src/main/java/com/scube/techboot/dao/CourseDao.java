package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.MetaTitleBO;
import com.scube.techboot.vo.CourseDetailsVO;
import com.scube.techboot.vo.CourseRegisterationVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.MetaTagVO;
import com.scube.techboot.vo.courseCategoryVO;

public interface CourseDao {

	CourseVO saveCourse(CourseVO courseVO);

	int retriveOfCourse(CourseVO courseVO);

	List<CourseVO> viewCourseList(CourseVO courseVO);

	long retrieveCourseCount(CourseVO courseVO);

	CourseRegisterationVO saveCourseRegister(CourseRegisterationVO courseRegistrationVO);

	List<CourseRegisterationVO> viewCourseRegistrationList(CourseRegisterationVO courseRegisterationVO);

	CourseRegisterationVO viewCourseDetails(CourseRegisterationVO courseRegisterationVO);
	
	CourseDetailsVO saveCourseDetails(CourseDetailsVO courseDetailsVO);

	List<CourseDetailsVO> viewCoursedetails(CourseDetailsVO courseDetailsVO);
	
	long retrieveCourseCompanyCount(CourseVO courseVO);

	CourseDetailsVO getCourseDetails(CourseDetailsVO courseDetailsVO);


	CourseVO getCourseObject(CourseVO courseVO);

	int searchCourse(CourseVO courseVO);

	List<CourseVO> searchPageCourse(CourseVO courseVO);

	CourseVO editCourse(CourseVO courseVO);

	boolean postEditCourse(CourseVO courseVO);

	boolean deleteCourse(CourseVO courseVO);

	boolean checkCourse(CourseDetailsVO courseDetailsVO);

	CourseDetailsVO getEditCourseDetails(CourseDetailsVO courseDetailsVo);

	boolean postEditCourseDetails(CourseDetailsVO courseDetailsVo);

	boolean deleteCourseDetails(CourseDetailsVO courseDetailsVo);

	long saveMetaTad(MetaTagVO metaTagVO);

	boolean isExixts(CourseRegisterationVO courseRegistrationVO);

	boolean isMobileNumbaerExixts(CourseRegisterationVO courseRegistrationVO);

	long retrieveOfCourseRegistrationCount(CourseRegisterationVO courseRegisterVo);

	courseCategoryVO courseCatogery(courseCategoryVO categoryVO);

	List<courseCategoryVO> viewCourseCatogery(courseCategoryVO categoryVO);

	courseCategoryVO getEditCoursecategory(courseCategoryVO categoryVO);

	Boolean postEditCoursecategory(courseCategoryVO categoryVO);

	boolean deleteCourseCategory(courseCategoryVO courseCategoryvO);

	long retrievecourseCategoryCount(courseCategoryVO categoryVO);
     
	List<MetaTitleBO> getKeywords(MetaTagVO metaTitleVo);

	boolean isExixstsCategory(courseCategoryVO categoryVO);

	CourseRegisterationVO editCourseRegistration(CourseRegisterationVO courseRegisterationVO);

	CourseRegistrationBO PosteditCourseRegistration(CourseRegisterationVO courseRegisterationVO);

	boolean DeleteCourseRegistration(CourseRegisterationVO courseRegisterationVO);

	CourseRegisterationVO viewCourseRegister(CourseRegisterationVO courseRegisterationVO);

	int retrieveOfEventsCount(courseCategoryVO courseCategoryVO);

	List<courseCategoryVO> searchPageCourse(courseCategoryVO courseCategoryVO);

	courseCategoryVO viewCategoryDetails(courseCategoryVO courseCategoryVO);

	int searchCategory(courseCategoryVO courseCategoryVO);

	List<com.scube.techboot.vo.courseCategoryVO> searchPageCategory(courseCategoryVO courseCategoryVO);

	boolean courseNameValidations(CourseVO courseVO);



}
