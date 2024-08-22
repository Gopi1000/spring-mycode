package com.scube.mycognitiv.service;

import com.scube.mycognitiv.bo.CompanyRegistrationBO;

public interface CompanyService {

	CompanyRegistrationBO registeration(CompanyRegistrationBO companyReg);

	CompanyRegistrationBO authendicate(CompanyRegistrationBO regist);

	CompanyRegistrationBO getCompanydetails(String companyId);

	public int getCompanyCount(CompanyRegistrationBO companyBO)throws Exception;

	public CompanyRegistrationBO retrieveAllCompanies(CompanyRegistrationBO companyBO)throws Exception;

	public CompanyRegistrationBO adminDeleteCompany(CompanyRegistrationBO companyBO)throws Exception;

}
