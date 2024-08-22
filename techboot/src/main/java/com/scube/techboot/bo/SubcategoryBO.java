package com.scube.techboot.bo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class SubcategoryBO extends BaseBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subcategoryId;
	
	
	  @NotEmpty(message="Subcategory name is required")
	  @Pattern(regexp = "[A-Za-z0-9]*",message="Subcategory name is not correct format")
	private String subcategory;
	private CategoryBO categoryBO;
	
	private int priority;
	
	
	public int getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public CategoryBO getCategoryBO() {
		return categoryBO;
	}
	public void setCategoryBO(CategoryBO categoryBO) {
		this.categoryBO = categoryBO;
	}
	

}
