package com.scube.techboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.bo.FeedbackBO;
import com.scube.techboot.bo.StudentCouresBO;

import com.scube.techboot.bo.StudentRegisterBO;
import com.scube.techboot.dao.StudentDao;
import com.scube.techboot.utils.EncryptAndDecrypt;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.CustomerVO;
import com.scube.techboot.vo.FeedbackVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.StudentEnrollment;
import com.scube.techboot.vo.StudentRegisterVO;
import com.scube.techboot.vo.UserRoleVO;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;
	
	
	
	@Override
	@Transactional
	public StudentRegisterBO saveStudent(StudentRegisterBO studentRegisterBO) throws Exception{
		

		StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
		
		//set Student Details
		studentRegisterVO.setStudentId(studentRegisterBO.getStudentRegisterId());
		studentRegisterVO.setFirstName(studentRegisterBO.getFirstName());
		studentRegisterVO.setLastName(studentRegisterBO.getLastName());
		studentRegisterVO.setConfirmemailAddress(studentRegisterBO.getConfirmemailAddress());        
		studentRegisterVO.setEmailAddress(studentRegisterBO.getEmailAddress());
		String password=EncryptAndDecrypt.encrypt(studentRegisterBO.getPassword(),EncryptAndDecrypt.TOKEN);
		studentRegisterVO.setPassword(password);
		String confirmpassword=EncryptAndDecrypt.encrypt(studentRegisterBO.getConfirmPassword(), EncryptAndDecrypt.TOKEN);
		studentRegisterVO.setConfirmPassword(confirmpassword);
		studentRegisterVO.setAddress(studentRegisterBO.getAddress());
		studentRegisterVO.setMobileNo(studentRegisterBO.getMobileNo());
		studentRegisterVO.setWhatsAppNo(studentRegisterBO.getWhatsAppNo());
		studentRegisterVO.setCity(studentRegisterBO.getCity());
		studentRegisterVO.setState(studentRegisterBO.getState());
		studentRegisterVO.setGender(studentRegisterBO.getGender());
		/* StudentRegisterVO.setCountry(studentRegisterBO.getCountry()); */
		studentRegisterVO.setMaritalStatus(studentRegisterBO.getMaritalStatus());
		studentRegisterVO.setLanguage(studentRegisterBO.getLanguage());
		studentRegisterVO.setIsDelete(false);
		studentRegisterVO.setSending_status(true);
		studentRegisterVO.setCreatedBy(studentRegisterBO.getCreatedBy());
		studentRegisterVO.setCreatedTime(new Date());
		//Set Login Details
		LoginVO loginVO=new LoginVO();
		loginVO.setEmailAddress(studentRegisterBO.getEmailAddress());
		String password1=EncryptAndDecrypt.encrypt(studentRegisterBO.getPassword(),EncryptAndDecrypt.TOKEN);
		loginVO.setPassword(password1);
		loginVO.setCreatedTime(new Date());
		loginVO.setIsDelete(false);
		loginVO.setCreatedBy(studentRegisterBO.getCreatedBy());
		loginVO.setSending_status(true);
		loginVO.setUserType("student");
		UserRoleVO userRoleVO=new UserRoleVO();
		userRoleVO.setUserRoleId(4);
		loginVO.setUserRoleVO(userRoleVO);
		loginVO.setIsActive(true);
		loginVO.setStudent(studentRegisterVO);		
		studentRegisterVO.setLogin(loginVO);
		
		
		//Set Enrollment Details
		CourseVO courseVO=new CourseVO();
		courseVO.setCourseId(Integer.parseInt(studentRegisterBO.getCourseBO().getCourseName()));
		//CourseVO courseVo=studentDao.getCompanyId(courseVO.getCourseId());
		//
		List<StudentEnrollment> Enrollment=new  ArrayList<StudentEnrollment>();
		//
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		studentEnrollment.setCourseVO(courseVO);
		studentEnrollment.setStudent(studentRegisterVO);
		studentEnrollment.setEnrollmentDate(new Date());
		studentEnrollment.setIsDelete(false);
		studentEnrollment.setSending_status(true);
		studentEnrollment.setCreatedBy(studentRegisterBO.getCreatedBy());
		studentEnrollment.setCreatedTime(new Date());
		//
		Enrollment.add(studentEnrollment);
		studentRegisterVO.setEnrollment(Enrollment);
		/*//company
		if(null!=studentRegisterBO.getCompanyBO() && 0!=studentRegisterBO.getCompanyBO().getCompanyId()){
			CompanyVO companyVO =new CompanyVO();
			 companyVO.setCompanyId(studentRegisterBO.getCompanyBO().getCompanyId());
			 studentRegisterVO.setCompanyVO(companyVO);
		}*/
		
		
		studentRegisterBO=studentDao.saveStudent(studentRegisterVO);
		

		return studentRegisterBO;
	}
	
	@Override
	public boolean emailAddressValidation(String emailAddress) {
		
		StudentRegisterVO StudentRegisterVO=new StudentRegisterVO();
		StudentRegisterVO.setEmailAddress(emailAddress);
		StudentRegisterVO.setIsDelete(false);

		return studentDao.emailAddressValidation(StudentRegisterVO);
	}
	
	@Override
	@Transactional
	public long saveCourseEnrollment(StudentCouresBO studentCouresBO, long studentId) {
	
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		StudentRegisterVO StudentRegisterVO=new StudentRegisterVO();
		long id=0;
		if(0<studentId){
			CourseVO courseVO=new CourseVO();
			courseVO.setCourseId(Integer.parseInt(studentCouresBO.getCourseBO().getCourseName()));
			LoginVO login=new LoginVO();
			login.setLoginId(studentId);
			StudentRegisterVO.setStudentId(studentId);
			studentEnrollment.setStudent(StudentRegisterVO);
			studentEnrollment.setCourseVO(courseVO);
			studentEnrollment.setCreatedTime(new Date());
			studentEnrollment.setEnrollmentDate(new Date());
			studentEnrollment.setCreatedBy(studentId);//login id
			//studentEnrollment.setLoginVO(login);
			id=studentDao.saveStudentCourse(studentEnrollment);

		}

		return id;
	}
	
	@Override
	public long saveCourseEnrollment(StudentCouresBO studentCouresBO) {
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		long id=0;
		if(null!=studentCouresBO.getStudentLoginId().getStudentRegisterBO().getFirstName()){
			CourseVO courseVO=new CourseVO();
			courseVO.setCourseId(Integer.parseInt(studentCouresBO.getCourseBO().getCourseName()));
			studentEnrollment.setCourseVO(courseVO);
		    StudentRegisterVO student = new StudentRegisterVO();
		    student.setStudentId(Long.parseLong(studentCouresBO.getStudentLoginId().getStudentRegisterBO().getFirstName()));
		    studentEnrollment.setStudent(student);
		    //my modification
		    studentEnrollment.setCreatedTime(new Date());
		    studentEnrollment.setCreatedBy(studentCouresBO.getCreatedBy());
		    studentEnrollment.setIsDelete(false);
		    studentEnrollment.setSending_status(true);


		    studentEnrollment.setEnrollmentDate(new Date());

		    //
			id=studentDao.saveStudentCourse(studentEnrollment);

		}

		return id;
	}

	
	@Override
	public List<StudentCouresBO> getStudentCouresList(long studentId) {
		
		List<StudentCouresBO> listBo=new ArrayList<StudentCouresBO>();
		List<StudentEnrollment> listVo=new ArrayList<StudentEnrollment>();
		StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		studentRegisterVO.setStudentId(studentId);
		studentEnrollment.setStudent(studentRegisterVO);
		studentEnrollment.setIsDelete(true);
		List<StudentEnrollment> list=studentDao.getStudentCouresList(studentEnrollment);
		if(null!=list && !list.isEmpty() && list.size()>0) {
			int sNo = studentEnrollment.getRecordIndex();
		    for(StudentEnrollment enrollment:list) {
		    	StudentEnrollment studEnrollment=new StudentEnrollment();
				CourseVO courseVO=new CourseVO();
				StudentRegisterVO studentRegisterVo=new StudentRegisterVO();
				courseVO.setCourseId(enrollment.getCourseVO().getCourseId());
				courseVO.setCourseName(enrollment.getCourseVO().getCourseName());
				studentRegisterVo.setFirstName(enrollment.getStudent().getFirstName());
				studentRegisterVo.setStudentId(enrollment.getStudent().getStudentId());//
				studEnrollment.setStudent(studentRegisterVo);
				studEnrollment.setStudentEnrollmentId(enrollment.getStudentEnrollmentId());//
				courseVO.setFees(enrollment.getCourseVO().getFees());
				studEnrollment.setCourseVO(courseVO);
				studEnrollment.setS_No(++sNo);
				listVo.add(studEnrollment);
		    }
		    listVo.forEach(listvo->{
			StudentCouresBO studentCouresBO=new StudentCouresBO();
			CourseBO courseBO=new CourseBO();
			StudentRegisterBO studentRegisterBO=new StudentRegisterBO();
			courseBO.setCourseId(listvo.getCourseVO().getCourseId());
			courseBO.setCourseName(listvo.getCourseVO().getCourseName());
			studentRegisterBO.setFirstName(listvo.getStudent().getFirstName());
			studentRegisterBO.setStudentRegisterId(listvo.getStudent().getStudentId());//
			studentCouresBO.setStudentRegisterBO(studentRegisterBO);
			courseBO.setFees(listvo.getCourseVO().getFees());
			studentCouresBO.setCourseBO(courseBO);

			studentCouresBO.setStudentCouresId(listvo.getStudentEnrollmentId());//

			studentCouresBO.setS_No(listvo.getS_No());
			listBo.add(studentCouresBO);
		});
	}
		return listBo;
	}
	
	@Override
	@Transactional
	public List<StudentCouresBO> getEntrollmentList(StudentCouresBO student) {
		List<StudentCouresBO> listBo=new ArrayList<StudentCouresBO>();
		List<StudentEnrollment> listVo=new ArrayList<StudentEnrollment>();
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		//my modification
		studentEnrollment.setIsDelete(student.getIsDelete());
		studentEnrollment.setSending_status(student.getSending_status());
		//
		studentEnrollment.setRecordIndex(student.getRecordIndex());
		studentEnrollment.setMaxRecord(student.getMaxRecord());
		List<StudentEnrollment> list=studentDao.getStudentCouresList(studentEnrollment);
		if(null!=list && !list.isEmpty() && list.size()>0) {
			int sNo = student.getRecordIndex();
		    for(StudentEnrollment enrollment:list) {
		    	StudentEnrollment studEnrollment=new StudentEnrollment();
				CourseVO courseVO=new CourseVO();
				StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
				courseVO.setCourseId(enrollment.getCourseVO().getCourseId());
				courseVO.setCourseName(enrollment.getCourseVO().getCourseName());
				studentRegisterVO.setFirstName(enrollment.getStudent().getFirstName());
				//my modification
				studentRegisterVO.setStudentId(enrollment.getStudent().getStudentId());
				//
				studEnrollment.setStudent(studentRegisterVO);
				studEnrollment.setStudentEnrollmentId(enrollment.getStudentEnrollmentId());//my modification

				courseVO.setFees(enrollment.getCourseVO().getFees());
				studEnrollment.setCourseVO(courseVO);
				studEnrollment.setS_No(++sNo);
				listVo.add(studEnrollment);
		    }
		    listVo.forEach(listvo->{
			StudentCouresBO studentCouresBO=new StudentCouresBO();
			CourseBO courseBO=new CourseBO();
			StudentRegisterBO studentRegisterBO=new StudentRegisterBO();
			courseBO.setCourseId(listvo.getCourseVO().getCourseId());
			courseBO.setCourseName(listvo.getCourseVO().getCourseName());
			studentRegisterBO.setFirstName(listvo.getStudent().getFirstName());
			//my modification

			studentRegisterBO.setStudentRegisterId(listvo.getStudent().getStudentId());
			//
			studentCouresBO.setStudentRegisterBO(studentRegisterBO);
			courseBO.setFees(listvo.getCourseVO().getFees());
			studentCouresBO.setCourseBO(courseBO);
			studentCouresBO.setStudentCouresId(listvo.getStudentEnrollmentId());//my modification
			studentCouresBO.setS_No(listvo.getS_No());
			listBo.add(studentCouresBO);
		});
	}
		return listBo;
	}
	
	@Override
	public List<StudentRegisterBO> studentList(StudentRegisterBO student) {
		StudentRegisterVO registerVo = new StudentRegisterVO();
		List<StudentRegisterBO> studentList = new ArrayList<StudentRegisterBO>(); 
		registerVo.setIsDelete(student.getIsDelete());
		registerVo.setSending_status(student.getSending_status());
		List<StudentRegisterVO> registerList = studentDao.studentList(registerVo);
		if(null!=registerList && !registerList.isEmpty() && registerList.size()>0) {
			AtomicInteger incre=new AtomicInteger(0);
			registerList.forEach(register->{
				StudentRegisterBO registerBo = new StudentRegisterBO();
				registerBo.setStudentRegisterId(register.getStudentId());
				registerBo.setFirstName(register.getFirstName());
				registerBo.setEmailAddress(register.getEmailAddress());
				registerBo.setS_No(incre.incrementAndGet());
				registerBo.setMobileNo(register.getMobileNo());
				studentList.add(registerBo);
			});
		}
		return studentList;
	}
	
	@Override
	public StudentRegisterBO getStudentProfile(long studentId) {
		
       //StudentEnrollment StudentEnrollment=new StudentEnrollment();
		StudentRegisterBO  StudentRegisterBO=new StudentRegisterBO();
		StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
		
		studentRegisterVO =studentDao.getStudentProfile(studentId);
		if(null!=studentRegisterVO) {
			StudentRegisterBO.setStudentRegisterId(studentRegisterVO.getStudentId());
			StudentRegisterBO.setFirstName(studentRegisterVO.getFirstName());
			StudentRegisterBO.setLastName(studentRegisterVO.getLastName());
			StudentRegisterBO.setEmailAddress(studentRegisterVO.getEmailAddress());
			StudentRegisterBO.setConfirmemailAddress(studentRegisterVO.getConfirmemailAddress());
			StudentRegisterBO.setAddress(studentRegisterVO.getAddress());
			StudentRegisterBO.setCity(studentRegisterVO.getCity());
			StudentRegisterBO.setGender(studentRegisterVO.getGender());
			StudentRegisterBO.setMaritalStatus(studentRegisterVO.getMaritalStatus());
			StudentRegisterBO.setLanguage(studentRegisterVO.getLanguage());
			StudentRegisterBO.setState(studentRegisterVO.getState());
			StudentRegisterBO.setMobileNo(studentRegisterVO.getMobileNo());
			StudentRegisterBO.setWhatsAppNo((studentRegisterVO.getWhatsAppNo()));
			
		}
		
		
		return StudentRegisterBO;
	}
	
	@Override
	public StudentCouresBO getEditCourseEnrollement(StudentCouresBO studentCouresBO) {
		StudentEnrollment  studentEnrollment=new StudentEnrollment();
		CourseVO CourseVO=new CourseVO();
		CourseVO.setCourseId(studentCouresBO.getCourseBO().getCourseId());
		StudentRegisterVO registerVO = new StudentRegisterVO();
		 registerVO.setStudentId(studentCouresBO.getStudentRegisterBO().getStudentRegisterId());
		 studentEnrollment.setCourseVO(CourseVO);
		 studentEnrollment.setStudent(registerVO);
		studentEnrollment=studentDao.getEditCourseEnrollement(studentEnrollment);
		if(null!=studentEnrollment) {
			CourseBO CourseBO=new CourseBO();
			studentCouresBO.setStudentCouresId(studentEnrollment.getStudentEnrollmentId());
			CourseBO.setCourseName(studentEnrollment.getCourseVO().getCourseName());
			CourseBO.setCourseId(studentEnrollment.getCourseVO().getCourseId());
			studentCouresBO.setCourseBO(CourseBO);
		}

		return studentCouresBO;
	}
	@Override
	public boolean editSaveCourseEnrollment(StudentCouresBO studentCouresBO, long studentLoginId) {
		boolean status=false;
		
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		
		if(0<studentLoginId){
			CourseVO courseVO=new CourseVO();
			courseVO.setCourseId(Integer.parseInt(studentCouresBO.getCourseBO().getCourseName()));
			//LoginVO login=new LoginVO();
			//login.setLoginId(studentLoginId);
			studentEnrollment.setCourseVO(courseVO);
			StudentRegisterVO studentregistervo= new StudentRegisterVO();
			studentregistervo.setStudentId(studentLoginId);
			studentEnrollment.setStudent(studentregistervo);
			studentEnrollment.setStudentEnrollmentId(studentCouresBO.getStudentCouresId());
			
			studentEnrollment.setModifiedBy(studentCouresBO.getModifiedBy());
			studentEnrollment.setModifiedTime(new Date());
				status=studentDao.editSaveStudentCourse(studentEnrollment);
			}
		return status;
	}
	@Override
	@Transactional
	public boolean deleteCourseEnrollemt(StudentCouresBO studentCouresBO) {			
		StudentEnrollment StudentCouresVo=new StudentEnrollment();
		StudentCouresVo.setStudentEnrollmentId(studentCouresBO.getStudentCouresId());
		StudentCouresVo.setIsDelete(true);
		StudentCouresVo.setSending_status(false);
		return studentDao.deleteCourseEnrollemt(StudentCouresVo);
		
	}

	/*@Override
	public List<StudentEnrollmentBO> getStudentReporting(StudentEnrollmentBO student) {
		// TODO Auto-generated method stub
		List<StudentEnrollmentBO> listBo=new ArrayList<StudentEnrollmentBO>();
		StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
		StudentEnrollment studentEnrollment=new StudentEnrollment();
		//studentRegisterVO.setStudentId(studentId);
		//studentEnrollment.setStudent(studentRegisterVO);
		studentEnrollment.setIsDelete(student.getIsDelete());
		List<StudentEnrollment> list=studentDao.getStudentReporting(studentEnrollment);
		if(null!=list && !list.isEmpty() && list.size()>0) {
			list.forEach(listVO->{
				StudentEnrollmentBO studentEnrollmentBO=new StudentEnrollmentBO();
				CourseVO courseVO=new CourseVO();
				StudentRegisterVO studentRegistervo=new StudentRegisterVO();
				courseVO.setCourseId(listVO.getCourseVO().getCourseId());
				courseVO.setCourseName(listVO.getCourseVO().getCourseName());
				courseVO.setFees(listVO.getCourseVO().getFees());
				studentRegistervo.setFirstName(listVO.getStudent().getFirstName());
				studentRegistervo.setMobileNo(listVO.getStudent().getMobileNo());
				studentEnrollmentBO.setStudent(studentRegistervo);
				studentEnrollmentBO.setCourseVO(courseVO);
				listBo.add(studentEnrollmentBO);
				
			});
				
			}

		return listBo;
		
	}*/
	
	
	@Override
	public boolean isCourseExist(StudentCouresBO studentCouresBO,long studId) {
		// TODO Auto-generated method stub
		StudentEnrollment studentenrollmentVO=new StudentEnrollment();
		CourseVO coursevo=new CourseVO();
		StudentRegisterVO studentvo=new StudentRegisterVO();
		
		    coursevo.setCourseId(Integer.parseInt(studentCouresBO.getCourseBO().getCourseName()));
		    studentvo.setStudentId(studId);
		    studentenrollmentVO.setStudent(studentvo);
		    studentenrollmentVO.setCourseVO(coursevo);
				
		return studentDao.isExist(studentenrollmentVO);
	}
	
	
	@Override
	public List<StudentCouresBO> searchCoures(StudentCouresBO studentCouresBO, long studentId) {
		// TODO Auto-generated method stub
		
		List<StudentCouresBO>studentCouresList=new ArrayList<StudentCouresBO>();
		List<StudentEnrollment> studnetEnrollmentList=new ArrayList<StudentEnrollment>();
		StudentEnrollment studentEnrollmentVO=new StudentEnrollment(); 
		StudentRegisterVO studentvo=new StudentRegisterVO();
		
		/*List<CourseVO>courseList=new ArrayList<CourseVO>();*/
		CourseVO courseVo=new CourseVO();                            
		courseVo.setCourseName(studentCouresBO.getCourseBO().getCourseName());		                          
		studentEnrollmentVO.setCourseVO(courseVo);
		studentvo.setStudentId(studentId);
	    studentEnrollmentVO.setStudent(studentvo);	    
		studnetEnrollmentList=studentDao.searchCourseDao(studentEnrollmentVO);
		
		for (StudentEnrollment studentEnrollment : studnetEnrollmentList) {
			 CourseBO coursebo=new CourseBO();
			 StudentCouresBO studentCourseBo=new StudentCouresBO();
			
			coursebo.setCourseName(studentEnrollment.getCourseVO().getCourseName());
			coursebo.setFees(studentEnrollment.getCourseVO().getFees());
			studentCourseBo.setCourseBO(coursebo);
			studentCouresList.add(studentCourseBo);
			
		}
		
		return studentCouresList;
	}

	@Override
	public long retrieveCourseCount(StudentCouresBO courseBo) {
		StudentEnrollment enrollment = new StudentEnrollment();
		enrollment.setIsDelete(false);
		return studentDao.retrieveCourseCount(enrollment);
	}

	@Override
	public List<FeedbackBO> viewFeedback() {
		List<FeedbackBO>feedbackbolist=new ArrayList<FeedbackBO>();
		List<FeedbackVO>feedbackvolist=new ArrayList<FeedbackVO>();
		
		feedbackvolist=studentDao.viewfeedback();
		
		for(FeedbackVO feedbackVO:feedbackvolist){
			FeedbackBO feedbackBO=new FeedbackBO();
			feedbackBO.setFeedbackId((int) feedbackVO.getFeedbackId());
			feedbackBO.setCommand(feedbackVO.getCommand());
			feedbackBO.setStarRating(feedbackVO.getStarRating());
			
			if(null!=feedbackVO.getCourseVO()){
				CourseBO course=new CourseBO();
				course.setCourseName(feedbackVO.getCourseVO().getCourseName());
				feedbackBO.setCourseBO(course);
			}
		
			feedbackbolist.add(feedbackBO);
		}
		return feedbackbolist;
		
	}

	@Override
	public FeedbackBO editFeedback(long id) {
		FeedbackBO feedbackBO=new FeedbackBO();
		FeedbackVO feedbackVO=new FeedbackVO();
		feedbackVO=studentDao.editFeedback(id);
		feedbackBO.setFeedbackId((int) feedbackVO.getFeedbackId());
		feedbackBO.setStarRating(feedbackVO.getStarRating());
		feedbackBO.setCommand(feedbackVO.getCommand());
		CourseBO course=new CourseBO();
		String ids=String.valueOf(feedbackVO.getCourseVO().getCourseId());
		course.setCourseName(ids);
		feedbackBO.setCourseBO(course);
		
		
		
		
		return feedbackBO;
	}

	@Override
	public boolean updateFeedback(FeedbackBO feedbackBO) {
		boolean status=false;
		FeedbackVO feedbackVO=new FeedbackVO();
		feedbackVO.setStarRating(feedbackBO.getStarRating());
		feedbackVO.setCommand(feedbackBO.getCommand());
		feedbackVO.setFeedbackId(feedbackBO.getFeedbackId());
		
		CourseVO course=new CourseVO();
		course.setCourseId(Integer.valueOf(feedbackBO.getCourseBO().getCourseName()));
		course.setCourseName(feedbackBO.getCourseBO().getCourseName());
		feedbackVO.setCourseVO(course);
		status=studentDao.updateFeedback(feedbackVO);
		
		return status;
	}

	@Override
	public boolean deletefeedback(FeedbackBO feedbackBO) {
		FeedbackVO feedbackVO=new FeedbackVO();
		feedbackVO.setFeedbackId(feedbackBO.getFeedbackId());
			return studentDao.deletefeedback(feedbackVO);	
	}
	

	@Override
	public long saveFeedback(FeedbackBO feedbackBO) {
		FeedbackVO feedbackVO=new FeedbackVO();
		feedbackVO.setStarRating(feedbackBO.getStarRating());
		feedbackVO.setCommand(feedbackBO.getCommand());
		
		StudentRegisterVO registerVO=new StudentRegisterVO();
		registerVO.setStudentId(feedbackBO.getStudentRegisterBO().getStudentRegisterId());
		feedbackVO.setStudentRegisterVO(registerVO);
		
		if(null!=feedbackBO.getCourseBO()&& null!=feedbackBO.getCourseBO().getCourseName()){
			CourseVO course=new CourseVO();
			 int courseId=Integer.parseInt(feedbackBO.getCourseBO().getCourseName());
			course.setCourseId(courseId);
			course.setCourseName(feedbackBO.getCourseBO().getCourseName());
			feedbackVO.setCourseVO(course);
		
		}
				
		return studentDao.saveFeedback(feedbackVO);
		 
	}

	@Override
	public List<StudentRegisterBO> listOfPageStudent(StudentRegisterBO student) {
		// TODO Auto-generated method stub
		StudentRegisterVO registerVo = new StudentRegisterVO();
		List<StudentRegisterBO> studentList = new ArrayList<StudentRegisterBO>(); 
		registerVo.setIsDelete(student.getIsDelete());
		registerVo.setSending_status(student.getSending_status());
		registerVo.setRecordIndex(student.getRecordIndex());
		registerVo.setMaxRecord(student.getMaxRecord());
		List<StudentRegisterVO> registerList = studentDao.listOfPageStudent(registerVo);
		if(null!=registerList && !registerList.isEmpty() && registerList.size()>0) {
			AtomicInteger incre=new AtomicInteger(0);
			registerList.forEach(register->{
				StudentRegisterBO registerBo = new StudentRegisterBO();
				registerBo.setStudentRegisterId(register.getStudentId());
				registerBo.setFirstName(register.getFirstName());
				registerBo.setEmailAddress(register.getEmailAddress());
				registerBo.setS_No(incre.incrementAndGet());
				registerBo.setMobileNo(register.getMobileNo());
				studentList.add(registerBo);
			});
		}
		return studentList;
	}

	@Override
	public List<StudentRegisterBO> totalSearchStudent(StudentRegisterBO studentBo) {
		// TODO Auto-generated method stub
				StudentRegisterVO studentVO=new StudentRegisterVO();
				List<StudentRegisterVO> listStudentVO=new ArrayList<StudentRegisterVO>();
				List<StudentRegisterBO> listStudentBO=new ArrayList<StudentRegisterBO>();
				studentVO.setIsDelete(false);
				if(null!=studentBo.getEmailAddress() && !studentBo.getEmailAddress().isEmpty() ){
					studentVO.setEmailAddress(studentBo.getEmailAddress());
				}
				else if(null!=studentBo.getFirstName() && !studentBo.getFirstName().isEmpty() ){
					studentVO.setFirstName(studentBo.getFirstName());

				}
				else{
					studentVO.setMobileNo(studentBo.getMobileNo());
				}
				listStudentVO=studentDao.totalSearchStudent(studentVO);
				if(null!=listStudentVO && ! listStudentVO.isEmpty() && listStudentVO.size()>0) {
					AtomicInteger incre=new AtomicInteger(0);
					listStudentVO.forEach(register->{
						StudentRegisterBO registerBo = new StudentRegisterBO();
						registerBo.setStudentRegisterId(register.getStudentId());
						registerBo.setFirstName(register.getFirstName());
						registerBo.setEmailAddress(register.getEmailAddress());
						registerBo.setS_No(incre.incrementAndGet());
						registerBo.setMobileNo(register.getMobileNo());
						listStudentBO.add(registerBo);
					});
				}
				return listStudentBO;
		
	}

	@Override
	public List<StudentRegisterBO> searchStudent(StudentRegisterBO studentBo) {
		// TODO Auto-generated method stub
		StudentRegisterVO studentVO=new StudentRegisterVO();
		List<StudentRegisterVO> listStudentVO=new ArrayList<StudentRegisterVO>();
		List<StudentRegisterBO> listStudentBO=new ArrayList<StudentRegisterBO>();
		int s_No=studentBo.getRecordIndex();
		studentVO.setIsDelete(false);
		if(null!=studentBo.getEmailAddress() && !studentBo.getEmailAddress().isEmpty() ){
			studentVO.setEmailAddress(studentBo.getEmailAddress());
		}
		else if(null!=studentBo.getFirstName() && !studentBo.getFirstName().isEmpty() ){
			studentVO.setFirstName(studentBo.getFirstName());
			studentVO.setMaxRecord(studentBo.getMaxRecord());
			studentVO.setRecordIndex(studentBo.getRecordIndex());
		}
		else{
			studentVO.setMobileNo(studentBo.getMobileNo());
		}
		listStudentVO=studentDao.searchStudent(studentVO);
		if(null!=listStudentVO && ! listStudentVO.isEmpty() && listStudentVO.size()>0) {
			AtomicInteger incre=new AtomicInteger(0);
			listStudentVO.forEach(register->{
				StudentRegisterBO registerBo = new StudentRegisterBO();
				registerBo.setStudentRegisterId(register.getStudentId());
				registerBo.setFirstName(register.getFirstName());
				registerBo.setEmailAddress(register.getEmailAddress());
				registerBo.setS_No(incre.incrementAndGet());
				registerBo.setMobileNo(register.getMobileNo());
				listStudentBO.add(registerBo);
			});
		}
		return listStudentBO;
		
	}

	@Override
	public boolean updateStudent(StudentRegisterBO studentRegisterBO) {
		StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
		
		//set Student Details
		studentRegisterVO.setStudentId(studentRegisterBO.getStudentRegisterId());
		studentRegisterVO.setFirstName(studentRegisterBO.getFirstName());
		studentRegisterVO.setLastName(studentRegisterBO.getLastName());
		studentRegisterVO.setConfirmemailAddress(studentRegisterBO.getConfirmemailAddress());        
		studentRegisterVO.setEmailAddress(studentRegisterBO.getEmailAddress());
		studentRegisterVO.setAddress(studentRegisterBO.getAddress());
		studentRegisterVO.setMobileNo(studentRegisterBO.getMobileNo());
		studentRegisterVO.setWhatsAppNo(studentRegisterBO.getWhatsAppNo());
		studentRegisterVO.setCity(studentRegisterBO.getCity());
		studentRegisterVO.setState(studentRegisterBO.getState());
		studentRegisterVO.setGender(studentRegisterBO.getGender());
		studentRegisterVO.setMaritalStatus(studentRegisterBO.getMaritalStatus());
		studentRegisterVO.setLanguage(studentRegisterBO.getLanguage());
		studentRegisterVO.setIsDelete(false);
		studentRegisterVO.setSending_status(true);
		studentRegisterVO.setModifiedBy(studentRegisterBO.getModifiedBy());
		studentRegisterVO.setModifiedTime(new Date());
		//login
		/*LoginVO loginVO=new LoginVO();
		loginVO.setEmailAddress(studentRegisterBO.getEmailAddress());
		loginVO.setModifiedBy(studentRegisterBO.getModifiedBy());
		loginVO.setModifiedTime(new Date());
		//loginVO.setStudent(studentRegisterVO);	
		studentRegisterVO.setLogin(loginVO);
		*/
		boolean status=studentDao.updateStudent(studentRegisterVO);
		

		return status;
	}

	@Override
	public boolean deleteStudent(StudentRegisterBO studentRegisterBO) {
		StudentRegisterVO studentRegisterVO=new StudentRegisterVO();
		LoginVO loginVO=new LoginVO();
		studentRegisterVO.setStudentId(studentRegisterBO.getStudentRegisterId());
		studentRegisterVO.setIsDelete(studentRegisterBO.getIsDelete());
		studentRegisterVO.setSending_status(studentRegisterBO.getSending_status());
		return studentDao.deleteStudent(studentRegisterVO);
	}

	@Override
	public boolean mobileValidation(long mobilenumber) {
		StudentRegisterVO StudentRegisterVO=new StudentRegisterVO();
		StudentRegisterVO.setMobileNo(mobilenumber);
		StudentRegisterVO.setIsDelete(false);

		 return studentDao.mobileValidation(StudentRegisterVO);
	}

	@Override
	public int getCompanyLogin(int companyId) {
		// TODO Auto-generated method stub
		return studentDao.getCompanyLogin(companyId);
	}
	
}

	
	
	


