package com.scube.techboot.bo;

import java.util.List;

import com.scube.techboot.vo.MetaTagVO;

public class CourseDetailsBO extends BaseBo {
	private int sNo;
	private int CourseDetailsId;
	private  String description;
	private String designed;
	private String curriculum;
	private String classesSchedule;
	private String keyfeatures;
	private float price;
    private int rupees;
	private boolean isDelete;
	private List<MetaTagVO> metataglist;
	
	private  CourseBO curseBO;
    private CompanyBO companyBO;

	public int getCourseDetailsId() {
		return CourseDetailsId;
	}
	public void setCourseDetailsId(int courseDetailsId) {
		CourseDetailsId = courseDetailsId;
	}
	public CourseBO getCurseBO() {
		return curseBO;
	}
	public void setCurseBO(CourseBO curseBO) {
		this.curseBO = curseBO;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDesigned() {
		return designed;
	}
	public void setDesigned(String designed) {
		this.designed = designed;
	}
	public String getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	public String getClassesSchedule() {
		return classesSchedule;
	}
	public void setClassesSchedule(String classesSchedule) {
		this.classesSchedule = classesSchedule;
	}
	public String getKeyfeatures() {
		return keyfeatures;
	}
	public void setKeyfeatures(String keyfeatures) {
		this.keyfeatures = keyfeatures;
	}
	/*public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}*/
		public boolean getisDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	
	public int getRupees() {
		return rupees;
	}
	public void setRupees(int rupees) {
		this.rupees = rupees;
	}
	public List<MetaTagVO> getMetataglist() {
		return metataglist;
	}
	public void setMetataglist(List<MetaTagVO> metataglist) {
		this.metataglist = metataglist;
	}
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	/*public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	*/
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
