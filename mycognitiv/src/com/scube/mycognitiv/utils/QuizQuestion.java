package com.scube.mycognitiv.utils;

import java.util.ArrayList;

import com.scube.mycognitiv.bo.BaseBO;

public class QuizQuestion extends BaseBO {
	int correctoptionId;

	/**
	 * @return the correctoptionId
	 */
	public int getCorrectoptionId() {
		return correctoptionId;
	}

	/**
	 * @param correctoptionId the correctoptionId to set
	 */
	public void setCorrectoptionId(int correctoptionId) {
		this.correctoptionId = correctoptionId;
	}

	private long sNo;

	int questionNumber;
	int testId;
	int answerId;

	ArrayList<Integer> answerIds;

	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	int quesId;
	String question;
	String correctOption;
	String userSelectedOption;
	String questionOptions[];
	int correctOptionIndex;
	int userSelected = -1;
	String userSelectedoption;

	private ArrayList<Integer> usersSelected;

	String answer;
	ArrayList<String> answers;
	String response;
	String correctAnswer;
	String errorMessage;
	ArrayList<QuizQuestion> reviewQuestionList;
	ArrayList<QuizQuestion> retrievequestionlist;
	String updateType;
	String option;
	int optionId;
	boolean flag;
	int categoryId;
	int subcategoryId;
	String questionType;
	float complexity = 0f;
	String description;
	int userId;
	String category;
	String teststatus;
	String subcategory;
	String subcategories[];
	int markedQuestion;
	long totalattempts;

	ArrayList<String> questionTypes;

	private int correctOptionsIndex[];

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * String subcategor[]; public String[] getSubcategory() { return subcategory; }
	 * 
	 * public void setSubcategory(String[] subcategory) { this.subcategory =
	 * subcategory; }
	 */

	public long getTotalattempts() {
		return totalattempts;
	}

	public void setTotalattempts(long totalattempts) {
		this.totalattempts = totalattempts;
	}

	public int getMarkedQuestion() {
		return markedQuestion;
	}

	public void setMarkedQuestion(int markedQuestion) {
		this.markedQuestion = markedQuestion;
	}

	public String[] getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(String[] subcategories) {
		this.subcategories = subcategories;
	}

	public String getTeststatus() {
		return teststatus;
	}

	public void setTeststatus(String teststatus) {
		this.teststatus = teststatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	/**
	 * @return the retrievequestionlist
	 */
	public ArrayList<QuizQuestion> getRetrievequestionlist() {
		return retrievequestionlist;
	}

	/**
	 * @param retrievequestionlist the retrievequestionlist to set
	 */
	public void setRetrievequestionlist(ArrayList<QuizQuestion> retrievequestionlist) {
		this.retrievequestionlist = retrievequestionlist;
	}

	private String testName;
	private String testLevel;

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the testLevel
	 */
	public String getTestLevel() {
		return testLevel;
	}

	/**
	 * @param testLevel the testLevel to set
	 */
	public void setTestLevel(String testLevel) {
		this.testLevel = testLevel;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(int userSelected) {
		this.userSelected = userSelected;
	}

	public String getQuestion() {
		return question;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int i) {
		questionNumber = i;
	}

	public int getCorrectOptionIndex() {
		return correctOptionIndex;
	}

	public String[] getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestion(String s) {
		question = s;
	}

	public void setCorrectOptionIndex(int i) {
		correctOptionIndex = i;
	}

	public void setQuestionOptions(String[] s) {
		questionOptions = s;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the correctOption
	 */
	public String getCorrectOption() {
		return correctOption;
	}

	/**
	 * @param correctOption the correctOption to set
	 */
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	/**
	 * @return the userSelectedOption
	 */
	public String getUserSelectedOption() {
		return userSelectedOption;
	}

	/**
	 * @param userSelectedOption the userSelectedOption to set
	 */
	public void setUserSelectedOption(String userSelectedOption) {
		this.userSelectedOption = userSelectedOption;
	}

	public String toString() {
		String str = questionNumber + "." + getQuestion();
		for (String option : getQuestionOptions()) {
			str = str + option + "  ";
		}
		str = str + "\n Correct Answer : " + getCorrectOptionIndex();
		return str;
	}

	/**
	 * @return the reviewQuestionList
	 */
	public ArrayList<QuizQuestion> getReviewQuestionList() {
		return reviewQuestionList;
	}

	/**
	 * @param reviewQuestionList the reviewQuestionList to set
	 */
	public void setReviewQuestionList(ArrayList<QuizQuestion> reviewQuestionList) {
		this.reviewQuestionList = reviewQuestionList;
	}

	public void setComplexity(float complexity) {
		this.complexity = complexity;
	}

	public float getComplexity() {
		return this.complexity;
	}

//	public Integer[] getUsersSelected() {
//		return usersSelected;
//	}
//
//	public void setUsersSelected(Integer[] numbers) {
//		this.usersSelected = numbers;
//	}

	public int[] getCorrectOptionsIndex() {
		return correctOptionsIndex;
	}

	public void setCorrectOptionsIndex(int correctOptionsIndex[]) {
		this.correctOptionsIndex = correctOptionsIndex;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public ArrayList<String> getQuestionTypes() {
		return questionTypes;
	}

	public void setQuestionTypes(ArrayList<String> questionTypes) {
		this.questionTypes = questionTypes;
	}

	public ArrayList<Integer> getAnswerIds() {
		return answerIds;
	}

	public void setAnswerIds(ArrayList<Integer> answerIds) {
		this.answerIds = answerIds;
	}

	public ArrayList<Integer> getUsersSelected() {
		return usersSelected;
	}

	public void setUsersSelected(ArrayList<Integer> usersSelected) {
		this.usersSelected = usersSelected;
	}

	public String getUserSelectedoption() {
		return userSelectedoption;
	}

	public void setUserSelectedoption(String userSelectedoption) {
		this.userSelectedoption = userSelectedoption;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

}