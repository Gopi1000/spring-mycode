package com.scube.mycognitiv.bo;

import java.util.List;
import java.util.Set;

public class ResultBO extends BaseBO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8812569675316387959L;
	private int serialNo;
	private String userName;
	private String question;
	private String answer;
	private String testName;
	private String correctAnswer;
	private String testType;
	private int resultId;
	private List<ResultBO> resultBOList;
	private int testId;
	private int userId;
	private Set<String> testTypeList;
	private String status;
	private String questionPath;
	private int inviteId;
	private String totaltimespent;
	private int totalQuestion;
	private int testCount;
	private int questionCount;
	private String overAllResult;
	

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getTestCount() {
		return testCount;
	}

	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public String getTotaltimespent() {
		return totaltimespent;
	}

	public void setTotaltimespent(String totaltimespent) {
		this.totaltimespent = totaltimespent;
	}

	/**
	 * @return the serialNo
	 */
	public int getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
		
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer
	 *            the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * @param testType
	 *            the testType to set
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public List<ResultBO> getResultBOList() {
		return resultBOList;
	}

	public void setResultBOList(List<ResultBO> resultBOList) {
		this.resultBOList = resultBOList;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public Set<String> getTestTypeList() {
		return testTypeList;
	}

	public void setTestTypeList(Set<String> testTypeList) {
		this.testTypeList = testTypeList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the questionPath
	 */
	public String getQuestionPath() {
		return questionPath;
	}

	/**
	 * @param questionPath the questionPath to set
	 */
	public void setQuestionPath(String questionPath) {
		this.questionPath = questionPath;
	}

	/**
	 * @return the inviteId
	 */
	public int getInviteId() {
		return inviteId;
	}

	/**
	 * @param inviteId the inviteId to set
	 */
	public void setInviteId(int inviteId) {
		this.inviteId = inviteId;
	}

	public String getOverAllResult() {
		return overAllResult;
	}

	public void setOverAllResult(String overAllResult) {
		this.overAllResult = overAllResult;
	}

}
