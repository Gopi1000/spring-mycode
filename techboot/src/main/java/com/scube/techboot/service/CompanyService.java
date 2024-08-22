package com.scube.techboot.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.scube.techboot.bo.CompanyBO;


public interface CompanyService {

	CompanyBO saveCompanyDetails(CompanyBO companyBo,HttpSession session) throws Exception;

	List<CompanyBO> retriveCompany(CompanyBO companybo);

	CompanyBO getCompanyObject(CompanyBO company) throws Exception;

	boolean updateCompany(CompanyBO companyBo,HttpSession session) throws Exception;

	boolean deleteCompany(CompanyBO companybo);

	List<CompanyBO> listOfPageCompany(CompanyBO companyBo);

	CompanyBO retriveOfCompanyId(CompanyBO companyBo);

	int searchCompany(CompanyBO companyBO);

	List<CompanyBO> searchPageCompany(CompanyBO companyBO);

	boolean isValidCompanyDetails(CompanyBO companyBO);

	boolean companyNameValidations(String companyName);

	boolean emailAddressValidations(String emailAddress);

	boolean websiteValidations(String website);

	CompanyBO getCompanyUserObject(CompanyBO company);

	boolean updateCompanyUser(CompanyBO companyBo, HttpSession session)throws Exception;

	CompanyBO getCompanyUserobject(CompanyBO company);

	



	

	
}
