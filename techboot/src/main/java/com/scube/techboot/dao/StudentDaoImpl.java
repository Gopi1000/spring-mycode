package com.scube.techboot.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.StudentRegisterBO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.CustomerVO;
import com.scube.techboot.vo.FeedbackVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.StudentEnrollment;
import com.scube.techboot.vo.StudentRegisterVO;
import com.scube.techboot.vo.TestimonialVO;

@Repository
public class StudentDaoImpl implements StudentDao {
	private static final Logger LOGGER=Logger.getLogger(StudentDaoImpl.class);
	@Autowired
	private SessionFactory SessionFactory;

	@Override
	public StudentRegisterBO saveStudent(StudentRegisterVO studentRegisterVO) {


		// TODO Auto-generated method stub
		StudentRegisterBO studentRegisterBo=new StudentRegisterBO();
		try {
			Session session=SessionFactory.getCurrentSession();
			long studentRegisterId=(long) session.save(studentRegisterVO);
			if(0!=studentRegisterId) {
				studentRegisterBo.setStudentRegisterId(studentRegisterId);
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}


		return studentRegisterBo;
	}

	@Override
	public LoginVO loginStudent(LoginVO loginVO) {
		// TODO Auto-generated method stub

		LoginVO login=new LoginVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			long loginId= (long) session.save(loginVO);
			if(0!=loginId){
				login.setLoginId(loginId);
			}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return login;

	}

	@Override
	public boolean emailAddressValidation(StudentRegisterVO studentRegisterVO) {
		// TODO Auto-generated method stub

		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(StudentRegisterVO.class);
			criteria.add(Restrictions.eq("emailAddress",studentRegisterVO.getEmailAddress()));
			criteria.add(Restrictions.eq("isDelete",studentRegisterVO.getIsDelete()));
			StudentRegisterVO studentRegisterVo=(StudentRegisterVO) criteria.uniqueResult();
			if(null!=studentRegisterVo){
				return true;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}




	@Override
	public long saveStudentCourse(StudentEnrollment studentEnrollment) {
		// TODO Auto-generated method stub
		long studentCurseId=0;
		try {
			Session session=SessionFactory.getCurrentSession();
			studentCurseId= (long) session.save(studentEnrollment);

		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return studentCurseId;
	}

	@Override
	public List<StudentEnrollment> getStudentCouresList(StudentEnrollment studentEnrollment) {
		// TODO Auto-generated method stub
		List<StudentEnrollment> lists=null;
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentEnrollment.class);
			if(studentEnrollment.getIsDelete()) {
				cr.createCriteria("student").add(Restrictions.eq("studentId",studentEnrollment.getStudent().getStudentId()));
				cr.add(Restrictions.eq("isDelete",false));
				cr.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE );
				cr.addOrder(Order.asc("studentEnrollmentId"));
				//cr.add(Restrictions.eq("student.studentId",studentEnrollment.getStudent().getStudentId()));

			}else {
				cr.add(Restrictions.eq("isDelete",false));
				//cr.setFirstResult(studentEnrollment.getRecordIndex());
				//cr.setMaxResults(studentEnrollment.getMaxRecord());
				//cr.addOrder(Order.desc("studentEnrollmentId"));
				//cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				cr.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE );
			}

			lists=cr.list();
		}

		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return lists;
	}

