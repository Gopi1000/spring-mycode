package com.scube.techboot.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.dao.CompanyDao;
import com.scube.techboot.utils.EncryptAndDecrypt;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.UserRoleVO;


@Service
@Transactional

public class CompanyServiceImpl<CompanyBo> implements CompanyService {

	@Autowired
	private CompanyDao companydaoDaoImpl;


	@Override
	public CompanyBO saveCompanyDetails(CompanyBO companyBo,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(companyBo.getCompanyId());
		companyVO.setCompanyName(companyBo.getCompanyName());
		companyVO.setCompanyPersonName(companyBo.getCompanyPersonName());
		companyVO.setfirstName(companyBo.getfirstName());
		companyVO.setlastName(companyBo.getlastName());
		companyVO.setemailAddress(companyBo.getemailAddress());
		companyVO.setConformEmailAddress(companyBo.getConformEmailAddress());
		String password=EncryptAndDecrypt.encrypt(companyBo.getPassword(),EncryptAndDecrypt.TOKEN);
				
		companyVO.setConformPassword(password);
		companyVO.setPassword(password);
		companyVO.setCompanyWebSite(companyBo.getCompanyWebSite());
		companyVO.setCompanyType(companyBo.getCompanyType());
		companyVO.setContectNumber(companyBo.getContectNumber());
		companyVO.setCreatedTime(new Date());
		companyVO.setAddress(companyBo.getAddress());
		companyVO.setIsDelete(false);
		companyVO.setSending_status(true);
		companyVO.setImageName(companyBo.getImageName());
		companyVO.setIndustry(companyBo.getIndustry());
		if(null!=session.getAttribute("adminId")){
			long id= (long) session.getAttribute("adminId");
			companyVO.setCreatedBy(id);
		}
		CompanyBO companyBO =companydaoDaoImpl.savecompanydetails(companyVO);
		if(0!=companyBO.getCompanyId()){
			LoginVO loginVO=new LoginVO();
			CompanyVO company=new CompanyVO();
			//login Company Object
			company.setCompanyId(companyBO.getCompanyId());					
			loginVO.setEmailAddress(companyBo.getemailAddress());
			
			String password1=EncryptAndDecrypt.encrypt(companyBo.getPassword(),EncryptAndDecrypt.TOKEN);
					
			loginVO.setPassword(password1);
			loginVO.setCreatedTime(new Date());
			loginVO.setCompanyVO(company);
			loginVO.setIsDelete(false);
			loginVO.setIsActive(true);
			loginVO.setUserType("company");
			
			//UserRole On Company
			UserRoleVO userRoleVO=new UserRoleVO();
			userRoleVO.setUserRoleId(2);
			userRoleVO.setUserRoleName("company");
			
			//set userrole on login
			//loginVO.setUserRoleVO(userRoleVO);
			//changed
			userRoleVO=companydaoDaoImpl.getUserRole(userRoleVO);
			if(null!=userRoleVO){
				loginVO.setUserRoleVO(userRoleVO);
			}
			//changed 
			if(null!=session.getAttribute("adminId")){
				Long id=(Long) session.getAttribute("adminId");
				loginVO.setCreatedBy(id);
			}
			LoginVO login=companydaoDaoImpl.loginComapny(loginVO);
			if(0!=login.getLoginId()){
				return companyBO;
			}
		}
		return companyBO;
	}
	@Override
	public List<CompanyBO> retriveCompany(CompanyBO companybo) {
		// TODO Auto-generated method stub
		List<CompanyVO> companyList=new ArrayList<CompanyVO>();
		List<CompanyBO> companyListBO=new ArrayList<CompanyBO>();
		CompanyVO companyVO=new CompanyVO();
		companyVO.setIsDelete(companybo.getIsDelete());

		companyList=companydaoDaoImpl.retrivecompanydao(companyVO);
		if(companyList!=null && companyList.size()>0 && !companyList.isEmpty()){
			companyList.forEach(company->{
				CompanyBO companyBO=new CompanyBO();
				companyBO.setCompanyId(company.getCompanyId());
				companyBO.setCompanyName(company.getCompanyName());
				companyBO.setCompanyPersonName(company.getCompanyPersonName());
				companyBO.setfirstName(company.getfirstName());
				companyBO.setlastName(company.getlastName());
				companyBO.setemailAddress(company.getemailAddress());
				companyBO.setConformEmailAddress(company.getConformEmailAddress());
				companyBO.setPassword(company.getPassword());
				companyBO.setConformPassword(company.getConformPassword());
				companyBO.setCompanyWebSite(company.getCompanyWebSite());
				companyBO.setCompanyType(company.getCompanyType());
				companyBO.setContectNumber(company.getContectNumber());
				companyBO.setAddress(company.getAddress());
				companyBO.setIsDelete(company.getIsDelete());
				companyBO.setSending_status(company.getSending_status());
				companyListBO.add(companyBO);
			});
		}
		return companyListBO;
	}

