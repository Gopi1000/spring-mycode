package com.scube.mycognitiv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.utils.DatabaseConnectionFactory;
import com.scube.mycognitiv.utils.QuizQuestion;

public class QuestionAndExamDaoImpl implements QuestionAndExamDao {
	static int prioritySize;
	private static final Logger LOGGER = Logger.getLogger(QuestionAndExamDaoImpl.class);

	@Override
	public ArrayList<QuizQuestion> retrievequestions(int categoryid, List<Integer> subcategoryid, boolean fulltest,
			int selfrating, int totalquestion, int userid, QuizQuestion questiontype) throws SQLException {

		ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>();
		String questiontype1 = "options";
		// if (questiontype.getQuestionType().equals("options")) {
		ArrayList<Integer> questionIdList = selectquestions(categoryid, subcategoryid, fulltest, selfrating,
				totalquestion, userid);
		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion bo;

		if (null != questionIdList && !questionIdList.isEmpty() && questionIdList.size() > 0) {
			String tempQuery = "SELECT * FROM questions WHERE question_id in(";
			for (int i = 0; i < questionIdList.size(); i++) {
				if (i == 0) {
					tempQuery = tempQuery + "" + questionIdList.get(i);
				} else {
					tempQuery = tempQuery + "," + questionIdList.get(i);
				}
			}
			tempQuery = tempQuery + ")";
			// System.out.println(tempQuery);
			PreparedStatement ps = con.prepareStatement(tempQuery);
			ResultSet rs = ps.executeQuery();
			int questionNumber = 0;
			while (rs.next()) {
				questionNumber++;
				bo = new QuizQuestion();

				bo.setQuesId(rs.getInt(1));
				bo.setQuestion(rs.getString(2));
				bo.setQuestionType(rs.getString(3));
				PreparedStatement ps1 = con
						.prepareStatement("select *from question_category_subcategory where question_id=?");
				ps1.setInt(1, bo.getQuesId());
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {
					bo.setCategoryId(rs1.getInt(2));
					bo.setSubcategoryId(rs1.getInt(3));
					// System.out.println("qId::" + bo.getQuesId() + "cId::" + bo.getCategoryId() +
					// "sId::" + bo.getSubcategoryId());
				}

				PreparedStatement ps2 = con.prepareStatement("select * from answer where question_id=?");
				ps2.setInt(1, bo.getQuesId());
				ResultSet rs2 = ps2.executeQuery();
				String[] QuestionOptions = new String[4];
				int count = 0;
				int[] counts = new int[4];
				int index = 0;
				int crtans = 1;
				ArrayList<String> list = new ArrayList<>();
				ArrayList<Integer> idsList = new ArrayList<>();
				while (rs2.next()) {
					QuestionOptions[count] = rs2.getInt(1) + "@`" + rs2.getString(3);
					// System.out.println("flag::" + rs2.getBoolean(4));

					if (rs2.getBoolean(4)) {
						if (questiontype.getQuestionTypes().contains("options")
								&& bo.getQuestionType().equals("options")) {
							bo.setAnswerId(rs2.getInt(1));
							bo.setAnswer(rs2.getString(3));
						} else {
							System.out.println(rs2.getString(3));
							idsList.add(rs2.getInt(1));
							list.add(rs2.getString(3));
							bo.setAnswerIds(idsList);
							bo.setAnswers(list);
						}

						if (questiontype.getQuestionTypes().contains("options")) {
							bo.setCorrectOptionIndex(count);
						}
						if (questiontype.getQuestionTypes().contains("Multiple Select")) {
							counts[index] = crtans;
							bo.setCorrectOptionsIndex(counts);
							index++;
						}
					}
					count++;
					crtans++;
				}
				bo.setQuestionOptions(QuestionOptions);
				bo.setQuestionNumber(questionNumber);
				questionList.add(bo);
			}
		}
		return questionList;

		/*
		 * } else if (questiontype.getQuestionType().equals("text")) { ||||||| .r375 }
		 * else if (questiontype.getQuestionType().equals("text")) { ======= } else if
		 * (questiontype.getQuestionType().equals("Multiple Select")) {
		 * ArrayList<Integer> questionIdList = selectquestions(categoryid,
		 * subcategoryid, fulltest, selfrating, totalquestion, userid); Connection con =
		 * DatabaseConnectionFactory.createConnection(); QuizQuestion bo;
		 * 
		 * String tempQuery = "SELECT * FROM questions WHERE question_id in("; for (int
		 * i = 0; i < questionIdList.size(); i++) { if (i == 0) { tempQuery = tempQuery
		 * + "" + questionIdList.get(i); } else { tempQuery = tempQuery + "," +
		 * questionIdList.get(i); } } tempQuery = tempQuery + ")"; //
		 * System.out.println(tempQuery); PreparedStatement ps =
		 * con.prepareStatement(tempQuery); ResultSet rs = ps.executeQuery(); int
		 * questionNumber = 0; while (rs.next()) { questionNumber++; bo = new
		 * QuizQuestion();
		 * 
		 * bo.setQuesId(rs.getInt(1)); bo.setQuestion(rs.getString(2));
		 * bo.setQuestionType(rs.getString(3)); PreparedStatement ps1 = con
		 * .prepareStatement("select *from question_category_subcategory where question_id=?"
		 * ); ps1.setInt(1, bo.getQuesId()); ResultSet rs1 = ps1.executeQuery(); while
		 * (rs1.next()) { bo.setCategoryId(rs1.getInt(2));
		 * bo.setSubcategoryId(rs1.getInt(3)); // System.out.println("qId::" +
		 * bo.getQuesId() + "cId::" + bo.getCategoryId() + // "sId::" +
		 * bo.getSubcategoryId()); }
		 * 
		 * PreparedStatement ps2 =
		 * con.prepareStatement("select * from answer where question_id=?");
		 * ps2.setInt(1, bo.getQuesId()); ResultSet rs2 = ps2.executeQuery(); String[]
		 * QuestionOptions = new String[4]; int count = 0; while (rs2.next()) {
		 * QuestionOptions[count] = rs2.getInt(1) + "@`" + rs2.getString(3); //
		 * System.out.println("flag::" + rs2.getBoolean(4)); if (rs2.getBoolean(4)) {
		 * bo.setAnswerId(rs2.getInt(1)); bo.setAnswer(rs2.getString(3));
		 * bo.setCorrectOptionIndex(count); } count++; }
		 * bo.setQuestionOptions(QuestionOptions); bo.setQuestionNumber(questionNumber);
		 * questionList.add(bo); }
		 * 
		 * return questionList; } else if
		 * (questiontype.getQuestionType().equals("text")) { >>>>>>> .r376
		 * 
		 * Connection con = DatabaseConnectionFactory.createConnection(); QuizQuestion
		 * bo; String subCatListString = subcategoryid.toString(); subCatListString =
		 * subCatListString.replace('[', '('); subCatListString =
		 * subCatListString.replace(']', ')'); String sql =
		 * "select * from questions q join question_category_subcategory qtc on q.question_id=qtc.question_id where category_id="
		 * + categoryid + " and subcategory_id in" + subCatListString; Statement stmt =
		 * con.createStatement(); ResultSet rs = stmt.executeQuery(sql); int
		 * questionNumber = 0; int quesId = 0; while (rs.next()) { questionNumber++; bo
		 * = new QuizQuestion();
		 * 
		 * bo.setQuesId(rs.getInt(1)); bo.setQuestion(rs.getString(2)); quesId =
		 * bo.getQuesId(); bo.setQuestionType(rs.getString(3)); /* String Question =
		 * rs.getInt(1) + "@`" + rs.getString(2);
		 * 
		 * bo.setQuestion(Question);
		 */
		/*
		 * ps = con.prepareStatement("select * from answer where question_id=?");
		 * ps.setInt(1, bo.getQuesId());
		 * 
		 * ResultSet rs1 = ps.executeQuery(); String[] QuestionOptions = new String[4];
		 * int count = 0; while (rs1.next()) {
		 * 
		 * QuestionOptions[count] = rs1.getString(3);
		 * 
		 * bo.setFlag(rs1.getBoolean(4)); if (bo.getFlag() == true) {
		 * bo.setAnswer(QuestionOptions[count]);
		 * 
		 * } count++;
		 * 
		 * } bo.setQuestionOptions(QuestionOptions);
		 */
		/*
		 * ps=con.prepareStatement( "select * from answers where question_id=?");
		 * ps.setInt(1,bo.getQuesId());
		 * 
		 * rs1=ps.executeQuery(); while(rs1.next()){ bo.setAnswerId(rs1.getInt(1));
		 * bo.setAnswer(rs1.getString(3)); bo.setCorrectOptionIndex(rs1.getInt(4));
		 * 
		 * 
		 * }
		 */

		// bo.setQuestionNumber(questionNumber);
		// questionList.add(bo);
		// quesId++;
		// System.out.println("quesId++" + quesId++);
		// }*/

		// System.out.println("size:::" + questionList.size());

	}
	// return questionList;

