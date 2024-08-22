package com.scube.mycognitiv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.ProffesorRegisterBO;
import com.scube.mycognitiv.bo.RegistrationBO;
import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.utils.CommenFuncation;
import com.scube.mycognitiv.utils.DatabaseConnectionFactory;
import com.scube.mycognitiv.utils.Exam;
import com.scube.mycognitiv.utils.QuizQuestion;

public class CandidateDaoImpl implements CandidateDao {

	private static final Logger LOGGER = Logger.getLogger(CandidateDaoImpl.class);
	public int currentQuestion = 0;
	public int totalNumberOfQuestions = 0;
	public int quizDuration = 0;
	public String selectedExam;
//	int testIds;
	public List<Integer> quizSelectionsList = new ArrayList<Integer>();
	CommenFuncation fun = new CommenFuncation();

	/**
	 * This method used to insert the user details in the system
	 */
	@Override
	public RegistrationBO candidateRegistration(RegistrationBO bo) {
		Connection con = DatabaseConnectionFactory.createConnection();

		try {
			if (!bo.getUserName().isEmpty() && !bo.getEmail().isEmpty() && !bo.getPassword().isEmpty()) {
				PreparedStatement ps = con.prepareStatement("INSERT INTO user(user_name, password,email,phone_no,"
						+ "user_type,createdBy,isDelete) values (?,?,?,?,?,?,?)");
				ps.setString(1, bo.getUserName());
				ps.setString(2, bo.getPassword());
				ps.setString(3, bo.getEmail());
				ps.setLong(4, bo.getPhoneNumber());
				ps.setString(5, "Candidate");
				ps.setLong(6, bo.getCreatedBy());
				ps.setBoolean(7, bo.getIsDelete());
				int i = ps.executeUpdate();
				/*
				 * Statement st = con.createStatement(); String sql =
				 * "INSERT INTO user(user_name, password,email,phone_no,user_type,createdBy,isDelete) values ('"
				 * + bo.getUserName() + "','" + bo.getPassword() + "','" + bo.getEmail() + "','"
				 * + bo.getPhoneNumber() +"','Candidate','" +bo.getCreatedBy()
				 * +"','"+bo.getIsDelete()+"')";
				 * 
				 * //System.out.println(sql); int i = st.executeUpdate(sql);
				 */
				if (i != 0) {
					bo.setId(i);
					bo.setUserName(bo.getUserName());
					bo.setResponse("Candidate registration Successfully");
				}
			} else {
				bo.setErrorMessage("Candidate registration failed!please contact Admin");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
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

		Connection con = DatabaseConnectionFactory.createConnection();
		ResultSet set = null;
		try {
			Statement st = con.createStatement();
			String sql = "Select * from  user where email='" + bo.getEmail() + "' and password='" + bo.getPassword()
					+ "' ";
			// System.out.println(sql);
			set = st.executeQuery(sql);
			while (set.next()) {

				bo.setRegId(set.getInt(1));
				bo.setUserType(set.getString(6));
				bo.setUserName(set.getString(2));
				bo.setEmail(set.getString(4));
				bo.setResponse("Login successfuly");

			}
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}

			return null;
		}
		return bo;
	}

	@Override
	public UserBO adminUpdateCandidate(UserBO userBO) throws Exception {

		int updateCount = 0;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			if (null != userBO && userBO.getUserId() > 0) {
				PreparedStatement ps = con.prepareStatement(
						"update user set user_name=?, password=?, email=?, phone_no=?, isDelete=?, user_type='Candidate' where user_id=? ");
				ps.setString(1, userBO.getUserName());
				ps.setString(2, userBO.getPassword());
				ps.setString(3, userBO.getEmail());
				ps.setLong(4, userBO.getPhoneNo());
				ps.setBoolean(5, userBO.getIsDelete());
				ps.setInt(6, userBO.getUserId());

				updateCount = ps.executeUpdate();
				if (updateCount > 0) {
					userBO.setErrorMessage("Candidate Updated Successfully!!");
				} else {
					userBO.setErrorMessage("Candidate Updated Failed!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBO;
	}

	/**
	 * This method used to insert the exam details in the system
	 * 
	 * @param exam
	 * @param taken
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int calculateResult(Exam exam, int taken, String timeSpent, int totalQuestions,
			ArrayList<Integer> subcategoryId, int categoryId, int testId, int userid) throws SQLException {
		int totalCorrect = 0;
		int count = 0;
		ResultSet set = null;
		String testName = null;
		int testIds = 0;

		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement testid = con
				.prepareStatement("select test_id from result where user_id=? order by result.test_id desc limit 1");
		testid.setInt(1, userid);
		set = testid.executeQuery();
		while (set.next()) {
			int id = set.getInt(1);
			testIds = id + 1;
		}
		if (testIds == 0) {
			testIds = 1;
		}

		PreparedStatement ps2 = con.prepareStatement("select test_name from test as t where t.test_id=?");
		ps2.setInt(1, testId);
		ResultSet rset = ps2.executeQuery();
		while (rset.next()) {
			testName = rset.getString(1);
		}

		Map<Integer, Integer> userSelectionsMap = exam.selections;
		List<Integer> userSelectionsList = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : userSelectionsMap.entrySet()) {
			userSelectionsList.add(entry.getValue());
		}

		quizSelectionsList = userSelectionsList;
		List<QuizQuestion> questionList = exam.questionList;
		List<Integer> correctAnswersList = new ArrayList<Integer>();
		for (QuizQuestion question : questionList) {

			correctAnswersList.add(question.getAnswerId());
		}

		for (QuizQuestion question : questionList) {
			boolean flag = true;
			count = count + 1;
			if (question.getQuestionType().equals("options")) {
				if (count <= questionList.size()) {
					try {
						PreparedStatement ps = con.prepareStatement(
								"INSERT INTO result(question_id,flag,user_selection, user_id,test_type,test_id,created_date,total_timespent,total_question,testCount,questionCount,answer_status) values(?,?,?,?,?,?,?,?,?,?,?,?)");
						ps.setInt(1, question.getQuesId());
						ps.setBoolean(2, flag);
						ps.setInt(3, question.getUserSelected());
						ps.setInt(4, userid);
						ps.setString(5, testName);
						ps.setInt(6, testId);
						ps.setString(7, fun.convertGMTtodbDate(new Date()));
						ps.setString(8, timeSpent);
						ps.setInt(9, totalQuestions);
						ps.setInt(10, 0);
						ps.setInt(11, 0);
						ps.setString(12, "");
						int id = ps.executeUpdate();
						if (id == 0) {
							totalCorrect = 0;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				for (Integer userSelected : question.getUsersSelected())
					if (count <= questionList.size()) {
						try {
							PreparedStatement ps = con.prepareStatement(
									"INSERT INTO result(question_id,flag,user_selection, user_id,test_type,test_id,created_date,total_timespent,total_question,testCount,questionCount,answer_status) values(?,?,?,?,?,?,?,?,?,?,?,?)");
							ps.setInt(1, question.getQuesId());
							ps.setBoolean(2, flag);

							ps.setInt(3, userSelected);
							ps.setInt(4, userid);
							ps.setString(5, testName);
							ps.setInt(6, testId);
							ps.setString(7, fun.convertGMTtodbDate(new Date()));
							ps.setString(8, timeSpent);
							ps.setInt(9, totalQuestions);
							ps.setInt(10, 0);
							ps.setInt(11, 0);
							ps.setString(12, "");
							int id = ps.executeUpdate();
							if (id == 0) {
								totalCorrect = 0;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}

			float progress = 0f;
			float totalTimeduration = 0f;
			PreparedStatement insertprogress = con.prepareStatement(
					"INSERT INTO candidate_competancy(user_id,category_id,sub_category_id,progress,total_timespent) values(?,?,?,?,?)");
			insertprogress.setInt(1, userid);
			insertprogress.setInt(2, question.getCategoryId());
			insertprogress.setInt(3, question.getSubcategoryId());
			insertprogress.setFloat(4, progress);
			insertprogress.setFloat(5, totalTimeduration);
			insertprogress.executeUpdate();

			PreparedStatement ps = con
					.prepareStatement("select total_attempts from question_competancy where question_id=?");
			ps.setInt(1, question.getQuesId());
			ResultSet rs = ps.executeQuery();
			int total_attempts = 0;
			while (rs.next()) {
				total_attempts = rs.getInt(1);
				total_attempts = total_attempts + 1;

			}
			if (total_attempts == 0) {
				total_attempts = 1;
			}

			PreparedStatement pstmt = con
					.prepareStatement("update question_competancy  set total_attempts=?  where question_id=?");
			pstmt.setInt(1, total_attempts);
			pstmt.setInt(2, question.getQuesId());
			pstmt.executeUpdate();

			int correct_attempts = 0;
			PreparedStatement flagset = con
					.prepareStatement("update result  set flag=?  where question_id=? and user_id=?");
			flagset.setBoolean(1, false);
			flagset.setInt(2, question.getQuesId());
			flagset.setInt(3, userid);
			flagset.executeUpdate();

			if (null != question.getAnswerIds() && null != question.getUsersSelected()) {
				Collections.sort(question.getAnswerIds());
				Collections.sort(question.getUsersSelected());
			}

			if (question.getAnswerId() == question.getUserSelected()) {
				totalCorrect++;
				PreparedStatement psstmt = con
						.prepareStatement("select correct_attempts from question_competancy  where  question_id=?");
				psstmt.setInt(1, question.getQuesId());
				ResultSet result = psstmt.executeQuery();

				while (result.next()) {
					correct_attempts = result.getInt(1);
					correct_attempts = correct_attempts + 1;
				}
				if (correct_attempts == 0) {
					correct_attempts = 1;
				}

				PreparedStatement stmt = con
						.prepareStatement("update question_competancy   set correct_attempts=?  where question_id=?");
				stmt.setInt(1, correct_attempts);
				stmt.setInt(2, question.getQuesId());
				stmt.executeUpdate();

				PreparedStatement status = con
						.prepareStatement("update  result set answer_status=?  where question_id=? and test_id=?");
				status.setString(1, "Correct");
				status.setInt(2, question.getQuesId());
				status.setInt(3, testId);
				status.executeUpdate();

				PreparedStatement flagupdate = con
						.prepareStatement("update result set flag=?  where question_id=? and test_id=?");
				flagupdate.setBoolean(1, true);
				flagupdate.setInt(2, question.getQuesId());
				flagupdate.setInt(3, testId);
				flagupdate.executeUpdate();

			} else if (null != question.getAnswerIds() && null != question.getUsersSelected()
					&& question.getAnswerIds().equals(question.getUsersSelected())) {
				totalCorrect++;
				PreparedStatement psstmt = con
						.prepareStatement("select correct_attempts from question_competancy  where  question_id=?");
				psstmt.setInt(1, question.getQuesId());
				ResultSet result = psstmt.executeQuery();

				while (result.next()) {
					correct_attempts = result.getInt(1);
					correct_attempts = correct_attempts + 1;
				}
				if (correct_attempts == 0) {
					correct_attempts = 1;
				}

				PreparedStatement stmt = con
						.prepareStatement("update question_competancy   set correct_attempts=?  where question_id=?");
				stmt.setInt(1, correct_attempts);
				stmt.setInt(2, question.getQuesId());
				stmt.executeUpdate();

				PreparedStatement status = con
						.prepareStatement("update  result set answer_status=?  where question_id=? and test_id=?");
				status.setString(1, "Correct");
				status.setInt(2, question.getQuesId());
				status.setInt(3, testId);
				status.executeUpdate();

				PreparedStatement flagupdate = con
						.prepareStatement("update result set flag=?  where question_id=? and test_id=?");
				flagupdate.setBoolean(1, true);
				flagupdate.setInt(2, question.getQuesId());
				flagupdate.setInt(3, testId);
				flagupdate.executeUpdate();

			} else if (question.getAnswerId() != question.getUserSelected()) {

				PreparedStatement status = con
						.prepareStatement("update result set answer_status=?  where question_id=? and test_id=?");
				status.setString(1, "Wrong");
				status.setInt(2, question.getQuesId());
				status.setInt(3, testId);
				status.executeUpdate();

				PreparedStatement flagupdate = con
						.prepareStatement("update result set flag=?  where question_id=? and test_id=? ");
				flagupdate.setBoolean(1, true);
				flagupdate.setInt(2, question.getQuesId());
				flagupdate.setInt(3, testId);
				flagupdate.executeUpdate();
			}

			PreparedStatement pstmt1 = con.prepareStatement("select * from question_competancy  where  question_id=?");
			pstmt1.setInt(1, question.getQuesId());
			ResultSet result = pstmt1.executeQuery();

			while (result.next()) {
				correct_attempts = result.getInt(4);
				total_attempts = result.getInt(3);
				if (total_attempts > 10) {

					float complecity = ((correct_attempts * 100) / (total_attempts));

					PreparedStatement complexity = con
							.prepareStatement("update question_competancy  set complecity=?  where question_id=?");
					complexity.setFloat(1, complecity);
					complexity.setInt(2, question.getQuesId());
					complexity.executeUpdate();
				}
			}

		}

		for (int val : subcategoryId) {
			QuizQuestion listsubcatId = null;
			int totalques = 0;
			int correctansbysId = 0;
			for (QuizQuestion listsubcatId1 : questionList) {
				listsubcatId = new QuizQuestion();
				listsubcatId.setSubcategoryId(listsubcatId1.getSubcategoryId());
				if (val == listsubcatId.getSubcategoryId()) {
					totalques++;

					listsubcatId.setQuesId(listsubcatId1.getQuesId());
					listsubcatId.setQuestion(listsubcatId1.getQuestion());
					if (listsubcatId1.getQuestionType().equals("options")) {
						listsubcatId.setAnswerId(listsubcatId1.getAnswerId());
						listsubcatId.setUserSelected(listsubcatId1.getUserSelected());
						if (listsubcatId.getAnswerId() == listsubcatId.getUserSelected()) {
							correctansbysId++;
						} else if (listsubcatId.getAnswerId() != listsubcatId.getUserSelected()) {
							// System.out.println("wrong:::" + "wrong");
						}
					} else if (listsubcatId1.getQuestionType().equals("Multiple Select")) {
						listsubcatId.setAnswerIds(listsubcatId1.getAnswerIds());
						listsubcatId.setUsersSelected(listsubcatId1.getAnswerIds());
						Collections.sort(listsubcatId.getAnswerIds());
						Collections.sort(listsubcatId.getUsersSelected());
						if (listsubcatId.getAnswerIds().equals(listsubcatId.getUsersSelected())) {
							correctansbysId++;
						} // System.out.println("wrong:::" + "wrong");

					}
				}
			}

			if (val == listsubcatId.getSubcategoryId()) {
				float updateTiming = 0f;
				float updatedLearningPower = 0f;
				// int questionTaken = questionList.size();
				updatedLearningPower = ((correctansbysId * 100) / totalques);

				PreparedStatement previousprogress = con.prepareStatement(
						"select * from candidate_competancy where user_id=? and category_id=? and sub_category_id=? order by category_id,sub_category_id,progress desc limit 1 ");
				previousprogress.setInt(1, userid);
				previousprogress.setInt(2, categoryId);
				previousprogress.setInt(3, val);
				ResultSet progress = previousprogress.executeQuery();
				while (progress.next()) {
					float oldLearningPower = progress.getFloat(5);
					updatedLearningPower = ((updatedLearningPower + oldLearningPower) / 2);

					float oldTiming = progress.getFloat(6);
					updateTiming = ((updateTiming + oldTiming) / 2);

				}

				PreparedStatement currentprogress = con.prepareStatement(
						"update candidate_competancy set progress=?, total_timespent=? where user_id=? and category_id=? and sub_category_id=?");
				currentprogress.setFloat(1, updatedLearningPower);
				currentprogress.setFloat(2, updateTiming);
				currentprogress.setInt(3, userid);
				currentprogress.setInt(4, categoryId);
				currentprogress.setInt(5, val);
				currentprogress.executeUpdate();

			}
		}
		// HttpSession session = request.getSession(false);
		// session = request.getSession(false);
		String overAllResult = null;
		// int totalQuestion =
		// Integer.valueOf(session.getAttribute("totalNumberOfQuizQuestions").toString());

		int result = totalQuestions / 2;
		if (totalCorrect >= result) {
			overAllResult = "Pass";
		} else {
			overAllResult = "Fail";
		}

		PreparedStatement ps = con
				.prepareStatement("INSERT INTO over_all_result(user_id, test_id, over_all_result) values(?,?,?)");
		ps.setInt(1, userid);
		ps.setInt(2, testId);
		ps.setString(3, overAllResult);
		ps.executeUpdate();

		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return totalCorrect;
	}

	@Override
	public ResultBO getTakenTestDetails(int userId) throws SQLException {
		List<ResultBO> resultBOlist = new ArrayList<ResultBO>();
		ResultBO bo = new ResultBO();
		ResultSet set = null;
		int testCount = 0;
		Connection con = DatabaseConnectionFactory.createConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select distinct test_id from result where user_id=? ");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ResultBO resultBO = new ResultBO();
				resultBO.setTestId(rs.getInt(1));
				PreparedStatement stmt = con.prepareStatement(
						"select distinct test_type,total_timespent,total_question from result where test_id=? ");
				stmt.setInt(1, resultBO.getTestId());
				ResultSet rs1 = stmt.executeQuery();

				while (rs1.next()) {

					testCount++;
					/* testCount = Integer.parseInt(br.readLine()); */
					resultBO.setTestType(rs1.getString(1));
					resultBO.setTotaltimespent(rs1.getString(2));
					resultBO.setTotalQuestion(rs1.getInt(3));
					resultBO.setTestCount(testCount);
					int quescount = resultBO.getTotalQuestion();

					resultBO.setQuestionCount(quescount);
					resultBOlist.add(resultBO);
				}

			}
			bo.setResultBOList(resultBOlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return bo;
	}

	@Override
	public UserBO getCandidateDetails(int userId) throws SQLException {
		UserBO bo = new UserBO();
		ResultSet set = null;
		Connection con = DatabaseConnectionFactory.createConnection();
		Statement st = con.createStatement();
		try {
			String sql = "Select * from  user where user_id='" + userId + "'";
			set = st.executeQuery(sql);

			while (set.next()) {

				UserBO userBO = new UserBO();
				userBO.setUserId(set.getInt(1));
				userBO.setUserName(set.getString(2));
				userBO.setPassword(set.getString(3));
				userBO.setEmail(set.getString(4));
				userBO.setPhoneNo(set.getLong(5));
				userBO.setUserType(set.getString(6));
				userBO.setIsDelete(set.getBoolean(7));
				bo = userBO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return bo;
	}

	@Override
	public UserBO retrieveAllCandidate(UserBO userBO) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		UserBO bo = new UserBO();
		ResultSet set = null;
		String sql = null;
		List<UserBO> userList = new ArrayList<UserBO>();
		try {
			Statement st = con.createStatement();

			sql = "Select * from  user where user_type='candidate' and isDelete='false'";

			if (userBO.getUserId() > 0) {
				sql = "Select * from  user where user_type='candidate' and isDelete='false' and createdBy='"
						+ userBO.getUserId() + "'";
			} else {
				sql = "Select * from  user where user_type='candidate' and isDelete='false'";
			}

			String paginationQuery = null;
			if (null != userBO && userBO.getRecordsPerPage() > 0) {
				paginationQuery = "and user_id ORDER BY user_id LIMIT " + userBO.getRecordsPerPage() + " OFFSET "
						+ userBO.getRecordIndex() + "";
				sql = sql + paginationQuery;
			}
			// System.out.println(sql);
			set = st.executeQuery(sql);
			int count = 0;
			while (set.next()) {
				count++;
				UserBO useBO = new UserBO();
				useBO.setUserId(set.getInt(1));
				useBO.setUserName(set.getString(2));
				useBO.setPassword(set.getString(3));
				useBO.setEmail(set.getString(4));
				useBO.setPhoneNo(set.getLong(5));
				useBO.setUserType(set.getString(6));
				useBO.setCreatedBy(set.getLong(7));
				useBO.setIsDelete(set.getBoolean(8));
				useBO.setSerialNo(count);
				userList.add(useBO);

			}
			bo.setUserList(userList);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}

		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return bo;
	}

	@Override
	public ResultBO retrieveTestDetails() throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultBO bo = new ResultBO();
		ResultSet set = null;
		List<ResultBO> userResultList = new ArrayList<ResultBO>();
		try {
			Statement st = con.createStatement();
			String sql = "select  * from result   join  user where result.user_id =user.user_id  group by user_name limit 5";
			// System.out.println(sql);
			set = st.executeQuery(sql);
			int count = 0;
			while (set.next()) {
				count++;
				ResultBO resultBO = new ResultBO();
				resultBO.setUserName(set.getString(10));
				resultBO.setQuestion(set.getString(2));
				resultBO.setAnswer(set.getString(3));
				resultBO.setCorrectAnswer(set.getString(4));
				resultBO.setTestType(set.getString(6));
				resultBO.setSerialNo(count);
				resultBO.setUserId(set.getInt(5));
				resultBO.setCreated(set.getDate(8));
				userResultList.add(resultBO);

			}
			bo.setResultBOList(userResultList);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	@Override
	public ResultBO retrieveCandidateTestDetails(int userId, String testType) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultBO bo = new ResultBO();
		ResultSet set = null;
		List<ResultBO> userResultList = new ArrayList<ResultBO>();
		try {
			if (null == testType) {
				Statement st = con.createStatement();
				String sql = "select *from result where user_id='" + userId + "' group by test_id";
				// System.out.println(sql);
				set = st.executeQuery(sql);
			} else if (null != testType && 0 != userId) {
				Statement st = con.createStatement();
				String sql = "select *from result where test_type='" + testType + "'and user_id='" + userId
						+ "' group by test_id";
				// System.out.println(sql);
				set = st.executeQuery(sql);
			}
			int count = 0;
			while (set.next()) {
				count++;
				ResultBO resultBO = new ResultBO();
				resultBO.setQuestion(set.getString(2));
				resultBO.setAnswer(set.getString(3));
				resultBO.setCorrectAnswer(set.getString(4));
				resultBO.setTestType(set.getString(6));
				resultBO.setSerialNo(count);
				resultBO.setUserId(set.getInt(5));
				resultBO.setTestId(set.getInt(7));
				resultBO.setCreated(set.getDate(8));
				userResultList.add(resultBO);

			}
			bo.setResultBOList(userResultList);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	@Override
	public ResultBO retrieveCandidateTestDetail(int userId, int testId) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultBO bo = new ResultBO();
		ResultSet set = null;
		List<ResultBO> userResultList = new ArrayList<ResultBO>();
		try {

			Statement st = con.createStatement();
			String sql = "select *from result where test_id='" + testId + "'and user_id='" + userId + "'";
			// System.out.println(sql);
			set = st.executeQuery(sql);

			int count = 0;
			while (set.next()) {
				count++;
				ResultBO resultBO = new ResultBO();
				resultBO.setQuestion(set.getString(2));
				resultBO.setAnswer(set.getString(3));
				resultBO.setCorrectAnswer(set.getString(4));
				resultBO.setTestType(set.getString(6));
				resultBO.setSerialNo(count);
				resultBO.setUserId(set.getInt(5));
				resultBO.setTestId(set.getInt(7));
				resultBO.setCreated(set.getDate(8));
				userResultList.add(resultBO);

			}
			bo.setResultBOList(userResultList);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	@Override
	public ResultBO retrieveTestDetail() throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultBO bo = new ResultBO();
		ResultSet set = null;
		List<ResultBO> userResultList = new ArrayList<ResultBO>();
		try {
			Statement st = con.createStatement();
			String sql = "select  * from question  order by test_name limit 10";
			// System.out.println(sql);
			set = st.executeQuery(sql);
			int count = 0;
			while (set.next()) {
				count++;
				ResultBO resultBO = new ResultBO();
				resultBO.setTestName(set.getString(2));
				resultBO.setTestType(set.getString(3));
				resultBO.setCreated(set.getDate(5));
				resultBO.setSerialNo(count);
				userResultList.add(resultBO);

			}
			bo.setResultBOList(userResultList);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	public void updateCandidateLearningPower(int userId, int questionTaken, int correctQuestion) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();

		float updatedLearningPower = 0f;

		updatedLearningPower = ((correctQuestion * 100) / questionTaken);

		PreparedStatement ps = con.prepareStatement("select * from candidate_competancy where user_id=?");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			float oldLearningPower = rs.getFloat(5);
			updatedLearningPower = ((updatedLearningPower + oldLearningPower) / 2);
		}

		PreparedStatement ps2 = con.prepareStatement("update candidate_competancy set progress=? where user_id=?");
		ps2.setFloat(1, updatedLearningPower);
		ps2.setInt(2, userId);
		ps2.executeUpdate();

	}

	@Override
	public ProffesorRegisterBO proffesorRegistration(ProffesorRegisterBO proffesorregisterBO) {
		Connection con = DatabaseConnectionFactory.createConnection();

		try {
			if (!proffesorregisterBO.getUserName().isEmpty() && !proffesorregisterBO.getEmail().isEmpty()
					&& !proffesorregisterBO.getPassword().isEmpty()) {
				Statement st = con.createStatement();
				String sql = "INSERT INTO professor(user_name, password,email,phone_no,user_type) values ('"
						+ proffesorregisterBO.getUserName() + "','" + proffesorregisterBO.getPassword() + "','"
						+ proffesorregisterBO.getEmail() + "','" + proffesorregisterBO.getPhoneNumber()
						+ "','Professor')";
				// System.out.println(sql);
				int i = st.executeUpdate(sql);
				if (i != 0) {
					proffesorregisterBO.setId(i);
					proffesorregisterBO.setUserName(proffesorregisterBO.getUserName());
					proffesorregisterBO.setResponse("Candidate registration Successfully");
				}
			} else {
				proffesorregisterBO.setErrorMessage("Candidate registration failed!please contact Admin");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return proffesorregisterBO;
	}

	@Override
	public ProffesorRegisterBO authendicate(ProffesorRegisterBO bo) {

		Connection con = DatabaseConnectionFactory.createConnection();
		ResultSet set = null;
		try {
			Statement st = con.createStatement();
			String sql = "Select * from  professor where email='" + bo.getEmail() + "' and password='"
					+ bo.getPassword() + "' ";
			// System.out.println(sql);
			set = st.executeQuery(sql);
			while (set.next()) {

				bo.setRegId(set.getInt(1));
				bo.setUserType(set.getString(6));
				bo.setUserName(set.getString(2));
				bo.setEmail(set.getString(4));
				bo.setResponse("Login successfuly");

			}
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}

			return null;
		}
		return bo;
	}

	@Override
	public QuizQuestion updateQuestion(QuizQuestion updatebo) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		/* QuizQuestion bo = new QuizQuestion(); */
		PreparedStatement ps = con.prepareStatement("update questions set question=? where question_id=?");
		ps.setString(1, updatebo.getQuestion());
		ps.setInt(2, updatebo.getQuesId());
		ps.executeUpdate();
		if (updatebo.getComplexity() != 0) {
			PreparedStatement ps1 = con
					.prepareStatement("update question_competancy set complecity=? where question_id=?");
			ps1.setFloat(1, updatebo.getComplexity());
			ps1.setInt(2, updatebo.getQuesId());
			ps1.executeUpdate();
		}
		QuizQuestion bo = null;
		Statement st = con.createStatement();
		String sql = "Select * from  answer where question_id='" + updatebo.getQuesId() + "' ";

		ResultSet rs = st.executeQuery(sql);
		String[] str = updatebo.getQuestionOptions();
		int i = 0;
		int value = 1;
		int index = 0;
		int length = 1;
		while (rs.next()) {
			String answer = str[i];
			PreparedStatement ps2 = con.prepareStatement(
					"update answer set answer=?,correctanswer_flag=? where question_id=? and answer_id=?");
			boolean flag = false;
			if (updatebo.getQuestionType().equals("options")) {
				if (value == Integer.parseInt(updatebo.getCorrectAnswer().replaceAll("<br>", ""))) {
					flag = true;
				}
				value++;
			} else {
				String cAns = updatebo.getCorrectAnswer();
				String[] cAnswer = cAns.split(",");

				if (length <= cAnswer.length) {
					if (value == Integer.parseInt(cAnswer[index].replaceAll("<br>", ""))) {
						if (1 == Integer.parseInt(cAnswer[index].replaceAll("<br>", ""))) {
							flag = true;
						}
						if (2 == Integer.parseInt(cAnswer[index].replaceAll("<br>", ""))) {
							flag = true;
						}
						if (3 == Integer.parseInt(cAnswer[index].replaceAll("<br>", ""))) {
							flag = true;
						}
						if (4 == Integer.parseInt(cAnswer[index].replaceAll("<br>", ""))) {
							flag = true;
						}
						index++;
						length++;
					}
					value++;
				}
			}
			ps2.setString(1, answer);
			ps2.setBoolean(2, flag);
			ps2.setInt(3, updatebo.getQuesId());
			ps2.setInt(4, rs.getInt("answer_id"));
			ps2.executeUpdate();
			i++;

		}
		return updatebo;
	}

	@Override
	public UserBO isExistEmailAddress(String emailAddress) throws Exception {

		UserBO bo = new UserBO();
		Connection con = DatabaseConnectionFactory.createConnection();
		ResultSet set = null;
		try {
			Statement st = con.createStatement();
			/*
			 * String sql = "Select * from  user where email='" + emailAddress +"' ";
			 */

			String sql = "Select * from  user where email='" + emailAddress + "' ";
			// System.out.println(sql);
			set = st.executeQuery(sql);
			while (set.next()) {

				bo.setUserId(set.getInt(1));
				bo.setUserName(set.getString(2));
				bo.setPassword(set.getString(3));
				bo.setEmail(set.getString(4));
				bo.setUserType(set.getString(6));
			}
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}

			return null;
		}
		return bo;
	}

	@Override
	public UserBO changePassword(UserBO userBO) throws Exception {

		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update user set password=? where user_id=? ");
			ps.setInt(2, userBO.getUserId());
			ps.setString(1, userBO.getPassword());

			int i = ps.executeUpdate();
			if (i > 0) {
				userBO.setErrorMessage("Candidate Reset Password Successfully!!");
			}
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}

			return null;
		}
		return userBO;
	}

	@Override
	public int getCandidateCount() {

		int candidateCount = 0;
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmtRef = con.createStatement();
			String candidateCountQuery = "select count(user_id)from user where user_type='Candidate'";
			ResultSet rsRef = stmtRef.executeQuery(candidateCountQuery);
			while (rsRef.next()) {
				candidateCount = rsRef.getInt(1);
			}
			if (0 < candidateCount) {
				return candidateCount;
			}
		} catch (Exception e) {

			// System.out.println(e);
		}
		return 0;
	}

	@Override
	public int getCandidateCount(UserBO bo) {
		int candidateCount = 0;
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmtRef = con.createStatement();
			String candidateCountQuery = "select count(user_id)from user where user_type='Candidate'and createdBy='"
					+ bo.getUserId() + "'";
			ResultSet rsRef = stmtRef.executeQuery(candidateCountQuery);
			while (rsRef.next()) {
				candidateCount = rsRef.getInt(1);
			}
			if (0 < candidateCount) {
				return candidateCount;
			}
		} catch (Exception e) {

			// System.out.println(e);
		}
		return 0;
	}

// for company details hit on the user table 
	@Override
	public void companyRegistration(RegistrationBO user) {
		Connection con = DatabaseConnectionFactory.createConnection();

		try {
			if (!user.getUserName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()) {

				PreparedStatement ps = con.prepareStatement("INSERT INTO user(user_name, password,email,phone_no,"
						+ "user_type,isDelete) values (?,?,?,?,?,?)");
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setLong(4, user.getPhoneNumber());
				ps.setString(5, user.getUserType());
				ps.setLong(6, user.getCreatedBy());
				ps.setBoolean(6, user.getIsDelete());
				int i = ps.executeUpdate();

				/*
				 * Statement st = con.createStatement(); String sql =
				 * "INSERT INTO user(user_name, password,email,phone_no,user_type,isDelete) values ('"
				 * + user.getUserName() + "','" + user.getPassword() + "','" + user.getEmail() +
				 * "','" + user.getPhoneNumber() + "','" + user.getUserType() +
				 * "','"+user.getIsDelete()+"')"; //System.out.println(sql); int i =
				 * st.executeUpdate(sql);
				 */
				if (i != 0) {
					user.setId(i);
					user.setUserName(user.getUserName());
					user.setResponse("Company registration Successfully");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}

	}

	@Override
	public UserBO adminDeleteCandidate(UserBO userBO) throws Exception {
		int updateCount = 0;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			if (null != userBO && userBO.getUserId() > 0) {
				PreparedStatement updatePs = con.prepareStatement("update user set isDelete=? where user_id=? ");
				updatePs.setBoolean(1, userBO.getIsDelete());
				updatePs.setInt(2, userBO.getUserId());
				updateCount = updatePs.executeUpdate();
				if (updateCount > 0) {
					userBO.setErrorMessage("Candidate Deleted Successfully!!");
				} else {
					userBO.setErrorMessage("Candidate Deleted Failed!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBO;
	}

	public static void main(String[] args) {
		List<Integer> listOne = Arrays.asList(2, 4, 1);
		List<Integer> listTwo = Arrays.asList(1, 2, 4);

		Collections.sort(listOne);
		Collections.sort(listTwo);
		System.out.println(listOne);
		System.out.println(listTwo);

		boolean isEqual = listOne.equals(listTwo);
		System.out.println(isEqual);

	}

}
