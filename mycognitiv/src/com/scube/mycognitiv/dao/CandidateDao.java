package com.scube.mycognitiv.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.scube.mycognitiv.bo.ProffesorRegisterBO;
import com.scube.mycognitiv.bo.RegistrationBO;
import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.utils.Exam;
import com.scube.mycognitiv.utils.QuizQuestion;

public interface CandidateDao {
	/**This method used to insert the candidate details in the system
	 * 
	 * @param bo
	 * @return
	 */
	public RegistrationBO candidateRegistration(RegistrationBO bo);
	
	/**This method used to authendicate the candidate details in the system
	 * 
	 * @param bo
	 * @return
	 */
	public RegistrationBO authendicate(RegistrationBO bo);

	/**
	 * This method used to insert the exam details in the system
	 * @param exam
	 * @param taken
	 * @param request
	 * @param timeSpent 
	 * @param examName 
	 * @param totalQuestions 
	 * @return
	 * @throws SQLException 
	 */
	public int calculateResult(Exam exam, int taken, String timeSpent, int totalQuestions,ArrayList<Integer> subcategoryId,int categoryId,int testId,int userid) throws SQLException ;
	/**
	 * reterive candidate test details
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public ResultBO getTakenTestDetails(int userId) throws SQLException;
	/**
	 * This method used to reterive candidate profile details
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public UserBO getCandidateDetails(int userId) throws SQLException ;

	/**
	 * This method used to retrive the all candidate details
	 * @return
	 * @throws SQLException
	 */
	public UserBO retrieveAllCandidate(UserBO userBO)throws SQLException;
	
	/**
	 * This method used to reterieve the test details
	 * @return
	 * @throws SQLException
	 */
	public ResultBO retrieveTestDetails()throws SQLException;
	/**
	 * this method used to retrieve the candidate test details
	 * @return
	 * @throws SQLException
	 */
	public ResultBO retrieveCandidateTestDetails(int userId,String testType) throws SQLException ;
	
	/**
	 * this method used to retrieve the candidate test details
	 * @return
	 * @throws SQLException
	 */
	public ResultBO retrieveCandidateTestDetail(int userId,int testId) throws SQLException ;
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResultBO retrieveTestDetail() throws SQLException;
	
	public void updateCandidateLearningPower(int userId, int questionTaken,
			int correctQuestion) throws SQLException ;

	public ProffesorRegisterBO proffesorRegistration(
			ProffesorRegisterBO proffesorregisterBO);

	public ProffesorRegisterBO authendicate(ProffesorRegisterBO bo);

	public QuizQuestion updateQuestion(QuizQuestion updatebo) throws SQLException;

	public UserBO isExistEmailAddress(String emailAddress) throws Exception;

	public UserBO changePassword(UserBO userBO)throws Exception;
	
	public int getCandidateCount();

	public void companyRegistration(RegistrationBO user);

	public UserBO adminDeleteCandidate(UserBO userBO)throws Exception;

	public UserBO adminUpdateCandidate(UserBO userBO)throws Exception;

	public int getCandidateCount(UserBO bo);


}
