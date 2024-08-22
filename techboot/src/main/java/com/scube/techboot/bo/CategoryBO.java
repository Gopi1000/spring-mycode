package com.scube.techboot.bo;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryBO extends BaseBo{
	
	private static final long serialVersionUID = 1L;
	
	private int categoryId;
	
	@NotBlank(message="Category Name may not be empty")
	private String category;
	
	public int getCategoryId() {
		return categoryId;
	}	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}	
}
