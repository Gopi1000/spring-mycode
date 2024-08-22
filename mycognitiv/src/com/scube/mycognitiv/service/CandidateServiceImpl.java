package com.scube.mycognitiv.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.scube.mycognitiv.bo.ProffesorRegisterBO;
import com.scube.mycognitiv.bo.RegistrationBO;
import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.dao.CandidateDao;
import com.scube.mycognitiv.dao.CandidateDaoImpl;
import com.scube.mycognitiv.utils.Exam;
import com.scube.mycognitiv.utils.QuizQuestion;

public class CandidateServiceImpl implements CandidateService {

	CandidateDao dao = new CandidateDaoImpl();

	@Override
	public RegistrationBO candidateRegistration(RegistrationBO bo) {

		try {
			if(null!=bo) {
				bo=dao.candidateRegistration(bo);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return bo;
	}

	/**
	 * This method used to authendicate the candidate details in the system
	 * 
	 * @param bo
	 * @return
	 */
	@Override
	public RegistrationBO authendicate(RegistrationBO bo) {
		return dao.authendicate(bo);
	}

	@Override
	public int calculateResult(Exam exam, int taken,String timeSpent,int totalQuestions,ArrayList<Integer> subcategoryId, int categoryId, int testId,int userid)
			throws SQLException {
		return dao.calculateResult(exam, taken, timeSpent, totalQuestions, subcategoryId, categoryId, testId, userid);
	}

	@Override
	public ResultBO getTakenTestDetails(int userId) throws SQLException {
		return dao.getTakenTestDetails(userId);
	}

	@Override
	public UserBO getCandidateDetails(int userId) throws SQLException {
		return dao.getCandidateDetails(userId);
	}

	@Override
	public UserBO retrieveAllCandidate(UserBO userBO) throws SQLException {
		return dao.retrieveAllCandidate(userBO);
	}

	@Override
	public ResultBO retrieveTestDetails() throws SQLException {
		return dao.retrieveTestDetails();
	}

	@Override
	public ResultBO retrieveCandidateTestDetails(int userId, String testType)
			throws SQLException {
		return dao.retrieveCandidateTestDetails(userId, testType);
	}

	@Override
	public ResultBO retrieveCandidateTestDetail(int userId, int testId)
			throws SQLException {
		return dao.retrieveCandidateTestDetail(userId, testId);
	}

	@Override
	public ResultBO retrieveTestDetail() throws SQLException {
		return dao.retrieveTestDetail();
	}

	public void updateCandidateLearningPower(int userId, int questionTaken,
			int correctQuestion) throws SQLException {
		dao.updateCandidateLearningPower(userId, questionTaken, correctQuestion);
	}

	@Override
	public ProffesorRegisterBO proffesorRegistration(
			ProffesorRegisterBO proffesorregisterBO) {
		
		return dao.proffesorRegistration(proffesorregisterBO);
	}

	@Override
	public ProffesorRegisterBO authendicate(ProffesorRegisterBO bo) {
		
		return dao.authendicate(bo);
	}

	@Override
	public QuizQuestion updateQuestion(QuizQuestion updatebo) throws SQLException {
		
		return dao.updateQuestion( updatebo);
	}

	@Override
	public UserBO isExistEmailAddress(String emailAddress) throws Exception {
		return dao.isExistEmailAddress(emailAddress);
	}

	@Override
	public UserBO changePassword(UserBO userBO) throws Exception {
		return dao.changePassword(userBO);
	}

	@Override
	public int getCandidateCount() {
		
		int candidateCount=0;
		candidateCount=dao.getCandidateCount();
		if(0<candidateCount) {
			return candidateCount;
		}
		return 0;
	}

	@Override
	public UserBO adminDeleteCandidate(UserBO userBO) throws Exception {
		return dao.adminDeleteCandidate(userBO);
	}

	@Override
	public UserBO adminUpdateCandidate(UserBO userBO) throws Exception {
		
		if(null!=userBO && userBO.getUserId()>0) {
			userBO=dao.adminUpdateCandidate(userBO);
		}
     return userBO;
	}

	@Override
	public int getCandidateCount(UserBO bo) {
		int candidateCount=0;
		candidateCount=dao.getCandidateCount(bo);
		if(0<candidateCount) {
			return candidateCount;
		}
		return 0;
	}

	 
}
