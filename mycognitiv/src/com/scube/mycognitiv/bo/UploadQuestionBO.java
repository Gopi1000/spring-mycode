package com.scube.mycognitiv.bo;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class UploadQuestionBO extends BaseBO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2938979379788689635L;
private int testId;
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

	private int queId;
	private Date created;
	private String testName;
	private String testLevel;
	private String questionLocation;
	private List<UploadQuestionBO>getTestName;
	private TreeSet<String>testLevelList;
	TreeSet<String>testNameList;
	TreeSet<Integer>testIdList;
	/**
	 * @return the testIdList
	 */
	public TreeSet<Integer> getTestIdList() {
		return testIdList;
	}

	/**
	 * @param testIdList the testIdList to set
	 */
	public void setTestIdList(TreeSet<Integer> testIdList) {
		this.testIdList = testIdList;
	}

	/**
	 * @return the queId
	 */
	public int getQueId() {
		return queId;
	}

	/**
	 * @param queId
	 *            the queId to set
	 */
	public void setQueId(int queId) {
		this.queId = queId;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName
	 *            the testName to set
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
	 * @param testLevel
	 *            the testLevel to set
	 */
	public void setTestLevel(String testLevel) {
		this.testLevel = testLevel;
	}

	/**
	 * @return the questionLocation
	 */
	public String getQuestionLocation() {
		return questionLocation;
	}

	/**
	 * @param questionLocation
	 *            the questionLocation to set
	 */
	public void setQuestionLocation(String questionLocation) {
		this.questionLocation = questionLocation;
	}

	/**
	 * @return the getTestName
	 */
	public List<UploadQuestionBO> getGetTestName() {
		return getTestName;
	}

	/**
	 * @param getTestName the getTestName to set
	 */
	public void setGetTestName(List<UploadQuestionBO> getTestName) {
		this.getTestName = getTestName;
	}

	/**
	 * @return the testLevelList
	 */
	public TreeSet<String> getTestLevelList() {
		return testLevelList;
	}

	/**
	 * @param testLevelList the testLevelList to set
	 */
	public void setTestLevelList(TreeSet<String> testLevelList) {
		this.testLevelList = testLevelList;
	}

	/**
	 * @return the testNameList
	 */
	public TreeSet<String> getTestNameList() {
		return testNameList;
	}

	/**
	 * @param testNameList the testNameList to set
	 */
	public void setTestNameList(TreeSet<String> testNameList) {
		this.testNameList = testNameList;
	}


}
