package com.scube.techboot.bo;

import java.sql.Blob;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.scube.techboot.vo.MetaTagVO;

public class CourseBO extends BaseBo{
	
	private int courseId;
	private String courseUrl;
	@NotBlank(message="Course Name may not be empty")
	private String courseName;
	
	@NotBlank(message="authorName Name may not be empty")
	private String authorName;
	
	private String metaTaq;
	
	private float fees;
	private int rupees;
	
	private Blob courseLogo;
	private String imageName;
	private String categoryName;
	private MetaTitleBO metaTitleBO;
	private List<MetaTagVO> metataglist;
	
	private CourseCategoryBO courseCategoryBO;
    private CompanyBO companyBO;

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public float getFees() {
		return fees;
	}
	public void setFees(float fees) {
		this.fees = fees;
	}
	public Blob getCourseLogo() {
		return courseLogo;
	}
	public void setCourseLogo(Blob courseLogo) {
		this.courseLogo = courseLogo;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getCourseUrl() {
		return courseUrl;
	}
	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}
	public int getRupees() {
		return rupees;
	}
	public void setRupees(int rupees) {
		this.rupees = rupees;
	}
	public String getMetaTaq() {
		return metaTaq;
	}
	public void setMetaTaq(String metaTaq) {
		this.metaTaq = metaTaq;
	}
	public List<MetaTagVO> getMetataglist() {
		return metataglist;
	}
	public void setMetataglist(List<MetaTagVO> metataglist) {
		this.metataglist = metataglist;
	}
	public MetaTitleBO getMetaTitleBO() {
		return metaTitleBO;
	}
	public void setMetaTitleBO(MetaTitleBO metaTitleBO) {
		this.metaTitleBO = metaTitleBO;
	}
	public CourseCategoryBO getCourseCategoryBO() {
		return courseCategoryBO;
	}
	public void setCourseCategoryBO(CourseCategoryBO courseCategoryBO) {
		this.courseCategoryBO = courseCategoryBO;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	
}
