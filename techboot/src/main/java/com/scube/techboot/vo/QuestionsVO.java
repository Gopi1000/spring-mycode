package com.scube.techboot.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Questions")
public class QuestionsVO extends BasicEntity{

private int questionId;
private String question;
private String questionType;
private String Answer1;
private String Answer2;
private String Answer3;
private String Answer4;
private String correctAnswer;

private SubcategoryVO subCategoryVO;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="questionId")
public int getQuestionId() {
	return questionId;
}

public void setQuestionId(int questionId) {
	this.questionId = questionId;
}

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}

public String getQuestionType() {
	return questionType;
}

public void setQuestionType(String questionType) {
	this.questionType = questionType;
}
public String getAnswer1() {
	return Answer1;
}

public void setAnswer1(String answer1) {
	Answer1 = answer1;
}

public String getAnswer3() {
	return Answer3;
}

public void setAnswer3(String answer3) {
	Answer3 = answer3;
}

public String getAnswer4() {
	return Answer4;
}

public void setAnswer4(String answer4) {
	Answer4 = answer4;
}

public String getAnswer2() {
	return Answer2;
}

public void setAnswer2(String answer2) {
	Answer2 = answer2;
}
/*
public String getCourseSubcategoryName() {
	return CourseSubcategoryName;
}

public void setCourseSubcategoryName(String courseSubcategoryName) {
	CourseSubcategoryName = courseSubcategoryName;
}
*/

public String getCorrectAnswer() {
	return correctAnswer;
}

public void setCorrectAnswer(String correctAnswer) {
	this.correctAnswer = correctAnswer;
}
@OneToOne 
@JoinColumn(name="subcategoryId")
public SubcategoryVO getSubCategoryVO() {
	return subCategoryVO;
}

public void setSubCategoryVO(SubcategoryVO subCategoryVO) {
	this.subCategoryVO = subCategoryVO;
}}
