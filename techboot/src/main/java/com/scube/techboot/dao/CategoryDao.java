package com.scube.techboot.dao;


import java.util.List;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.vo.CategoryVO;

public interface CategoryDao {

	int saveCategory(CategoryVO categoryVo);

	boolean isExixstsCategory(CategoryVO categoryVO);

	boolean deleteCategory(CategoryVO categoryVo);

	CategoryVO updateCategory(CategoryVO categoryVo);

	boolean updateSaveCategory(CategoryVO categoryVo);

	long retrievecategoryCount(CategoryVO categoryVO);

	List<CategoryVO> viewCatogery(CategoryVO categoryVO);

	int searchCategory(CategoryVO categoryVO);

	List<CategoryVO> searchPageCategory(CategoryVO categoryVO);

	List<CategoryBO> viewcat();

}
