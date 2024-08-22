/**
 * 
 */
package com.scube.mycognitiv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.bo.GroupBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.utils.CommenFuncation;
import com.scube.mycognitiv.utils.DatabaseConnectionFactory;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * @author Administrator
 * 
 */
public class AdminDaoImpl implements AdminDao {

	CategoryBO cbo = null;
	SubcategoryBO sbo = null;
	private static final Logger LOGGER = Logger.getLogger(AdminDaoImpl.class);

	// convert date int db formate
	CommenFuncation fun = new CommenFuncation();

	/**
	 * This method used to insert the question details in the system
	 * 
	 * @param bo
	 * @return
	 */

	@Override
	public QuizQuestion createQuestions(QuizQuestion bo) {

		Connection con = DatabaseConnectionFactory.createConnection();

		try {

			PreparedStatement ps = con
					.prepareStatement("INSERT INTO questions(question,questiontype,question_flag) values (?,?,?)");
			ps.setString(1, bo.getQuestion());
			ps.setString(2, bo.getQuestionType());
			ps.setBoolean(3, false);
			int i = ps.executeUpdate();
			if (i == 1) {

				PreparedStatement ps1 = con.prepareStatement(
						"select question_id,questiontype from questions order by question_id desc limit 1 ");
				ResultSet list = ps1.executeQuery();
				while (list.next()) {

					bo.setQuestionType(list.getString("questiontype"));
					// System.out.println("type::::::" + bo.getQuestionType());
					bo.setQuesId(list.getInt("Question_id"));

				}
			}

			PreparedStatement ps_complexity = con
					.prepareStatement("insert into question_competancy(question_id,complecity) values (?,?)");
			ps_complexity.setInt(1, bo.getQuesId());
			ps_complexity.setFloat(2, bo.getComplexity());
			int status = ps_complexity.executeUpdate();
			if (bo.getQuestionType().equals("options")) {
				PreparedStatement ps2 = con
						.prepareStatement("insert into answer(question_id,answer,correctanswer_flag) values (?,?,?)");
				String[] tempOption = bo.getQuestionOptions();
				int value = 1;

				for (String option : bo.getQuestionOptions()) {
					boolean flag = false;
					if (value == Integer.parseInt(bo.getCorrectAnswer().replaceAll("<br>", ""))) {
						flag = true;
					}
					value++;

					ps2.setInt(1, bo.getQuesId());
					ps2.setString(2, option);
					ps2.setBoolean(3, flag);
					ps2.executeUpdate();
				}
			} else {
				PreparedStatement ps2 = con
						.prepareStatement("insert into answer(question_id,answer,correctanswer_flag) values (?,?,?)");
				String cAns = bo.getCorrectAnswer();
				String[] cAnswer = cAns.split(",");
				int value = 1;
				int index = 0;
				int length = 1;
				for (String option : bo.getQuestionOptions()) {
					boolean flag = false;
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
					ps2.setInt(1, bo.getQuesId());
					ps2.setString(2, option);
					ps2.setBoolean(3, flag);
					ps2.executeUpdate();
				}

			}
			/*
			 * if(bo.getQuestionType().equals("text")){ PreparedStatement ps1 = con
			 * .prepareStatement("insert into test_answer(question_id) values (?)");
			 * 
			 * ps1.setInt(1, bo.getQuesId());
			 * 
			 * ps1.executeUpdate(); }
			 */

			PreparedStatement ps3 = con.prepareStatement(
					"insert into question_category_subcategory(question_id,category_id,subcategory_id) values (?,?,?)");
			ps3.setInt(1, bo.getQuesId());
			ps3.setInt(2, bo.getCategoryId());
			ps3.setInt(3, bo.getSubcategoryId());

			int j = ps3.executeUpdate();
			if (j != 0) {

				bo.setResponse("Question Details upload Successfully");
			} else {
				bo.setErrorMessage("Question Details upload failed!please contact Admin");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		/*
		 * try { con.close(); } catch (SQLException se) { if (LOGGER.isDebugEnabled()) {
		 * LOGGER.debug(se.getMessage() + se); } }
		 */

		return bo;
	}

	@Override
	public QuizQuestion uploadQuestions(QuizQuestion bo) {
		Connection con = DatabaseConnectionFactory.createConnection();

		try {

			Statement st = con.createStatement();
			String sql = "INSERT INTO questions(question,answer,correct_answer,created_date) values ('"
					+ bo.getQuestion() + "','" + bo.getCorrectOption() + "','" + bo.getCorrectAnswer() + "','"
					+ fun.convertGMTtodbDate(new Date()) + "')";
			// System.out.println(sql);
			int i = st.executeUpdate(sql);
			if (i != 0) {
				bo.setQuesId(i);
				bo.setResponse("Question Details upload Successfully");
			} else {
				bo.setErrorMessage("Question Details upload failed!please contact Admin");
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

	@Override
	public CreateTestBO createTestName(CreateTestBO testbo) {
		Connection con = DatabaseConnectionFactory.createConnection();

		try {

			PreparedStatement ps = con.prepareStatement("insert into create_test(test_name) values (?)");
			ps.setString(1, testbo.getTestName());

			int i = ps.executeUpdate();
			if (i != 0) {

				testbo.setTestName(testbo.getTestName());
				testbo.setResponse("TestName Successfully Registered,  Please Upload The Questions.");
			} else {
				testbo.setErrorMessage("TestName Registeration Failed!");
			}

		} catch (Exception sqe) {
			sqe.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(sqe.getMessage() + sqe);
			}
		}

		try {
			con.close();
		} catch (SQLException se) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(se.getMessage() + se);
			}
		}
		return testbo;
	}

	/*
	 * @Override public List<CreateTestBO> retrieveTestName() {
	 * 
	 * List<CreateTestBO> testNameList = new ArrayList<CreateTestBO>();
	 * 
	 * Connection con = DatabaseConnectionFactory.createConnection();
	 * 
	 * try { PreparedStatement ps = con
	 * .prepareStatement("select * from create_test");
	 * 
	 * ResultSet set = ps.executeQuery(); while (set.next()) { CreateTestBO testbo =
	 * new CreateTestBO(); testbo.setTestId(set.getInt(1));
	 * testbo.setTestName(set.getString(2)); testNameList.add(testbo); } } catch
	 * (SQLException e) { e.printStackTrace(); } return testNameList;
	 * 
	 * }
	 */

	@Override
	public QuizQuestion retrievequestionbyId(int quesId) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion bo = null;

		ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>();
		PreparedStatement ps = con.prepareStatement("select * from questions where question_id=?");
		/* ps.setInt(1, testId); */
		ps.setInt(1, quesId);
		ResultSet rs = ps.executeQuery();
		int questionNumber = 0;
		while (rs.next()) {
			questionNumber++;
			bo = new QuizQuestion();

			bo.setQuesId(rs.getInt(1));
			bo.setQuestion(rs.getString(2));
			bo.setQuestionType(rs.getString(3));

			ps = con.prepareStatement("select * from answer where question_id=?");
			ps.setInt(1, bo.getQuesId());

			ResultSet rs1 = ps.executeQuery();
			String[] QuestionOptions = new String[4];
			int count = 0;
			while (rs1.next()) {

				QuestionOptions[count] = rs1.getString(3);
				bo.setFlag(rs1.getBoolean(4));
				if (bo.getQuestionType().equals("options")) {
					if (bo.getFlag() == true) {
						bo.setAnswer(QuestionOptions[count]);

					}
				} else {
					if (bo.getFlag() == true) {
						if (null != bo.getAnswer()) {
							bo.setAnswer(bo.getAnswer().replaceAll("<br>", "") + ","
									+ QuestionOptions[count].replaceAll("<br>", ""));
						} else {
							bo.setAnswer(QuestionOptions[count].replaceAll("<br>", ""));
						}

					}
				}
				count++;

			}
			bo.setQuestionOptions(QuestionOptions);

			ps = con.prepareStatement("select * from question_category_subcategory q where question_id=?");
			ps.setInt(1, bo.getQuesId());

			rs1 = ps.executeQuery();
			while (rs1.next()) {
				bo.setCategoryId(rs1.getInt(2));
				bo.setSubcategoryId(rs1.getInt(3));
			}

			ps = con.prepareStatement("select * from category  where category_id=?");
			ps.setInt(1, bo.getCategoryId());

			rs1 = ps.executeQuery();
			while (rs1.next()) {
				bo.setCategory(rs1.getString(2));

			}

			ps = con.prepareStatement("select * from subcategory  where subcategory_id=?");
			ps.setInt(1, bo.getSubcategoryId());

			rs1 = ps.executeQuery();
			while (rs1.next()) {
				bo.setSubcategory(rs1.getString(3));

			}

			ps = con.prepareStatement("select * from question_competancy  where question_id=?");
			ps.setInt(1, bo.getQuesId());

			rs1 = ps.executeQuery();
			while (rs1.next()) {
				bo.setComplexity(rs1.getFloat(5));
				bo.setTotalattempts(rs1.getLong(3));
			}

			bo.setQuestionNumber(questionNumber);

		}

		return bo;

	}

	@Override
	public QuizQuestion updateQuestion(QuizQuestion quizQuestion) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		/* QuizQuestion bo = new QuizQuestion(); */
		PreparedStatement ps = con.prepareStatement("update questions set question_flag=? where question_id=?");
		ps.setBoolean(1, true);
		ps.setInt(2, quizQuestion.getQuesId());
		ps.executeUpdate();
		if (quizQuestion.getComplexity() != 0) {
			PreparedStatement ps1 = con
					.prepareStatement("update question_competancy set complecity=? where question_id=?");
			ps1.setFloat(1, quizQuestion.getComplexity());
			ps1.setInt(2, quizQuestion.getQuesId());
			ps1.executeUpdate();
		}

		return quizQuestion;
	}