	// }

	@Override
	public String getCategoryAndSubCategoryName(int categoryId, List<Integer> subcategoryId) {
		SubcategoryBO bo = null;
		Connection con = DatabaseConnectionFactory.createConnection();
		String values = "";
		try {
			String tempQuery = "SELECT * FROM subcategory WHERE subcategory_id in(";
			for (int i = 0; i < subcategoryId.size(); i++) {
				if (i == 0) {
					tempQuery = tempQuery + "" + subcategoryId.get(i);
				} else {
					tempQuery = tempQuery + "," + subcategoryId.get(i);
				}
			}
			tempQuery = tempQuery + ")";
			// System.out.println(tempQuery);
			PreparedStatement ps = con.prepareStatement(tempQuery);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				bo = new SubcategoryBO();
				bo.setSubcategoryId(set.getInt(1));
				bo.setCategory(set.getString(2));

				if (subcategoryId.size() > 0) {
					PreparedStatement ps1 = con
							.prepareStatement("select subcategory from subcategory where subcategory_id=?");
					ps1.setInt(1, bo.getSubcategoryId());
					ResultSet set1 = ps1.executeQuery();
					while (set1.next()) {
						values = bo.getCategory() + " - " + set1.getString(1);
					}
				}
			}
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
		}
		return values;
	}

	private ArrayList<Integer> selectquestions(int categoryid, List<Integer> subcategoryid, boolean fulltest,
			int selfrating, int totalquestion, int userid) {
		String userlevel = null;
		String selfratedUserLevel = null;
		String prepRating = null;
		float learningPower = 0;
		if (!fulltest && subcategoryid.size() == 1) {
			learningPower = computeLearningPower(categoryid, subcategoryid.get(0), userid);
		} else if (!fulltest && subcategoryid.size() > 1) {
			subcategoryid = OrderByPriority(categoryid, subcategoryid);
			for (int i = 0; i < subcategoryid.size(); i++) {
				learningPower += computeLearningPower(categoryid, subcategoryid.get(0), userid);
			}
		} else {
			learningPower = computeLearningPower(categoryid, subcategoryid.get(0), userid);
			subcategoryid = selectallSidsBySubcategory(categoryid, subcategoryid.get(0));
		}
		if (learningPower <= 35.0) {
			userlevel = "easy";
		} else if (learningPower > 35.0 && learningPower < 70.0) {
			userlevel = "medium";
		} else if (learningPower >= 70.0) {
			userlevel = "difficult";
		}
		if (!(selfrating == 0)) {
			if (selfrating <= 3) {
				selfratedUserLevel = "easy";
			} else if (selfrating > 3 && selfrating <= 7) {
				selfratedUserLevel = "medium";
			} else if (selfrating > 7) {
				selfratedUserLevel = "difficult";
			}
			if (userlevel.equalsIgnoreCase(selfratedUserLevel)) {
				prepRating = selfratedUserLevel;
			} else {
				if (userlevel.equalsIgnoreCase("easy") && selfratedUserLevel.equalsIgnoreCase("medium")) {
					prepRating = "easy";
				}
				if (userlevel.equalsIgnoreCase("easy") && selfratedUserLevel.equalsIgnoreCase("difficult")) {
					prepRating = "medium";
				}
				if (userlevel.equalsIgnoreCase("medium") && selfratedUserLevel.equalsIgnoreCase("easy")) {
					prepRating = "medium";
				}
				if (userlevel.equalsIgnoreCase("medium") && selfratedUserLevel.equalsIgnoreCase("difficult")) {
					prepRating = "difficult";
				}
				if (userlevel.equalsIgnoreCase("difficult") && selfratedUserLevel.equalsIgnoreCase("medium")) {
					prepRating = "difficult";
				}
				if (userlevel.equalsIgnoreCase("difficult") && selfratedUserLevel.equalsIgnoreCase("easy")) {
					prepRating = "medium";
				}
			}
		} else {
			prepRating = userlevel;
		}
		return problemsolution(categoryid, subcategoryid, totalquestion, prepRating, userid);
	}

	private ArrayList<Integer> selectallSidsBySubcategory(int categoryid, int subcategoryid) {
		ArrayList<Integer> subcategory = new ArrayList<Integer>();
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			PreparedStatement stmt = con.prepareStatement(
					"select distinct(subcategory.subcategory_id) from question_category_subcategory,subcategory where question_category_subcategory.category_id=? order by subcategory.priority");
			stmt.setInt(1, categoryid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				subcategory.add(rs.getInt("subcategory_id"));
			}
			con.close();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return subcategory;
	}

	private ArrayList<Integer> OrderByPriority(int categoryid, List<Integer> subcategoryid) {

		String sids = "";
		for (int i = 0; i < subcategoryid.size() - 1; i++) {
			sids = sids + subcategoryid.get(i) + ",";
		}
		sids += subcategoryid.get(subcategoryid.size() - 1);
		// System.out.println(sids);
		ArrayList<Integer> subcategory = new ArrayList<Integer>();
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			PreparedStatement stmt = con.prepareStatement(
					"select subcategory.subcategory_id  from  subcategory where  subcategory_id in(?) order by priority");
			stmt.setString(1, sids);
			ResultSet rs = stmt.executeQuery();
			// System.out.println("length:::::" + rs.getFetchSize());
			while (rs.next()) {
				subcategory.add(rs.getInt("subcategory_id"));
			}
			con.close();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return subcategory;
	}

	private float computeLearningPower(int categoryid, int subcategoryid, int userid) {

		float power = 0;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			PreparedStatement stmt = con.prepareStatement(
					"SELECT progress FROM candidate_competancy c where user_id=? and category_id=? and sub_category_id=?");
			stmt.setInt(1, userid);
			stmt.setInt(2, categoryid);
			stmt.setInt(3, subcategoryid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				power = rs.getFloat("progress");
			}
			// power=35;
			con.close();
		} catch (Exception e) {
			// System.out.println(e);
		}
		return power;
	}

	private ArrayList<Integer> problemsolution(int categoryid, List<Integer> subcategory, int totalquestion,
			String preprating, int userid) {
		ArrayList<Integer> questionlist = new ArrayList<Integer>();
		int subcategorySize = subcategory.size();
		int roundrobinSize = totalquestion / subcategorySize;
		prioritySize = totalquestion % subcategorySize;
		Map<Integer, Integer> selectionlist = new LinkedHashMap<Integer, Integer>();
		for (Integer values : subcategory) {
			if (prioritySize > 0) {
				selectionlist.put(values, roundrobinSize + 1);
			} else {
				selectionlist.put(values, roundrobinSize);
			}
			prioritySize--;
		}
		int easy_bag = 0, medium_bag = 0, difficult_bag = 0;
		if (preprating.equalsIgnoreCase("easy")) {
			easy_bag = (int) Math.floor((double) (totalquestion * 100) / 100);
			//easy_bag = (int) Math.floor((double) (totalquestion * 70) / 100); this line commented to pull all the question set in Test creation screen.
			medium_bag = (int) Math.ceil((double) (totalquestion * 30) / 100);

		} else if (preprating.equalsIgnoreCase("medium")) {
			easy_bag = (totalquestion * 20) / 100;
			difficult_bag = (totalquestion * 20) / 100;
			medium_bag = (totalquestion * 60) / 100;

		} else {
			difficult_bag = (int) Math.floor((double) (totalquestion * 70) / 100);
			medium_bag = (int) Math.ceil((double) (totalquestion * 30) / 100);
		}
		Map<Integer, Integer> easy_map = new LinkedHashMap<Integer, Integer>();
		Map<Integer, Integer> medium_map = new LinkedHashMap<Integer, Integer>();
		Map<Integer, Integer> difficult_map = new LinkedHashMap<Integer, Integer>();
		for (Integer string : subcategory) {
			easy_map.put(string, 0);
			difficult_map.put(string, 0);
			medium_map.put(string, 0);
		}
		int variable = 0;
		int easyvariable = 0;
		int mediumvariable = 0;
		int difficultvariable = 0;
		int value = 0;
		while (variable != totalquestion) {

			for (Map.Entry<Integer, Integer> entry : selectionlist.entrySet()) {
				value = entry.getValue();
				if ((easyvariable != easy_bag) && (value != 0)) {
					easy_map.put(entry.getKey(), easy_map.get(entry.getKey()) + 1);
					selectionlist.put(entry.getKey(), entry.getValue() - 1);
					easyvariable++;
					variable++;
				} else if ((mediumvariable != medium_bag) && (value != 0)) {
					medium_map.put(entry.getKey(), medium_map.get(entry.getKey()) + 1);
					selectionlist.put(entry.getKey(), entry.getValue() - 1);
					mediumvariable++;
					variable++;
				} else if ((difficultvariable != difficult_bag) && (value != 0)) {
					difficult_map.put(entry.getKey(), difficult_map.get(entry.getKey()) + 1);
					selectionlist.put(entry.getKey(), entry.getValue() - 1);
					difficultvariable++;
					variable++;
				}
			}
		}
		if (preprating.equalsIgnoreCase("easy")) {
			questionlist.addAll(SelectQuestion("easy", easy_map, userid, categoryid));
			questionlist.addAll(SelectQuestion("medium", medium_map, userid, categoryid));
		} else if (preprating.equalsIgnoreCase("medium")) {
			questionlist.addAll(SelectQuestion("easy", easy_map, userid, categoryid));
			questionlist.addAll(SelectQuestion("medium", medium_map, userid, categoryid));
			questionlist.addAll(SelectQuestion("difficult", difficult_map, userid, categoryid));
		} else {
			questionlist.addAll(SelectQuestion("medium", medium_map, userid, categoryid));
			questionlist.addAll(SelectQuestion("difficult", difficult_map, userid, categoryid));
		}
		return questionlist;
	}

	private ArrayList<Integer> SelectQuestion(String type, Map<Integer, Integer> ratiomap, int userid, int categoryid) {
		ArrayList<Integer> questionList = new ArrayList<Integer>();
		int from;
		int to;
		if (type.equalsIgnoreCase("easy")) {
			from = 0;
			to = 35;
		} else if (type.equalsIgnoreCase("medium")) {
			from = 35;
			to = 70;
		} else {
			from = 70;
			to = 100;
		}
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			for (Map.Entry<Integer, Integer> entry : ratiomap.entrySet()) {

				PreparedStatement stmt = con.prepareStatement(
						"SELECT distinct(question_category_subcategory.question_id) FROM question_competancy,question_category_subcategory where question_competancy.complecity between ? and ? and question_category_subcategory.category_id=? and question_category_subcategory.subcategory_id=? and question_competancy.question_id=question_category_subcategory.question_id and question_competancy.question_id  in(select question_id from result where user_id=? ) ORDER BY RAND() LIMIT ? ;");
				stmt.setInt(1, from);
				stmt.setInt(2, to);
				stmt.setInt(3, categoryid);
				stmt.setInt(4, entry.getKey());
				stmt.setInt(5, userid);
				// stmt.setString(6, "Wrong");
				// stmt.setBoolean(7, true);
				// stmt.setInt(8, entry.getValue());
				stmt.setInt(6, entry.getValue());

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					questionList.add(rs.getInt("question_id"));

				}
				PreparedStatement stmt1 = con.prepareStatement(
						"SELECT distinct(question_category_subcategory.question_id) FROM question_competancy,question_category_subcategory where question_competancy.complecity between ? and ? and question_category_subcategory.category_id=? and question_category_subcategory.subcategory_id=? and question_competancy.question_id=question_category_subcategory.question_id and question_competancy.question_id not in(select question_id from result where user_id=? ) ORDER BY RAND() LIMIT ? ;");
				stmt1.setInt(1, from);
				stmt1.setInt(2, to);
				stmt1.setInt(3, categoryid);
				stmt1.setInt(4, entry.getKey());
				stmt1.setInt(5, userid);
				stmt1.setInt(6, entry.getValue());
				ResultSet rs1 = stmt1.executeQuery();
				while (rs1.next()) {
					questionList.add(rs1.getInt("question_id"));
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return questionList;
	}

	@Override
	public QuizQuestion insertanswer(List<QuizQuestion> questionList, int userid, int testId) throws SQLException {

//		int testIds = 0;
		String testName = null;
		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion answerbo = null;

		PreparedStatement ps2 = con.prepareStatement("select test_name from test as t where t.test_id=?");
		ps2.setInt(1, testId);
		ResultSet rset = ps2.executeQuery();
		while (rset.next()) {
			testName = rset.getString(1);
		}

		for (QuizQuestion textans : questionList) {
			if (null != textans && null != textans.getQuestionType() && textans.getQuestionType().equals("options")) {
				answerbo = new QuizQuestion();
				answerbo.setQuesId(textans.getQuesId());
				if (null != textans && null != textans.getQuestionType()
						&& textans.getQuestionType().equals("options")) {
					answerbo.setDescription(textans.getAnswer());

//			else if (null != textans && null != textans.getQuestionType()
//					&& textans.getQuestionType().equals("Multiple Select")) {
//				answerbo.setDescription(textans.getAnswers());
				} else {
					answerbo.setDescription(textans.getDescription());
				}
				PreparedStatement ps1 = con.prepareStatement(
						"INSERT INTO test_answer(question_id,answer,user_id,test_name,test_id) values (?,?,?,?,?)");
				ps1.setInt(1, answerbo.getQuesId());
				ps1.setString(2, answerbo.getDescription());
				ps1.setInt(3, userid);
				ps1.setString(4, testName);
				ps1.setInt(5, testId);

				int i = ps1.executeUpdate();
				if (i != 0) {
					answerbo.setErrorMessage("Answers Sucessfully Updated.");
				} else {
					answerbo.setErrorMessage("Answer Updated Failed.");
				}
				/*
				 * PreparedStatement ps1= con
				 * .prepareStatement("select c.category,s.subcategory from test_answer as t join category as c on t.category_id=c.category_id where user_id=?"
				 * ); ps.setInt(1, userid); ResultSet set=ps1.executeQuery(); while(set.next()){
				 * QuizQuestion bo = new QuizQuestion(); bo.setCategory(set.getString(1));
				 * bo.setSubcategory(set.getString(2)); }
				 */
			} else if (null != textans && null != textans.getQuestionType()
					&& textans.getQuestionType().equals("Multiple Select")) {

				for (String id : textans.getAnswers()) {
					answerbo = new QuizQuestion();
					answerbo.setQuesId(textans.getQuesId());
					if (null != textans && null != textans.getQuestionType()
							&& textans.getQuestionType().equals("Multiple Select")) {
						answerbo.setDescription(id);
//				 
					} else {
						answerbo.setDescription(textans.getDescription());
					}
					PreparedStatement ps1 = con.prepareStatement(
							"INSERT INTO test_answer(question_id,answer,user_id,test_name,test_id) values (?,?,?,?,?)");
					ps1.setInt(1, answerbo.getQuesId());
					ps1.setString(2, answerbo.getDescription());
					ps1.setInt(3, userid);
					ps1.setString(4, testName);
					ps1.setInt(5, testId);

					int i = ps1.executeUpdate();
					if (i != 0) {
						answerbo.setErrorMessage("Answers Sucessfully Updated.");
					} else {
						answerbo.setErrorMessage("Answer Updated Failed.");
					}
				}

			}
		}
		return answerbo;
	}

	@Override
	public ArrayList<ResultBO> retrieveteststatus(int userid) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		ArrayList<ResultBO> testtaken = new ArrayList<ResultBO>();

		PreparedStatement ps = con.prepareStatement("select distinct test_id from test_answer where user_id=? ");
		ps.setInt(1, userid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ResultBO resultBO = new ResultBO();
			resultBO.setTestId(rs.getInt(1));
			resultBO.setUserId(userid);
			PreparedStatement stmt = con.prepareStatement(
					"select distinct user_id,test_id,test_name from test_answer where test_id=? and user_id=?");
			stmt.setInt(1, resultBO.getTestId());
			stmt.setInt(2, resultBO.getUserId());
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {

				resultBO.setUserId(rs1.getInt(1));
				resultBO.setTestId(rs1.getInt(2));
				resultBO.setTestType(rs1.getString(3));

				PreparedStatement prstm = con.prepareStatement(
						"select distinct user_id,test_id,over_all_result from over_all_result where test_id=? and user_id=?");

				prstm.setInt(1, resultBO.getTestId());
				prstm.setInt(2, resultBO.getUserId());
				ResultSet rs2 = prstm.executeQuery();
				while (rs2.next()) {
					resultBO.setUserId(rs2.getInt(1));
					resultBO.setTestId(rs2.getInt(2));
					resultBO.setOverAllResult(rs2.getString(3));

//					PreparedStatement ps1 = con.prepareStatement("select user_name from user where user_id=? ");
//					ps1.setInt(1, resultBO.getUserId());
//					ResultSet rs3 = ps1.executeQuery();
//					while (rs3.next()) {
//						resultBO.setUserName(rs3.getString(1));
//
//					}
					testtaken.add(resultBO);

				}

			}
		}

		return testtaken;
	}

	@Override
	public ArrayList<ResultBO> retrievetakentest(int userId) throws SQLException {

		ArrayList<ResultBO> resultBOlist = new ArrayList<ResultBO>();

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
					resultBO.setTestId(resultBO.getTestId());
					resultBO.setUserId(userId);
					int quescount = resultBO.getTotalQuestion();

					resultBO.setQuestionCount(quescount);
					resultBOlist.add(resultBO);
				}

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
		return resultBOlist;
	}

	@Override
	public ArrayList<QuizQuestion> retrievetakentestbyId(int userId, int testId) throws SQLException {

		QuizQuestion resultbyIdbo = null;
		ArrayList<QuizQuestion> testList = new ArrayList<QuizQuestion>();
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps = con
				.prepareStatement("select question_id,user_selection from result where user_id=? and test_id=?");
		ps.setInt(1, userId);
		ps.setInt(2, testId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			resultbyIdbo = new QuizQuestion();
			resultbyIdbo.setUserId(userId);
			resultbyIdbo.setQuesId(rs.getInt(1));
			resultbyIdbo.setUserSelected(rs.getInt(2));
			PreparedStatement ps1 = con.prepareStatement("select question from questions where question_id=?");
			ps1.setInt(1, resultbyIdbo.getQuesId());
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				resultbyIdbo.setQuestion(rs1.getString(1));

				PreparedStatement ps2 = con.prepareStatement("select * from answer where question_id=?");
				ps2.setInt(1, resultbyIdbo.getQuesId());
				ResultSet rs2 = ps2.executeQuery();
				String[] QuestionOptions = new String[4];
				int count = 0;
				int correctcount = 0;
				int CorrectOptionIndex = 0;
				while (rs2.next()) {
					CorrectOptionIndex++;

					QuestionOptions[count] = rs2.getInt(1) + "@`" + rs2.getString(3);
					// System.out.println("flag::" + rs2.getBoolean(4));
					/* if (rs2.getBoolean(4)) { */
					resultbyIdbo.setAnswerId(rs2.getInt(1));
					resultbyIdbo.setAnswer(rs2.getString(3));
					resultbyIdbo.setFlag(rs2.getBoolean(4));
					if (resultbyIdbo.getFlag() == true) {
						correctcount++;
						int c = ((CorrectOptionIndex) - (correctcount) + (1));
						resultbyIdbo.setCorrectOptionIndex(c);
					}
					if (resultbyIdbo.getUserSelected() == resultbyIdbo.getAnswerId()) {
						resultbyIdbo.setUserSelected((count + 1));
					}

					count++;
				}
				resultbyIdbo.setQuestionOptions(QuestionOptions);

			}
			testList.add(resultbyIdbo);
		}

		return testList;
	}

	@Override
	public ArrayList<QuizQuestion> retrievemarkedquestions(int userid) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion bo = null;
		ArrayList<QuizQuestion> markedquestionList = new ArrayList<QuizQuestion>();
		PreparedStatement ps = con.prepareStatement("select * from marked_question where user_id=?");

		ps.setInt(1, userid);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			bo = new QuizQuestion();
			bo.setQuesId(rs.getInt(1));

			PreparedStatement ps1 = con.prepareStatement("select question from questions where question_id=?");
			ps1.setInt(1, bo.getQuesId());
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				bo.setQuestion(rs1.getString(1));
				/*
				 * PreparedStatement ps2 = con
				 * .prepareStatement("select user_selection from result where user_id=? and question_id=?"
				 * ); ps2.setInt(1, bo.getQuesId()); ResultSet rs2=ps2.executeQuery();
				 * while(rs2.next()){
				 * 
				 * }
				 */
				PreparedStatement ps3 = con.prepareStatement("select * from answer where question_id=?");
				ps3.setInt(1, bo.getQuesId());
				ResultSet rs3 = ps3.executeQuery();
				String[] QuestionOptions = new String[4];
				int count = 0;
				int correctcount = 0;
				int CorrectOptionIndex = 0;
				while (rs3.next()) {
					CorrectOptionIndex++;

					QuestionOptions[count] = rs3.getInt(1) + "@`" + rs3.getString(3);
					// System.out.println("flag::" + rs3.getBoolean(4));
					/* if (rs2.getBoolean(4)) { */
					bo.setAnswerId(rs3.getInt(1));
					bo.setAnswer(rs3.getString(3));
					bo.setFlag(rs3.getBoolean(4));
					if (bo.getFlag() == true) {
						correctcount++;
						int c = ((CorrectOptionIndex) - (correctcount) + (1));
						bo.setCorrectOptionIndex(c);
					}
					if (bo.getUserSelected() == bo.getAnswerId()) {
						bo.setUserSelected((count + 1));
					}

					count++;
				}
				bo.setQuestionOptions(QuestionOptions);
			}
			markedquestionList.add(bo);

		}
		return markedquestionList;
	}

	@Override
	public QuizQuestion addMarkedQuestion(QuizQuestion addbo) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion bo = new QuizQuestion();
		PreparedStatement ps = con.prepareStatement("select * from marked_question where question_id=? and user_id=?");
		ps.setInt(1, addbo.getQuesId());
		ps.setInt(2, addbo.getUserId());
		ResultSet set = ps.executeQuery();
		while (set.next()) {
			bo.setQuesId(set.getInt(1));
			bo.setUserId(set.getInt(2));
			if (addbo.getQuesId() == bo.getQuesId() && addbo.getUserId() == bo.getUserId()) {
				bo.setResponse("Question to be already Added ");
				return bo;
			}

		}
		if (addbo.getQuesId() != bo.getQuesId() && addbo.getUserId() != bo.getUserId()) {

			PreparedStatement ps1 = con
					.prepareStatement("insert into marked_question (question_id,user_id) values(?,?)");

			ps1.setInt(1, addbo.getQuesId());
			ps1.setInt(2, addbo.getUserId());
			int i = ps1.executeUpdate();
			while (i != 0) {
				addbo.setResponse("Question Added Successfully");
				return addbo;
			}
		}

		return addbo;

	}

	@Override
	public ArrayList<QuizQuestion> retrievetexttakentestbyId(int userId, int testId) throws SQLException {

		QuizQuestion resultbyIdbo = null;
		ArrayList<QuizQuestion> testList = new ArrayList<QuizQuestion>();
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps = con
				.prepareStatement("select question_id from test_answer where user_id=? and test_id=?");
		ps.setInt(1, userId);
		ps.setInt(2, testId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			resultbyIdbo = new QuizQuestion();
			resultbyIdbo.setUserId(userId);
			resultbyIdbo.setTestId(testId);
			resultbyIdbo.setQuesId(rs.getInt(1));
			PreparedStatement ps1 = con.prepareStatement("select question from questions where question_id=?");
			ps1.setInt(1, resultbyIdbo.getQuesId());
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				resultbyIdbo.setQuestion(rs1.getString(1));
				PreparedStatement ps2 = con.prepareStatement("select answer from test_answer where question_id=?");
				ps2.setInt(1, resultbyIdbo.getQuesId());
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					resultbyIdbo.setAnswer(rs2.getString(1));
				}
			}
			testList.add(resultbyIdbo);
		}

		return testList;

	}

}
