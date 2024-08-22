package com.scube.techboot.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.MetaTitleBO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CourseDetailsVO;
import com.scube.techboot.vo.CourseRegisterationVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.EventVO;
import com.scube.techboot.vo.MetaTagVO;
import com.scube.techboot.vo.courseCategoryVO;


@Repository
public class CourseDaoImpl implements CourseDao{


	private static final Logger LOGGER=Logger.getLogger(CourseDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private CourseRegistrationBO CourseRegistrationBO;


	@Override
	public CourseVO saveCourse(CourseVO courseVO) {

		try {
			Session session= sessionFactory.openSession();
			int  courseId=  (int) session.save(courseVO);
			session.flush();
			session.clear();
			session.close();
			if(0!=courseId) {
				courseVO.setCourseId(courseId);
				return courseVO;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return courseVO;
	}

	@Override
	public int retriveOfCourse(CourseVO courseVO) {
		// TODO Auto-generated method stub
		int value=0;
		Session session= sessionFactory.openSession();

		try {
			Criteria criteria=session.createCriteria(CourseVO.class);
			criteria.addOrder(Order.desc("courseId"));
			List<CourseVO> listVo=criteria.list();
			if(null!=listVo && !listVo.isEmpty() && listVo.size()>0){
				courseVO=listVo.get(0);
				int count=(courseVO.getCourseId());	
				return count; 
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		finally{
			session.flush();
			session.close();
		}

		return value;
	}

	@Override
	public List<CourseVO> viewCourseList(CourseVO courseVO) {
		// TODO Auto-generated method stub
		List<CourseVO> courseList=new ArrayList<CourseVO>();
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			if(null!=courseVO.getCompanyVO() && 0!=courseVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",courseVO.getCompanyVO().getCompanyId()));
			}
			
			if(null!=courseVO.getCourseCategoryVo() && 0!=courseVO.getCourseCategoryVo().getCourseCategoryId()){
				criteria.add(Restrictions.eq("courseCategoryVo.courseCategoryId",courseVO.getCourseCategoryVo().getCourseCategoryId()));
			}
			if(null!=courseVO && courseVO.getIsDelete()==false) {
				criteria.setFirstResult(courseVO.getRecordIndex());
				criteria.setMaxResults(courseVO.getMaxRecord());
			}
			if(courseVO.getIsDelete()==true){
				courseVO.setIsDelete(false);
			}

			criteria.add(Restrictions.eq("isDelete",courseVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", courseVO.getSending_status()));
			criteria.addOrder(Order.desc("courseId"));
			courseList=criteria.list();
			if(null!=courseList && !courseList.isEmpty() && courseList.size()>0) {
				return courseList;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return courseList;
	}

	private Object getIsDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long retrieveCourseCount(CourseVO courseVO) {
		// TODO Auto-generated method stub
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			criteria.add(Restrictions.eq("isDelete",courseVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", courseVO.getSending_status()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
				return count;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return 0;
	}

	/*@Override
	public CourseRegisterationVO getCourseObject(CourseRegisterationVO courseRegisterationVO) {
		// TODO Auto-generated method stub
		CourseRegisterationVO courseRegisteration=new CourseRegisterationVO();
		CourseVO cousreVO=new CourseVO();
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("isDelete",courseRegisterationVO.getIsDelete()));
			criteria.add(Restrictions.eq("isActive",courseRegisterationVO.getIsActive()));
			criteria.add(Restrictions.eq("courseVO.courseId",courseRegisterationVO.getCourseVO().getCourseId()));
			courseRegisteration=(CourseRegisterationVO) criteria.uniqueResult();
			if(null!=courseRegisteration) {
				return courseRegisteration;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return courseRegisteration;
	}*/

	/*	@Override
	public CourseVO getCourseObject(CourseVO courseVO) {
		CourseVO courseVo=new CourseVO();
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			criteria.add(Restrictions.eq("courseName",courseVO.getCourseName()));
			courseVo=(CourseVO) criteria.uniqueResult();

		}catch(Exception e){

			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return courseVo;
	}
	 */

	@Override
	public CourseVO getCourseObject(CourseVO courseVO) {
		// TODO Auto-generated method stub
		CourseVO courseVo=new CourseVO();
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			criteria.add(Restrictions.eq("isDelete", courseVO.getIsDelete()));
			criteria.add(Restrictions.eq("courseId", courseVO.getCourseId()));
			courseVo=(CourseVO) criteria.uniqueResult();
			if(null!=courseVo) {
				return courseVo;
			}

		}catch(Exception e){

			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}


		return courseVo;
	}

	@Override
	public boolean isExixts(CourseRegisterationVO courseRegistrationVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("courseVO.courseId",courseRegistrationVO.getCourseVO().getCourseId()));
			criteria.add(Restrictions.eq("emailAddress", courseRegistrationVO.getEmailAddress()));
			CourseRegisterationVO CourseRegisterationVo=(CourseRegisterationVO) criteria.uniqueResult();
			if(null!=CourseRegisterationVo){
				return true;
			}
		}catch(Exception e){
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
	public boolean isMobileNumbaerExixts(CourseRegisterationVO courseRegistrationVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("courseVO.courseId",courseRegistrationVO.getCourseVO().getCourseId()));
			criteria.add(Restrictions.eq("mobileNumber", courseRegistrationVO.getMobileNumber()));
			CourseRegisterationVO CourseRegisterationVo=(CourseRegisterationVO) criteria.uniqueResult();
			if(null!=CourseRegisterationVo){
				return true;
			}
		}catch(Exception e){
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
	public CourseRegisterationVO saveCourseRegister(CourseRegisterationVO courseRegistrationVO) {

		try{
			Session session=sessionFactory.getCurrentSession();
			long courseRegId=(long) session.save(courseRegistrationVO);
			if(0!=courseRegId){
				courseRegistrationVO.setCourseRegisterId(courseRegId);
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return courseRegistrationVO;
	}


	@Override
	public List<CourseRegisterationVO> viewCourseRegistrationList(CourseRegisterationVO courseRegisterationVO) {
		List<CourseRegisterationVO> registerList=new ArrayList<CourseRegisterationVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			if(null!=courseRegisterationVO.getCourseVO()){
				criteria.add(Restrictions.eq("courseVO.courseId", courseRegisterationVO.getCourseVO().getCourseId()));
			}			
			if(!courseRegisterationVO.getIsDelete()){
				criteria.setFirstResult(courseRegisterationVO.getRecordIndex());
				criteria.setMaxResults(courseRegisterationVO.getMaxRecord());
			}
			if(courseRegisterationVO.getIsDelete()){
				courseRegisterationVO.setIsDelete(false);
			}
			if(null!=courseRegisterationVO && null!=courseRegisterationVO.getCompanyVO()){
				criteria.add(Restrictions.eq("companyVO.companyId", courseRegisterationVO.getCompanyVO().getCompanyId()));
			}
			criteria.add(Restrictions.eq("isDelete", courseRegisterationVO.getIsDelete()));
			criteria.add(Restrictions.eq("isActive", courseRegisterationVO.getIsActive()));
			criteria.addOrder(Order.desc("courseRegisterId"));
			/*			criteria.add(Restrictions.eq("coursereg_id",courseRegisterationVO.getCourseRegisterId()));
			 */
			registerList=criteria.list();
			if(null!=registerList && !registerList.isEmpty() && registerList.size()>0){
				return registerList;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return registerList;
	}


	@Override
	public CourseRegisterationVO viewCourseDetails(CourseRegisterationVO courseRegisterationVO) {
		CourseRegisterationVO courseRegisterationVo=new CourseRegisterationVO();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("courseRegisterId",courseRegisterationVO.getCourseRegisterId()));
			criteria.add(Restrictions.eq("isDelete",courseRegisterationVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status",courseRegisterationVO.getIsActive()));
			courseRegisterationVo=(CourseRegisterationVO) criteria.uniqueResult();
			if(null!=courseRegisterationVo){
				return courseRegisterationVo;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return courseRegisterationVo;
	}



	@Override
	public CourseDetailsVO saveCourseDetails(CourseDetailsVO courseDetailsVO) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetails=new CourseDetailsVO();
		try {
			Session session=sessionFactory.getCurrentSession();
			int coursedetailsId=(int) session.save(courseDetailsVO);
			if(0!=coursedetailsId) {
				courseDetails.setCourseDetailsId(coursedetailsId);
				return courseDetails;

			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return courseDetails;
	}

	@Override
	public List<CourseDetailsVO> viewCoursedetails(CourseDetailsVO courseDetailsVO) {
		// TODO Auto-generated method stub
		List<CourseDetailsVO>courseDetails=new ArrayList<CourseDetailsVO>();
			try {
				Session session= sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(CourseDetailsVO.class);
				
				if(null!=getIsDelete()==false){
					criteria.setFirstResult(courseDetailsVO.getRecordIndex());
					criteria.setMaxResults(courseDetailsVO.getMaxRecord());
					courseDetailsVO.setIsDelete(false);
				}
				if(courseDetailsVO.getIsDelete()==true){
					courseDetailsVO.setIsDelete(false);
				}
				if(null!=courseDetailsVO && null!=courseDetailsVO.getCompanyVO()){
				    criteria.add(Restrictions.eq("companyVO.companyId",courseDetailsVO.getCompanyVO().getCompanyId()));
					criteria.add(Restrictions.eq("isDelete",courseDetailsVO.getIsDelete()));
					criteria.add(Restrictions.eq("sending_status",courseDetailsVO.getSending_status()));
					
					courseDetails=criteria.list();
				}
				if(null!=courseDetailsVO.getCourseVO()&&0!=courseDetailsVO.getCourseVO().getCourseId()){
					criteria.add(Restrictions.eq("courseVO.courseId",courseDetailsVO.getCourseVO().getCourseId()));
				}
				criteria.add(Restrictions.eq("isDelete",courseDetailsVO.getIsDelete()));
				criteria.add(Restrictions.eq("sending_status",courseDetailsVO.getSending_status()));
				criteria.addOrder(Order.desc("courseDetailsId"));
				
				courseDetails=criteria.list();
			
				if(null!=courseDetails && !courseDetails.isEmpty() &&courseDetails.size()>0) {
					return courseDetails;
				}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return courseDetails;
	}

	@Override
	public long retrieveCourseCompanyCount(CourseVO courseVO) {
		// TODO Auto-generated method stub
		List<CourseVO> courselist=new ArrayList<CourseVO>();
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			if(null!=courseVO.getCompanyVO() && 0!=courseVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",courseVO.getCompanyVO().getCompanyId()));
				criteria.add(Restrictions.eq("isDelete",courseVO.getIsDelete()));
				criteria.add(Restrictions.eq("sending_status", courseVO.getSending_status()));
				criteria.addOrder(Order.desc("courseId"));
				courselist=criteria.list();
			}
			if(null!=courselist && !courselist.isEmpty() && courselist.size()>0){
				long count=courselist.size();
				return count;
			}
		}catch(Exception e){
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
	public CourseDetailsVO getCourseDetails(CourseDetailsVO courseDetailsVO) {
		// TODO Auto-generated method stub
		CourseDetailsVO courseDetails=new CourseDetailsVO();
		CourseVO CourseVO=new CourseVO();
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseDetailsVO.class).
			createCriteria("courseVO", "course").add(Restrictions.eq("course.courseName", courseDetailsVO.getCourseVO().getCourseName()));
			criteria.add(Restrictions.eq("isDelete",courseDetailsVO.getIsDelete()));
			courseDetails=(CourseDetailsVO) criteria.uniqueResult();
			
			if(null!=courseDetails) {
				return courseDetails;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return courseDetails;
	}

	@Override
	public int searchCourse(CourseVO courseVO) {
		// TODO Auto-generated method stub
		List<CourseVO> courseList=new ArrayList<CourseVO>();
		int totalCourse=0;
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);

			if(null!=courseVO.getCompanyVO() && 0!=courseVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",courseVO.getCompanyVO().getCompanyId()));
			}
			if(null!=courseVO.getMetatagVO()&& null!=courseVO.getMetatagVO()&& null!=courseVO.getMetatagVO().getMetaName())
			{
				criteria.createCriteria("metataglist", "metaVO");
				Criterion metaName=Restrictions.eq("metaVO.metaName",courseVO.getMetatagVO().getMetaName());
				criteria.add(Restrictions.or(metaName));

			}
			if(null!=courseVO&&null!=courseVO.getCourseName()){			
				criteria.add(Restrictions.ilike("courseName", courseVO.getCourseName(),MatchMode.ANYWHERE));
			}
			criteria.add(Restrictions.eq("isDelete", courseVO.getIsDelete()));
			courseList=criteria.list();
			if(null!=courseList && !courseList.isEmpty() && courseList.size()>0){
				totalCourse=courseList.size();
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return totalCourse;
	}

	@Override
	public List<CourseVO> searchPageCourse(CourseVO courseVO) {
		// TODO Auto-generated method stub
		List<CourseVO> companyList=new ArrayList<CourseVO>();
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			if(null!=courseVO.getCompanyVO() && 0!=courseVO.getCompanyVO().getCompanyId()){
				criteria.add(Restrictions.eq("companyVO.companyId",courseVO.getCompanyVO().getCompanyId()));
			}
			if(null!=courseVO.getMetatagVO()&& null!=courseVO.getMetatagVO()&& null!=courseVO.getMetatagVO().getMetaName())
			{
				criteria.createCriteria("metataglist", "metaVO");
				Criterion metaName=Restrictions.ilike("metaVO.metaName",courseVO.getMetatagVO().getMetaName(),MatchMode.ANYWHERE);
				criteria.add(Restrictions.or(metaName));
			}
			if(null!=courseVO&&null!=courseVO.getCourseName()){			
				criteria.add(Restrictions.ilike("courseName", courseVO.getCourseName(),MatchMode.ANYWHERE));
			}
			criteria.setFirstResult(courseVO.getRecordIndex());
			criteria.setMaxResults(courseVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete", courseVO.getIsDelete()));
			companyList=criteria.list();

		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return companyList;
	}

	@Override
	public CourseVO editCourse(CourseVO courseVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			criteria.add(Restrictions.eq("courseId", courseVO.getCourseId()));
			CourseVO courseVo = (CourseVO) criteria.uniqueResult();
			if(null!=courseVo){
				return courseVo;
			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return courseVO;
	}

	@Override
	public boolean postEditCourse(CourseVO courseVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=courseVO){
				session.saveOrUpdate(courseVO);
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean deleteCourse(CourseVO courseVO) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE CourseVO C set C.isDelete = :isDelete,C.sending_status = :sending_status WHERE C.courseId = :courseId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", courseVO.getIsDelete());
			query.setParameter("sending_status", courseVO.getSending_status());
			query.setParameter("courseId", courseVO.getCourseId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
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
	public boolean checkCourse(CourseDetailsVO courseDetailsVO) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseDetailsVO.class);
			criteria.add(Restrictions.eq("courseVO.courseId", courseDetailsVO.getCourseVO().getCourseId()));
			CourseDetailsVO courseDetails=(CourseDetailsVO) criteria.uniqueResult();
			if(null!=courseDetails){
				return true;
			}
		}catch(Exception e){
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
	public CourseDetailsVO getEditCourseDetails(CourseDetailsVO courseDetailsVo) {
		// TODO Auto-generated method stub
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(CourseDetailsVO.class);
			criteria.add(Restrictions.eq("courseDetailsId", courseDetailsVo.getCourseDetailsId()));
			criteria.add(Restrictions.eq("isDelete", courseDetailsVo.getIsDelete()));
			CourseDetailsVO courseDetailsVO = (CourseDetailsVO) criteria.uniqueResult();
			if(null!=courseDetailsVO){
				return courseDetailsVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return courseDetailsVo;
	}

	@Override
	public boolean postEditCourseDetails(CourseDetailsVO courseDetailsVo) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=courseDetailsVo){
				session.saveOrUpdate(courseDetailsVo);
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean deleteCourseDetails(CourseDetailsVO courseDetailsVo) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE CourseDetailsVO C set C.isDelete = :isDelete,C.sending_status = :sending_status WHERE C.courseDetailsId = :courseDetailsId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", courseDetailsVo.getIsDelete());
			query.setParameter("sending_status", courseDetailsVo.getSending_status());
			query.setParameter("courseDetailsId", courseDetailsVo.getCourseDetailsId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
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
	public long retrieveOfCourseRegistrationCount(CourseRegisterationVO courseRegisterVo) {
		// TODO Auto-generated method stub
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("companyVO.companyId", courseRegisterVo.getCompanyVO().getCompanyId()));
			criteria.add(Restrictions.eq("isDelete", courseRegisterVo.getIsDelete()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
				return count;
			}
		}catch(Exception e){
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
	public long saveMetaTad(MetaTagVO metaTagVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			long metaId=(long) session.save(metaTagVO);
			if(0!=metaId){
				return metaId;
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
	public courseCategoryVO courseCatogery(courseCategoryVO categoryVO) {
		// TODO Auto-generated method stub
		try {
			Session session= sessionFactory.getCurrentSession();
			int courseCatogeryId=  (int) session.save(categoryVO);
			if(0!=courseCatogeryId) {
				categoryVO.setCourseCategoryId(courseCatogeryId);
				return categoryVO;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return categoryVO;
	}

	@Override
	public List<courseCategoryVO> viewCourseCatogery(courseCategoryVO categoryVO) {
		// TODO Auto-generated method stub
		List<courseCategoryVO> courseCategoryList=new ArrayList<courseCategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.eq("isDelete",categoryVO.getIsDelete()));
			criteria.addOrder(Order.desc("courseCategoryId"));
			criteria.setFirstResult(categoryVO.getRecordIndex());
			criteria.setMaxResults(categoryVO.getMaxRecord());
			courseCategoryList=	criteria.list();
			if(null!=courseCategoryList && !courseCategoryList.isEmpty()&& courseCategoryList.size()>0){
				return courseCategoryList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}


		}
		return courseCategoryList;
	}

	@Override
	public courseCategoryVO getEditCoursecategory(courseCategoryVO categoryVO) {
		// TODO Auto-generated method stub

		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.eq("courseCategoryId", categoryVO.getCourseCategoryId()));
			courseCategoryVO Categoryvo = (courseCategoryVO) criteria.uniqueResult();
			if(null!=Categoryvo){
				return Categoryvo;
			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return categoryVO;


	}

	@Override
	public Boolean postEditCoursecategory(courseCategoryVO categoryVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=categoryVO){
				session.saveOrUpdate(categoryVO);
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return false;


	}

	@Override
	public boolean deleteCourseCategory(courseCategoryVO courseCategoryvO) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE courseCategoryVO C set C.isDelete = :isDelete WHERE C.courseCategoryId = :courseCategoryId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete",courseCategoryvO.getIsDelete());
			query.setParameter("courseCategoryId", courseCategoryvO.getCourseCategoryId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
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
	public long retrievecourseCategoryCount(courseCategoryVO categoryVO) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.eq("isDelete",categoryVO.getIsDelete()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
				return count;
			}
		}catch(Exception e){
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
	public List<MetaTitleBO> getKeywords(MetaTagVO metaTitleVo) {
		// TODO Auto-generated method stub
		List<MetaTagVO> metaList=new ArrayList<MetaTagVO>();
		List<MetaTitleBO> metaListBO=new ArrayList<>();
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(MetaTagVO.class);
			criteria.add(Restrictions.ilike("metaName", metaTitleVo.getMetaName(),MatchMode.ANYWHERE));
			metaList = criteria.list();
			List<String> metaNameList=new ArrayList<String>();
			String metaNames=null;
			if(null!=metaList && !metaList.isEmpty() && metaList.size()>0){
				for(MetaTagVO metaTag:metaList){
					boolean meta=false;
					if(null!=metaNames){
						metaNames=metaTag.getMetaName();
					}
					for(String name : metaNameList){
						if(name.equalsIgnoreCase(metaNames)){
							meta=true;
							break;
						}
					}
					if(!meta){
						MetaTitleBO metaTitle=new MetaTitleBO();
						metaTitle.setMetaTitle(metaTag.getMetaName());
						metaListBO.add(metaTitle);
						metaNames=metaTag.getMetaName();
						metaNameList.add(metaTag.getMetaName());
					}
				}
				return metaListBO;
			}

		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}   
		return metaListBO;
	}

	@Override
	public boolean isExixstsCategory(courseCategoryVO categoryVO) {
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.eq("courseCategoryName", categoryVO.getCourseCategoryName()));
			criteria.add(Restrictions.eq("isDelete", categoryVO.getIsDelete()));
			courseCategoryVO courseCategoryVo = (courseCategoryVO) criteria.uniqueResult();
			if(null!=courseCategoryVo){
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
	public CourseRegisterationVO editCourseRegistration(CourseRegisterationVO courseRegisterationVO) {
		CourseRegisterationVO courseRegisteration=new CourseRegisterationVO();
		CourseVO cousreVO=new CourseVO();
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("isDelete",courseRegisterationVO.getIsDelete()));
			criteria.add(Restrictions.eq("isActive",courseRegisterationVO.getIsActive()));
			criteria.add(Restrictions.eq("courseRegisterId",courseRegisterationVO.getCourseRegisterId()));
			courseRegisteration=(CourseRegisterationVO) criteria.uniqueResult();

			if(null!=courseRegisteration) {
				return courseRegisteration;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return courseRegisteration;
	}

	@Override
	public CourseRegistrationBO PosteditCourseRegistration(CourseRegisterationVO courseRegisterationVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=courseRegisterationVO){
				session.saveOrUpdate(courseRegisterationVO);

			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return CourseRegistrationBO;
	}

	@Override
	public boolean DeleteCourseRegistration(CourseRegisterationVO courseRegisterationVO) {
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=courseRegisterationVO){
				session.delete(courseRegisterationVO);
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM INFO: Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public CourseRegisterationVO viewCourseRegister(CourseRegisterationVO courseRegisterationVO) {

		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseRegisterationVO.class);
			criteria.add(Restrictions.eq("courseRegisterId",courseRegisterationVO.getCourseRegisterId()));
			criteria.add(Restrictions.eq("isDelete",courseRegisterationVO.getIsDelete()));
			courseRegisterationVO=(CourseRegisterationVO) criteria.uniqueResult();

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return courseRegisterationVO;
	}

	@Override
	public int retrieveOfEventsCount(courseCategoryVO courseCategoryVO) {
		List<courseCategoryVO> courseCatgoryList=new ArrayList<courseCategoryVO>();
		int totalEvent=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.ilike("courseCategoryName",courseCategoryVO.getCourseCategoryName(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("isDelete",courseCategoryVO.getIsDelete()));
			courseCatgoryList=criteria.list();
			if(null!=courseCatgoryList && courseCatgoryList.size()>0 && !courseCatgoryList.isEmpty()){
				totalEvent=courseCatgoryList.size();
			}
		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return totalEvent;
	}

	@Override
	public List<courseCategoryVO> searchPageCourse(courseCategoryVO courseCategoryVO) {
		List<courseCategoryVO> courseCatgoryList=new ArrayList<courseCategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.ilike("courseCategoryName",courseCategoryVO.getCourseCategoryName(),MatchMode.ANYWHERE));
			criteria.setFirstResult(courseCategoryVO.getRecordIndex());
			criteria.setMaxResults(courseCategoryVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete",courseCategoryVO.getIsDelete()));
			courseCatgoryList=criteria.list();

		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return courseCatgoryList;
	}

	@Override
	public courseCategoryVO viewCategoryDetails(courseCategoryVO courseCategoryVO) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.eq("courseCategoryId",courseCategoryVO.getCourseCategoryId()));
			criteria.add(Restrictions.eq("isDelete",courseCategoryVO.getIsDelete()));
			courseCategoryVO=(courseCategoryVO) criteria.uniqueResult();

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return courseCategoryVO;
	}

	@Override
	public int searchCategory(courseCategoryVO courseCategoryVO) {
		List<courseCategoryVO> categoryList=new ArrayList<courseCategoryVO>();
		int totalCategory=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.ilike("courseCategoryName", courseCategoryVO.getCourseCategoryName(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("isDelete",courseCategoryVO.getIsDelete()));
			categoryList=criteria.list();
			if(null!=categoryList && categoryList.size()>0 && !categoryList.isEmpty()){
				totalCategory=categoryList.size();
			}
		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return totalCategory;
	}

	@Override
	public List<courseCategoryVO> searchPageCategory(courseCategoryVO courseCategoryVO) {
		List<courseCategoryVO> categoryList=new ArrayList<courseCategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(courseCategoryVO.class);
			criteria.add(Restrictions.ilike("courseCategoryName",courseCategoryVO.getCourseCategoryName(),MatchMode.ANYWHERE));
			criteria.setFirstResult(courseCategoryVO.getRecordIndex());
			criteria.setMaxResults(courseCategoryVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete",courseCategoryVO.getIsDelete()));
			categoryList=criteria.list();

		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return categoryList;
	}

	@Override
	public boolean courseNameValidations(CourseVO courseVO) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CourseVO.class);
			criteria.add(Restrictions.eq("courseName",courseVO.getCourseName()));
			criteria.add(Restrictions.eq("isDelete",false));
			CourseVO courseVo= (CourseVO) criteria.uniqueResult();
			if(null!=courseVo.getCourseName()){
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

}






