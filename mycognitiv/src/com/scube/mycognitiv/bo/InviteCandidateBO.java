/**
 * 
 */
package com.scube.mycognitiv.bo;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class InviteCandidateBO extends BaseBO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 976766161650010691L;
	
	private int inviteId;
	private int candidateId;
	private int testId;
	private int queId;
	/**
	 * @return the queId
	 */
	public int getQueId() {
		return queId;
	}
	/**
	 * @param queId the queId to set
	 */
	public void setQueId(int queId) {
		this.queId = queId;
	}
	private String candidateName;
	private String status;
	private String testName;
	private String testLevel;
	private String questionPath;
	
	
	
	private List<InviteCandidateBO>inviteCandidateTestDetails;
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
	/**
	 * @return the candidateName
	 */
	public String getCandidateName() {
		return candidateName;
	}
	/**
	 * @param candidateName the candidateName to set
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
	/**
	 * @return the candidateId
	 */
	public int getCandidateId() {
		return candidateId;
	}
	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
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
	 * @return the inviteCandidateTestDetails
	 */
	public List<InviteCandidateBO> getInviteCandidateTestDetails() {
		return inviteCandidateTestDetails;
	}
	/**
	 * @param inviteCandidateTestDetails the inviteCandidateTestDetails to set
	 */
	public void setInviteCandidateTestDetails(
			List<InviteCandidateBO> inviteCandidateTestDetails) {
		this.inviteCandidateTestDetails = inviteCandidateTestDetails;
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
	
	

}
