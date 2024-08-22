package com.scube.techboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.bo.QuestionsBO;
import com.scube.techboot.dao.QuestionsDao;
import com.scube.techboot.vo.CategoryVO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.SubcategoryVO;
import com.scube.techboot.vo.QuestionsVO;
import com.scube.techboot.vo.courseCategoryVO;
@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsService{
	@Autowired
	private QuestionsDao questionsDao;
	@Override
	public QuestionsBO saveQuestions(QuestionsBO questionsBO) {
		// TODO Auto-generated method stub


		QuestionsVO questionsVO=new QuestionsVO();

		SubcategoryVO subCategoryVO=new SubcategoryVO();
		questionsVO.setQuestionId(questionsBO.getQuestionId());
		questionsVO.setQuestion(questionsBO.getQuestion());
		questionsVO.setQuestionType(questionsBO.getQuestionType());
		questionsVO.setAnswer1(questionsBO.getAns1());
		questionsVO.setAnswer2(questionsBO.getAns2());
		questionsVO.setAnswer3(questionsBO.getAns3());
		questionsVO.setAnswer4(questionsBO.getAns4());
		questionsVO.setIsDelete(questionsBO.getIsDelete());
		questionsVO.setSending_status(questionsBO.getSending_status());
		questionsVO.setCorrectAnswer(questionsBO.getCorrectAnswer());
		if(null!=questionsBO){
			subCategoryVO.setSubcategoryId(questionsBO.getSubCategoryBO().getSubcategoryId());
			subCategoryVO.setSubcategory(questionsBO.getSubCategoryBO().getSubcategory());
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		questionsVO=questionsDao.saveQuestions(questionsVO);
		if(null!=questionsVO){
			questionsBO.setQuestionId(questionsVO.getQuestionId());
			questionsBO.setQuestion(questionsVO.getQuestion());
			questionsBO.setQuestionType(questionsVO.getQuestionType());
			if(null!=questionsVO){
				SubcategoryBO subCategoryBO=new SubcategoryBO();
				subCategoryBO.setSubcategoryId(questionsVO.getSubCategoryVO().getSubcategoryId());
				subCategoryBO.setSubcategory(questionsVO.getSubCategoryVO().getSubcategory());
				questionsBO.setSubCategoryBO(subCategoryBO);
			}
			questionsBO.setAns1(questionsVO.getAnswer1());
			questionsBO.setAns2(questionsVO.getAnswer2());
			questionsBO.setAns3(questionsVO.getAnswer3());
			questionsBO.setAns4(questionsVO.getAnswer4());
			questionsBO.setCorrectAnswer(questionsVO.getCorrectAnswer());
			questionsBO.setRecordIndex(questionsVO.getRecordIndex());
			questionsBO.setMaxRecord(questionsVO.getMaxRecord());
			questionsBO.setIsDelete(questionsVO.getIsDelete());
			questionsBO.setSending_status(questionsVO.getSending_status());
			questionsBO.setS_No(questionsVO.getS_No());
			questionsBO.setCreatedBy(questionsVO.getCreatedBy());
			questionsBO.setCreatedTime(questionsVO.getCreatedTime());

		}
		return questionsBO;
	}
	@Override
	public List<SubcategoryBO> getCourseSubCatogery() {
		// TODO Auto-generated method stub
		List<SubcategoryBO> subCategoryListBO=new ArrayList<SubcategoryBO>();
		List<SubcategoryVO> subCategoryListVO=new ArrayList<SubcategoryVO>();
		subCategoryListVO=questionsDao.getCourseSubCatogery();
		if(null!=subCategoryListVO&& !subCategoryListVO.isEmpty() && subCategoryListVO.size()>0){
			for(SubcategoryVO subCategoryVO:subCategoryListVO){
				SubcategoryBO subCategoryBO=new SubcategoryBO();
				subCategoryBO.setSubcategoryId(subCategoryVO.getSubcategoryId());
				subCategoryBO.setSubcategory(subCategoryVO.getSubcategory());
				subCategoryListBO.add(subCategoryBO);
			}
		}
		return subCategoryListBO;
	}

	@Override
	public long listOfQuestionCount(QuestionsBO questionsBO) {
		QuestionsVO questionsVO=new QuestionsVO();
		SubcategoryVO subCategoryVO=new SubcategoryVO();
		CategoryVO categoryVO=new CategoryVO();

		questionsVO.setQuestionId(questionsBO.getQuestionId());
		questionsVO.setQuestion(questionsBO.getQuestion());
		questionsVO.setQuestionType(questionsBO.getQuestionType());
		questionsVO.setAnswer1(questionsBO.getAns1());
		questionsVO.setAnswer2(questionsBO.getAns2());
		questionsVO.setAnswer3(questionsBO.getAns3());
		questionsVO.setAnswer4(questionsBO.getAns4());
		questionsVO.setCorrectAnswer(questionsBO.getCorrectAnswer());
		questionsVO.setIsDelete(false);
		questionsVO.setSending_status(true);
		if(null!=questionsBO.getSubCategoryBO()){
			subCategoryVO.setSubcategoryId(questionsBO.getSubCategoryBO().getSubcategoryId());
			subCategoryVO.setSubcategory(questionsBO.getSubCategoryBO().getSubcategory());
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		if(null!=questionsBO.getSubCategoryBO().getCategoryBO()){
			categoryVO.setCategoryId(questionsBO.getSubCategoryBO().getCategoryBO().getCategoryId());
			categoryVO.setCategory(questionsBO.getSubCategoryBO().getCategoryBO().getCategory());
			subCategoryVO.setCategoryVO(categoryVO);
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		return questionsDao.listOfQuestionCount(questionsVO);
	}
	@Override
	public List<QuestionsBO> searchQuestion(QuestionsBO questionsBO) {
		QuestionsVO questionsVO=new QuestionsVO();
		List<QuestionsVO> listVo=new ArrayList<QuestionsVO>();
		List<QuestionsBO> listBo=new ArrayList<QuestionsBO>();
		SubcategoryVO subCategoryVO=new SubcategoryVO();
		CategoryVO categoryVO=new CategoryVO();

		if(null!=questionsBO.getSubCategoryBO()){
			subCategoryVO.setSubcategoryId(questionsBO.getSubCategoryBO().getSubcategoryId());
			subCategoryVO.setSubcategory(questionsBO.getSubCategoryBO().getSubcategory());
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		if(null!=questionsBO.getSubCategoryBO().getCategoryBO()){
			categoryVO.setCategoryId(questionsBO.getSubCategoryBO().getCategoryBO().getCategoryId());
			categoryVO.setCategory(questionsBO.getSubCategoryBO().getCategoryBO().getCategory());
			subCategoryVO.setCategoryVO(categoryVO);
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		questionsVO.setQuestionId(questionsBO.getQuestionId());
		questionsVO.setQuestion(questionsBO.getQuestion());
		questionsVO.setQuestionType(questionsBO.getQuestionType());
		questionsVO.setRecordIndex(questionsBO.getRecordIndex());
		questionsVO.setMaxRecord(questionsBO.getMaxRecord());
		questionsVO.setQuestion(questionsBO.getQuestion());
		questionsVO.setIsDelete(false);
		List<QuestionsVO> listVO=questionsDao.searchQuestions(questionsVO);
		if(null!=listVO && !listVO.isEmpty() && listVO.size()>0){
			int sNo=1;
			for(QuestionsVO questions:listVO){
				SubcategoryBO subCategoryBO=new SubcategoryBO();
				QuestionsBO questionsBo=new QuestionsBO();
				CategoryBO categoryBO=new CategoryBO();
				questionsBo.setQuestionId(questions.getQuestionId());
				questionsBo.setQuestion(questions.getQuestion());
				questionsBo.setQuestionType(questions.getQuestionType());
				questionsBo.setIsDelete(false);
				if(null!=questions.getSubCategoryVO()){
					subCategoryBO.setSubcategoryId(questions.getSubCategoryVO().getSubcategoryId());
					subCategoryBO.setSubcategory(questions.getSubCategoryVO().getSubcategory());
					questionsBo.setSubCategoryBO(subCategoryBO);
				}
				if(null!=questions.getSubCategoryVO().getCategoryVO()){
					categoryBO.setCategoryId(questions.getSubCategoryVO().getCategoryVO().getCategoryId());
					categoryBO.setCategory(questions.getSubCategoryVO().getCategoryVO().getCategory());
					subCategoryBO.setCategoryBO(categoryBO);
					questionsBo.setSubCategoryBO(subCategoryBO);
				}
				questionsBo.setS_No(sNo++);
				listBo.add(questionsBo);
			}
		}

		return listBo;
	}
	@Override
	public QuestionsBO editQuestion(QuestionsBO questionsBO) {
		QuestionsVO questionsVO=new QuestionsVO();
		questionsVO.setQuestionId(questionsBO.getQuestionId());
		questionsVO=questionsDao.editQuestion(questionsVO);
		if(null!=questionsVO){
			SubcategoryBO subCategoryBO=new SubcategoryBO();
			CategoryBO categoryBO=new CategoryBO();

			questionsBO.setQuestionId(questionsVO.getQuestionId());
			questionsBO.setQuestion(questionsVO.getQuestion());
			questionsBO.setQuestionType(questionsVO.getQuestionType());
			questionsBO.setAns1(questionsVO.getAnswer1());
			questionsBO.setAns2(questionsVO.getAnswer2());
			questionsBO.setAns3(questionsVO.getAnswer3());
			questionsBO.setAns4(questionsVO.getAnswer4());
			questionsBO.setCorrectAnswer(questionsVO.getCorrectAnswer());
			questionsBO.setIsDelete(false);
			questionsBO.setSending_status(true);
			if(null!=questionsVO.getSubCategoryVO()){
				subCategoryBO.setSubcategoryId(questionsVO.getSubCategoryVO().getSubcategoryId());
				subCategoryBO.setSubcategory(questionsVO.getSubCategoryVO().getSubcategory());
				questionsBO.setSubCategoryBO(subCategoryBO);
			}
			if(null!=questionsVO.getSubCategoryVO().getCategoryVO()){
				categoryBO.setCategoryId(questionsVO.getSubCategoryVO().getCategoryVO().getCategoryId());
				categoryBO.setCategory(questionsVO.getSubCategoryVO().getCategoryVO().getCategory());
				subCategoryBO.setCategoryBO(categoryBO);
				questionsBO.setSubCategoryBO(subCategoryBO);
			}
			questionsBO.setCreatedTime(questionsVO.getCreatedTime());
			questionsBO.setCreatedBy(questionsVO.getCreatedBy());
		}
		return questionsBO;
	}
	@Override
	public boolean updateQuestion(QuestionsBO questionsBO) {
		QuestionsVO questionsVO=new QuestionsVO();
		SubcategoryVO subCategoryVO=new SubcategoryVO();
		CategoryVO categoryVO=new CategoryVO();

		questionsVO.setQuestionId(questionsBO.getQuestionId());
		questionsVO.setQuestion(questionsBO.getQuestion());
		questionsVO.setQuestionType(questionsBO.getQuestionType());
		questionsVO.setAnswer1(questionsBO.getAns1());
		questionsVO.setAnswer2(questionsBO.getAns2());
		questionsVO.setAnswer3(questionsBO.getAns3());
		questionsVO.setAnswer4(questionsBO.getAns4());
		questionsVO.setCorrectAnswer(questionsBO.getCorrectAnswer());

		if(null!=questionsBO.getSubCategoryBO()){
			subCategoryVO.setSubcategoryId(questionsBO.getSubCategoryBO().getSubcategoryId());
			subCategoryVO.setSubcategory(questionsBO.getSubCategoryBO().getSubcategory());
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		if(null!=questionsBO.getSubCategoryBO().getCategoryBO()){
			categoryVO.setCategoryId(questionsBO.getSubCategoryBO().getCategoryBO().getCategoryId());
			categoryVO.setCategory(questionsBO.getSubCategoryBO().getCategoryBO().getCategory());
			subCategoryVO.setCategoryVO(categoryVO);
			questionsVO.setSubCategoryVO(subCategoryVO);
		}
		questionsVO.setSending_status(true);
		questionsVO.setIsDelete(false);
		questionsVO.setModifiedTime(new Date());

		return questionsDao.updateQuestion(questionsVO);
	}
	@Override
	public long getListOfQuestions(QuestionsBO questionsBO) {
		QuestionsVO questionsVO=new QuestionsVO();
		questionsVO.setIsDelete(false);
		return questionsDao.getListOfQuestions(questionsVO);
	}
	@Override
	public List<QuestionsBO> ListOfQuestions(QuestionsBO questionsBO) {
		List<QuestionsBO> listBo=new ArrayList<QuestionsBO>();
		List<QuestionsVO> listVO=new ArrayList<QuestionsVO>();

		QuestionsVO questionsVO=new QuestionsVO();
		questionsVO.setIsDelete(false);
		listVO=questionsDao.ListOfQuestions(questionsVO);
		if(null!=listVO && !listVO.isEmpty() && listVO.size()>0){
			int sNo=1;
			for(QuestionsVO questions:listVO){
				SubcategoryBO subCategoryBO=new SubcategoryBO();
				CategoryBO categoryBO=new CategoryBO();
				QuestionsBO questionsBo=new QuestionsBO();
				questionsBo.setQuestionId(questions.getQuestionId());
				questionsBo.setQuestion(questions.getQuestion());
				questionsBo.setQuestionType(questions.getQuestionType());
				questionsBo.setAns1(questions.getAnswer1());
				questionsBo.setAns2(questions.getAnswer2());
				questionsBo.setAns3(questions.getAnswer3());
				questionsBo.setAns4(questions.getAnswer4());
				questionsBo.setCorrectAnswer(questions.getCorrectAnswer());
				questionsBo.setIsDelete(false);
				if(null!=questions.getSubCategoryVO()){
					subCategoryBO.setSubcategoryId(questions.getSubCategoryVO().getSubcategoryId());
					subCategoryBO.setSubcategory(questions.getSubCategoryVO().getSubcategory());
					questionsBo.setSubCategoryBO(subCategoryBO);
				}
				if(null!=questions.getSubCategoryVO().getCategoryVO()){
					categoryBO.setCategoryId(questions.getSubCategoryVO().getCategoryVO().getCategoryId());
					categoryBO.setCategory(questions.getSubCategoryVO().getCategoryVO().getCategory());
					subCategoryBO.setCategoryBO(categoryBO);
					questionsBo.setSubCategoryBO(subCategoryBO);
				}
				questionsBo.setS_No(sNo++);
				listBo.add(questionsBo);
			}
		}
		return listBo;
	}
	@Override
	public boolean deleteQuestion(QuestionsBO questionsBO) {
		QuestionsVO questionsVO=new QuestionsVO();
		questionsVO.setQuestionId(questionsBO.getQuestionId());		
		questionsVO.setIsDelete(true);
		return questionsDao.deleteQuestion(questionsVO);
	}
	@Override
	public List<CategoryBO> getCatogery() {
		List<CategoryBO> CategoryListBO=new ArrayList<CategoryBO>();
		List<CategoryVO> CategoryListVO=new ArrayList<CategoryVO>();
		CategoryListVO=questionsDao.getCatogery();
		if(null!=CategoryListVO&& !CategoryListVO.isEmpty() && CategoryListVO.size()>0){

			for(CategoryVO CategoryVO:CategoryListVO){
				CategoryBO categoryBO=new CategoryBO();
				categoryBO.setCategoryId(CategoryVO.getCategoryId());
				categoryBO.setCategory(CategoryVO.getCategory());
				categoryBO.setIsDelete(false);
				CategoryListBO.add(categoryBO);
			}
		}
		return CategoryListBO;
	}
	@Override
	public QuestionsBO viewQuestionDetails(QuestionsBO questionsBO) {		
		QuestionsVO questionsVO=new QuestionsVO();
		questionsVO.setQuestionId(questionsBO.getQuestionId());
		questionsVO.setIsDelete(false);
		questionsVO=questionsDao.viewQuestionDetails(questionsVO);
		if(null!=questionsVO){
			SubcategoryBO subCategoryBO=new SubcategoryBO();
			CategoryBO categoryBO=new CategoryBO();
			
			questionsBO.setQuestion(questionsVO.getQuestion());
			questionsBO.setQuestionType(questionsVO.getQuestionType());
			questionsBO.setCorrectAnswer(questionsVO.getCorrectAnswer());
			questionsBO.setAns1(questionsVO.getAnswer1());
			questionsBO.setAns2(questionsVO.getAnswer2());
			questionsBO.setAns3(questionsVO.getAnswer3());
			questionsBO.setAns4(questionsVO.getAnswer4());
			questionsBO.setIsDelete(false);
			if(null!=questionsVO.getSubCategoryVO()){
				subCategoryBO.setSubcategoryId(questionsVO.getSubCategoryVO().getSubcategoryId());
				subCategoryBO.setSubcategory(questionsVO.getSubCategoryVO().getSubcategory());
				questionsBO.setSubCategoryBO(subCategoryBO);
			}
			if(null!=questionsVO.getSubCategoryVO().getCategoryVO()){
				categoryBO.setCategoryId(questionsVO.getSubCategoryVO().getCategoryVO().getCategoryId());
				categoryBO.setCategory(questionsVO.getSubCategoryVO().getCategoryVO().getCategory());
				subCategoryBO.setCategoryBO(categoryBO);
				questionsBO.setSubCategoryBO(subCategoryBO);

			}
		}
		return questionsBO;
	}
	@Override
	public boolean questionValidations(String question) {
		QuestionsVO questionsVO=new QuestionsVO();
		questionsVO.setQuestion(question);
		questionsVO.setIsDelete(false);
		return questionsDao.questionValidations(questionsVO);
	}
}