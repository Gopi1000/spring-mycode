package com.scube.techboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.CourseDetailsBO;
import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.MetaTitleBO;
import com.scube.techboot.vo.CourseRegisterationVO;

public interface CourseService {

 CourseBO saveCourse(CourseBO courseBo);

 int retriveOfCourse(CourseBO courseBo);

List<CourseBO> viewCourseList(CourseBO courseBO);

long retrieveCourseCount(CourseBO courseBO);

CourseBO getCourseObject(CourseBO courseBO);

/*CourseBO getCourseObject(CourseBO courseBO);*/

CourseRegistrationBO saveCourseRegister(CourseRegistrationBO courseRegistrationBO);

List<CourseRegistrationBO> viewCourseRegistrationList(CourseRegistrationBO courseRegistrationBO);

CourseRegistrationBO viewCourseDetails(CourseRegistrationBO courseRegistrationBO);

CourseDetailsBO saveCourseDetails(CourseDetailsBO courseDetailsBO, HttpSession session);

List<CourseDetailsBO> viewCourseDetails(CourseDetailsBO courseDetailsBO);


long retrieveCourseCompanyCount(CourseBO courseBO);

CourseDetailsBO getCourseDetails(CourseDetailsBO courseDetailsBO);

int searchCourse(CourseBO courseBO);


List<CourseBO> searchPageCourse(CourseBO courseBO);

CourseBO editCourse(CourseBO courseBO);

boolean postEditCourse(CourseBO editCourseBo);

boolean deleteCourse(CourseBO courseBO);

List<CourseBO> getViewCourseList(CourseBO course);

List<CourseBO> getcurseDetails(CourseBO courseBO);

CourseDetailsBO getEditCourseDetails(CourseDetailsBO courseDetailsBo);

boolean postEditCourseDetails(CourseDetailsBO courseDetails);

boolean deleteCourseDetails(CourseDetailsBO courseDetailsBo);

boolean isEmailAddressExixts(CourseRegistrationBO courseRegistrationBO);

boolean isMobileNumbaerExixts(CourseRegistrationBO courseRegistrationBO);

long retrieveOfCourseRegistrationCount(CourseRegistrationBO courseRegister);

CourseCategoryBO courseCatogery(CourseCategoryBO courseCategoryBO);

List<CourseCategoryBO> viewCourseCatogery(CourseCategoryBO categoryBO);

CourseCategoryBO getEditCoursecategory(CourseCategoryBO categoryBO);

Boolean postEditCoursecategory(CourseCategoryBO categoryBO);

boolean deleteCourseCategory(CourseCategoryBO courseCategoryBO);

long retrievecourseCategoryCount(CourseCategoryBO categoryBO);

List<MetaTitleBO> getKeywords(MetaTitleBO metaTitleBO);

boolean courseCategoryValidations(CourseCategoryBO courseCategoryBO);

CourseRegistrationBO editCourseRegistration(CourseRegistrationBO courseRegistrationBO);

CourseRegistrationBO PostCourseRegistration(CourseRegistrationBO courseRegistrationBO);

boolean DeleteCourseRegistration(CourseRegistrationBO courseRegistrationBO);

CourseRegistrationBO viewCourseRegister(CourseRegistrationBO courseRegistrationBO);

int searchCourseCategoryName(CourseCategoryBO courseCategoryBO);

List<CourseCategoryBO> searchPageCourse(CourseCategoryBO courseCategoryBO);

List<CourseCategoryBO> viewCourseCategorysearch(CourseCategoryBO categoryBO);

CourseCategoryBO viewCategoryDetails(CourseCategoryBO categoryBO);

int searchCategory(CourseCategoryBO courseCategoryBO);

List<CourseCategoryBO> searchPageCategory(CourseCategoryBO courseCategoryBO);

boolean courseNameValidations(String courseName);

/*boolean courseNameValidations(CourseBO courseBO);
*/



}
