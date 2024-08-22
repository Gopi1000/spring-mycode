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
import com.scube.mycognitiv.dao.AdminDao;
import com.scube.mycognitiv.dao.AdminDaoImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * @author Administrator
 *
 */
public class AdminServiceImpl implements AdminService {

	AdminDao dao = new AdminDaoImpl();

	@Override
	public QuizQuestion createQuestions(QuizQuestion bo) {
		return dao.createQuestions(bo);
	}

	@Override
	public QuizQuestion retrievequestionbyId(int quesId) throws SQLException {
		return dao.retrievequestionbyId(quesId);
	}

	public QuizQuestion updateQuestion(QuizQuestion quizQuestion) throws SQLException {
		return dao.updateQuestion(quizQuestion);
	}

	@Override
	public QuizQuestion updateQuestionDetails(QuizQuestion updatebo) throws SQLException {
		return dao.updateQuestionDetails(updatebo);
	}

	@Override
	public List<CategoryBO> retrievecategory(CategoryBO categoryBO) throws SQLException {
		return dao.retrievecategory(categoryBO);
	}

	@Override
	public List<SubcategoryBO> retrievesubcategory(SubcategoryBO subCategoryBO) throws SQLException {
		return dao.retrievesubcategory(subCategoryBO);
	}

	@Override
	public ArrayList<QuizQuestion> retrievequestions(int categoryId, int subcategoryId, QuizQuestion quizObj)
			throws SQLException {
		return dao.retrievequestions(categoryId, subcategoryId, quizObj);
	}

	@Override
	public CategoryBO createCategory(CategoryBO bo) throws SQLException {

		return dao.createCategory(bo);
	}