	@Override
	public StudentRegisterVO getStudentProfile(long studentId) {
		// TODO Auto-generated method stub
		StudentRegisterVO StudentRegister=new StudentRegisterVO();

		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentRegisterVO.class);
			cr.add(Restrictions.eq("studentId",studentId));
			cr.add(Restrictions.eq("isDelete",false));
			cr.add(Restrictions.eq("sending_status",true));
			StudentRegister=(StudentRegisterVO) cr.uniqueResult();
		}

		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}


		return StudentRegister;
	}

	@Override
	public StudentEnrollment getEditCourseEnrollement(StudentEnrollment studentEnrollment) {
		// TODO Auto-generated method stub
		StudentEnrollment StudentEnrollment=new StudentEnrollment();
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentEnrollment.class);
			cr.add(Restrictions.eq("courseVO.courseId",studentEnrollment.getCourseVO().getCourseId()));
			cr.add(Restrictions.eq("student.studentId",studentEnrollment.getStudent().getStudentId()));
			cr.setMaxResults(1);
			cr.setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE );
			StudentEnrollment=(StudentEnrollment) cr.uniqueResult();

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return StudentEnrollment;
	}
	@Override
	public boolean editSaveStudentCourse(StudentEnrollment studentEnrollment) {
		StudentEnrollment enroll =new StudentEnrollment();
		try {
			Session session=SessionFactory.getCurrentSession();
			enroll=(StudentEnrollment) session.load(StudentEnrollment.class,studentEnrollment.getStudentEnrollmentId());
			enroll.setModifiedTime(studentEnrollment.getModifiedTime());
			enroll.setModifiedBy(studentEnrollment.getModifiedBy());
			enroll.setCourseVO(studentEnrollment.getCourseVO());
			session.saveOrUpdate(enroll);
			return true;
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return false;
	}

	@Override
	public boolean deleteCourseEnrollemt(StudentEnrollment studentCouresVo) {
		// TODO Auto-generated method stub
		StudentEnrollment StudentCoures=new StudentEnrollment();
		StudentCoures.setStudentEnrollmentId(studentCouresVo.getStudentEnrollmentId());
		StudentCoures.setIsDelete(true);
		StudentCoures.setSending_status(false);
		int result = 0;

		String deleteQuery = "UPDATE StudentEnrollment E set E.isDelete = :isDelete WHERE E.studentEnrollmentId = :studentEnrollmentId";
		try{
			final Query query = SessionFactory.getCurrentSession().createQuery(deleteQuery);

			query.setParameter("studentEnrollmentId", studentCouresVo.getStudentEnrollmentId());
			query.setParameter("isDelete",studentCouresVo.getIsDelete());

			result = query.executeUpdate();
			if(0!=result){

				return true;
			}
		} catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}


	@Override
	public List<StudentRegisterVO> studentList(StudentRegisterVO registerVo) {
		List<StudentRegisterVO> studentList = null;
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentRegisterVO.class);
			cr.add(Restrictions.eq("isDelete",registerVo.getIsDelete()));
			cr.add(Restrictions.eq("sending_status",registerVo.getSending_status()));
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			studentList=cr.list();
			if(null!=studentList && !studentList.isEmpty() && studentList.size()>0) {
				return studentList;
			} 
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return studentList;

	}

	@Override
	public List<StudentRegisterVO> listOfPageStudent(StudentRegisterVO registerVo) {
		
		List<StudentRegisterVO> studentList = null;
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentRegisterVO.class);
			cr.add(Restrictions.eq("isDelete",registerVo.getIsDelete()));
			cr.add(Restrictions.eq("sending_status",registerVo.getSending_status()));
			cr.setFirstResult(registerVo.getRecordIndex());
			cr.setMaxResults(registerVo.getMaxRecord());
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.addOrder(Order.desc("studentId"));
			studentList=cr.list();
			if(null!=studentList && !studentList.isEmpty() && studentList.size()>0) {
				return studentList;
			} 
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return studentList;
	}
	/*@Override
	public List<StudentEnrollment> getStudentReporting(StudentEnrollment studentEnrollment) {
		// TODO Auto-generated method stub

		List<StudentEnrollment> lists=null;
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentEnrollment.class);
			if(studentEnrollment.getIsDelete()) {
				 cr.createCriteria("student").add(Restrictions.eq("studentId",studentEnrollment.getStudent().getStudentId()));
				 cr.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE );
				//cr.add(Restrictions.eq("student.studentId",studentEnrollment.getStudent().getStudentId()));

		}

			lists=cr.list();
		}

		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}


		return lists;
	}*/


	@Override
	public boolean isExist(StudentEnrollment studentenrollmentVO) {
		// TODO Auto-generated method stub

		Session session = SessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(StudentEnrollment.class);

		crit.add(Restrictions.eq("student.studentId", studentenrollmentVO.getStudent().getStudentId()));
		crit.add(Restrictions.eq("courseVO.courseId", studentenrollmentVO.getCourseVO().getCourseId()));
	//	crit.add(Restrictions.eq("isDelete",false));
		System.out.println("checking values " + crit.list().size());

		StudentEnrollment studentenrollment = (StudentEnrollment) crit.uniqueResult();

		if(null!=studentenrollment){

			return true; 
		}

		return false;
	}

	@Override
	public List<StudentEnrollment> searchCourseDao(StudentEnrollment studentEnrollmentVO) {
		// TODO Auto-generated method stub

		List<StudentEnrollment> studentEnrollmentList=new ArrayList<StudentEnrollment>();
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria crit=session.createCriteria(StudentEnrollment.class);

			if(null!=studentEnrollmentVO && !studentEnrollmentVO.getCourseVO().getCourseName().isEmpty()) {
				crit.createAlias("courseVO", "courses");
			}

			if(0<studentEnrollmentVO.getStudent().getStudentId()) {
				crit.createCriteria("student").add(Restrictions.eq("studentId",studentEnrollmentVO.getStudent().getStudentId() ));
				crit.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE ); 
			}

			if(null!=crit) {
				crit.add(Restrictions.ilike("courses.courseName", studentEnrollmentVO.getCourseVO().getCourseName(), MatchMode.ANYWHERE));
			}

			studentEnrollmentList=crit.list();
		}

		catch (Exception e) {
			// TODO: handle exception

			System.out.println(e);

		}

		return studentEnrollmentList;
	}

	@Override
	public long retrieveCourseCount(StudentEnrollment enrollment) {
		try {
			Session session = SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(StudentEnrollment.class);
			criteria.add(Restrictions.eq("isDelete", enrollment.getIsDelete()));
			criteria.setProjection(Projections.rowCount());
			long count = (long) criteria.uniqueResult();
			if(0<count) {
				return count;
			}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return 0;
	}

	@Override
	public List<FeedbackVO> viewfeedback() {
		List<FeedbackVO>feedbackvolist=new ArrayList<FeedbackVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(FeedbackVO.class);
			cr.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE );
			feedbackvolist=cr.list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackvolist;
	}

	@Override
	public FeedbackVO editFeedback(long id) {
		FeedbackVO feedbackVO=new FeedbackVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(FeedbackVO.class);
			cr.add(Restrictions.eq("feedbackId", id));
			feedbackVO=	(FeedbackVO) cr.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackVO;
	}

	@Override
	public boolean updateFeedback(FeedbackVO feedbackVO) {
		try{
			Session session=SessionFactory.getCurrentSession();
			session.saveOrUpdate(feedbackVO);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletefeedback(FeedbackVO feedbackVO) {
		try{
			Session session=SessionFactory.getCurrentSession();
			session.delete(feedbackVO);
			return true;

		}catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}


	@Override
	public long saveFeedback(FeedbackVO feedbackVO) {
		long feedbackId=0;
		try{
			Session session=SessionFactory.getCurrentSession();
			feedbackId= (long) session.save(feedbackVO);


		}catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackId;
	}

	@Override
	public List<StudentRegisterVO> totalSearchStudent(StudentRegisterVO studentVO) {
		List<StudentRegisterVO> listVo=new ArrayList<StudentRegisterVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentRegisterVO.class);
			if(null!=studentVO.getFirstName() && !studentVO.getFirstName().isEmpty()){
				cr.add(Restrictions.ilike("firstName", studentVO.getFirstName(),MatchMode.ANYWHERE));
				cr.add(Restrictions.eq("isDelete", studentVO.getIsDelete()));
			}
			else if(null!=studentVO.getEmailAddress() && !studentVO.getEmailAddress().isEmpty()){
				cr.add(Restrictions.eq("emailAddress",studentVO.getEmailAddress()));
				cr.add(Restrictions.eq("isDelete", studentVO.getIsDelete()));
			}
			else{
				cr.add(Restrictions.eq("mobileNo", studentVO.getMobileNo()));
				cr.add(Restrictions.eq("isDelete", studentVO.getIsDelete()));
			}

			listVo=cr.list();
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVo;
	}

	@Override
	public List<StudentRegisterVO> searchStudent(StudentRegisterVO studentVO) {
		List<StudentRegisterVO> listVo=new ArrayList<StudentRegisterVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(StudentRegisterVO.class);
			if(null!=studentVO.getFirstName() && !studentVO.getFirstName().isEmpty()){
				cr.add(Restrictions.ilike("firstName", studentVO.getFirstName(),MatchMode.ANYWHERE));
				cr.setFirstResult(studentVO.getRecordIndex());
				cr.setMaxResults(studentVO.getMaxRecord());
				cr.add(Restrictions.eq("isDelete", studentVO.getIsDelete()));
			}
			else if(null!=studentVO.getEmailAddress() && !studentVO.getEmailAddress().isEmpty()){
				cr.add(Restrictions.eq("emailAddress",studentVO.getEmailAddress()));
				cr.add(Restrictions.eq("isDelete", studentVO.getIsDelete()));
			}
			else{
				cr.add(Restrictions.eq("mobileNo", studentVO.getMobileNo()));
				cr.add(Restrictions.eq("isDelete", studentVO.getIsDelete()));
			}

			listVo=cr.list();
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVo;
	}

	@Override
	public boolean updateStudent(StudentRegisterVO studentRegisterVO) {
		//StudentRegisterVO studentVO = new StudentRegisterVO();
		/*//try {
		//	Session session=SessionFactory.getCurrentSession();
			//studentVO=(StudentRegisterVO) session.get(StudentRegisterVO.class,studentRegisterVO.getStudentId());
		//	studentRegisterVO.getLogin().setLoginId(studentVO.getLogin().getLoginId());
		//	session.update(studentRegisterVO);
			//return true;
			
		//}
*/		int result = 0;

		String updateQuery = "UPDATE StudentRegisterVO E set E.firstName = :firstName,E.lastName = :lastName,E.emailAddress= :emailAddress,E.confirmemailAddress= :confirmemailAddress,E.mobileNo=:mobileNo,E.whatsAppNo=:whatsAppNo,"
				+ "E.address=:address,E.gender=:gender,E.maritalStatus=:maritalStatus, E.city=:city,E.state=:state,E.modifiedBy=:modifiedBy,E.modifiedTime=:modifiedTime"
				+ " WHERE E.studentId = :studentId";
		try{
			final Query query = SessionFactory.getCurrentSession().createQuery(updateQuery);

			query.setParameter("studentId", studentRegisterVO.getStudentId());
			query.setParameter("firstName",studentRegisterVO.getFirstName());
			query.setParameter("lastName",studentRegisterVO.getLastName());
			query.setParameter("emailAddress",studentRegisterVO.getEmailAddress());
			query.setParameter("confirmemailAddress",studentRegisterVO.getConfirmemailAddress());
			query.setParameter("mobileNo",studentRegisterVO.getMobileNo());
			query.setParameter("whatsAppNo",studentRegisterVO.getWhatsAppNo());
			query.setParameter("address",studentRegisterVO.getAddress());
			query.setParameter("gender",studentRegisterVO.getGender());
			query.setParameter("maritalStatus",studentRegisterVO.getMaritalStatus());
			query.setParameter("city",studentRegisterVO.getCity());
			query.setParameter("state",studentRegisterVO.getState());
			query.setParameter("modifiedBy",studentRegisterVO.getModifiedBy());
			query.setParameter("modifiedTime",studentRegisterVO.getModifiedTime());
			result = query.executeUpdate();
			if(0!=result){
				result = 0;

				String updateQuery1 ="UPDATE LoginVO E set E.emailAddress=:emailAddress,E.modifiedBy=:modifiedBy,E.modifiedTime=:modifiedTime WHERE E.student.studentId = :studentId";
				final Query query1 = SessionFactory.getCurrentSession().createQuery(updateQuery1);
				query1.setParameter("studentId", studentRegisterVO.getStudentId());
				query1.setParameter("emailAddress",studentRegisterVO.getEmailAddress());
				query1.setParameter("modifiedBy",studentRegisterVO.getModifiedBy());
				query1.setParameter("modifiedTime",studentRegisterVO.getModifiedTime());
				result = query1.executeUpdate();

				return true;
			}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
	
		return false;
	}	
	

	@Override
	public boolean deleteStudent(StudentRegisterVO studentRegisterVO) {
		int result = 0;
		boolean status=false;
		String deleteQuery = "UPDATE StudentRegisterVO E set E.isDelete = :isDelete WHERE E.studentId = :studentId";
		try{
			final Query query = SessionFactory.getCurrentSession().createQuery(deleteQuery);

			query.setParameter("studentId", studentRegisterVO.getStudentId());
			query.setParameter("isDelete",studentRegisterVO.getIsDelete());

			result = query.executeUpdate();
			if(0!=result){
				result = 0;
					String deleteQuery1 = "UPDATE LoginVO E set E.isDelete = :isDelete WHERE E.student.studentId = :studentId";
				
					final Query query1 = SessionFactory.getCurrentSession().createQuery(deleteQuery1);

					query1.setParameter("studentId", studentRegisterVO.getStudentId());
					query1.setParameter("isDelete",studentRegisterVO.getIsDelete());

					result = query1.executeUpdate();
					}
			if(0!=result){
				result = 0;
				String deleteQuery2 = "UPDATE StudentEnrollment E set E.isDelete = :isDelete WHERE E.student.studentId = :studentId";
				
				final Query query2 = SessionFactory.getCurrentSession().createQuery(deleteQuery2);

				query2.setParameter("studentId", studentRegisterVO.getStudentId());
				query2.setParameter("isDelete",studentRegisterVO.getIsDelete());

				result = query2.executeUpdate();
				if(0!=result){
				
					status= true;
				}
			}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return status;
		
	}

	@Override
	public boolean mobileValidation(StudentRegisterVO studentRegisterVO) {
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(StudentRegisterVO.class);
			criteria.add(Restrictions.eq("mobileNo",studentRegisterVO.getMobileNo()));
			criteria.add(Restrictions.eq("isDelete",studentRegisterVO.getIsDelete()));
			StudentRegisterVO studentRegisterVo=(StudentRegisterVO) criteria.uniqueResult();
			if(null!=studentRegisterVo){
				return true;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	
	}

	@Override
	public int getCompanyLogin(int companyId) {
		int loginId =0;
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(LoginVO.class);
			criteria.add(Restrictions.eq("companyVO.companyId",companyId));
			LoginVO loginVo=(LoginVO) criteria.uniqueResult();
			if(null!=loginVo){
				loginId=(int)loginVo.getLoginId();
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return loginId;
	}

	/*@Override
	public CourseVO getCompanyId(int courseId) {
		Session session=SessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(CourseVO.class);
		cr.add(Restrictions.eq("courseId",courseId));
		CourseVO courseVO=(CourseVO) cr.uniqueResult();
		return courseVO;
	}*/
	


	}





