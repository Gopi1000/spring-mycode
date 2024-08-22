package com.scube.mycognitiv.bo;

import java.util.List;

public class CreateTestBO extends BaseBO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int categoryId;
	List<Integer> subcategoryId;
	String category;
	String subcategory;
	int selfRate;
	int totalQuestion;
	int userId;
	String userType;
	private int testId;
	private int companyId;
	private String testName;
	
	private boolean isDelete;
	int sNo;
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSelfRate() {
		return selfRate;
	}
	public void setSelfRate(int selfRate) {
		this.selfRate = selfRate;
	}
	public int getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	/**
	 * @return the testId
	 */
	public int getTestId() {
		return testId;
	}
	/**
	 * @param testId the testId to set
	 */
	public void setTestId(int testId) {
		this.testId = testId;
	}
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
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getSubcategoryIdAsString() {
		String subcategory= subcategoryId.toString();
		return subcategory;
	}
	public List<Integer> getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(List<Integer> subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
	
	
}
