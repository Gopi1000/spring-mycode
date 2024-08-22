package com.scube.techboot.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.QuestionsBO;
import com.scube.techboot.vo.CategoryVO;
import com.scube.techboot.vo.CourseDetailsVO;
import com.scube.techboot.vo.CourseRegisterationVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.SubcategoryVO;
import com.scube.techboot.vo.QuestionsVO;
import com.scube.techboot.vo.courseCategoryVO;
@Repository
public class QuestionsDaoImpl implements QuestionsDao{
	private static final Logger LOGGER=Logger.getLogger(QuestionsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public QuestionsVO saveQuestions(QuestionsVO questionsVO) {

		try{
			Session session=sessionFactory.getCurrentSession();
			int questionId=(int) session.save(questionsVO);
			if(0!=questionId){
				questionsVO.setQuestionId(questionId);
				return questionsVO;
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
		return questionsVO;
	}
	@Override
	public List<SubcategoryVO> getCourseSubCatogery() {
		// TODO Auto-generated method stub
		List<SubcategoryVO> subCategoryList=new ArrayList<SubcategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(SubcategoryVO.class);
			subCategoryList=criteria.list();
			if(null!=subCategoryList && !subCategoryList.isEmpty()&& subCategoryList.size()>0){
				return subCategoryList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}


		}
		return subCategoryList;
	}

	@Override
	public long listOfQuestionCount(QuestionsVO questionsVO) {
		long count=0;
		try{
			if(null!=questionsVO){
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(QuestionsVO.class);

				if(null!=questionsVO.getQuestion()&&!questionsVO.getQuestion().isEmpty()){
					criteria.add(Restrictions.ilike("question", questionsVO.getQuestion(),MatchMode.ANYWHERE));
				}
				if(0!=questionsVO.getQuestionId()){
					criteria.add(Restrictions.eq("questionId",questionsVO.getQuestionId()));
					criteria.add(Restrictions.eq("isDelete", questionsVO.getIsDelete()));

				}
				if(null!=questionsVO.getQuestionType()&&!questionsVO.getQuestionType().isEmpty()){
					criteria.add(Restrictions.ilike("questionType", questionsVO.getQuestionType(),MatchMode.ANYWHERE));
				}
				if(null!=questionsVO.getSubCategoryVO()&&!questionsVO.getSubCategoryVO().getSubcategory().isEmpty()){
					criteria.createAlias("subCategoryVO","s");
					criteria.add(Restrictions.ilike("s.subcategory",questionsVO.getSubCategoryVO().getSubcategory(),MatchMode.ANYWHERE));	
					criteria.add(Restrictions.eq("isDelete",false));

				}
				criteria.setProjection(Projections.rowCount());
				criteria.add(Restrictions.eq("isDelete",false));
				count=(long) criteria.uniqueResult();
			}
			return count;
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
	public List<QuestionsVO> searchQuestions(QuestionsVO questionsVO) {
		List<QuestionsVO> listVO=new ArrayList<QuestionsVO>();
		List<QuestionsVO> listVO1=new ArrayList<QuestionsVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(QuestionsVO.class);
			Criteria cr1=session.createCriteria(QuestionsVO.class);

			if(null!=questionsVO.getQuestion()&&!questionsVO.getQuestion().isEmpty()){
				cr.add(Restrictions.ilike("question",questionsVO.getQuestion(),MatchMode.ANYWHERE));
			}
			if(null!=questionsVO.getQuestionType()&&!questionsVO.getQuestionType().isEmpty()){
				cr.add(Restrictions.ilike("questionType",questionsVO.getQuestionType(),MatchMode.ANYWHERE));	
			}
			if(0!=questionsVO.getQuestionId()){
				cr.add(Restrictions.eq("questionId",questionsVO.getQuestionId()));
				cr.add(Restrictions.eq("isDelete", questionsVO.getIsDelete()));

			}
			if(null!=questionsVO.getSubCategoryVO()&&!questionsVO.getSubCategoryVO().getSubcategory().isEmpty()){
				cr1.createAlias("subCategoryVO","s");
				cr1.add(Restrictions.ilike("s.subcategory",questionsVO.getSubCategoryVO().getSubcategory(),MatchMode.ANYWHERE));	
				cr1.add(Restrictions.eq("isDelete",false));
				listVO1=cr1.list();
				if(null!=listVO1 && listVO1.size()>0 && !listVO1.isEmpty()){
					return listVO1;
				}
			}	
			if(null!=questionsVO &&listVO.isEmpty()){
				cr.add(Restrictions.eq("isDelete",false));
				cr.setFirstResult(questionsVO.getRecordIndex());
				cr.setMaxResults(questionsVO.getMaxRecord());
				listVO=cr.list();
				if(null!=listVO && listVO.size()>0 && !listVO.isEmpty()){
					return listVO;
				}
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVO;
	}

	@Override
	public QuestionsVO editQuestion(QuestionsVO questionsVO) {

		try{
			if(0!=questionsVO.getQuestionId()){
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(QuestionsVO.class);
				criteria.add(Restrictions.eq("questionId",questionsVO.getQuestionId()));
				criteria.add(Restrictions.eq("isDelete",false));
				questionsVO=(QuestionsVO) criteria.uniqueResult();
				return questionsVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return questionsVO;

	}

	@Override
	public boolean updateQuestion(QuestionsVO questionsVO) {
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=questionsVO){
				session.update(questionsVO);
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
	public long getListOfQuestions(QuestionsVO questionsVO) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(QuestionsVO.class);
			criteria.add(Restrictions.eq("isDelete",false));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
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
	public List<QuestionsVO> ListOfQuestions(QuestionsVO questionsVO) {
		List<QuestionsVO> listVO=new ArrayList<QuestionsVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(QuestionsVO.class);
			cr.add(Restrictions.eq("isDelete",false));
			listVO=cr.list();
			if(null!=listVO && !listVO.isEmpty() && listVO.size()>0){
				return listVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVO;
	}

	@Override
	public boolean deleteQuestion(QuestionsVO questionsVO) {
		// TODO Auto-generated method stub
		int result=0;
		try{
			String deleteQuery="UPDATE QuestionsVO C set C.isDelete = :isDelete,C.sending_status = :sending_status WHERE C.questionId = :questionId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", questionsVO.getIsDelete());
			query.setParameter("sending_status", questionsVO.getSending_status());
			query.setParameter("questionId", questionsVO.getQuestionId());
			result = query.executeUpdate();
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
	public List<CategoryVO> getCatogery() {
		List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CategoryVO.class);
			categoryList=criteria.list();
			if(null!=categoryList && !categoryList.isEmpty()&& categoryList.size()>0){
				return categoryList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}


		}
		return categoryList;
	}
	@Override
	public QuestionsVO viewQuestionDetails(QuestionsVO questionsVO) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(QuestionsVO.class);

			criteria.add(Restrictions.eq("questionId",questionsVO.getQuestionId()));
			/*if(null!=questionsVO&& 0!=questionsVO.getSubCategoryVO().getSubcategoryId()){
				criteria.add(Restrictions.eq("subCategoryBO.subcategoryId",questionsVO.getSubCategoryVO().getSubcategoryId()));
			}
			if(null!=questionsVO&& 0!=questionsVO.getSubCategoryVO().getCategoryVO().getCategoryId()){
				criteria.add(Restrictions.eq("subCategoryBO.categoryBO.categoryId",questionsVO.getSubCategoryVO().getCategoryVO().getCategoryId()));
			}*/
			criteria.add(Restrictions.eq("isDelete",false));
			QuestionsVO questionsVo=(QuestionsVO) criteria.uniqueResult();
			if(null!=questionsVo){
				return questionsVo;
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
		return questionsVO;
	}
	@Override
	public boolean questionValidations(QuestionsVO questionsVO) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(QuestionsVO.class);
			criteria.add(Restrictions.eq("question",questionsVO.getQuestion()));
			criteria.add(Restrictions.eq("isDelete",false));
			QuestionsVO questionsVo= (QuestionsVO) criteria.uniqueResult();
			if(null!=questionsVo.getQuestion()){
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