	@Override
	public SubcategoryBO createSubCategory(SubcategoryBO bo) throws Exception {

		try {
			if (null != bo && bo.getSubcategoryId() > 0) {
				bo = dao.updateSubcategory(bo);
			} else {
				bo = dao.createSubCategory(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public ArrayList<SubcategoryBO> updatepriority(ArrayList<SubcategoryBO> updateList) throws SQLException {

		return dao.updatepriority(updateList);
	}

	@Override
	public CategoryBO retrievecategorybyId(int categoryId) throws SQLException {

		return dao.retrievecategorybyId(categoryId);
	}

	@Override
	public CategoryBO deletecategory(int categoryId) throws SQLException {

		return dao.deletecategory(categoryId);
	}

	@Override
	public CategoryBO updatecategory(CategoryBO updatebo) throws SQLException {

		return dao.updatecategory(updatebo);
	}

	@Override
	public QuizQuestion retrievequestiontype(int categoryid) throws SQLException {

		return dao.retrievequestiontype(categoryid);
	}

	@Override
	public ArrayList<QuizQuestion> retrieveanswer(int userid) throws SQLException {

		return dao.retrieveanswer(userid);
	}

	@Override
	public ArrayList<SubcategoryBO> getsubcategory(int categoryId) throws SQLException {
		return dao.getsubcategory(categoryId);
	}

	@Override
	public CreateTestBO createTestAdmin(CreateTestBO createTestBO) throws SQLException {

		return dao.createTestAdmin(createTestBO);
	}

	@Override
	public int getQuestionsCount(int catgoery, int subcatagory) {

		int totalCount = 0;
		totalCount = dao.getQuestionsCount(catgoery, subcatagory);
		if (0 < totalCount) {
			return totalCount;
		}
		return 0;
	}

	@Override
	public List<CreateTestBO> retrieveTest(CreateTestBO createTestBO) throws SQLException {
		return dao.retrieveTest(createTestBO);
	}

	@Override
	public List<CreateTestBO> retrieveTestByTestId(int testId) throws SQLException {
		return dao.retrieveTestByTestId(testId);
	}

	@Override
	public CreateTestBO assignTestAdmin(CreateTestBO createTestBO) throws SQLException {

		return dao.assignTestAdmin(createTestBO);
	}

	@Override
	public List<CreateTestBO> retrieveCandidateTest(CreateTestBO createTestBO) throws SQLException {

		return dao.retrieveCandidateTest(createTestBO);
	}

	@Override
	public int getCategoryCount() {

		int categoryCount = 0;
		categoryCount = dao.getCategoryCount();
		if (0 < categoryCount) {
			return categoryCount;
		}
		return 0;
	}

	@Override
	public int getSubCategoryCount() {

		int totalSubCategoryCount = 0;
		totalSubCategoryCount = dao.getSubCategoryCount();
		if (0 < totalSubCategoryCount) {
			return totalSubCategoryCount;
		}
		return 0;
	}

	@Override
	public SubcategoryBO getSubCategoryById(SubcategoryBO subCategoryBO) {

		SubcategoryBO subCategoryBOObj = new SubcategoryBO();
		if (null != subCategoryBOObj) {
			subCategoryBOObj = dao.getSubCategoryById(subCategoryBO);
			if (null != subCategoryBOObj) {
				return subCategoryBOObj;
			}
		}
		return null;
	}

	@Override
	public CategoryBO editCategory(int categoryId) throws SQLException {

		return dao.editCategory(categoryId);
	}

	@Override
	public boolean deleteCategory(CategoryBO bo) throws SQLException {

		if (null != bo && bo.getCategoryId() > 0) {
			bo.setIsDelete(true);
			boolean flag = dao.deleteCategory(bo);
			if (flag) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getTestCount(CreateTestBO createTestBO) {

		int totalTestCount = 0;
		totalTestCount = dao.getTestCount(createTestBO);
		if (0 < totalTestCount) {
			return totalTestCount;
		}
		return 0;
	}

	@Override
	public boolean deleteTest(CreateTestBO crateTestBO) {

		if (null != crateTestBO && 0 < crateTestBO.getTestId()) {
			crateTestBO.setIsDelete(true);
			boolean deleteFlag = dao.deleteTest(crateTestBO);
			if (deleteFlag) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SubcategoryBO deleteSubCategory(SubcategoryBO subcateogryBO) throws Exception {

		return dao.deleteSubCategory(subcateogryBO);
	}

	@Override
	public CreateTestBO editTestByTestId(CreateTestBO createTestBO) {

		if (null != createTestBO && 0 < createTestBO.getTestId()) {
			createTestBO = dao.editTestByTestId(createTestBO);
			if (null != createTestBO) {
				return createTestBO;
			}
		}

		return null;
	}

	@Override
	public CreateTestBO updateTest(CreateTestBO createTestBO) {

		if (null != createTestBO && 0 < createTestBO.getTestId()) {
			createTestBO.setIsDelete(false);
			createTestBO = dao.updateTest(createTestBO);
			if (null != createTestBO) {
				return createTestBO;
			}
		}
		return null;
	}

	@Override
	public ArrayList<UserBO> retrieveTakeTestCandidate(UserBO bo) throws Exception {
		return dao.retrieveTakeTestCandidate(bo);
	}

	@Override
	public ArrayList<UserBO> retrieveAllCandidate(UserBO bo) throws Exception {
		return dao.retrieveAllCandidate(bo);
	}

	@Override
	public CategoryBO getCategoryByName(String category) throws Exception {
		return dao.retrievecategorybyName(category);
	}

	@Override
	public SubcategoryBO getSubCategoryByName(String subCategory) throws Exception {

		return dao.getSubCategoryByName(subCategory);
	}

	@Override
	public List<QuizQuestion> retrieveQuestion(QuizQuestion quizQuestion) throws SQLException {

		return dao.retrieveQuestion(quizQuestion);
	}

	@Override
	public GroupBO createGroup(GroupBO groupBO) throws SQLException {
		// TODO Auto-generated method stub
		return dao.createGroup(groupBO);
	}

}
