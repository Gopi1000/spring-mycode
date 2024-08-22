package com.scube.mycognitiv.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.utils.QuizQuestion;

public interface QuestionAndExamService {

	public ArrayList<QuizQuestion> retrievequestions(int categoryid,
			List<Integer> subcategoryid, boolean fulltest, int selfrating,
			int totalquestion, int userid, QuizQuestion questiontype) throws SQLException;

	String getCategoryAndSubCategoryName(int categoryId, List<Integer> subcategoryId);

	public QuizQuestion insertanswer(List<QuizQuestion> questionList, int userid, int testId) throws SQLException;

	
	public ArrayList<ResultBO> retrieveteststatus(int userid) throws SQLException;

	public ArrayList<ResultBO> retrievetakentest(int userId) throws SQLException;

	public ArrayList<QuizQuestion> retrievetakentestbyId(int userId, int testId) throws SQLException;

	public ArrayList<QuizQuestion> retrievemarkedquestions(int userid)throws SQLException;

	public QuizQuestion addMarkedQuestion(QuizQuestion addbo) throws SQLException;

	public ArrayList<QuizQuestion> retrievetexttakentestbyId(int userId,
			int testId) throws SQLException;

}
