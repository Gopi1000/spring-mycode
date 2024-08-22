package com.scube.techboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.CourseDetailsBO;
import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.EventsBO;
import com.scube.techboot.bo.MetaTitleBO;
import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.dao.CourseDao;
import com.scube.techboot.model.EmailModel;
import com.scube.techboot.utils.SendEmailService;
import com.scube.techboot.utils.TechbootResourceBundle;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CourseDetailsVO;
import com.scube.techboot.vo.CourseRegisterationVO;
import com.scube.techboot.vo.SubcategoryVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.EventVO;
import com.scube.techboot.vo.MetaTagVO;
import com.scube.techboot.vo.courseCategoryVO;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CourseDao courseDao;
	EmailModel model = new EmailModel();
    @Autowired
    private SendEmailService emailService;
    
    private static final Logger LOGGER=Logger.
    		getLogger(CourseServiceImpl.class);
	@Override
	public CourseBO saveCourse(CourseBO courseBO) {
		// TODO Auto-generated method stub
		List<MetaTagVO> metaTagList=new ArrayList<MetaTagVO>();
		CourseVO courseVO=new CourseVO();
		CompanyVO companyVo=new CompanyVO();
		courseCategoryVO categoryVO=new courseCategoryVO();
		companyVo.setCompanyId(courseBO.getCompanyBO().getCompanyId());
		courseVO.setCompanyVO(companyVo);
		if(null!=courseBO&&null!=courseBO.getCourseCategoryBO()&&0<courseBO.getCourseCategoryBO().getCourseCategoryId()) {
		categoryVO.setCourseCategoryId(courseBO.getCourseCategoryBO().getCourseCategoryId());}
		courseVO.setCourseCategoryVo(categoryVO);
		courseVO.setCourseId(courseBO.getCourseId());
		courseVO.setCreatedTime(new Date());
		courseVO.setModifiedTime(new Date());
		courseVO.setCourseName(courseBO.getCourseName());
		courseVO.setAuthorName(courseBO.getAuthorName());
		courseVO.setFees(courseBO.getFees());
		courseVO.setIsDelete(false);
		courseVO.setSending_status(true);
		courseVO.setCourseUrl(courseBO.getCourseUrl());
		courseVO.setImageName(courseBO.getImageName());
		
		if(null!=courseBO.getMetaTaq()){
			String[] meta=courseBO.getMetaTaq().split(",");
			for(int i=0; i<meta.length; i++){
				MetaTagVO metaTagVO=new MetaTagVO();
				String s=meta[i];
				metaTagVO.setMetaName(s);
				long metaID=courseDao.saveMetaTad(metaTagVO);
				metaTagVO.setMetaTagId(metaID);
				metaTagList.add(metaTagVO);
			}
		}
		if(null!=metaTagList && metaTagList.size()>0 && !metaTagList.isEmpty()){
			courseVO.setMetataglist(metaTagList);
		}
		courseVO=courseDao.saveCourse(courseVO);
		if(null!=courseVO) {
			courseBO.setCourseId(courseVO.getCourseId());
		}
		return courseBO;
	}


	@Override
	public int retriveOfCourse(CourseBO courseBo) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		int count=courseDao.retriveOfCourse(courseVO);
		if(0!=count) {
			return count;
		}
		return count;
	}
	
	@Override
	public List<CourseBO> getcurseDetails(CourseBO courseBO) {
		// TODO Auto-generated method stub
		List<CourseBO> courseListBO=new ArrayList<CourseBO>();
		CourseVO courseVO=new CourseVO();
		if(null!=courseBO.getCompanyBO() && 0!=courseBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(courseBO.getCompanyBO().getCompanyId());
			courseVO.setCompanyVO(companyVO);
			courseVO.setCourseName(courseBO.getCourseName());
		}
		if(courseBO.getIsDelete()==false) {
			courseVO.setMaxRecord(courseBO.getMaxRecord());
			courseVO.setRecordIndex(courseBO.getRecordIndex());
			courseVO.setIsDelete(false);
		}
		if(courseBO.getIsDelete()==true){
			courseVO.setIsDelete(true);
		}
		courseVO.setSending_status(true);
		List<CourseVO> courseList=courseDao.viewCourseList(courseVO);
		if(null!=courseList && !courseList.isEmpty() && courseList.size()>0){
			 List<CourseVO> myList = new CopyOnWriteArrayList<CourseVO>();
			 myList.addAll(courseList);
			 List<CourseVO> courseLists = new CopyOnWriteArrayList<CourseVO>();
/*			 courseLists.addAll(courseList);
*/			for(CourseVO courseVo:myList){
				boolean status;
				CourseVO course=new CourseVO();
				CourseDetailsVO courseDetailsVO=new CourseDetailsVO();
				course.setCourseId(courseVo.getCourseId());
				courseDetailsVO.setCourseVO(courseVo);
				 if(status=courseDao.checkCourse(courseDetailsVO)){
					 myList.remove(courseVo);
				 }else{
					 courseLists.add(courseVo);
				 }
			}for(CourseVO courseListVO:courseLists) {
					CourseBO courseBo=new CourseBO();
					courseBo.setCourseId(courseListVO.getCourseId());
					courseBo.setCourseName(courseListVO.getCourseName());
					courseListBO.add(courseBo);
			   }
		   }
		return courseListBO;
	}
	
	@Override
	public List<CourseBO> getViewCourseList(CourseBO course) {
		// TODO Auto-generated method stub
		List<CourseBO> courseListBO=new ArrayList<CourseBO>();
		CourseVO courseVO=new CourseVO();
		if(null!=course.getCompanyBO() && 0!=course.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(course.getCompanyBO().getCompanyId());
			courseVO.setCompanyVO(companyVO);
			courseVO.setCourseName(course.getCourseName());
		}
		if(course.getIsDelete()==false) {
			courseVO.setMaxRecord(course.getMaxRecord());
			courseVO.setRecordIndex(course.getRecordIndex());
			courseVO.setIsDelete(false);
		}
		if(course.getIsDelete()==true){
			courseVO.setIsDelete(true);
		}
		courseVO.setSending_status(true);
		List<CourseVO> courseList=courseDao.viewCourseList(courseVO);
		if(null!=courseList && !courseList.isEmpty() && courseList.size()>0){
			int count=course.getRecordIndex();
			for(CourseVO courseListVO:courseList) {
				CourseBO courseBo=new CourseBO();
				CompanyBO company=new CompanyBO();
				CourseCategoryBO CourseCategoryBO=new CourseCategoryBO();
				if(null!=courseListVO&&null!=courseListVO.getCourseCategoryVo()&&0<courseListVO.getCourseCategoryVo().getCourseCategoryId()){
				CourseCategoryBO.setCourseCategoryId(courseListVO.getCourseCategoryVo().getCourseCategoryId());}
				courseBo.setCourseCategoryBO(CourseCategoryBO);
				if(null!=courseListVO&&null!=courseListVO.getCourseCategoryVo()&&null!=courseListVO.getCourseCategoryVo().getCourseCategoryName()){
				CourseCategoryBO.setCourseCategoryName(courseListVO.getCourseCategoryVo().getCourseCategoryName());}
				courseBo.setCourseCategoryBO(CourseCategoryBO);
				company.setCompanyName(courseListVO.getCompanyVO().getCompanyName());
				courseBo.setCompanyBO(company);
				courseBo.setCourseId(courseListVO.getCourseId());
				courseBo.setCourseName(courseListVO.getCourseName());
				courseBo.setAuthorName(courseListVO.getAuthorName());
		//Convert float to int
				float rs=courseListVO.getFees();
				int rupees=(int) rs;
				courseBo.setRupees(rupees);
				courseBo.setImageName(courseListVO.getImageName());
				courseBo.setCourseUrl(courseListVO.getCourseUrl());
				count=count+1;
				courseBo.setS_No(count);
				courseListBO.add(courseBo);
			}	
        }
		return courseListBO;
	}


	@Override
	public List<CourseBO> viewCourseList(CourseBO courseBO) {
		// TODO Auto-generated method stub
	
		List<CourseBO> courseListBO=new ArrayList<CourseBO>();
		CourseVO courseVO=new CourseVO();
		if(null!=courseBO.getCourseCategoryBO() && 0!=courseBO.getCourseCategoryBO().getCourseCategoryId()){
			courseCategoryVO categoryVO=new courseCategoryVO();
			categoryVO.setCourseCategoryId(courseBO.getCourseCategoryBO().getCourseCategoryId());
			courseVO.setCourseCategoryVo(categoryVO);
		}
		if(null!=courseBO.getCompanyBO() && 0!=courseBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(courseBO.getCompanyBO().getCompanyId());
			courseVO.setCompanyVO(companyVO);
			courseVO.setCourseName(courseBO.getCourseName());
		}
		if(courseBO.getIsDelete()==false) {
			courseVO.setMaxRecord(courseBO.getMaxRecord());
			courseVO.setRecordIndex(courseBO.getRecordIndex());
			courseVO.setIsDelete(false);
		}
		if(courseBO.getIsDelete()==true){
			courseVO.setIsDelete(true);
		}
		courseVO.setSending_status(true);
		List<CourseVO> courseList=courseDao.viewCourseList(courseVO);
		if(null!=courseList && !courseList.isEmpty() && courseList.size()>0){
			 List<CourseVO> myList = new CopyOnWriteArrayList<CourseVO>();
			 myList.addAll(courseList);
			 List<CourseVO> courseLists = new CopyOnWriteArrayList<CourseVO>();
			for(CourseVO courseVo:myList){
				boolean status;
				CourseVO course=new CourseVO();
				CourseDetailsVO courseDetailsVO=new CourseDetailsVO();
				course.setCourseId(courseVo.getCourseId());
				courseDetailsVO.setCourseVO(courseVo);
				 if(status=courseDao.checkCourse(courseDetailsVO)){
					 courseLists.add(courseVo);
				 }else{
					 myList.remove(courseVo);
				 }
			}
			int count=courseBO.getRecordIndex();
			for(CourseVO courseListVO:courseLists) {
				CourseBO courseBo=new CourseBO();
				CompanyBO company=new CompanyBO();
				company.setCompanyName(courseListVO.getCompanyVO().getCompanyName());
				courseBo.setCompanyBO(company);
				courseBo.setCourseId(courseListVO.getCourseId());
				courseBo.setCourseName(courseListVO.getCourseName());
				courseBo.setAuthorName(courseListVO.getAuthorName());
		//Convert float to int
				float rs=courseListVO.getFees();
				int rupees=(int) rs;
				courseBo.setRupees(rupees);
				courseBo.setImageName(courseListVO.getImageName());
				courseBo.setCourseUrl(courseListVO.getCourseUrl());
				count=count+1;
				courseBo.setS_No(count);
				courseBo.setMetataglist(courseListVO.getMetataglist());
				courseListBO.add(courseBo);
			}	
        }
		return courseListBO;
	}




	@Override
	public long retrieveCourseCount(CourseBO courseBO) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		courseVO.setIsDelete(false);
		courseVO.setSending_status(true);
		return courseDao.retrieveCourseCount(courseVO);
	}


	@Override
	public CourseRegistrationBO viewCourseDetails(CourseRegistrationBO courseRegistrationBO){
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		courseRegisterationVO.setCourseRegisterId(courseRegistrationBO.getCourseRegisterId());
		courseRegisterationVO.setIsActive(true);
		courseRegisterationVO.setIsDelete(false);
		CourseRegistrationBO courseRegisterationBO=new CourseRegistrationBO();
		courseRegisterationVO=courseDao.viewCourseDetails(courseRegisterationVO);
		if(null!=courseRegisterationVO){
			courseRegisterationBO.setcandidateName(courseRegisterationVO.getCandidateName());
			courseRegisterationBO.setEmailAddress(courseRegisterationVO.getEmailAddress());
			courseRegisterationBO.setMobileNumber(courseRegisterationVO.getMobileNumber());
			courseRegisterationBO.setProfessional(courseRegisterationVO.getProfessional());
			courseRegisterationBO.setScheme(courseRegisterationVO.getScheme());
			CourseBO courseBO=new CourseBO();
			courseBO.setCourseId(courseRegisterationVO.getCourseVO().getCourseId());
			courseBO.setCourseName(courseRegisterationVO.getCourseVO().getCourseName());
			courseRegisterationBO.setCourseBO(courseBO);
		}
		return courseRegisterationBO;

	}

	
	/*@Override
	public CourseRegistrationBO getCourseObject(CourseRegistrationBO courseRegistrationBO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
		courseRegisterationVO.setCourseVO(courseVO);
		courseRegisterationVO.setIsDelete(false);
		courseRegisterationVO.setIsActive(true);
		courseRegisterationVO=courseDao.getCourseObject(courseRegisterationVO);
		if(null!=courseRegisterationVO) {
			CourseBO courseBO=new CourseBO();
			courseRegistrationBO.setCourseRegisterId(courseRegisterationVO.getCourseRegisterId());
			courseRegistrationBO.setcandidateName(courseRegisterationVO.getCandidateName());
			courseRegistrationBO.setEmailAddress(courseRegisterationVO.getEmailAddress());
			courseRegistrationBO.setMobileNumber(courseRegisterationVO.getMobileNumber());
			courseRegistrationBO.setProfessional(courseRegisterationVO.getProfessional());
			courseRegistrationBO.setScheme(courseRegisterationVO.getScheme());
			courseBO.setCourseId(courseRegisterationVO.getCourseVO().getCourseId());
			courseRegistrationBO.setCourseBO(courseBO);
			courseBO.setCourseName(courseRegisterationVO.getCourseVO().getCourseName());
			courseRegistrationBO.setCourseBO(courseBO);
			}
		    return courseRegistrationBO;
}*/

	/*@Override
	public CourseBO getCourseObject(CourseBO courseBO) {
		CourseBO course=new CourseBO();
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseName(courseBO.getCourseName());
		courseVO.setIsDelete(false);
		courseVO=courseDao.getCourseObject(courseVO);
		if(null!=courseVO) {
			course.setCourseName(courseVO.getCourseName());
			course.setCourseId(courseVO.getCourseId());
		}
		return course;
	}*/
	
	
	@Override
	public CourseBO getCourseObject(CourseBO courseBO) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(courseBO.getCourseId());
		courseVO.setIsDelete(false);
		courseVO=courseDao. getCourseObject(courseVO);
		if(null!=courseVO) {
			CompanyBO companyBo=new CompanyBO();
			companyBo.setCompanyId(courseVO.getCompanyVO().getCompanyId());
			courseBO.setCompanyBO(companyBo);
			courseBO.setCourseId(courseVO.getCourseId());
			courseBO.setCourseName(courseVO.getCourseName());
			}
		return courseBO;
	}
	
	@Override
	public boolean isEmailAddressExixts(CourseRegistrationBO courseRegistrationBO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegistrationVO=new CourseRegisterationVO();
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
		courseRegistrationVO.setEmailAddress(courseRegistrationBO.getEmailAddress());
		courseRegistrationVO.setCourseVO(courseVO);
		return courseDao.isExixts(courseRegistrationVO);
	}
	
	@Override
	public boolean isMobileNumbaerExixts(CourseRegistrationBO courseRegistrationBO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegistrationVO=new CourseRegisterationVO();
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
		courseRegistrationVO.setMobileNumber(courseRegistrationBO.getMobileNumber());
		courseRegistrationVO.setCourseVO(courseVO);
		return courseDao.isMobileNumbaerExixts(courseRegistrationVO);
	}

	@Override
	public CourseRegistrationBO saveCourseRegister(CourseRegistrationBO courseRegistrationBO) {
		CourseRegisterationVO courseRegistrationVO=new CourseRegisterationVO();
		CourseVO courseVO=new CourseVO();
		CourseBO courseBO=new CourseBO();

		CompanyVO companyVo=new CompanyVO();
		companyVo.setCompanyId(courseRegistrationBO.getCourseBO().getCompanyBO().getCompanyId());
		courseRegistrationVO.setCandidateName(courseRegistrationBO.getcandidateName());
		courseRegistrationVO.setEmailAddress(courseRegistrationBO.getEmailAddress());
		courseRegistrationVO.setMobileNumber(courseRegistrationBO.getMobileNumber());
		courseRegistrationVO.setProfessional(courseRegistrationBO.getProfessional());
		courseRegistrationVO.setScheme(courseRegistrationBO.getScheme());
		courseRegistrationVO.setCreatedTime(new Date());
		courseRegistrationVO.setModifiedTime(new Date());
		courseRegistrationVO.setIsDelete(false);
		courseRegistrationVO.setIsActive(true);
		courseRegistrationVO.setCompanyVO(companyVo);
		courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
		courseVO.setCourseName(courseRegistrationBO.getCourseBO().getCourseName());
/*		courseVO.setFees(courseRegistrationBO.getCourseBO().getFees());
*/		courseRegistrationVO.setCourseVO(courseVO);
		courseRegistrationVO=courseDao.saveCourseRegister(courseRegistrationVO);
		if(null!=courseRegistrationVO && 0!=courseRegistrationVO.getCourseRegisterId()){
			this.sendCourseRegisterationMail(courseRegistrationVO);
			courseRegistrationBO.setCourseRegisterId(courseRegistrationVO.getCourseRegisterId());
		}
		return courseRegistrationBO;
	}


	private void sendCourseRegisterationMail(CourseRegisterationVO courseRegistrationVO) {
		// TODO Auto-generated method stub
		try{
			final String toaddress=TechbootResourceBundle.
					getValue("admin.mailId");
			final String subject = "Techboot:Course Registration Details!";
			String bodycontent = "courseRegistration";
			model.setFirstname(courseRegistrationVO.getCandidateName());
			model.setEmail(courseRegistrationVO.getEmailAddress());
			model.setCoursetitle(courseRegistrationVO.getCourseVO().getCourseName());
			model.setPhoneno(courseRegistrationVO.getMobileNumber());
			model.setProfessional(courseRegistrationVO.getProfessional());
			//model.setEmailCC(TechbootResourceBundle.getValue("admin.ccmailId"));
		String success=emailService.sendEmail(toaddress, subject, bodycontent, model);
		System.out.println(success);
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
	}


	@Override
	public List<CourseRegistrationBO> viewCourseRegistrationList(CourseRegistrationBO courseRegistrationBO) {

		List<CourseRegistrationBO> registerListBO=new ArrayList<CourseRegistrationBO>();
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		if(null!=courseRegistrationBO.getCourseBO()){
			CourseVO courseVO=new CourseVO();
			courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
			courseRegisterationVO.setCourseVO(courseVO);
		}

		if(!courseRegistrationBO.isDelete()){
			courseRegisterationVO.setMaxRecord(courseRegistrationBO.getMaxRecord());
			courseRegisterationVO.setRecordIndex(courseRegistrationBO.getRecordIndex());
			courseRegisterationVO.setIsDelete(false);
		}

		if(courseRegistrationBO.isDelete()){
			courseRegisterationVO.setIsDelete(true);
		}
		if(null!=courseRegistrationBO && null!=courseRegistrationBO.getCompanyBO()){
			CompanyVO companyVo=new CompanyVO();
			companyVo.setCompanyId(courseRegistrationBO.getCompanyBO().getCompanyId());
			courseRegisterationVO.setCompanyVO(companyVo);
			courseRegisterationVO.setIsDelete(false);
		}
		courseRegisterationVO.setIsActive(true);
		List<CourseRegisterationVO> registerList=courseDao.viewCourseRegistrationList(courseRegisterationVO);
		if(null!=registerList && !registerList.isEmpty() && registerList.size()>0){
			int count=courseRegistrationBO.getRecordIndex();
			for(CourseRegisterationVO registercourse:registerList){
				CourseRegistrationBO courseRegistrationBo=new CourseRegistrationBO();
				CourseBO courseBo=new CourseBO();
				CompanyBO companyBo=new CompanyBO();
				companyBo.setCompanyName(registercourse.getCourseVO().getCompanyVO().getCompanyName());
				courseRegistrationBo.setcandidateName(registercourse.getCandidateName());
				courseRegistrationBo.setEmailAddress(registercourse.getEmailAddress());
				courseRegistrationBo.setMobileNumber(registercourse.getMobileNumber());
				courseRegistrationBo.setProfessional(registercourse.getProfessional());
				courseRegistrationBo.setScheme(registercourse.getScheme());
				courseBo.setCompanyBO(companyBo);
				courseBo.setCourseName(registercourse.getCourseVO().getCourseName());
				count=count+1;
				courseRegistrationBo.setS_No(count);
				courseRegistrationBo.setCourseBO(courseBo);
				courseBo.setCourseId(registercourse.getCourseVO().getCourseId());
				//courseRegistrationBo.setS_No(registercourse.getS_No());
				courseRegistrationBo.setCourseRegisterId(registercourse.getCourseRegisterId());
				registerListBO.add(courseRegistrationBo);
			}
		}
		return registerListBO;
	}


	@Override
	public CourseDetailsBO saveCourseDetails(CourseDetailsBO courseDetailsBO, HttpSession session) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetailsVO=new CourseDetailsVO();
		CourseVO courseVo=new CourseVO();
		CompanyVO companyVo=new CompanyVO();
		companyVo.setCompanyId(courseDetailsBO.getCompanyBO().getCompanyId());
		companyVo.setCompanyName(courseDetailsBO.getCompanyBO().getCompanyName());
		courseDetailsVO.setCourseDetailsId(courseDetailsBO.getCourseDetailsId());
		courseDetailsVO.setClassesSchedule(courseDetailsBO.getClassesSchedule());
		courseDetailsVO.setCurriculum(courseDetailsBO.getCurriculum());
		courseDetailsVO.setDescription(courseDetailsBO.getDescription());
		courseDetailsVO.setDesigned(courseDetailsBO.getDesigned());
		courseDetailsVO.setKeyfeatures(courseDetailsBO.getKeyfeatures());
		courseDetailsVO.setPrice(courseDetailsBO.getPrice());
		courseDetailsVO.setCourseVO(courseVo);
		courseDetailsVO.setPrice(courseDetailsBO.getPrice());
		courseDetailsVO.setIsDelete(false);
		courseDetailsVO.setSending_status(true);
		courseDetailsVO.setCreatedTime(new Date());
		courseDetailsVO.setModifiedTime(new Date());
		courseVo.setCourseId(courseDetailsBO.getCurseBO().getCourseId());
		courseDetailsVO.setCourseVO(courseVo);
		int  courseid=courseDetailsBO.getCurseBO().getCourseId();
		courseVo.setCourseId(courseid);
		courseDetailsVO.setCourseVO(courseVo);
		courseDetailsVO.setCompanyVO(companyVo);
		
		if(null!=session.getAttribute("adminId")){
			long id=(long) session.getAttribute("adminId");
			courseDetailsVO.setCreatedBy(id);
		}
		courseDetailsVO=courseDao.saveCourseDetails(courseDetailsVO);
		if(null!=courseDetailsVO) {
			courseDetailsBO.setCourseDetailsId(courseDetailsVO.getCourseDetailsId());
		}
		return courseDetailsBO;
	}


	@Override
	public List<CourseDetailsBO> viewCourseDetails(CourseDetailsBO courseDetailsBO) {
		// TODO Auto-generated method stub
		
		List<CourseDetailsBO> listCourseDetailsBO =new ArrayList<CourseDetailsBO>();
		CourseDetailsVO courseDetailsVO=new CourseDetailsVO();
		if(courseDetailsBO.getIsDelete()==false) {
			courseDetailsVO.setMaxRecord(courseDetailsBO.getMaxRecord());
			courseDetailsVO.setRecordIndex(courseDetailsBO.getRecordIndex());
			courseDetailsVO.setIsDelete(false);
		}
		if(courseDetailsBO.getIsDelete()==true){
			courseDetailsBO.setIsDelete(true);
		}
	//admin	
		if(null!=courseDetailsBO.getCurseBO()) {
			CourseVO courseVO=new CourseVO();
			courseVO.setCourseId(courseDetailsBO.getCurseBO().getCourseId());
			courseDetailsVO.setCourseVO(courseVO);
			courseVO.setCourseName(courseDetailsBO.getCurseBO().getCourseName());
			courseDetailsVO.setCourseVO(courseVO);
		}
	//Company
		
		if(null!=courseDetailsBO.getCompanyBO() && 0!=courseDetailsBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			CourseVO courseVO=new CourseVO();
			companyVO.setCompanyId(courseDetailsBO.getCompanyBO().getCompanyId());
			/*courseVO.setCompanyVO(companyVO);
			courseDetailsVO.setCourseVO(courseVO);*/
            courseDetailsVO.setCompanyVO(companyVO);
		}
		courseDetailsVO.setSending_status(true);
		
		List<CourseDetailsVO> courselist=courseDao.viewCoursedetails(courseDetailsVO);
		if(null!=courselist && !courselist.isEmpty() && courselist.size()>0) {
			int count=courseDetailsBO.getRecordIndex();
			for(CourseDetailsVO courseDetailVO:courselist ) {
				CourseDetailsBO courseDetails=new CourseDetailsBO();								
				CourseBO courseBO=new CourseBO();
				CompanyBO companyBO=new CompanyBO();
				courseDetails.setCourseDetailsId(courseDetailVO.getCourseDetailsId());
				courseDetails.setClassesSchedule(courseDetailVO.getClassesSchedule());
				courseDetails.setCurriculum(courseDetailVO.getCurriculum());
				courseDetails.setDescription(courseDetailVO.getDescription());
				courseDetails.setDesigned(courseDetailVO.getDesigned());
				courseDetails.setKeyfeatures(courseDetailVO.getKeyfeatures().replace("<br>", ""));
				//float to int
				float rs=courseDetailVO.getPrice();
				int rupees=(int) rs;
				courseDetails.setRupees(rupees);
				if(null!=companyBO){
				companyBO.setCompanyId(courseDetailVO.getCourseVO().getCompanyVO().getCompanyId());
				companyBO.setCompanyName(courseDetailVO.getCourseVO().getCompanyVO().getCompanyName());
/*				courseBO.setCompanyBO(companyBO);
*/				courseDetails.setCompanyBO(companyBO);
			}
				courseBO.setCourseName(courseDetailVO.getCourseVO().getCourseName());
				courseBO.setCourseId(courseDetailVO.getCourseVO().getCourseId());
				courseDetails.setCurseBO(courseBO);
				count=count+1;
				courseDetails.setS_No(count);
				listCourseDetailsBO.add(courseDetails);
			}
 
		}

		return listCourseDetailsBO;
	}

	@Override
	public long retrieveCourseCompanyCount(CourseBO courseBO) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		if(null!=courseBO.getCompanyBO() && 0!=courseBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(courseBO.getCompanyBO().getCompanyId());
			courseVO.setCompanyVO(companyVO);
			courseVO.setIsDelete(false);
			courseVO.setSending_status(true);
		}
		return courseDao.retrieveCourseCompanyCount(courseVO);
	}
	@Override
	public CourseDetailsBO getCourseDetails(CourseDetailsBO courseDetailsBO) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetailsVO=new CourseDetailsVO();
		CourseVO courseVO=new CourseVO();
		CompanyVO companyVO=new CompanyVO();
		courseVO.setCourseName(courseDetailsBO.getCurseBO().getCourseName());
		courseDetailsVO.setCourseVO(courseVO);
	/*	if(null!=courseDetailsBO.getCompanyBO()){
			companyVO.setCompanyId(courseDetailsBO.getCurseBO().getCompanyBO().getCompanyId());
			companyVO.setCompanyName(courseDetailsBO.getCurseBO().getCompanyBO().getCompanyName());
			courseVO.setCompanyVO(companyVO);
			courseDetailsVO.setCourseVO(courseVO);
		}*/
		
		courseDetailsVO.setIsDelete(false);
		courseDetailsVO= courseDao.getCourseDetails(courseDetailsVO);
		if(null!=courseDetailsVO) {
			CourseBO courseBO=new CourseBO();
			courseDetailsBO.setCourseDetailsId(courseDetailsVO.getCourseDetailsId());
			courseDetailsBO.setClassesSchedule(courseDetailsVO.getClassesSchedule());
			courseDetailsBO.setCurriculum(courseDetailsVO.getCurriculum());
			courseDetailsBO.setDescription(courseDetailsVO.getDescription());
			courseDetailsBO.setDesigned(courseDetailsVO.getDesigned());
			courseDetailsBO.setKeyfeatures(courseDetailsVO.getKeyfeatures());
			//float to int  
			float rs= courseDetailsVO.getCourseVO().getFees();
			int rupees=(int) rs;
			courseDetailsBO.setRupees(rupees);
			
			courseDetailsBO.setCurseBO(courseBO);
			courseBO.setImageName(courseDetailsVO.getCourseVO().getImageName());
			courseBO.setCourseId(courseDetailsVO.getCourseVO().getCourseId());
			courseBO.setCourseName(courseDetailsVO.getCourseVO().getCourseName());
			if(null!=courseDetailsVO && null!=courseDetailsVO.getCourseVO() && null!=courseDetailsVO.getCourseVO().getMetataglist()){
				courseDetailsBO.setMetataglist(courseDetailsVO.getCourseVO().getMetataglist());
			}
			courseDetailsBO.setCurseBO(courseBO);
		}
		return courseDetailsBO;
	}

	@Override
	public int searchCourse(CourseBO courseBO) {
		// TODO Auto-generated method stub
		int totalCourse;
		CourseVO courseVO=new CourseVO();
		if(null!=courseBO.getCompanyBO() && 0!=courseBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(courseBO.getCompanyBO().getCompanyId());
			courseVO.setCompanyVO(companyVO);	
			courseVO.setCourseName(courseBO.getCourseName());
			courseVO.setIsDelete(false);
		}
		if(null!=courseBO.getMetaTitleBO() && null!=courseBO.getMetaTitleBO().getMetaTitle()){
			MetaTitleBO bo=new MetaTitleBO();
			MetaTagVO metatag=new MetaTagVO();
			bo.setMetaTitle(courseBO.getMetaTitleBO().getMetaTitle());
			metatag.setMetaName(bo.getMetaTitle());
			courseVO.setMetatagVO(metatag);
		}else
			if(null!=courseBO&&null!=courseBO.getCourseName()){
				courseVO.setCourseName(courseBO.getCourseName());
			}
		return totalCourse=courseDao.searchCourse(courseVO);
	}
	@Override
	public List<CourseBO> searchPageCourse(CourseBO courseBO) {
		// TODO Auto-generated method stub
		List<CourseVO> courseList=new ArrayList<CourseVO>();
		List<CourseBO> courseListBO=new ArrayList<CourseBO>();
		List<CourseBO> courseLists=new ArrayList<CourseBO>();
		CourseVO courseVO=new CourseVO();
		if(null!=courseBO.getCompanyBO() && 0!=courseBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(courseBO.getCompanyBO().getCompanyId());
			courseVO.setCompanyVO(companyVO);	
			courseVO.setRecordIndex(courseBO.getRecordIndex());
			courseVO.setMaxRecord(courseBO.getMaxRecord());
			if(null!=courseBO&&null!=courseBO.getCourseName()){
				courseVO.setCourseName(courseBO.getCourseName());
			}
			courseVO.setIsDelete(false);
		}
		if(null!=courseBO.getMetaTitleBO() && null!=courseBO.getMetaTitleBO().getMetaTitle()){
			MetaTitleBO bo=new MetaTitleBO();
			MetaTagVO metatag=new MetaTagVO();
			bo.setMetaTitle(courseBO.getMetaTitleBO().getMetaTitle());
			metatag.setMetaName(bo.getMetaTitle());
			courseVO.setMetatagVO(metatag);
		}else
			if(null!=courseBO&&null!=courseBO.getCourseName()){
				courseVO.setCourseName(courseBO.getCourseName());
			}
		courseList=courseDao.searchPageCourse(courseVO);
		if(null!=courseList && courseList.size()>0 && !courseList.isEmpty()){
			int sNo=courseBO.getRecordIndex();
			for(CourseVO course:courseList){
				CourseBO courseBo=new CourseBO();
				courseCategoryVO categoryVO=new courseCategoryVO();
				CourseCategoryBO courseCategory=new CourseCategoryBO();
				courseCategory.setCourseCategoryId(course.getCourseCategoryVo().getCourseCategoryId());
				courseCategory.setCourseCategoryName(course.getCourseCategoryVo().getCourseCategoryName());
				courseBo.setCourseCategoryBO(courseCategory);
				courseBo.setAuthorName(course.getAuthorName());
				courseBo.setCourseId(course.getCourseId());
				courseBo.setCourseName(course.getCourseName());
				courseBo.setFees(course.getFees());
				courseBo.setS_No(++sNo);
				courseBo.setImageName(course.getImageName());
				courseListBO.add(courseBo);
			}
			courseListBO.forEach(course->{
				CourseBO courseBO1=new CourseBO();
				CourseCategoryBO courseCategory=new CourseCategoryBO();
				courseCategory.setCourseCategoryId(course.getCourseCategoryBO().getCourseCategoryId());
				courseCategory.setCourseCategoryName(course.getCourseCategoryBO().getCourseCategoryName());
				courseBO1.setCourseCategoryBO(courseCategory);
				courseBO1.setAuthorName(course.getAuthorName());
				courseBO1.setCourseId(course.getCourseId());
				courseBO1.setCourseName(course.getCourseName());
				courseBO1.setFees(course.getFees());
				float rs=course.getFees();
				int rupees=(int) rs;
				courseBO1.setRupees(rupees);
				courseBO1.setS_No(course.getS_No());
				courseBO1.setImageName(course.getImageName());
				courseLists.add(courseBO1);

			});

		}

		return courseLists;
	}


	@Override
	public CourseBO editCourse(CourseBO courseBO) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(courseBO.getCourseId());
		courseVO = courseDao.editCourse(courseVO);
		if(null!=courseVO && null!=courseVO.getCourseName()){
			CompanyBO companyBo=new CompanyBO();
			CourseCategoryBO courseCategoryBO=new CourseCategoryBO();
			companyBo.setCompanyId(courseVO.getCompanyVO().getCompanyId());
			companyBo.setCompanyName(courseVO.getCompanyVO().getCompanyName());
			courseBO.setCompanyBO(companyBo);
			if(null!=courseVO.getCourseCategoryVo() && 0!=courseVO.getCourseCategoryVo().getCourseCategoryId()){
				courseCategoryBO.setCourseCategoryId(courseVO.getCourseCategoryVo().getCourseCategoryId());
				courseCategoryBO.setCourseCategoryName(courseVO.getCourseCategoryVo().getCourseCategoryName());
				courseBO.setCourseCategoryBO(courseCategoryBO);	
			}
			courseBO.setCourseName(courseVO.getCourseName());
			courseBO.setAuthorName(courseVO.getAuthorName());
			courseBO.setFees(courseVO.getFees());
			courseBO.setCourseId(courseVO.getCourseId());
			courseBO.setImageName(courseVO.getImageName());
		}
		return courseBO;
	}


	@Override
	public boolean postEditCourse(CourseBO editCourseBo) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		CompanyVO companyVo=new CompanyVO();
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setCourseCategoryId(editCourseBo.getCourseCategoryBO().getCourseCategoryId());
		courseVO.setCourseCategoryVo(categoryVO);
		courseVO.setCourseId(editCourseBo.getCourseId());
		courseVO.setAuthorName(editCourseBo.getAuthorName());
		courseVO.setCourseName(editCourseBo.getCourseName());
		courseVO.setImageName(editCourseBo.getImageName());
		companyVo.setCompanyId(editCourseBo.getCompanyBO().getCompanyId());
		courseVO.setCompanyVO(companyVo);
		courseVO.setIsDelete(false);
		courseVO.setSending_status(true);
		courseVO.setModifiedTime(new Date());
		courseVO.setFees(editCourseBo.getFees());
		return courseDao.postEditCourse(courseVO);
	}


	@Override
	public boolean deleteCourse(CourseBO courseBO) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(courseBO.getCourseId());
		courseVO.setIsDelete(true);
		courseVO.setSending_status(false);
		return courseDao.deleteCourse(courseVO);
	}


	@Override
	public CourseDetailsBO getEditCourseDetails(CourseDetailsBO courseDetailsBo) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetailsVo=new CourseDetailsVO();
		CompanyVO companyVO=new CompanyVO();
		courseDetailsVo.setCourseDetailsId(courseDetailsBo.getCourseDetailsId());
		courseDetailsVo.setIsDelete(false);
		courseDetailsVo=courseDao.getEditCourseDetails(courseDetailsVo);
		if(null!=courseDetailsVo && null!=courseDetailsVo.getCourseVO()){
			CourseBO courseBO=new CourseBO();
			CompanyBO companyBO=new CompanyBO();
			courseDetailsBo.setCourseDetailsId(courseDetailsVo.getCourseDetailsId());
			courseDetailsBo.setClassesSchedule(courseDetailsVo.getClassesSchedule());
			courseDetailsBo.setCurriculum(courseDetailsVo.getCurriculum());
			courseDetailsBo.setDescription(courseDetailsVo.getDescription());
			courseDetailsBo.setDesigned(courseDetailsVo.getDesigned());
			courseDetailsBo.setKeyfeatures(courseDetailsVo.getKeyfeatures());
			courseBO.setCourseId(courseDetailsVo.getCourseVO().getCourseId());
			courseBO.setCourseName(courseDetailsVo.getCourseVO().getCourseName());
			courseDetailsBo.setPrice(courseDetailsVo.getPrice());
			courseDetailsBo.setCurseBO(courseBO);
		}
		return courseDetailsBo;
	}


	@Override
	public boolean postEditCourseDetails(CourseDetailsBO courseDetails) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetailsVo=new CourseDetailsVO();
		CourseVO courseVO=new CourseVO();
		CompanyVO companyVO=new CompanyVO();
		courseDetailsVo.setCourseDetailsId(courseDetails.getCourseDetailsId());
		courseDetailsVo.setDescription(courseDetails.getDescription());
		courseDetailsVo.setDesigned(courseDetails.getDesigned());
		courseDetailsVo.setKeyfeatures(courseDetails.getKeyfeatures());
		courseDetailsVo.setClassesSchedule(courseDetails.getClassesSchedule());
		courseDetailsVo.setCurriculum(courseDetails.getCurriculum());
		courseDetailsVo.setIsDelete(false);
		courseDetailsVo.setModifiedTime(new Date());
		courseDetailsVo.setSending_status(true);
		courseVO.setCourseId(courseDetails.getCurseBO().getCourseId());
		courseDetailsVo.setCourseVO(courseVO);
		courseDetailsVo.setPrice(courseDetails.getPrice());
		courseDetailsVo.setCreatedTime(courseDetails.getCreatedTime());
		courseDetailsVo.setModifiedTime(new Date());
		
		if(null!=courseDetails.getCompanyBO()){
		companyVO.setCompanyId(courseDetails.getCompanyBO().getCompanyId());
		courseDetailsVo.setCompanyVO(companyVO);
		}
		return courseDao.postEditCourseDetails(courseDetailsVo);
	}


	@Override
	public boolean deleteCourseDetails(CourseDetailsBO courseDetailsBo) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetailsVo=new CourseDetailsVO();
		courseDetailsVo.setCourseDetailsId(courseDetailsBo.getCourseDetailsId());
		courseDetailsVo.setIsDelete(true);
		courseDetailsVo.setSending_status(false);
		return courseDao.deleteCourseDetails(courseDetailsVo);
	}


	@Override
	public long retrieveOfCourseRegistrationCount(CourseRegistrationBO courseRegister) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public CourseCategoryBO courseCatogery(CourseCategoryBO courseCategoryBO) {
		// TODO Auto-generated method stub
		courseCategoryVO categoryVO=new courseCategoryVO();
		SubcategoryVO courseSubCategoryVO=new SubcategoryVO();
		categoryVO.setCourseCategoryId(courseCategoryBO.getCourseCategoryId());
		categoryVO.setCourseCategoryName(courseCategoryBO.getCourseCategoryName());
		
		/*courseSubCategoryVO.setCourseSubcategoryId(courseCategoryBO.getCourseSubCategoryBO().getCourseSubcategoryId());
		courseSubCategoryVO.setCourseSubcategoryName(courseCategoryBO.getCourseSubCategoryBO().getCourseSubcategoryName());
		categoryVO.setCourseSubCategoryVO(courseSubCategoryVO);*/
		
		categoryVO.setCreatedDate(new Date());
		categoryVO.setModifiedDate(new Date());
		categoryVO.setIsDelete(false);
		boolean status = courseDao.isExixstsCategory(categoryVO);
		if(!status){
			categoryVO.setCreatedDate(new Date());
			categoryVO=courseDao.courseCatogery(categoryVO);
			if(null!=categoryVO && 0!=categoryVO.getCourseCategoryId()){
				courseCategoryBO.setCourseCategoryId(categoryVO.getCourseCategoryId()); 
				courseCategoryBO.setCourseCategoryName(categoryVO.getCourseCategoryName());
			}
		}
		return courseCategoryBO;
	}


	@Override
	public List<CourseCategoryBO> viewCourseCatogery(CourseCategoryBO categoryBO) {
		// TODO Auto-generated method stub
		List<CourseCategoryBO> courseCategoryListBO=new ArrayList<CourseCategoryBO>();
		List<courseCategoryVO> courseCategoryListVO=new ArrayList<courseCategoryVO>();
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setCourseCategoryId(categoryBO.getCourseCategoryId());
		categoryVO.setRecordIndex(categoryBO.getRecordIndex());
		categoryVO.setMaxRecord(categoryBO.getMaxRecord());
		categoryVO.setIsDelete(false);
		courseCategoryListVO=courseDao.viewCourseCatogery(categoryVO);
		if(null!=courseCategoryListVO&& !courseCategoryListVO.isEmpty() && courseCategoryListVO.size()>0){
			int sNo=categoryBO.getRecordIndex();
			for(courseCategoryVO courseCategoryvo:courseCategoryListVO){
				CourseCategoryBO categorybo=new CourseCategoryBO();
				categorybo.setCourseCategoryId(courseCategoryvo.getCourseCategoryId());
				categorybo.setCourseCategoryName(courseCategoryvo.getCourseCategoryName());
				categorybo.setCreatedDate(courseCategoryvo.getCreatedDate());
				courseCategoryListBO.add(categorybo);
			}
		}
		return courseCategoryListBO;
	}


	@Override
	public CourseCategoryBO getEditCoursecategory(CourseCategoryBO categoryBO) {
		// TODO Auto-generated method stub
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setCourseCategoryId(categoryBO.getCourseCategoryId());
		categoryVO=courseDao.getEditCoursecategory(categoryVO);
		if(null!=categoryVO){
			CourseCategoryBO courseCategorybo=new CourseCategoryBO();
			categoryBO.setCourseCategoryId(categoryVO.getCourseCategoryId());
			categoryBO.setCourseCategoryName(categoryVO.getCourseCategoryName());
		}

		return categoryBO;
	}


	@Override
	public Boolean postEditCoursecategory(CourseCategoryBO categoryBO) {
		// TODO Auto-generated method stub
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setCourseCategoryId(categoryBO.getCourseCategoryId());
		categoryVO.setCourseCategoryName(categoryBO.getCourseCategoryName());
		categoryVO.setIsDelete(categoryBO.getIsDelete());
		categoryVO.setModifiedDate(new Date());
		return courseDao.postEditCoursecategory(categoryVO);

	}


	@Override
	public boolean deleteCourseCategory(CourseCategoryBO courseCategoryBO) {
		// TODO Auto-generated method stub
		courseCategoryVO courseCategoryvO=new courseCategoryVO();
		courseCategoryvO.setCourseCategoryId(courseCategoryBO.getCourseCategoryId());
		courseCategoryvO.setIsDelete(true);
		return courseDao.deleteCourseCategory(courseCategoryvO);
	}


	@Override
	public long retrievecourseCategoryCount(CourseCategoryBO categoryBO) {
		// TODO Auto-generated method stub
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setIsDelete(false);
		return courseDao.retrievecourseCategoryCount(categoryVO);

	}

	@Override
	public List<MetaTitleBO> getKeywords(MetaTitleBO metaTitleBO) {
		// TODO Auto-generated method stub
		MetaTagVO metaTitleVo=new MetaTagVO();
		metaTitleVo.setMetaName(metaTitleBO.getMetaTitle());
		return courseDao.getKeywords(metaTitleVo);
	}


	@Override
	public boolean courseCategoryValidations(CourseCategoryBO courseCategoryBO) {
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setCourseCategoryName(courseCategoryBO.getCourseCategoryName());
		categoryVO.setIsDelete(false);
		boolean status = courseDao.isExixstsCategory(categoryVO);
		if(status){
			return true;
		}
		return false;
	}


	@Override
	public CourseRegistrationBO editCourseRegistration(CourseRegistrationBO courseRegistrationBO) {
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		/*CourseVO courseVO=new CourseVO();
	courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
	courseRegisterationVO.setCourseVO(courseVO);
	CompanyVO companyVO=new CompanyVO();
	companyVO.setCompanyId(courseRegistrationBO.getCompanyBO().getCompanyId());
	courseRegisterationVO.setCompanyVO(companyVO);*/

		courseRegisterationVO.setIsDelete(false);
		courseRegisterationVO.setIsActive(true);
		courseRegisterationVO.setCourseRegisterId(courseRegistrationBO.getCourseRegisterId());
		courseRegisterationVO=courseDao.editCourseRegistration(courseRegisterationVO);
		if(null!=courseRegisterationVO) {
			CourseBO courseBO=new CourseBO();
			CompanyBO companyBO=new CompanyBO();
			CourseVO courseVO=new CourseVO();
			courseRegistrationBO.setCourseRegisterId(courseRegisterationVO.getCourseRegisterId());
			int id=courseRegisterationVO.getCompanyVO().getCompanyId();
			companyBO.setCompanyId(id);
			companyBO.setCompanyName(courseRegisterationVO.getCompanyVO().getCompanyName());
			courseRegistrationBO.setCompanyBO(companyBO);
			courseRegistrationBO.setcandidateName(courseRegisterationVO.getCandidateName());
			courseRegistrationBO.setEmailAddress(courseRegisterationVO.getEmailAddress());
			courseRegistrationBO.setMobileNumber(courseRegisterationVO.getMobileNumber());
			courseRegistrationBO.setScheme(courseRegisterationVO.getScheme());
			courseRegistrationBO.setProfessional(courseRegisterationVO.getProfessional());

			courseRegisterationVO.getCourseVO().getCourseId();
			courseBO.setCourseId(courseRegisterationVO.getCourseVO().getCourseId());
			courseBO.setCourseName(courseRegisterationVO.getCourseVO().getCourseName());
			courseRegistrationBO.setCourseBO(courseBO);

		}
		return courseRegistrationBO;
	}


	@Override
	public CourseRegistrationBO PostCourseRegistration(CourseRegistrationBO courseRegistrationBO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		CourseVO courseVO=new CourseVO();
		CompanyVO companyVO=new CompanyVO();

		courseRegisterationVO.setIsDelete(false);
		courseRegisterationVO.setIsActive(true);
		courseRegisterationVO.setCourseRegisterId(courseRegistrationBO.getCourseRegisterId());
		courseRegisterationVO.setCandidateName(courseRegistrationBO.getcandidateName());
		courseRegisterationVO.setEmailAddress(courseRegistrationBO.getEmailAddress());
		courseRegisterationVO.setMobileNumber(courseRegistrationBO.getMobileNumber());
		courseRegisterationVO.setProfessional(courseRegistrationBO.getProfessional());
		courseRegisterationVO.setScheme(courseRegistrationBO.getScheme());
		courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
		companyVO.setCompanyId(courseRegistrationBO.getCompanyBO().getCompanyId());
		/*	courseVO.setCourseName(courseRegistrationBO.getCourseBO().getCourseName());
		 */	courseRegisterationVO.setCourseVO(courseVO);
		 companyVO.setCompanyName(courseRegistrationBO.getCompanyBO().getCompanyName());
		 courseRegisterationVO.setCompanyVO(companyVO);
		 courseRegistrationBO=courseDao.PosteditCourseRegistration(courseRegisterationVO);

		 return courseRegistrationBO;
	}


	@Override
	public boolean DeleteCourseRegistration(CourseRegistrationBO courseRegistrationBO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		CourseVO courseVO=new CourseVO();
		courseRegisterationVO.setIsDelete(false);
		courseRegisterationVO.setIsActive(true);
		courseRegisterationVO.setCourseRegisterId(courseRegistrationBO.getCourseRegisterId());
		boolean check=courseDao.DeleteCourseRegistration(courseRegisterationVO);

		return check;
	}


	@Override
	public CourseRegistrationBO viewCourseRegister(CourseRegistrationBO courseRegistrationBO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegisterationVO=new CourseRegisterationVO();
		courseRegisterationVO.setIsDelete(false);
		courseRegisterationVO.setIsActive(true);
		CourseVO courseVO=new CourseVO();
		courseRegisterationVO.setCourseRegisterId(courseRegistrationBO.getCourseRegisterId());
		courseRegisterationVO=courseDao.viewCourseRegister(courseRegisterationVO);
		/*courseVO.setCourseId(courseRegistrationBO.getCourseBO().getCourseId());
		courseRegisterationVO.setCourseVO(courseVO);
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(courseRegistrationBO.getCompanyBO().getCompanyId());
		courseRegisterationVO.setCompanyVO(companyVO);*/

		if(null!=courseRegisterationVO) {
			CompanyBO companyBO=new CompanyBO();
			CourseBO courseBO=new CourseBO();
			int id=courseRegisterationVO.getCompanyVO().getCompanyId();
			companyBO.setCompanyName(courseRegisterationVO.getCompanyVO().getCompanyName());
			companyBO.setCompanyId(id);
			courseRegistrationBO.setCompanyBO(companyBO);;
			courseRegistrationBO.setcandidateName(courseRegisterationVO.getCandidateName());
			courseRegistrationBO.setEmailAddress(courseRegisterationVO.getEmailAddress());
			courseRegistrationBO.setMobileNumber(courseRegisterationVO.getMobileNumber());
			courseRegistrationBO.setScheme(courseRegisterationVO.getScheme());
			courseRegistrationBO.setProfessional(courseRegisterationVO.getProfessional());
			courseRegisterationVO.getCourseVO().getCourseId();
			courseBO.setCourseId(courseRegisterationVO.getCourseVO().getCourseId());
			courseBO.setCourseName(courseRegisterationVO.getCourseVO().getCourseName());
			courseRegistrationBO.setCourseBO(courseBO);
		}	
		return courseRegistrationBO;
	}


	@Override
	public int searchCourseCategoryName(CourseCategoryBO courseCategoryBO) {
		courseCategoryVO courseCategoryVO=new courseCategoryVO();
		courseCategoryVO.setIsDelete(false);
		return courseDao.retrieveOfEventsCount(courseCategoryVO);
	}



	@Override
	public List<CourseCategoryBO> searchPageCourse(CourseCategoryBO courseCategoryBO) {
		List<courseCategoryVO> courseCatgoryList=new ArrayList<courseCategoryVO>();
		List<CourseCategoryBO> courseCatgoryListBO=new ArrayList<CourseCategoryBO>();
		List<CourseCategoryBO> listcourseCatgoryBO=new ArrayList<CourseCategoryBO>();
		courseCategoryVO courseCategoryVO=new courseCategoryVO();
		courseCategoryVO.setRecordIndex(courseCategoryBO.getRecordIndex());
		courseCategoryVO.setMaxRecord(courseCategoryBO.getMaxRecord());
		courseCategoryVO.setCourseCategoryName(courseCategoryBO.getCourseCategoryName());
		courseCategoryVO.setIsDelete(false);
		courseCategoryVO.setCourseCategoryId(courseCategoryBO.getCourseCategoryId());
		courseCatgoryList=courseDao.searchPageCourse(courseCategoryVO);
		if(null!=courseCatgoryList && courseCatgoryList.size()>0 && !courseCatgoryList.isEmpty()){
			int sNo=courseCategoryVO.getRecordIndex();
			int s_No=1;
			for(courseCategoryVO courseCategoryVo:courseCatgoryList){
				CourseCategoryBO courseCategoryBO1=new CourseCategoryBO();
				courseCategoryBO1.setCourseCategoryId(courseCategoryVo.getCourseCategoryId());
				courseCategoryBO1.setCourseCategoryName(courseCategoryVo.getCourseCategoryName());
				courseCatgoryListBO.add(courseCategoryBO1);
			}
			courseCatgoryListBO.forEach(courseCategoryVo->{
				CourseCategoryBO courseCategoryBo=new CourseCategoryBO();
				courseCategoryBo.setCourseCategoryId(courseCategoryVo.getCourseCategoryId());
				courseCategoryBo.setCourseCategoryName(courseCategoryVo.getCourseCategoryName());
				listcourseCatgoryBO.add(courseCategoryBo);
			});
		}
		return listcourseCatgoryBO;
	}
	@Override
	public List<CourseCategoryBO> viewCourseCategorysearch(CourseCategoryBO categoryBO) {
		List<CourseCategoryBO> courseCategoryListBO=new ArrayList<CourseCategoryBO>();
		List<courseCategoryVO> courseCategoryListVO=new ArrayList<courseCategoryVO>();
		courseCategoryVO categoryVO=new courseCategoryVO();
		categoryVO.setCourseCategoryId(categoryBO.getCourseCategoryId());
		categoryVO.setRecordIndex(categoryBO.getRecordIndex());
		categoryVO.setMaxRecord(categoryBO.getMaxRecord());
/*		categoryVO.setIsDelete(false);
*/		courseCategoryListVO=courseDao.viewCourseCatogery(categoryVO);
		if(null!=courseCategoryListVO&& !courseCategoryListVO.isEmpty() && courseCategoryListVO.size()>0){
			int sNo=categoryBO.getRecordIndex();
			for(courseCategoryVO courseCategoryvo:courseCategoryListVO){
				CourseCategoryBO categorybo=new CourseCategoryBO();
				categorybo.setCourseCategoryId(courseCategoryvo.getCourseCategoryId());
				categorybo.setCourseCategoryName(courseCategoryvo.getCourseCategoryName());				courseCategoryListBO.add(categorybo);
			}
		}
		return courseCategoryListBO;
	}


	@Override
	public CourseCategoryBO viewCategoryDetails(CourseCategoryBO categoryBO) {
		CourseCategoryBO CourseCategoryBO=new CourseCategoryBO();
		courseCategoryVO courseCategoryVO=new courseCategoryVO();
		if(categoryBO.getIsDelete()==false) {
			courseCategoryVO.setMaxRecord(categoryBO.getMaxRecord());
			courseCategoryVO.setRecordIndex(categoryBO.getRecordIndex());
			courseCategoryVO.setIsDelete(false);
		}
		if(courseCategoryVO.getIsDelete()==true){
			courseCategoryVO.setIsDelete(true);
		}
		courseCategoryVO.setCourseCategoryId(categoryBO.getCourseCategoryId());
		courseCategoryVO.setCourseCategoryName(categoryBO.getCourseCategoryName());
		courseCategoryVO=courseDao.viewCategoryDetails(courseCategoryVO);
		if(null!=courseCategoryVO) {
			CourseCategoryBO.setCourseCategoryId(courseCategoryVO.getCourseCategoryId());
			CourseCategoryBO.setCourseCategoryName(courseCategoryVO.getCourseCategoryName());
		}

		return CourseCategoryBO;
	}


	@Override
	public int searchCategory(CourseCategoryBO courseCategoryBO) {
		courseCategoryVO courseCategoryVO=new courseCategoryVO();
		courseCategoryVO.setCourseCategoryName(courseCategoryBO.getCourseCategoryName());
		courseCategoryVO.setIsDelete(false);
		int totalCategory=courseDao.searchCategory(courseCategoryVO);
		return totalCategory;
	}


	@Override
	public List<CourseCategoryBO> searchPageCategory(CourseCategoryBO courseCategoryBO) {
		List<courseCategoryVO> categoryList=new ArrayList<courseCategoryVO>();
		List<CourseCategoryBO> categoryListBO=new ArrayList<CourseCategoryBO>();
		List<CourseCategoryBO> ListcategoryBO=new ArrayList<CourseCategoryBO>();
		courseCategoryVO courseCategoryVO=new courseCategoryVO();
		courseCategoryVO.setRecordIndex(courseCategoryBO.getRecordIndex());
		courseCategoryVO.setMaxRecord(courseCategoryBO.getMaxRecord());
		courseCategoryVO.setCourseCategoryName(courseCategoryBO.getCourseCategoryName());
		courseCategoryVO.setIsDelete(false);
		categoryList=courseDao.searchPageCategory(courseCategoryVO);
		if(null!=categoryList && categoryList.size()>0 && !categoryList.isEmpty()){
			int sNo=courseCategoryBO.getRecordIndex();
			int s_No=1;
			for(courseCategoryVO courseCategory:categoryList){
				courseCategoryBO.setCourseCategoryId(courseCategory.getCourseCategoryId());
				courseCategoryBO.setCourseCategoryName(courseCategory.getCourseCategoryName());
				courseCategoryBO.setS_No(s_No++);
				categoryListBO.add(courseCategoryBO);
			}
			categoryListBO.forEach(category->{
				courseCategoryBO.setCourseCategoryId(category.getCourseCategoryId());
				courseCategoryBO.setCourseCategoryName(category.getCourseCategoryName());
				courseCategoryBO.setS_No(category.getS_No());
				ListcategoryBO.add(courseCategoryBO);
				
			});
		}
		return ListcategoryBO;
	}


	@Override
	public boolean courseNameValidations(String courseName) {
		// TODO Auto-generated method stub
		CourseVO courseVO=new CourseVO();

		courseVO.setCourseName(courseName);
		courseVO.setIsDelete(false);
		return courseDao.courseNameValidations(courseVO);
	}


/*	@Override
	public boolean courseNameValidations(CourseBO courseBO) {
		CourseVO courseVO=new CourseVO();

		courseVO.setCourseName(courseBO.getCourseName());
		courseVO.setIsDelete(false);
		return courseDao.courseNameValidations(courseVO);
	}*/
}