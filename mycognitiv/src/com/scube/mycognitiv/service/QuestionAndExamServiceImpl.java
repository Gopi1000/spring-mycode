package com.scube.mycognitiv.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.dao.QuestionAndExamDao;
import com.scube.mycognitiv.dao.QuestionAndExamDaoImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

public class QuestionAndExamServiceImpl implements QuestionAndExamService {

	QuestionAndExamDao questionAndExamDao = new QuestionAndExamDaoImpl();

	@Override
	public ArrayList<QuizQuestion> retrievequestions(int categoryid,
			List<Integer> subcategoryid, boolean fulltest, int selfrating,
			int totalquestion, int userid,QuizQuestion questiontype) throws SQLException {
		return questionAndExamDao.retrievequestions(categoryid, subcategoryid,
				fulltest, selfrating, totalquestion, userid,questiontype);
	}

	@Override
	public String getCategoryAndSubCategoryName(int categoryId,
			List<Integer> subcategoryId) {
		return questionAndExamDao.getCategoryAndSubCategoryName(categoryId, subcategoryId);
	}

	@Override
	public QuizQuestion insertanswer(
			List<QuizQuestion> questionList,int userid, int testId) throws SQLException {
		
		return questionAndExamDao.insertanswer(questionList,userid,testId);
	}

	@Override
	public ArrayList<ResultBO> retrieveteststatus(int userid) throws SQLException {
		
		return questionAndExamDao.retrieveteststatus(userid);
	}

	@Override
	public ArrayList<ResultBO> retrievetakentest(int userId) throws SQLException {
		
		return questionAndExamDao.retrievetakentest(userId);
	}

	@Override
	public ArrayList<QuizQuestion> retrievetakentestbyId(int userId, int testId) throws SQLException {
		
		return questionAndExamDao.retrievetakentestbyId(userId,testId);
	}

	@Override
	public ArrayList<QuizQuestion> retrievemarkedquestions(int userid)
			throws SQLException {
		
		return questionAndExamDao.retrievemarkedquestions( userid);
	}

	@Override
	public QuizQuestion addMarkedQuestion(QuizQuestion addbo)
			throws SQLException {
		
		return questionAndExamDao.addMarkedQuestion(addbo);
	}

	@Override
	public ArrayList<QuizQuestion> retrievetexttakentestbyId(int userId,
			int testId) throws SQLException {
		
		return questionAndExamDao.retrievetexttakentestbyId(userId,testId);
	}

	

}
