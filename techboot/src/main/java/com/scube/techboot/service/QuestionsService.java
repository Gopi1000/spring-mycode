package com.scube.techboot.service;

import java.util.List;

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.bo.QuestionsBO;

public interface QuestionsService {

QuestionsBO saveQuestions(QuestionsBO questionsBO);

//List<CourseSubCategoryBO> getCourseSubCatogery(CourseSubCategoryBO courseSubCategoryBO);

List<SubcategoryBO> getCourseSubCatogery();

long getListOfQuestions(QuestionsBO questionsBO);

List<QuestionsBO> ListOfQuestions(QuestionsBO questionsBO);

long listOfQuestionCount(QuestionsBO questionsBO);

List<QuestionsBO> searchQuestion(QuestionsBO questionsBO);

QuestionsBO editQuestion(QuestionsBO questionsBO);

boolean updateQuestion(QuestionsBO questionsBO);

boolean deleteQuestion(QuestionsBO questionsBO);

List<CategoryBO> getCatogery();

QuestionsBO viewQuestionDetails(QuestionsBO questionsBO);

boolean questionValidations(String question);

}