	@Override
	public CompanyBO getCompanyObject(CompanyBO company) throws Exception {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		CompanyBO companyBO=new CompanyBO();
		companyVO.setCompanyId(company.getCompanyId());
		companyVO=companydaoDaoImpl.getCompanyObject(companyVO);
		if(null!=companyVO) {
			companyBO.setCompanyId(companyVO.getCompanyId());
			companyBO.setCompanyName(companyVO.getCompanyName());
			companyBO.setCompanyPersonName(companyVO.getCompanyPersonName());
			companyBO.setfirstName(companyVO.getfirstName());
			companyBO.setlastName(companyVO.getlastName());
			companyBO.setemailAddress(companyVO.getemailAddress());
			/*String password=EncryptAndDecrypt.decrypt(companyVO.getPassword(),
					EncryptAndDecrypt.TOKEN );*/
			companyBO.setConformEmailAddress(companyVO.getConformEmailAddress());
			companyBO.setPassword(companyVO.getPassword());
			companyBO.setConformPassword(companyVO.getConformPassword());
			companyBO.setCompanyWebSite(companyVO.getCompanyWebSite());
			companyBO.setCompanyType(companyVO.getCompanyType());
			companyBO.setContectNumber(companyVO.getContectNumber());
			companyBO.setAddress(companyVO.getAddress());
			companyBO.setIsDelete(companyVO.getIsDelete());
			companyBO.setSending_status(companyVO.getSending_status());
			companyBO.setImageName(companyVO.getImageName());
			companyBO.setIndustry(companyVO.getIndustry());
		}
		return companyBO;
	}
	@Override
	public boolean updateCompany(CompanyBO companyBo,HttpSession session) throws Exception {
		CompanyVO companyvo=new CompanyVO();
		CompanyVO campanyVo=new CompanyVO();
		if(0!=companyBo.getCompanyId()){
			companyvo.setCompanyId(companyBo.getCompanyId());
			campanyVo=companydaoDaoImpl.getCompany(companyvo);
		}
		companyvo.setImageName(campanyVo.getImageName());
		companyvo.setCreatedTime(campanyVo.getCreatedTime());
		companyvo.setCompanyId(companyBo.getCompanyId());
		companyvo.setCompanyName(companyBo.getCompanyName());
		companyvo.setCompanyPersonName(companyBo.getCompanyPersonName());
		companyvo.setfirstName(companyBo.getfirstName());
		companyvo.setlastName(companyBo.getlastName());
		companyvo.setemailAddress(companyBo.getemailAddress());
		companyvo.setConformEmailAddress(companyBo.getConformEmailAddress());
		/*String password=EncryptAndDecrypt.encrypt(companyBo.getPassword(), 
				EncryptAndDecrypt.TOKEN);*/
		companyvo.setConformPassword(campanyVo.getPassword());
		companyvo.setPassword(campanyVo.getConformPassword());
		companyvo.setCompanyWebSite(companyBo.getCompanyWebSite());
		companyvo.setCompanyType(companyBo.getCompanyType());
		companyvo.setContectNumber(companyBo.getContectNumber());
		companyvo.setAddress(companyBo.getAddress());
		companyvo.setIsDelete(false);
		companyvo.setSending_status(true);
		companyvo.setModifiedTime(new Date());
		companyvo.setIndustry(companyBo.getIndustry());
		boolean status=companydaoDaoImpl.updatecompany(companyvo);
		if(status){
			LoginVO loginVO=new LoginVO();
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(companyBo.getCompanyId());
			loginVO.setCompanyVO(companyVO);
			loginVO.setEmailAddress(companyBo.getemailAddress());
			/*String password1=EncryptAndDecrypt.encrypt(companyBo.getPassword(), 
					EncryptAndDecrypt.TOKEN);*/
			loginVO.setPassword(campanyVo.getPassword());
			loginVO.setIsDelete(false);
			loginVO.setIsActive(true);
			loginVO.setModifiedTime(new Date());
			loginVO.setCreatedTime(campanyVo.getCreatedTime());
			loginVO.setUserType("company");
			boolean value=companydaoDaoImpl.updateLogine(loginVO);
			if(value){
				return true;
			}
		}
		return true;
	}
	@Override
	public boolean deleteCompany(CompanyBO companybo) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(companybo.getCompanyId());
		companyVO.setIsDelete(true);
		return companydaoDaoImpl.deleteCompany(companyVO);
	}

	@Override
	public List<CompanyBO> listOfPageCompany(CompanyBO companyBo) {
		List<CompanyVO> companyList=new ArrayList<CompanyVO>();
		List<CompanyBO> companyListBO=new ArrayList<CompanyBO>();
		List<CompanyBO> listcompanyBO=new ArrayList<CompanyBO>();
		CompanyVO companyVO=new CompanyVO();
		companyVO.setMaxRecord(companyBo.getMaxRecord());
		companyVO.setRecordIndex(companyBo.getRecordIndex());
		companyVO.setIsDelete(false);
		int sNo=companyBo.getRecordIndex();
		companyList=companydaoDaoImpl.listOfPageCompany(companyVO);

		if(null!=companyList && companyList.size()>0 && !companyList.isEmpty()){
			for(CompanyVO company:companyList){
				CompanyBO companyBO=new CompanyBO();
				companyBO.setCompanyId(company.getCompanyId());
				companyBO.setCompanyName(company.getCompanyName());
				companyBO.setCompanyPersonName(company.getCompanyPersonName());
				companyBO.setCompanyType(company.getCompanyType());
				companyBO.setCompanyWebSite(company.getCompanyWebSite());
				companyBO.setConformEmailAddress(company.getConformEmailAddress());
				companyBO.setConformPassword(company.getConformPassword());
				companyBO.setContectNumber(company.getContectNumber());
				companyBO.setemailAddress(company.getemailAddress());
				companyBO.setfirstName(company.getfirstName());
				companyBO.setlastName(company.getlastName());
				companyBO.setPassword(company.getPassword());
				companyBO.setAddress(company.getAddress());
				companyBO.setS_No(++sNo);
				companyListBO.add(companyBO);
			}
			companyListBO.forEach(company->{
				CompanyBO companyBO=new CompanyBO();
				companyBO.setCompanyId(company.getCompanyId());
				companyBO.setCompanyName(company.getCompanyName());
				companyBO.setCompanyPersonName(company.getCompanyPersonName());
				companyBO.setCompanyType(company.getCompanyType());
				companyBO.setCompanyWebSite(company.getCompanyWebSite());
				companyBO.setConformEmailAddress(company.getConformEmailAddress());
				companyBO.setConformPassword(company.getConformPassword());
				companyBO.setContectNumber(company.getContectNumber());
				companyBO.setemailAddress(company.getemailAddress());
				companyBO.setfirstName(company.getfirstName());
				companyBO.setlastName(company.getlastName());
				companyBO.setPassword(company.getPassword());
				companyBO.setAddress(company.getAddress());
				companyBO.setS_No(company.getS_No());
				listcompanyBO.add(companyBO);
			});
		}
		return listcompanyBO;
	}


	@Override
	public CompanyBO retriveOfCompanyId(CompanyBO companyBo) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO ();
		CompanyBO companyBO=new CompanyBO();
		companyVO=companydaoDaoImpl.retriveOfCompanyId(companyVO);
		if(null!=companyVO){
			companyBO.setCompanyId(companyVO.getCompanyId());
		}
		return companyBO;
	}

	@Override
	public int searchCompany(CompanyBO companyBO) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO ();
		companyVO.setCompanyName(companyBO.getCompanyName());
		companyVO.setIsDelete(false);
		int totalCompany=companydaoDaoImpl.searchCompany(companyVO);
		return totalCompany;
	}
	@Override
	public List<CompanyBO> searchPageCompany(CompanyBO companyBO) {
		// TODO Auto-generated method stub
		List<CompanyVO> companyList=new ArrayList<CompanyVO>();
		List<CompanyBO> companyListBO=new ArrayList<CompanyBO>();
		List<CompanyBO> listcompanyBO=new ArrayList<CompanyBO>();
		CompanyVO companyVO=new CompanyVO();
		companyVO.setRecordIndex(companyBO.getRecordIndex());
		companyVO.setMaxRecord(companyBO.getMaxRecord());
		companyVO.setCompanyName(companyBO.getCompanyName());
		companyVO.setIsDelete(false);
		companyList=companydaoDaoImpl.searchPageCompany(companyVO);
		if(null!=companyList && companyList.size()>0 && !companyList.isEmpty()){
			int sNo=companyBO.getRecordIndex();
			for(CompanyVO company:companyList){
				CompanyBO companys=new CompanyBO();
				companys.setCompanyId(company.getCompanyId());
				companys.setCompanyName(company.getCompanyName());
				companys.setCompanyPersonName(company.getCompanyPersonName());
				companys.setCompanyType(company.getCompanyType());
				companys.setCompanyWebSite(company.getCompanyWebSite());
				companys.setConformEmailAddress(company.getConformEmailAddress());
				companys.setConformPassword(company.getConformPassword());
				companys.setContectNumber(company.getContectNumber());
				companys.setemailAddress(company.getemailAddress());
				companys.setfirstName(company.getfirstName());
				companys.setlastName(company.getlastName());
				companys.setPassword(company.getPassword());
				companys.setAddress(company.getAddress());
				companys.setS_No(++sNo);
				companyListBO.add(companys);
			}
			companyListBO.forEach(company->{
				CompanyBO companybo=new CompanyBO();
				companybo.setCompanyId(company.getCompanyId());
				companybo.setCompanyName(company.getCompanyName());
				companybo.setCompanyPersonName(company.getCompanyPersonName());
				companybo.setCompanyType(company.getCompanyType());
				companybo.setCompanyWebSite(company.getCompanyWebSite());
				companybo.setConformEmailAddress(company.getConformEmailAddress());
				companybo.setConformPassword(company.getConformPassword());
				companybo.setContectNumber(company.getContectNumber());
				companybo.setemailAddress(company.getemailAddress());
				companybo.setfirstName(company.getfirstName());
				companybo.setlastName(company.getlastName());
				companybo.setPassword(company.getPassword());
				companybo.setAddress(company.getAddress());
				companybo.setS_No(company.getS_No());
				listcompanyBO.add(companybo);
			});
		}
		return listcompanyBO;
	}
	@Override
	public boolean isValidCompanyDetails(CompanyBO companyBO) {
		// TODO Auto-generated method stub
		CompanyVO companyVo=new CompanyVO();
		companyVo.setemailAddress(companyBO.getemailAddress());
		companyVo.setCompanyName(companyBO.getCompanyName());
		companyVo.setCompanyWebSite(companyBO.getCompanyWebSite());
		companyVo.setIsDelete(false);
		return companydaoDaoImpl.isValidCompanyDetails(companyVo);
	}

	@Override
	public boolean companyNameValidations(String companyName) {
		// TODO Auto-generated method stub
		CompanyVO companyVo=new CompanyVO();
		companyVo.setCompanyName(companyName);
		companyVo.setIsDelete(false);
		return companydaoDaoImpl.companyNameValidations(companyVo);
	}
	@Override
	public boolean emailAddressValidations(String emailAddress) {
		// TODO Auto-generated method stub
		CompanyVO companyVo=new CompanyVO();
		companyVo.setemailAddress(emailAddress);
		companyVo.setIsDelete(false);
		return companydaoDaoImpl.emailAddressValidations(companyVo);
	}
	@Override
	public boolean websiteValidations(String website) {
		// TODO Auto-generated method stub
		CompanyVO companyVo=new CompanyVO();
		companyVo.setCompanyWebSite(website);
		companyVo.setIsDelete(false);
		return companydaoDaoImpl.websiteValidations(companyVo);
	}
	@Override
	public CompanyBO getCompanyUserObject(CompanyBO company) {
		// TODO Auto-generated method stub
		
		CompanyVO companyVO=new CompanyVO();
		CompanyBO companyBO=new CompanyBO();
		companyVO.setCompanyId(company.getCompanyId());
/*		companyVO.setemailAddress(company.getemailAddress());
*/		companyVO=companydaoDaoImpl.getCompanyUserObject(companyVO);
		if(null!=companyVO) {
			companyBO.setCompanyId(companyVO.getCompanyId());
			companyBO.setCompanyName(companyVO.getCompanyName());
			companyBO.setCompanyPersonName(companyVO.getCompanyPersonName());
			companyBO.setfirstName(companyVO.getfirstName());
			companyBO.setlastName(companyVO.getlastName());
			companyBO.setemailAddress(companyVO.getemailAddress());
			/*String password=EncryptAndDecrypt.decrypt(companyVO.getPassword(),
					EncryptAndDecrypt.TOKEN );*/
			companyBO.setConformEmailAddress(companyVO.getConformEmailAddress());
			companyBO.setPassword(companyVO.getPassword());
			companyBO.setConformPassword(companyVO.getConformPassword());
			companyBO.setCompanyWebSite(companyVO.getCompanyWebSite());
			companyBO.setCompanyType(companyVO.getCompanyType());
			companyBO.setContectNumber(companyVO.getContectNumber());
			companyBO.setAddress(companyVO.getAddress());
			companyBO.setIsDelete(companyVO.getIsDelete());
			companyBO.setSending_status(companyVO.getSending_status());
			companyBO.setImageName(companyVO.getImageName());
			companyBO.setIndustry(companyVO.getIndustry());
		}
		return companyBO;
		
		
	}
	@Override
	public boolean updateCompanyUser(CompanyBO companyBo1, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
		CompanyVO companyvo=new CompanyVO();
		CompanyVO campanyVo=new CompanyVO();
		if(0!=companyBo1.getCompanyId()){
			companyvo.setCompanyId(companyBo1.getCompanyId());
			campanyVo=companydaoDaoImpl.getCompanyUser(companyvo);
		}
		companyvo.setImageName(campanyVo.getImageName());
		companyvo.setCreatedTime(campanyVo.getCreatedTime());
		companyvo.setCompanyId(companyBo1.getCompanyId());
		companyvo.setCompanyName(companyBo1.getCompanyName());
		companyvo.setCompanyPersonName(companyBo1.getCompanyPersonName());
		companyvo.setfirstName(companyBo1.getfirstName());
		companyvo.setlastName(companyBo1.getlastName());
		companyvo.setemailAddress(companyBo1.getemailAddress());
		companyvo.setConformEmailAddress(companyBo1.getConformEmailAddress());
		/*String password=EncryptAndDecrypt.encrypt(companyBo.getPassword(), 
				EncryptAndDecrypt.TOKEN);*/
		companyvo.setConformPassword(campanyVo.getPassword());
		companyvo.setPassword(campanyVo.getConformPassword());
		companyvo.setCompanyWebSite(companyBo1.getCompanyWebSite());
		companyvo.setCompanyType(companyBo1.getCompanyType());
		companyvo.setContectNumber(companyBo1.getContectNumber());
		companyvo.setAddress(companyBo1.getAddress());
		companyvo.setIsDelete(false);
		companyvo.setSending_status(true);
		companyvo.setModifiedTime(new Date());
		companyvo.setIndustry(companyBo1.getIndustry());
		boolean status=companydaoDaoImpl.updatecompanyUser(companyvo);
		if(status){
			LoginVO loginVO=new LoginVO();
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(companyBo1.getCompanyId());
			loginVO.setCompanyVO(companyVO);
			loginVO.setEmailAddress(companyBo1.getemailAddress());
			/*String password1=EncryptAndDecrypt.encrypt(companyBo.getPassword(), 
					EncryptAndDecrypt.TOKEN);*/
			loginVO.setPassword(campanyVo.getPassword());
			loginVO.setIsDelete(false);
			loginVO.setIsActive(true);
			loginVO.setModifiedTime(new Date());
			loginVO.setCreatedTime(campanyVo.getCreatedTime());
			loginVO.setUserType("company");
			boolean value=companydaoDaoImpl.updateLoginByCompany(loginVO);
			if(value){
				return true;
			}
		}
		return true;
		
		
	}
	@Override
	public CompanyBO getCompanyUserobject(CompanyBO company) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		CompanyBO companyBO=new CompanyBO();
		companyVO.setCompanyId(company.getCompanyId());
		companyVO=companydaoDaoImpl.getCompanyuserObject(companyVO);
		if(null!=companyVO) {
			companyBO.setCompanyId(companyVO.getCompanyId());
			companyBO.setCompanyName(companyVO.getCompanyName());
			companyBO.setCompanyPersonName(companyVO.getCompanyPersonName());
			companyBO.setfirstName(companyVO.getfirstName());
			companyBO.setlastName(companyVO.getlastName());
			companyBO.setemailAddress(companyVO.getemailAddress());
			/*String password=EncryptAndDecrypt.decrypt(companyVO.getPassword(),
					EncryptAndDecrypt.TOKEN );*/
			companyBO.setConformEmailAddress(companyVO.getConformEmailAddress());
			companyBO.setPassword(companyVO.getPassword());
			companyBO.setConformPassword(companyVO.getConformPassword());
			companyBO.setCompanyWebSite(companyVO.getCompanyWebSite());
			companyBO.setCompanyType(companyVO.getCompanyType());
			companyBO.setContectNumber(companyVO.getContectNumber());
			companyBO.setAddress(companyVO.getAddress());
			companyBO.setIsDelete(companyVO.getIsDelete());
			companyBO.setSending_status(companyVO.getSending_status());
			companyBO.setImageName(companyVO.getImageName());
			companyBO.setIndustry(companyVO.getIndustry());
		}
		return companyBO;
	}
	
	

}
