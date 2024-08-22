package com.scube.techboot.service;


import java.util.List;

import com.scube.techboot.bo.CategoryBO;

public interface CategoryService {

	int saveCategory(CategoryBO categoryBo) throws  Exception;

	boolean categoryNameValidations(CategoryBO categoryBO);

	boolean deleteCategory(CategoryBO categoryBo) throws Exception;

	CategoryBO retrieveCategory(CategoryBO categoryBo) throws Exception;

	boolean updateSaveStudent(CategoryBO categoryBo) throws Exception;

	long retrieveCategoryCount(CategoryBO categoryBO);

	List<CategoryBO> viewCatogery(CategoryBO categoryBO);

	int searchCategory(CategoryBO categoryBo);

	List<CategoryBO> searchPageCategory(CategoryBO categoryBo);

	List<CategoryBO> viewcat();

}
