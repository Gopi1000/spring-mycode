package com.scube.techboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.SubcategoryBO;

public interface SubcategoryService {

	SubcategoryBO savesubcategory(SubcategoryBO subcategoryBO, HttpSession session);

	List<SubcategoryBO> getSubcategoryObject();

	List<SubcategoryBO> searchSubcategory(String subcategory);

	SubcategoryBO editSubcategoryObject(SubcategoryBO subcategoryBO);

	Boolean subcategoryUpdate(SubcategoryBO subcategoryBO);

	List<SubcategoryBO> searchSubcategory(SubcategoryBO subcategorybo);

	boolean subcategoryNameValidations(SubcategoryBO subcategoryBO);

	long retrieveSubcategoryCount(SubcategoryBO subcategoryBO);

	List<SubcategoryBO> viewSubcategory(SubcategoryBO subcategoryBO);

	

	

}
