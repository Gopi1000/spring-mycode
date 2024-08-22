package com.scube.mycognitiv.service;

import com.scube.mycognitiv.bo.CompanyRegistrationBO;
import com.scube.mycognitiv.bo.RegistrationBO;
import com.scube.mycognitiv.dao.CandidateDao;
import com.scube.mycognitiv.dao.CandidateDaoImpl;
import com.scube.mycognitiv.dao.CompanyDao;
import com.scube.mycognitiv.dao.CompanyDaoImpl;

public class CompanyServiceImpl implements CompanyService{
	
	CompanyDao company=new CompanyDaoImpl();
	
	CandidateDao dao = new CandidateDaoImpl();

	@Override
	public CompanyRegistrationBO registeration(CompanyRegistrationBO companyReg) {
		if(null!=companyReg) {
		companyReg=company.registration(companyReg);
		if(companyReg.getId()>0) {
			RegistrationBO user=new RegistrationBO();
			user.setUserName(companyReg.getCompanyName());
			user.setPassword(companyReg.getPassword());
			user.setUserType(companyReg.getUserType());
			user.setEmail(companyReg.getCompanyEmail());
			user.setPhoneNumber(companyReg.getCompanyContactNo());
			user.setIsDelete(false);
			dao.companyRegistration(user);
		}
		}
		return companyReg;
	}

	@Override
	public CompanyRegistrationBO authendicate(CompanyRegistrationBO regist) {
		return company.authendicate(regist);
	}

	@Override
	public CompanyRegistrationBO getCompanydetails(String companyId) {
		return company.getCompanydetails(companyId);
	}

	@Override
	public int getCompanyCount(CompanyRegistrationBO companyBO) throws Exception {
		int companyCount=0;
		companyCount=company.getCompanyCount(companyBO);
		if(companyCount>0) {
			return companyCount;
		}
		return 0;
	}

	@Override
	public CompanyRegistrationBO retrieveAllCompanies(CompanyRegistrationBO companyBO) throws Exception {
     
	return company.retrieveAllCompanies(companyBO);	
	}

	@Override
	public CompanyRegistrationBO adminDeleteCompany(CompanyRegistrationBO companyBO) throws Exception {
		return company.adminDeleteCompany(companyBO);
	}

}
