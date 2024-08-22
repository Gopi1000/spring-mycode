package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.QuestionsBO;
import com.scube.techboot.vo.CampaignVO;
import com.scube.techboot.vo.CategoryVO;
import com.scube.techboot.vo.SubcategoryVO;
import com.scube.techboot.vo.QuestionsVO;
import com.scube.techboot.vo.courseCategoryVO;

public interface QuestionsDao {

	QuestionsVO saveQuestions(QuestionsVO questionsVO);

	//List<CourseSubCategoryVO> getCourseSubCatogery(CourseSubCategoryVO courseSubCategoryVO);

	List<SubcategoryVO> getCourseSubCatogery();

	long getListOfQuestions(QuestionsVO questionsVO);

	
	long listOfQuestionCount(QuestionsVO questionsVO);

	List<QuestionsVO> searchQuestions(QuestionsVO questionsVO);

	QuestionsVO editQuestion(QuestionsVO questionsVO);

	boolean updateQuestion(QuestionsVO questionsVO);

	List<QuestionsVO> ListOfQuestions(QuestionsVO questionsVO);

	boolean deleteQuestion(QuestionsVO questionsVO);

	List<CategoryVO> getCatogery();

	QuestionsVO viewQuestionDetails(QuestionsVO questionsVO);

	boolean questionValidations(QuestionsVO questionsVO);

}
