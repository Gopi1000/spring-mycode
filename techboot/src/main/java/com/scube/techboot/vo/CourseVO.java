
package com.scube.techboot.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="course")
public class CourseVO extends BasicEntity {
	private int courseId;
	private String courseName;
	private String courseUrl;
	private String authorName;
	private float fees;
	private String imageName;
	private MetaTagVO metatagVO;
	private List<MetaTagVO> metataglist;
	private courseCategoryVO courseCategoryVo;
	private CompanyVO companyVO;

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	@Column(name="course_name",nullable=false)
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Column(name="author_name",nullable=false)
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Column(name="fees",nullable=false)
	public float getFees() {
		return fees;
	}
	public void setFees(float fees) {
		this.fees = fees;
	}
	@Column(name="image_name",nullable=false)
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Column(name="course_url")
	public String getCourseUrl() {
		return courseUrl;
	}
	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "course_metatag", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "metatag_id") })
	public List<MetaTagVO> getMetataglist() {
		return metataglist;
	}
	public void setMetataglist(List<MetaTagVO> metataglist) {
		this.metataglist = metataglist;
	}
	@Transient
	public MetaTagVO getMetatagVO() {
		return metatagVO;
	}
	public void setMetatagVO(MetaTagVO metatagVO) {
		this.metatagVO = metatagVO;
	}
	@OneToOne
	@JoinColumn(name="courseCategoryId")
	public courseCategoryVO getCourseCategoryVo() {
		return courseCategoryVo;
	}
	public void setCourseCategoryVo(courseCategoryVO courseCategoryVo) {
		this.courseCategoryVo = courseCategoryVo;
	}
	@OneToOne/*(cascade = CascadeType.ALL,fetch = FetchType.EAGER)*/
	@JoinColumn(name="company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	
}
