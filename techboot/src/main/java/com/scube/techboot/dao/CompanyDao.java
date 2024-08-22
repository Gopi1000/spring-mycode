package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.UserRoleVO;

public interface CompanyDao {

	CompanyBO savecompanydetails(CompanyVO companyVO);

	List<CompanyVO> retrivecompanydao(CompanyVO companyVO);

	CompanyVO getCompanyObject(CompanyVO companyvo);

	boolean updatecompany(CompanyVO companyvo);

	boolean deleteCompany(CompanyVO companyvo);

	LoginVO loginComapny(LoginVO loginVO);

	boolean updateLogine(LoginVO loginVO);

	CompanyVO getCompany(CompanyVO companyvo);

	CompanyVO retriveOfCompanyId(CompanyVO companyVO);

	List<CompanyVO> listOfPageCompany(CompanyVO companyVO);

	int searchCompany(CompanyVO companyVO);

	List<CompanyVO> searchPageCompany(CompanyVO companyVO);

	boolean isValidCompanyDetails(CompanyVO companyVo);

	UserRoleVO getUserRole(UserRoleVO userRoleVO);

	boolean companyNameValidations(CompanyVO companyVo);

	boolean emailAddressValidations(CompanyVO companyVo);

	boolean websiteValidations(CompanyVO companyVo);

    CompanyVO getCompanyUserObject(CompanyVO companyVO);

	CompanyVO getCompanyUser(CompanyVO companyvo);

	boolean updatecompanyUser(CompanyVO companyvo);

	boolean updateLoginByCompany(LoginVO loginVO);

	CompanyVO getCompanyuserObject(CompanyVO companyVO);

	
	
     
}
