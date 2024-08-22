/**
 * 
 */
package com.scube.mycognitiv.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.bo.GroupBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * @author Administrator
 *
 */
public interface AdminService {

	public QuizQuestion createQuestions(QuizQuestion bo);

	public QuizQuestion retrievequestionbyId(int questId) throws SQLException;

	public QuizQuestion updateQuestion(QuizQuestion quizQuestion) throws SQLException;

	public QuizQuestion updateQuestionDetails(QuizQuestion updatebo) throws SQLException;

	public List<CategoryBO> retrievecategory(CategoryBO categoryBO) throws SQLException;

	public List<SubcategoryBO> retrievesubcategory(SubcategoryBO subCategoryBO) throws SQLException;

	public ArrayList<QuizQuestion> retrievequestions(int categoryId, int subcategoryId, QuizQuestion quizObj)
			throws SQLException;

	public CategoryBO createCategory(CategoryBO bo) throws SQLException;

	public SubcategoryBO createSubCategory(SubcategoryBO bo) throws SQLException, Exception;

	public ArrayList<SubcategoryBO> updatepriority(ArrayList<SubcategoryBO> updateList) throws SQLException;

	public CategoryBO retrievecategorybyId(int categoryId) throws SQLException;

	public CategoryBO deletecategory(int categoryId) throws SQLException;

	public CategoryBO updatecategory(CategoryBO updatebo) throws SQLException;

	public QuizQuestion retrievequestiontype(int categoryid) throws SQLException;

	public ArrayList<QuizQuestion> retrieveanswer(int userid) throws SQLException;

	public ArrayList<SubcategoryBO> getsubcategory(int categoryId) throws SQLException;

	public CreateTestBO createTestAdmin(CreateTestBO createTestBO) throws SQLException;

	public int getQuestionsCount(int catgoery, int subcatagory);

	public List<CreateTestBO> retrieveTest(CreateTestBO createTestBO) throws SQLException;

	public CreateTestBO assignTestAdmin(CreateTestBO createTestBO) throws SQLException;

	public List<CreateTestBO> retrieveCandidateTest(CreateTestBO createTestBO) throws SQLException;

	public int getCategoryCount();

	public int getSubCategoryCount();

	public SubcategoryBO getSubCategoryById(SubcategoryBO subCategoryBO);

	public int getTestCount(CreateTestBO createTestBO);

	public CategoryBO editCategory(int categoryId) throws SQLException;

	public SubcategoryBO deleteSubCategory(SubcategoryBO subcateogryBO) throws Exception;

	public boolean deleteCategory(CategoryBO bo) throws SQLException;

	public boolean deleteTest(CreateTestBO createTestBO);

	public CreateTestBO editTestByTestId(CreateTestBO createTestBO);

	public CreateTestBO updateTest(CreateTestBO createTestBO);

	public ArrayList<UserBO> retrieveTakeTestCandidate(UserBO bo) throws Exception;

	public ArrayList<UserBO> retrieveAllCandidate(UserBO bo) throws Exception;

	public List<CreateTestBO> retrieveTestByTestId(int testId) throws SQLException;

	public CategoryBO getCategoryByName(String category) throws Exception;

	public SubcategoryBO getSubCategoryByName(String subCategory) throws Exception;

	public List<QuizQuestion> retrieveQuestion(QuizQuestion quizQuestion) throws SQLException;

	public GroupBO createGroup(GroupBO groupBO) throws SQLException;

}
