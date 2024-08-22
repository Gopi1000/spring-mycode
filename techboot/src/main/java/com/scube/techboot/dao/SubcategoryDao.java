package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.vo.SubcategoryVO;

public interface SubcategoryDao {

	SubcategoryBO savesubcategory(SubcategoryVO subcategoryVO);

	List<SubcategoryBO> getSubcategoryobject();

	List<SubcategoryVO> search(String subcategory);

	SubcategoryVO editsubcategory(SubcategoryVO subcategoryVO);

	SubcategoryVO retrievesubcategory(SubcategoryVO subcategoryVO);

	Boolean subcategoryUpdate(SubcategoryVO subcategoryVO);

	List<SubcategoryVO> search(SubcategoryVO subcategoryVO);

	boolean isExixstsCategory(SubcategoryVO subcategoryVO);

	long retrievesubcategoryCount(SubcategoryVO subcategoryVO);

	List<SubcategoryVO> viewsubcategory(SubcategoryVO subcategoryVO);

}
