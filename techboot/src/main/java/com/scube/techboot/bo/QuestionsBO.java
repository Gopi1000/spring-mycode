package com.scube.techboot.bo;

public class QuestionsBO extends BaseBo{

	private int questionId;
	private String subCategory;
	private String questionType;
	private String question;
	private String ans1;
	private String ans2;
	private String ans3;
	private String ans4;
	private String correctAnswer;

	private SubcategoryBO subCategoryBO;
/*	private CategoryBO categoryBO; 
*/	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	public String getAns4() {
		return ans4;
	}
	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	/*public CategoryBO getCategoryBO() {
		return categoryBO;
	}
	public void setCategoryBO(CategoryBO categoryBO) {
		this.categoryBO = categoryBO;
	}*/
	public SubcategoryBO getSubCategoryBO() {
		return subCategoryBO;
	}
	public void setSubCategoryBO(SubcategoryBO subCategoryBO) {
		this.subCategoryBO = subCategoryBO;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

}
