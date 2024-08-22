/**
 * 
 */
package com.scube.crm.service;

import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.dao.EmployerclientDao;
import com.scube.crm.exception.JLogger;

import com.scube.crm.model.EmployerLoginVO;
import com.scube.crm.model.EmployerVO;
import com.scube.crm.vo.CompanyVO;


/**
 * @author User
 *
 */


public class EmployerclientServiceImpl implements Employerclientservice {
	private static final JLogger LOGGER = JLogger.getLogger(EmployerclientServiceImpl.class);
	
	private EmployerclientDao clientDao;
	
	

	/**
	 * @return the clientdao
	 */
	public EmployerclientDao getClientdao() {
		return clientDao;
	}



	/**
	 * @param clientdao the clientdao to set
	 */
	public void setClientdao(EmployerclientDao clientdao) {
		this.clientDao = clientdao;
	}
	
	/*public EmployerVO register(EmployerVO VO) {
		VO=  clientdao.register(VO);
		return VO;
	}*/
	@Override
	public EmployerRegisterBO clientTomyjobkartRegister(EmployerRegisterBO employerRegisterBO) {
		LOGGER.entry();
		EmployerVO employerVO = new EmployerVO();
		try {
			
			if(null !=employerRegisterBO.getFirstName()){
				employerVO.setFirstName(employerRegisterBO.getFirstName());
			}
			if(null !=employerRegisterBO.getLastName()){
				employerVO.setLastName(employerRegisterBO.getLastName());
			}
			if(null !=employerRegisterBO.getEmailAddress()){
				employerVO.setEmailAddress(employerRegisterBO.getEmailAddress());
			}
			if(null !=employerRegisterBO.getConfirmEmailAddress()){
				employerVO.setConfirmEmailAddress(employerRegisterBO.getConfirmEmailAddress());
			}
			if(null !=employerRegisterBO.getCompanyName()){
				employerVO.setCompanyName(employerRegisterBO.getCompanyName());
			}
			if (null !=employerRegisterBO.getMobileNo()){
				employerVO.setMobileNumber(Long.parseLong(employerRegisterBO.getMobileNo()));
			}
			if(null !=employerRegisterBO.getWebsite()){
				employerVO.setWebSite(employerRegisterBO.getWebsite());
			}
			if(null !=employerRegisterBO.getContactNo()){
				employerVO.setContactNumber(Long.parseLong(employerRegisterBO.getContactNo()));
			}
			CompanyVO companyVO = new CompanyVO();
			String company=  employerRegisterBO.getCompanyName();
			companyVO = clientDao.retrieveCompany(company);
			if(0 != companyVO.getCompaniesId()){
				employerVO.setCompanyVO(companyVO);
			}
			else{
				String companyname = employerRegisterBO.getCompanyName();
				companyVO.setCompaniesName(companyname);
				companyVO.setAdminChecked(true);
				long companyid = clientDao.addnewcompany(companyVO);
				if(0 != companyid){
					companyVO.setCompaniesId(companyid);
					employerVO.setCompanyVO(companyVO);
				}
			}
			employerVO.setIsActive(true);
			employerVO.setIsDeleted(false);
			employerVO.setTermsConditionsAgreed(true);
			employerVO.setConfirmPassword(employerRegisterBO.getPassword());
			employerVO.setPassword(employerRegisterBO.getConfirmPassword());
			employerVO=  clientDao.clientTomyjobkartRegister(employerVO);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			LOGGER.exit();
		}
		return employerRegisterBO ;
	}



	/* (non-Javadoc)
	 * @see com.scube.myjobkart.service.Employerclientservice#signin(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public EmployerLoginVO signinTomyjobkart(EmployerRegisterBO BO) {
		EmployerLoginVO loginVO=new EmployerLoginVO();
		try{
			loginVO.setActive(true);
			loginVO.setEmailAddress(BO.getEmailAddress());
			loginVO.setPassword(BO.getPassword());
			loginVO.setConfirmPassword(BO.getConfirmPassword());
			EmployerVO VO=new EmployerVO();
			VO.setId(BO.getId());
			loginVO.setEmployerRegistration(VO);	
			loginVO=clientDao.signinTomyjobkart(loginVO);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return loginVO;
	}
	
	
	
	

}