	@Override
	public QuizQuestion updateQuestionDetails(QuizQuestion updatebo) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		/* QuizQuestion updatebo=new QuizQuestion(); */
		if (updatebo.getUpdateType().equals("question")) {

			PreparedStatement ps = con.prepareStatement("update questions set question=? where question_id=?");
			ps.setString(1, updatebo.getQuestion());
			ps.setInt(2, updatebo.getQuesId());
			int i = ps.executeUpdate();
			while (i != 0) {
				updatebo.setResponse("Question Update Successfully");
				return updatebo;
			}

		} else if (updatebo.getUpdateType().equals("option")) {
			PreparedStatement ps = con.prepareStatement("update answer set answer=? where answer_id=?");
			ps.setString(1, updatebo.getOption());
			ps.setInt(2, updatebo.getOptionId());
			int j = ps.executeUpdate();
			while (j != 0) {
				updatebo.setResponse("Option Update Successfully");
				return updatebo;
			}

		} else {

			PreparedStatement ps = con.prepareStatement("update answer set correctanswer_flag=? where question_id=?");
			ps.setBoolean(1, false);
			ps.setInt(2, updatebo.getQuesId());
			int k = ps.executeUpdate();
			PreparedStatement ps1 = con.prepareStatement("update answer set correctanswer_flag=? where answer=?");
			ps1.setString(2, updatebo.getAnswer());
			ps1.setBoolean(1, true);
			int L = ps1.executeUpdate();
			while (L != 0) {
				updatebo.setResponse("Answer update Successfully");
				return updatebo;
			}

		}
		return updatebo;

	}

	@Override
	public List<CategoryBO> retrievecategory(CategoryBO categoryBO) throws SQLException {
		List<CategoryBO> categoryList = new ArrayList<CategoryBO>();

		Connection con = DatabaseConnectionFactory.createConnection();

		PreparedStatement ps1 = con.prepareStatement("select * from category where isDelete='false'");
		if (0 < categoryBO.getRecordsPerPage()) {
			ps1 = con.prepareStatement("SELECT * FROM category where isDelete='false' ORDER BY category_id LIMIT "
					+ categoryBO.getRecordsPerPage() + " OFFSET " + categoryBO.getRecordIndex() + "");
		}

		ResultSet set1 = ps1.executeQuery();
		while (set1.next()) {
			CategoryBO categorybo = new CategoryBO();
			categorybo.setCategoryId(set1.getInt(1));
			categorybo.setCategory(set1.getString(2));
			categorybo.setIsDelete(false);
			categoryList.add(categorybo);
		}

		return categoryList;
	}

	@Override
	public List<SubcategoryBO> retrievesubcategory(SubcategoryBO subCategoryBO) throws SQLException {
		List<SubcategoryBO> subcategoryList = new ArrayList<SubcategoryBO>();
		SubcategoryBO subcategorybo;
		Connection con = DatabaseConnectionFactory.createConnection();
		Statement stmt = con.createStatement();
		PreparedStatement ps1 = con.prepareStatement("select * from subcategory where isDelete='false'");
		if (null != subCategoryBO && subCategoryBO.getRecordsPerPage() > 0) {
			ps1 = con.prepareStatement("select * from subcategory where isDelete='false' ORDER BY subcategory_id LIMIT "
					+ subCategoryBO.getRecordsPerPage() + " OFFSET " + subCategoryBO.getRecordIndex() + "");
		}
		ResultSet set1 = ps1.executeQuery();
		int totalQuestionsCount = 0;
		while (set1.next()) {
			subcategorybo = new SubcategoryBO();
			subcategorybo.setSubcategoryId(set1.getInt(1));
			subcategorybo.setCategory(set1.getString(2));
			subcategorybo.setSubcategory(set1.getString(3));
			subcategorybo.setPriority(set1.getInt(4));

			ps1 = con.prepareStatement("select * from category where category=?");
			ps1.setString(1, subcategorybo.getCategory());
			ResultSet set2 = ps1.executeQuery();
			while (set2.next()) {
				subcategorybo.setCategoryId(set2.getInt(1));
				if (subcategorybo.getCategoryId() > 0 && subcategorybo.getSubcategoryId() > 0) {
					String questionQuery = "select count(q.question_id) from questions q where q.question_id in (select question_id from question_category_subcategory qtc where qtc.category_id="
							+ subcategorybo.getCategoryId() + " and qtc.subcategory_id="
							+ subcategorybo.getSubcategoryId() + ")";
					ResultSet rs = stmt.executeQuery(questionQuery);
					while (rs.next()) {
						totalQuestionsCount = rs.getInt(1);
					}
				}
			}
			subcategoryList.add(subcategorybo);
		}
		return subcategoryList;
	}

	@Override
	public ArrayList<QuizQuestion> retrievequestions(int categoryId, int subcategoryId, QuizQuestion quizObj)
			throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion bo;
		String sql = null;

		ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>();
		if (null != quizObj && 0 < quizObj.getRecordsPerPage()) {
			sql = "select * from questions q where q.question_id in (select question_id from question_category_subcategory qtc where qtc.category_id=? and qtc.subcategory_id=?) ORDER BY question_id LIMIT "
					+ quizObj.getRecordsPerPage() + " OFFSET " + quizObj.getRecordIndex() + "";
		} else {

			sql = "select * from questions q where q.question_id in (select question_id from question_category_subcategory qtc where qtc.category_id=? and qtc.subcategory_id=?)";
		}
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, categoryId);
		ps.setInt(2, subcategoryId);
		ResultSet rs = ps.executeQuery();

		long sNo = 0;
		if (0 < quizObj.getRecordIndex()) {
			sNo = quizObj.getRecordIndex();
		}

		while (rs.next()) {
			bo = new QuizQuestion();
			bo.setQuesId(rs.getInt(1));
			bo.setQuestion(rs.getString(2));
			bo.setsNo(++sNo);

			questionList.add(bo);
		}
		// System.out.println("size:::" + questionList.size());
		return questionList;

	}

	@Override
	public CategoryBO createCategory(CategoryBO bo) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();

		PreparedStatement ps = con.prepareStatement("select category from category where category=?");
		ps.setString(1, bo.getCategory());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			cbo = new CategoryBO();
			cbo.setCategory(rs.getString(1));
			if (cbo.getCategory().equals(bo.getCategory())) {
				cbo.setErrorMessage("category was already Registered");
				return cbo;
			}
		}

		if (null != bo.getCategory()) {

			PreparedStatement ps1 = con.prepareStatement("insert into category (category,isDelete)values(?,?)");
			ps1.setLong(1, bo.getCategoryId());
			ps1.setString(1, bo.getCategory());
			ps1.setBoolean(2, bo.getIsDelete());
			int i = ps1.executeUpdate();
			if (i != 0) {
				bo.setErrorMessage("Category Successfully Registered.");
			} else {
				bo.setErrorMessage("Category Registeration Failed!");
			}

			return bo;
		}
		return bo;
	}

	@Override
	public SubcategoryBO createSubCategory(SubcategoryBO bo) throws SQLException {

		int priority = 0;

		Connection con = DatabaseConnectionFactory.createConnection();

		PreparedStatement ps = con.prepareStatement("select subcategory from subcategory where subcategory=?");
		ps.setString(1, bo.getSubcategory());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			sbo = new SubcategoryBO();
			sbo.setSubcategory(rs.getString(1));
			if (sbo.getSubcategory().equals(bo.getSubcategory())) {
				sbo.setErrorMessage("subcategory was already Registered");
				return sbo;
			}
		}
		if (null == sbo) {
			
				PreparedStatement ps2 = con.prepareStatement(
						"insert into subcategory (category,subcategory,priority,isDelete)values(?,?,?,?)");

				ps2.setString(1, bo.getCategory());
				ps2.setString(2, bo.getSubcategory());
				ps2.setInt(3, priority);
				ps2.setBoolean(4, false);
				int j = ps2.executeUpdate();

				if (j != 0) {
					bo.setErrorMessage("SubCategory Successfully Registered.");
				} else {
					bo.setErrorMessage("SubCategory Registeration Failed!");
				}
			}			
		return bo;
	}

	@Override
	public ArrayList<SubcategoryBO> updatepriority(ArrayList<SubcategoryBO> updateList) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		for (SubcategoryBO bo1 : updateList) {
			PreparedStatement ps = con.prepareStatement("update subcategory set priority=? where subcategory=? ");
			ps.setString(2, bo1.getSubcategory());
			ps.setInt(1, bo1.getPriority());
			ps.executeUpdate();
		}
		return updateList;
	}

	@Override
	public CategoryBO retrievecategorybyId(int categoryId) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		CategoryBO bo = null;

		PreparedStatement ps = con.prepareStatement("select * from category where category_id=?");
		/* ps.setInt(1, testId); */
		ps.setInt(1, categoryId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			bo = new CategoryBO();
			bo.setCategoryId(rs.getInt(1));
			bo.setCategory(rs.getString(2));

		}
		return bo;

	}

	@Override
	public CategoryBO deletecategory(int categoryId) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		CategoryBO bo = new CategoryBO();
		PreparedStatement ps = con.prepareStatement("delete from category where category_id=?");
		ps.setInt(1, categoryId);
		int i = ps.executeUpdate();
		if (i == 1) {

			bo.setErrorMessage("Successfully Deleted. ");
		} else {
			bo.setErrorMessage("Deleted Failed.");
		}
		return bo;
	}

	@Override
	public CategoryBO updatecategory(CategoryBO updatebo) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		CategoryBO bo = null;
		PreparedStatement ps = con.prepareStatement(
				"update subcategory set category=? where category=(select category from category where category_id=?)");
		ps.setInt(2, updatebo.getCategoryId());
		ps.setString(1, updatebo.getCategory());
		int i = ps.executeUpdate();
		if (i == 1) {
			bo = new CategoryBO();
			bo.setCategoryId(updatebo.getCategoryId());
			bo.setCategory(updatebo.getCategory());
			bo.setErrorMessage("Successfully Updated.");
		}
		PreparedStatement ps1 = con.prepareStatement("update category set category=? where category_id=?");
		ps1.setInt(2, updatebo.getCategoryId());
		ps1.setString(1, updatebo.getCategory());
		int j = ps1.executeUpdate();
		if (j == 1) {
			bo = new CategoryBO();
			bo.setErrorMessage("Successfully Updated. ");
		}
		return bo;
	}

	@Override
	public QuizQuestion retrievequestiontype(int categoryid) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		QuizQuestion bo = null;
		PreparedStatement ps = con.prepareStatement(
				"select * from questions q join question_category_subcategory qtc on q.question_id=qtc.question_id where category_id=?");
		/* ps.setInt(1, testId); */
		ps.setInt(1, categoryid);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> list = new ArrayList<>();
		while (rs.next()) {

			bo = new QuizQuestion();
			bo.setQuestionType(rs.getString(3));
			list.add(rs.getString(3));
			// System.out.println("type::::::::" + bo.getQuestionType());
			bo.setQuestionTypes(list);

		}
		return bo;

	}

	@Override
	public ArrayList<QuizQuestion> retrieveanswer(int userid) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		ArrayList<QuizQuestion> answerList = new ArrayList<QuizQuestion>();
		QuizQuestion answerbo = null;

		PreparedStatement ps = con.prepareStatement(
				"select a.*,q.question from test_answer as a join questions as q on q.question_id=a.question_id where user_id=?");
		ps.setInt(1, userid);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			answerbo = new QuizQuestion();
			answerbo.setQuestion(rs.getString(5));
			answerbo.setDescription(rs.getString(3));

			answerList.add(answerbo);
		}
		return answerList;
	}

	@Override
	public ArrayList<SubcategoryBO> getsubcategory(int categoryId) throws SQLException {

		ArrayList<SubcategoryBO> subcategoryList = new ArrayList<SubcategoryBO>();
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps = con.prepareStatement("select category from category where category_id=?");
		ps.setInt(1, categoryId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			PreparedStatement ps1 = con
					.prepareStatement("select subcategory_id,subcategory,priority from subcategory where category=?");

			ps1.setString(1, rs.getString(1));
			ResultSet rs1 = ps1.executeQuery();
			SubcategoryBO bo = null;
			while (rs1.next()) {
				bo = new SubcategoryBO();
				bo.setSubcategoryId(rs1.getInt(1));
				bo.setSubcategory(rs1.getString(2));
				bo.setPriority(rs1.getInt(3));
				subcategoryList.add(bo);
			}

		}
		return subcategoryList;
	}

	@Override
	public CreateTestBO createTestAdmin(CreateTestBO createTestBO) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps1 = con.prepareStatement("insert into test values(?,?,?,?,?,?,?)");
		ps1.setInt(1, createTestBO.getTestId());
		ps1.setInt(2, createTestBO.getCategoryId());
		ps1.setString(3, createTestBO.getSubcategoryIdAsString());
		ps1.setInt(4, createTestBO.getSelfRate());
		ps1.setInt(5, createTestBO.getTotalQuestion());
		ps1.setBoolean(6, false);
		ps1.setString(7, createTestBO.getTestName());

		int i = ps1.executeUpdate();
		if (i != 0) {
			createTestBO.setErrorMessage("Test Successfully Registered.");
		} else {
			createTestBO.setErrorMessage("Test Registeration Failed!");
		}

		return createTestBO;

	}

	@Override
	public int getQuestionsCount(int category, int subcategory) {

		Connection con = DatabaseConnectionFactory.createConnection();
		int totalQuestionsCount = 0;
		try {
			Statement stmt = con.createStatement();
			String questionQuery = "select count(q.question_id) from questions q where q.question_id in (select question_id from question_category_subcategory qtc where qtc.category_id="
					+ category + " and qtc.subcategory_id=" + subcategory + ")";
			ResultSet rs = stmt.executeQuery(questionQuery);
			while (rs.next()) {
				totalQuestionsCount = rs.getInt(1);
			}
			if (0 < totalQuestionsCount) {
				return totalQuestionsCount;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<CreateTestBO> retrieveTest(CreateTestBO createTestBO) throws SQLException {
		List<CreateTestBO> createTestBOList = new ArrayList<CreateTestBO>();
		String query = null;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			if (null != createTestBO && createTestBO.getUserId() > 0) {

				if (null != createTestBO && null != createTestBO.getUserType()
						&& createTestBO.getUserType().equalsIgnoreCase("candidate")) {
					query = "SELECT * FROM assign_test LEFT JOIN test on assign_test.test_id=test.test_id where user_id= '"
							+ createTestBO.getUserId() + "' ";
				}
				if (null != createTestBO && null != createTestBO.getUserType()
						&& createTestBO.getUserType().equalsIgnoreCase("company")) {
					query = "SELECT * FROM assign_test LEFT JOIN test on assign_test.test_id=test.test_id where company_id= '"
							+ createTestBO.getUserId() + "' ";
				}
				// if (null != createTestBO && null != createTestBO.getUserType(){
				// query = "SELECT * FROM assign_test LEFT JOIN test on
				// assign_test.test_id=test.test_id";
				// }
				if (null != createTestBO && 0 < createTestBO.getUserId()) {
					query = "SELECT * FROM assign_test LEFT JOIN test on assign_test.test_id=test.test_id where user_id= '"
							+ createTestBO.getUserId() + "' ";
				} else {
					query = "SELECT * FROM assign_test LEFT JOIN test on assign_test.test_id=test.test_id";
				}
				PreparedStatement st = con.prepareStatement(query);
				ResultSet set = st.executeQuery();
				int sNo = 0;
				while (set.next()) {
					CreateTestBO bo = new CreateTestBO();
					bo.setTestId(set.getInt(2));
					int testId = set.getInt("test_id");
					int categoryId = set.getInt("category_id");
					String subCategoryId = set.getString("subcategory_id");
					List<Integer> subcatList = null;
					if (null != subCategoryId) {
						subcatList = getSubCategories(subCategoryId);
					}

					if (0 < testId) {
						Statement stmt = con.createStatement();
						String categoryQuery = "select * from test where test_id=" + testId + "";
						ResultSet rsRef = stmt.executeQuery(categoryQuery);
						while (rsRef.next()) {
							bo.setTestId(rsRef.getInt(1));
							bo.setTestName(rsRef.getString("test_name"));
						}
					}

					if (0 < categoryId) {
						Statement stmt = con.createStatement();
						String categoryQuery = "select * from category where category_id=" + categoryId + "";
						ResultSet rsRef = stmt.executeQuery(categoryQuery);
						while (rsRef.next()) {
							bo.setCategoryId(rsRef.getInt(1));
							bo.setCategory(rsRef.getString(2));
						}
					}
					if (null != subcatList && subcatList.size() > 0) {
						String subCatListString = subcatList.toString();
						subCatListString = subCatListString.replace('[', '(');
						subCatListString = subCatListString.replace(']', ')');
						Statement stmt = con.createStatement();
						String subcategoryRef = "select * from subcategory where subcategory_id in " + subCatListString;
						ResultSet rsRef = stmt.executeQuery(subcategoryRef);
						List<Integer> subList = new ArrayList<Integer>();
						while (rsRef.next()) {
							subList.add(rsRef.getInt(1));
							bo.setSubcategory(rsRef.getString(3));
						}
						bo.setSubcategoryId(subList);
					}
					bo.setSelfRate(set.getInt(4));
					bo.setTotalQuestion(set.getInt(5));
					sNo++;
					bo.setsNo(sNo);
					createTestBOList.add(bo);
				}
			} else {
				PreparedStatement ps1 = con.prepareStatement(
						"SELECT c.test_id,s.category_id,p.subcategory_id,c.self_rate,c.total_questions FROM test c LEFT JOIN category s ON c.category_id = s.category_id LEFT JOIN subcategory p ON c.subcategory_id = p.subcategory_id where c.isDelete='false';");
				if (null != createTestBO && 0 < createTestBO.getRecordsPerPage()) {
					ps1 = con.prepareStatement("SELECT * from test where isDelete='false' ORDER BY test_id LIMIT "
							+ createTestBO.getRecordsPerPage() + " OFFSET " + createTestBO.getRecordIndex() + "");
				}
				ResultSet set1 = ps1.executeQuery();
				int sNo = 0;
				while (set1.next()) {
					CreateTestBO bo = new CreateTestBO();
					bo.setTestId(set1.getInt(1));

					String categoryRef = set1.getString(2);
					String subCategoryRef = set1.getString(3);
					bo.setSelfRate(set1.getInt(4));
					bo.setTotalQuestion(set1.getInt(5));
					if (null != categoryRef) {
						int categoryId = Integer.parseInt(categoryRef);
						Statement stmt = con.createStatement();
						String categoryQuery = "select * from category where category_id=" + categoryId + "";
						ResultSet rsRef = stmt.executeQuery(categoryQuery);
						while (rsRef.next()) {
							bo.setCategoryId(rsRef.getInt(1));
							bo.setCategory(rsRef.getString(2));
						}
					}

					if (0 < bo.getTestId()) {
						Statement stmt = con.createStatement();
						String categoryQuery = "select * from test where test_id=" + bo.getTestId() + "";
						ResultSet rsRef = stmt.executeQuery(categoryQuery);
						while (rsRef.next()) {
							bo.setTestId(rsRef.getInt(1));
							bo.setTestName(rsRef.getString("test_name"));
						}
					}

					if (null != subCategoryRef) {
						List<Integer> subcatList = getSubCategories(subCategoryRef);
						String subCatListString = subcatList.toString();
						subCatListString = subCatListString.replace('[', '(');
						subCatListString = subCatListString.replace(']', ')');
						Statement stmt = con.createStatement();
						String subcategoryRef = "select * from subcategory where subcategory_id in " + subCatListString
								+ "";
						ResultSet rsRef = stmt.executeQuery(subcategoryRef);
						List<Integer> subList = new ArrayList<Integer>();
						while (rsRef.next()) {
							subList.add(rsRef.getInt(1));
							bo.setSubcategory(rsRef.getString(3));
						}
						bo.setSubcategoryId(subList);
					}
					// bo.setPriority(set1.getInt(4));
					sNo++;
					bo.setsNo(sNo);
					createTestBOList.add(bo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createTestBOList;
	}

	@Override
	public List<CreateTestBO> retrieveTestByTestId(int testId) throws SQLException {
		List<CreateTestBO> createTestBOList = new ArrayList<CreateTestBO>();
		String query = null;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();

			query = "SELECT * FROM test where test_id=" + testId;

			PreparedStatement st = con.prepareStatement(query);
			ResultSet set = st.executeQuery();
			while (set.next()) {
				CreateTestBO bo = new CreateTestBO();
				bo.setTestId(set.getInt(1));
				bo.setTestName(set.getString("test_name"));
				int categoryId = set.getInt("category_id");
				String subCategoryId = set.getString("subcategory_id");
				List<Integer> subcatList = null;
				if (null != subCategoryId) {
					subcatList = getSubCategories(subCategoryId);
				}

				if (0 < categoryId) {
					Statement stmt = con.createStatement();
					String categoryQuery = "select * from category where category_id=" + categoryId + "";
					ResultSet rsRef = stmt.executeQuery(categoryQuery);
					while (rsRef.next()) {
						bo.setCategoryId(rsRef.getInt(1));
						bo.setCategory(rsRef.getString(2));
					}
				}
				if (null != subcatList && subcatList.size() > 0) {
					String subCatListString = subcatList.toString();
					subCatListString = subCatListString.replace('[', '(');
					subCatListString = subCatListString.replace(']', ')');
					Statement stmt = con.createStatement();
					String subcategoryRef = "select * from subcategory where subcategory_id in " + subCatListString
							+ "";
					ResultSet rsRef = stmt.executeQuery(subcategoryRef);
					while (rsRef.next()) {
						List<Integer> subCatList = new ArrayList<Integer>();
						subCatList.add(rsRef.getInt(1));
						// bo.setSubcategory(rsRef.getString(3));
					}
					bo.setSubcategoryId(subcatList);
				}
				bo.setSelfRate(set.getInt(4));
				bo.setTotalQuestion(set.getInt(5));
				createTestBOList.add(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createTestBOList;
	}

	@Override
	public CreateTestBO assignTestAdmin(CreateTestBO createTestBO) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps1 = con.prepareStatement("insert into assign_test values(?,?,?,?,?)");
		ps1.setLong(1, createTestBO.getId());
		ps1.setInt(2, createTestBO.getTestId());
		ps1.setInt(3, createTestBO.getUserId());
		ps1.setBoolean(4, createTestBO.getIsDelete());
		ps1.setInt(5, createTestBO.getCompanyId());

		int i = ps1.executeUpdate();
		if (i != 0) {
			createTestBO.setErrorMessage("AssignTest Successfully.");
		} else {
			createTestBO.setErrorMessage("AssignTest Failed!");
		}
		return createTestBO;
	}

	@Override
	public List<CreateTestBO> retrieveCandidateTest(CreateTestBO createTestBO) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps1 = con.prepareStatement(
				"SELECT c.test_id,s.category,p.subcategory FROM test c LEFT JOIN category s ON c.category_id = s.category_id LEFT JOIN subcategory p ON c.subcategory_id = p.subcategory_id left join assign_test at on c.test_id=at.test_id where at.user_id='"
						+ createTestBO.getUserId() + "'");
		ResultSet set1 = ps1.executeQuery();
		List<CreateTestBO> List = new ArrayList<CreateTestBO>();
		int sno = 0;
		while (set1.next()) {
			CreateTestBO bo = new CreateTestBO();
			sno++;
			bo.setTestId(set1.getInt(1));
			bo.setCategory(set1.getString(2));
			bo.setSubcategory(set1.getString(3));
			bo.setsNo(sno);
			List.add(bo);
		}
		return List;
	}

	@Override
	public int getCategoryCount() {

		Connection con = DatabaseConnectionFactory.createConnection();
		int totalCategoryCount = 0;
		try {
			Statement stmt = con.createStatement();
			String questionQuery = "select count(category_id) from category where isDelete='false'";
			ResultSet rs = stmt.executeQuery(questionQuery);
			while (rs.next()) {
				totalCategoryCount = rs.getInt(1);
			}
			if (0 < totalCategoryCount) {
				return totalCategoryCount;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int getSubCategoryCount() {

		Connection con = DatabaseConnectionFactory.createConnection();
		int totalSubCategoryCount = 0;
		try {
			Statement stmt = con.createStatement();
			String questionQuery = "select count(subcategory_id) from subcategory";
			ResultSet rs = stmt.executeQuery(questionQuery);
			while (rs.next()) {
				totalSubCategoryCount = rs.getInt(1);
			}
			if (0 < totalSubCategoryCount) {
				return totalSubCategoryCount;
			}
		} catch (Exception e) {

			// System.out.println(e);
		}
		return 0;
	}

	@Override
	public SubcategoryBO getSubCategoryById(SubcategoryBO subCategoryBOObj) {

		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmt = con.createStatement();
			String subCategoryEditQuery = "select * from subcategory where subcategory_id="
					+ subCategoryBOObj.getSubcategoryId() + "";
			ResultSet resultsetRef = stmt.executeQuery(subCategoryEditQuery);
			SubcategoryBO subCategoryBO = new SubcategoryBO();
			while (resultsetRef.next()) {
				subCategoryBO.setSubcategoryId(resultsetRef.getInt(1));
				subCategoryBO.setCategory(resultsetRef.getString(2));
				subCategoryBO.setSubcategory(resultsetRef.getString(3));
				subCategoryBO.setPriority(resultsetRef.getInt(4));
			}
			if (null != subCategoryBO) {
				return subCategoryBO;
			}
		} catch (Exception e) {

			// System.out.println(e);
		}

		return null;
	}

	@Override
	public CategoryBO editCategory(int categoryId) throws SQLException {

		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmt = con.createStatement();
			String categoryEditQuery = "select * from category where category_id=" + categoryId + "";
			ResultSet resultsetRef = stmt.executeQuery(categoryEditQuery);
			CategoryBO categoryBO = new CategoryBO();
			while (resultsetRef.next()) {
				categoryBO.setCategoryId(resultsetRef.getInt(1));
				categoryBO.setCategory(resultsetRef.getString(2));

			}
			if (null != categoryBO) {
				return categoryBO;
			}
		} catch (Exception e) {

			// System.out.println(e);
		}

		return null;
	}

	@Override
	public boolean deleteCategory(CategoryBO bo) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmt = con.createStatement();
			String deleteCategoryQuery = "update category set isDelete=" + bo.getIsDelete() + " where category_id="
					+ bo.getCategoryId() + "";
			int deleteId = stmt.executeUpdate(deleteCategoryQuery);
			if (0 < deleteId) {
				return true;
			}

		} catch (Exception e) {
			// System.out.println(e);
		}

		return false;
	}

	@Override
	public int getTestCount(CreateTestBO createTestBO) {

		int totalCount = 0;
		Connection con = DatabaseConnectionFactory.createConnection();
		String questionQuery = null;
		try {
			Statement stmt = con.createStatement();
			if (null != createTestBO && createTestBO.getUserId() > 0) {
				questionQuery = "select count(test_id) from assign_test where user_id=" + createTestBO.getUserId()
						+ " and isDelete='false'";
			} else {
				questionQuery = "select count(test_id) from assign_test where isDelete='false'";
			}
			ResultSet rsRef = stmt.executeQuery(questionQuery);
			while (rsRef.next()) {
				totalCount = rsRef.getInt(1);
			}
			if (0 < totalCount) {
				return totalCount;
			}
		} catch (Exception e) {
			// System.out.println(e);
		}
		return 0;
	}

	@Override
	public boolean deleteTest(CreateTestBO createTestBO) {

		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmt = con.createStatement();
			String testDeleteQuery = "update test set isDelete=" + createTestBO.getIsDelete() + " where test_id="
					+ createTestBO.getTestId() + "";
			// System.out.println(testDeleteQuery);
			int deletedId = stmt.executeUpdate(testDeleteQuery);
			if (0 < deletedId) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	@Override
	public SubcategoryBO deleteSubCategory(SubcategoryBO subcateogryBO) throws Exception {

		Connection con = DatabaseConnectionFactory.createConnection();
		SubcategoryBO subcategoryBO = new SubcategoryBO();
		PreparedStatement ps = con.prepareStatement("update subcategory set isDelete=? where subcategory_id=?");
		ps.setBoolean(1, true);
		ps.setInt(2, subcateogryBO.getSubcategoryId());
		int i = ps.executeUpdate();
		if (i == 1) {
			subcategoryBO.setErrorMessage("Successfully Deleted. ");
		} else {
			subcategoryBO.setErrorMessage("Deleted Failed.");
		}
		return subcateogryBO;
	}

	@Override
	public SubcategoryBO updateSubcategory(SubcategoryBO bo) throws Exception {

		int updateCount = 0;
		try {
			Connection con = DatabaseConnectionFactory.createConnection();
			if (null != bo && bo.getSubcategoryId() > 0) {
				PreparedStatement updatePs = con
						.prepareStatement("update subcategory set category=?, subcategory=? where subcategory_id=? ");
				updatePs.setString(1, bo.getCategory());
				updatePs.setString(2, bo.getSubcategory());
				updatePs.setInt(3, bo.getSubcategoryId());
				updateCount = updatePs.executeUpdate();
				if (updateCount > 0) {
					bo.setErrorMessage("SubCategory Updated Successfully!!");
				} else {
					bo.setErrorMessage("SubCategory Updated Failed!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public CreateTestBO editTestByTestId(CreateTestBO createTestBO) {

		Connection con = DatabaseConnectionFactory.createConnection();
		CreateTestBO createTestBO1 = new CreateTestBO();
		try {
			Statement stmt = con.createStatement();
			String editQuery = "select * from test where test_id=" + createTestBO.getTestId() + "";
			ResultSet rsRef = stmt.executeQuery(editQuery);
			while (rsRef.next()) {
				createTestBO1.setTestId(rsRef.getInt(1));
				String categoryIdRef = rsRef.getString(2);
				String subCategoryRef = rsRef.getString("subcategory_id");
				List<Integer> subcatList = null;
				if (null != subCategoryRef) {
					subcatList = getSubCategories(subCategoryRef);
				}
				createTestBO1.setSelfRate(rsRef.getInt(4));
				createTestBO1.setTotalQuestion(rsRef.getInt(5));
				if (null != categoryIdRef) {
					int categoryId = Integer.parseInt(categoryIdRef);
					Statement stmtRef1 = con.createStatement();
					String categoryQuery = "select * from category where category_id=" + categoryId + "";
					ResultSet rs = stmtRef1.executeQuery(categoryQuery);
					while (rs.next()) {
						createTestBO1.setCategoryId(rs.getInt(1));
						createTestBO1.setCategory(rs.getString(2));
					}

				}
				if (null != subcatList && subcatList.size() > 0) {
					String subCatListString = subcatList.toString();
					subCatListString = subCatListString.replace('[', '(');
					subCatListString = subCatListString.replace(']', ')');
					Statement stmtRef1 = con.createStatement();
					String subCategoryQuery = "select * from subcategory where subcategory_id in " + subCatListString
							+ "";
					ResultSet rs = stmtRef1.executeQuery(subCategoryQuery);
					while (rs.next()) {
						createTestBO1.getSubcategoryId().add(rs.getInt(1));
						// createTestBO1.setSubcategory(rs.getString(3));
					}
				}
			}
			if (null != createTestBO1) {
				return createTestBO1;
			}
		} catch (Exception e) {

			// System.out.println(e);
		}
		return null;
	}

	@Override
	public CreateTestBO updateTest(CreateTestBO createTestBO) {

		Connection con = DatabaseConnectionFactory.createConnection();
		try {
			Statement stmt = con.createStatement();
			String updateTestQuery = "update test set category_id=" + createTestBO.getCategoryId() + ",subcategory_id="
					+ createTestBO.getSubcategoryId() + ",self_rate=" + createTestBO.getSelfRate() + ",total_questions="
					+ createTestBO.getTotalQuestion() + " where test_id=" + createTestBO.getTestId() + "";
			// System.out.println(updateTestQuery);
			if (null != updateTestQuery) {
				int testCount = stmt.executeUpdate(updateTestQuery);
				if (0 < testCount) {
					if (null != createTestBO) {
						return createTestBO;
					}
				}
			}
		} catch (Exception e) {

			// System.out.println(e);
		}
		return null;
	}

	@Override
	public ArrayList<UserBO> retrieveTakeTestCandidate(UserBO bo) throws Exception {
		ArrayList<UserBO> list = new ArrayList<>();
		Connection con = DatabaseConnectionFactory.createConnection();
		String userQuery;
		try {
			Statement stmt = con.createStatement();
			String takeTestQuery = "SELECT DISTINCT user_id from test_answer";
			ResultSet rsRef = stmt.executeQuery(takeTestQuery);
			while (rsRef.next()) {
				int user_id = rsRef.getInt(1);
				Statement stmtref = con.createStatement();
				if (0 != bo.getCreatedBy()) {
					userQuery = "SELECT * from user where user_id=" + user_id + " and createdBy=" + bo.getCreatedBy()
							+ "";
				} else {
					userQuery = "SELECT * from user where user_id=" + user_id + "";
				}
				ResultSet rsref = stmtref.executeQuery(userQuery);
				while (rsref.next()) {
					UserBO userbo = new UserBO();
					userbo.setUserId(rsref.getInt(1));
					userbo.setUserName(rsref.getString(2));
					if (null != userbo) {
						list.add(userbo);
						if (null != list && 0 < list.size() && !list.isEmpty()) {
							return list;
						}
					}
				}
			}

		} catch (Exception e) {

			// System.out.println(e);
		}
		return null;
	}

	@Override
	public ArrayList<UserBO> retrieveAllCandidate(UserBO bo) throws Exception {
		ArrayList<UserBO> list = new ArrayList<>();
		PreparedStatement ps1 = null;
		Connection con = DatabaseConnectionFactory.createConnection();
		if (0 != bo.getCreatedBy()) {
			ps1 = con.prepareStatement(
					"select * from user where isDelete='false' and user_type='Candidate' and createdBy="
							+ bo.getCreatedBy() + "");
		} else {
			ps1 = con.prepareStatement("select * from user where isDelete='false' and user_type='Candidate'");
		}
		ResultSet set1 = ps1.executeQuery();
		while (set1.next()) {
			UserBO userBO = new UserBO();
			userBO.setUserId(set1.getInt(1));
			userBO.setUserName(set1.getString(2));
			userBO.setIsDelete(false);
			list.add(userBO);
		}
		return list;
	}

	private List<Integer> getSubCategories(String subcategories) {

		List<Integer> subcategoryList = new ArrayList<Integer>();
		String values = subcategories.replace("[", " ");
		String valuesubcatid = values.replace("]", " ");
		StringTokenizer st = new StringTokenizer(valuesubcatid, ",");
		// System.out.println(st.toString());

		while (st.hasMoreTokens()) {
			String val = st.nextToken();
			subcategoryList.add(Integer.valueOf(val.trim()));
		}
		return subcategoryList;
	}

	@Override
	public CategoryBO retrievecategorybyName(String category) throws SQLException {
		CategoryBO categorybo = null;

		Connection con = DatabaseConnectionFactory.createConnection();

		PreparedStatement ps1 = con
				.prepareStatement("select * from category where category='" + category + "' and isDelete='false'");

		ResultSet set1 = ps1.executeQuery();
		while (set1.next()) {
			categorybo = new CategoryBO();
			categorybo.setCategoryId(set1.getInt(1));
			categorybo.setCategory(set1.getString(2));
			categorybo.setIsDelete(false);

		}

		return categorybo;
	}

	@Override
	public SubcategoryBO getSubCategoryByName(String subCategory) throws SQLException {
		SubcategoryBO bo = null;
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps1 = con
				.prepareStatement("select subcategory_id,subcategory,priority from subcategory where subcategory=?");

		ps1.setString(1, subCategory);
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			bo = new SubcategoryBO();
			bo.setSubcategoryId(rs1.getInt(1));
			bo.setSubcategory(rs1.getString(2));
			bo.setPriority(rs1.getInt(3));

		}

		return bo;
	}

	@Override
	public List<QuizQuestion> retrieveQuestion(QuizQuestion quizQuestion) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();
		PreparedStatement ps1 = con.prepareStatement(
				"select qcs.category_id,c.category,qcs.subcategory_id,sc.subcategory,count(question_id) from question_category_subcategory qcs,category c, subcategory sc where qcs.category_id=c.category_id and qcs.subcategory_id=sc.subcategory_id group by qcs.subcategory_id");
		ResultSet set1 = ps1.executeQuery();
		List<QuizQuestion> List = new ArrayList<QuizQuestion>();
		while (set1.next()) {
			QuizQuestion bo = new QuizQuestion();
			bo.setCategoryId(set1.getInt(1));
			bo.setCategory(set1.getString(2));
			bo.setSubcategoryId(set1.getInt(3));
			bo.setSubcategory(set1.getString(4));
			bo.setCount(set1.getInt(5));

			List.add(bo);
		}
		return List;

	}

	@Override
	public GroupBO createGroup(GroupBO groupBO) throws SQLException {
		Connection con = DatabaseConnectionFactory.createConnection();

		PreparedStatement ps = con.prepareStatement("select group_name from groupt where group_name=?");
		ps.setString(1, groupBO.getGroupName());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			GroupBO groupbo = new GroupBO();
			groupbo.setGroupName(rs.getString(1));
			if (groupbo.getGroupName().equals(groupbo.getGroupName())) {
				groupbo.setErrorMessage("Group was already Registered");
				return groupbo;
			}
		}

		if (null != groupBO.getGroupName()) {

			PreparedStatement ps1 = con.prepareStatement("insert into groupt(group_name,isDelete)values(?,?)");
			ps1.setLong(1, groupBO.getGroupId());
			ps1.setString(1, groupBO.getGroupName());
			ps1.setBoolean(2, groupBO.isDelete());
			int i = ps1.executeUpdate();
			if (i != 0) {
				groupBO.setErrorMessage("Group Successfully Registered.");
			} else {
				groupBO.setErrorMessage("Group Registeration Failed!");
			}

			return groupBO;
		}
		return groupBO;
	}

}
