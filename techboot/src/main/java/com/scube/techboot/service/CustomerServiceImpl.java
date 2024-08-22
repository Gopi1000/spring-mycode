package com.scube.techboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.dao.CustomerDao;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CustomerVO;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {


	@Autowired
	private CustomerDao customerDao;

	@Override
	public CustomerBO saveCustomerDetails(CustomerBO customerBo) {
		// TODO Auto-generated method stub

		CustomerVO customerVO=new CustomerVO();
		CompanyVO companyVO=new CompanyVO();
		CustomerBO customerBO=new CustomerBO();
		BeanUtils.copyProperties(customerBo, customerVO);
		int id=customerBo.getCompanyBO().getCompanyId();
		companyVO.setCompanyId(id);
		customerVO.setCompanyVO(companyVO);
		customerVO.setSending_status(true);
		customerVO.setIsDelete(false);
		customerVO.setCreatedTime(new Date());
		customerVO=customerDao.saveCustomer(customerVO);
		if(0!=customerVO.getCustomerId()){
			customerBO.setCustomerId(customerVO.getCustomerId());
		}
		return customerBO;
	}

	@Override
	public List<CustomerBO> retriveCustomer(CustomerBO customerBo) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		List<CustomerVO> customerList=new ArrayList<CustomerVO>();
		List<CustomerBO> customerListBO=new ArrayList<CustomerBO>();
		//comapny
		if(null!=customerBo.getCompanyBO()){
			int id=customerBo.getCompanyBO().getCompanyId();
			CompanyVO companyVO=new CompanyVO();
			companyVO.setCompanyId(id);
			customerVO.setCompanyVO(companyVO);
		}
		customerVO.setIsDelete(false);
		customerList=customerDao.retriveCustomerDao(customerVO);
		if(customerList!=null && customerList.size()>0&& !customerList.isEmpty()){
			//for(CustomerVO customerVO:listvo){
			customerList.forEach(customer->{
				CustomerBO customerBO=new CustomerBO();
				customerBO.setCustomerId(customerVO.getCustomerId());
				customerBO.setFirstName(customerVO.getFirstName());
				customerBO.setEmailId(customerVO.getEmailId());
				//customerBO.setCustomerType(customerVO.getCustomerType());
				customerBO.setAddress(customerVO.getAddress());
				customerBO.setMobileNumber(customerVO.getMobileNumber());
				customerBO.setWhatsappNumber(customerVO.getWhatsappNumber());
				customerBO.setIsDelete(customerVO.getIsDelete());
				customerBO.setSending_status(customerVO.getSending_status());
				customerBO.setLastName(	customerVO.getLastName());
				customerBO.setDateOfBirth(customerVO.getDateOfBirth());

				customerListBO.add(customerBO);

			});
		}

		return customerListBO;
	}

	@Override
	public CustomerBO editCustomer(CustomerBO customer) {
		// TODO Auto-generated method stub
		CustomerBO customerBO=new CustomerBO();
		CustomerVO customerVO=new CustomerVO();
		BeanUtils.copyProperties(customer, customerVO);
		customerVO=customerDao.editcustomerdetails(customerVO);
		if(null!=customerVO){
			BeanUtils.copyProperties(customerVO, customerBO);
			CompanyBO companyBo=new CompanyBO();
			companyBo.setCompanyName(customerVO.getCompanyVO().getCompanyName());
			customerBO.setCompanyBO(companyBo);
		}
		return customerBO;
	}

	@Override
	public Boolean updateCustomer(CustomerBO customerBo){
		// TODO Auto-generated method stub

		CustomerVO customerVO=new CustomerVO();
		CustomerVO customerVo=new CustomerVO();
		CompanyVO companyVO=new CompanyVO();
		if(0!=customerBo.getCustomerId()){
			customerVO.setCustomerId(customerBo.getCustomerId());
			customerVo=customerDao.checkCustomer(customerVO);
		}
		BeanUtils.copyProperties(customerBo, customerVO);
		int id=customerVo.getCompanyVO().getCompanyId();
		companyVO.setCompanyId(id);
		customerVO.setCompanyVO(companyVO);
		customerVO.setIsDelete(customerVo.getIsDelete());
		customerVO.setSending_status(customerVo.getSending_status());
		customerVO.setCreatedTime(customerVo.getCreatedTime());
		customerVO.setCreatedBy(customerVo.getCreatedBy());
		customerVO.setModifiedTime(new Date());
		return customerDao.updateCustomer(customerVO);
	}

	@Override
	public Boolean deleteCustomer(CustomerBO customer) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		BeanUtils.copyProperties(customer, customerVO);
		customerVO.setSending_status(false);
		customerVO.setIsDelete(true);
		return customerDao.deleteCustomer(customerVO);
	}

	@Override
	public CustomerBO viewCustomerDetails(CustomerBO customer) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		BeanUtils.copyProperties(customer, customerVO);
		return customerDao.viewCustomerDetails(customerVO);
	}

	/*@Override
	public List<CustomerBO> retriveCompanyCustomer(CustomerBO customerBo) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		CompanyVO companyVO=new CompanyVO();
		List<CustomerVO> listCustomerVO=new ArrayList<CustomerVO>();
		List<CustomerBO> lustomerListBO=new ArrayList<CustomerBO>();
		customerVO.setIsDelete(customerBo.getIsDelete());
		int id=customerBo.getCompanyBO().getCompanyId();
		companyVO.setCompanyId(id);
		customerVO.setCompanyVO(companyVO);
		listCustomerVO= customerDao.getListOfComapnyCustomer(customerVO);
		return null;
	}*/

	@Override
	public List<CustomerBO> listOfPageCustomer(CustomerBO customerBO) {
		// TODO Auto-generated method stub
		List<CustomerVO> customerListVO=new ArrayList<CustomerVO>();
		List<CustomerBO> companylistBO=new ArrayList<CustomerBO>();
		List<CustomerVO> companyList=new ArrayList<CustomerVO>();
		CustomerVO customerVO=new CustomerVO();
	
		//comapny
		if(null!=customerBO.getCompanyBO()){
			CompanyVO companyVO=new CompanyVO();
			int id=customerBO.getCompanyBO().getCompanyId();
			companyVO.setCompanyId(id);
			customerVO.setCompanyVO(companyVO);
		}
		customerVO.setRecordIndex(customerBO.getRecordIndex());
		customerVO.setMaxRecord(customerBO.getMaxRecord());
		customerVO.setIsDelete(false);
		customerListVO= customerDao.listOfPageCustomer(customerVO);
		if(customerListVO!=null && customerListVO.size()>0 && !customerListVO.isEmpty()) {
			//for(CustomerVO customerVO:listvo){
			int s_No=customerVO.getRecordIndex();
			for(CustomerVO customer:customerListVO){
				customerVO=new CustomerVO ();
				customerVO.setCustomerId(customer.getCustomerId());
				customerVO.setFirstName(customer.getFirstName());
				customerVO.setEmailId(customer.getEmailId());
				//customerVO.setCustomerType(customer.getCustomerType());
				customerVO.setAddress(customer.getAddress());
				customerVO.setMobileNumber(customer.getMobileNumber());
				customerVO.setWhatsappNumber(customer.getWhatsappNumber());
				customerVO.setIsDelete(customer.getIsDelete());
				customerVO.setSending_status(customer.getSending_status());
				customerVO.setLastName(	customer.getLastName());
				customerVO.setDateOfBirth(customer.getDateOfBirth());
				customerVO.setS_No(++s_No);
				companyList.add(customerVO);
			}
			companyList.forEach(customer->{
				CustomerBO customerBo=new CustomerBO();
				customerBo.setCustomerId(customer.getCustomerId());
				customerBo.setFirstName(customer.getFirstName());
				customerBo.setEmailId(customer.getEmailId());
				//customerBo.setCustomerType(customer.getCustomerType());
				customerBo.setAddress(customer.getAddress());
				customerBo.setMobileNumber(customer.getMobileNumber());
				customerBo.setWhatsappNumber(customer.getWhatsappNumber());
				customerBo.setIsDelete(customer.getIsDelete());
				customerBo.setSending_status(customer.getSending_status());
				customerBo.setLastName(	customer.getLastName());
				customerBo.setDateOfBirth(customer.getDateOfBirth());
				customerBo.setS_No(customer.getS_No());
				companylistBO.add(customerBo);
			});
		}
		return companylistBO;
	}

	@Override
	public List<CustomerBO> searchCustomer(CustomerBO customerBo) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		List<CustomerVO> listCustomerVO=new ArrayList<CustomerVO>();
		List<CustomerBO> listCustomerBO=new ArrayList<CustomerBO>();
		List<CustomerBO> listCustomer=new ArrayList<CustomerBO>();
		int s_No=customerBo.getRecordIndex();
		customerVO.setIsDelete(false);
		if(null!=customerBo.getEmailId() && !customerBo.getEmailId().isEmpty() ){
			customerVO.setEmailId(customerBo.getEmailId());
		}
		 if(null!=customerBo.getFirstName() && !customerBo.getFirstName().isEmpty() ){
			customerVO.setFirstName(customerBo.getFirstName());
			customerVO.setMaxRecord(customerBo.getMaxRecord());
			customerVO.setRecordIndex(customerBo.getRecordIndex());
		}
		if(null!=customerBo.getMobileNumber()){
			customerVO.setMobileNumber(customerBo.getMobileNumber());
		}
		listCustomerVO=customerDao.searchCustomer(customerVO);
		if(null!=listCustomerVO && !listCustomerVO.isEmpty() && listCustomerVO.size()>0){

			for(CustomerVO customer:listCustomerVO){
				CustomerBO customerBO=new CustomerBO();
				customerBO.setCustomerId(customer.getCustomerId());
				customerBO.setFirstName(customer.getFirstName());
				customerBO.setEmailId(customer.getEmailId());
				//customerBO.setCustomerType(customer.getCustomerType());
				customerBO.setAddress(customer.getAddress());
				customerBO.setMobileNumber(customer.getMobileNumber());
				customerBO.setWhatsappNumber(customer.getWhatsappNumber());
				customerBO.setIsDelete(customer.getIsDelete());
				customerBO.setSending_status(customer.getSending_status());
				customerBO.setLastName(	customer.getLastName());
				customerBO.setDateOfBirth(customer.getDateOfBirth());
				customerBO.setS_No(++s_No);
				listCustomerBO.add(customerBO);
			}
			listCustomerBO.forEach(customers->{
				CustomerBO customerBO=new CustomerBO();
				customerBO.setCustomerId(customers.getCustomerId());
				customerBO.setFirstName(customers.getFirstName());
				customerBO.setEmailId(customers.getEmailId());
				//customerBO.setCustomerType(customers.getCustomerType());
				customerBO.setAddress(customers.getAddress());
				customerBO.setMobileNumber(customers.getMobileNumber());
				customerBO.setWhatsappNumber(customers.getWhatsappNumber());
				customerBO.setIsDelete(customers.getIsDelete());
				customerBO.setSending_status(customers.getSending_status());
				customerBO.setLastName(	customers.getLastName());
				customerBO.setDateOfBirth(customers.getDateOfBirth());
				customerBO.setS_No(customers.getS_No());
				listCustomer.add(customerBO);
			});
		}
		return listCustomer;
	}

	@Override
	public List<CustomerBO> totalSearchCustomer(CustomerBO customerBo) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		List<CustomerVO> listCustomerVO=new ArrayList<CustomerVO>();
		List<CustomerBO> listCustomerBO=new ArrayList<CustomerBO>();
		customerVO.setIsDelete(false);
		if(null!=customerBo.getEmailId() && !customerBo.getEmailId().isEmpty() ){
			customerVO.setEmailId(customerBo.getEmailId());
		}
		else if(null!=customerBo.getFirstName() && !customerBo.getFirstName().isEmpty() ){
			customerVO.setFirstName(customerBo.getFirstName());

		}
		else{
			customerVO.setMobileNumber(customerBo.getMobileNumber());
		}
		listCustomerVO=customerDao.totalSearchCustomer(customerVO);
		if(null!=listCustomerVO && !listCustomerVO.isEmpty() && listCustomerVO.size()>0){
			listCustomerVO.forEach(customer->{
				CustomerBO customerBO=new CustomerBO();
				customerBO.setCustomerId(customer.getCustomerId());
				customerBO.setFirstName(customer.getFirstName());
				customerBO.setEmailId(customer.getEmailId());
				//customerBO.setCustomerType(customer.getCustomerType());
				customerBO.setAddress(customer.getAddress());
				customerBO.setMobileNumber(customer.getMobileNumber());
				customerBO.setWhatsappNumber(customer.getWhatsappNumber());
				customerBO.setIsDelete(customer.getIsDelete());
				customerBO.setSending_status(customer.getSending_status());
				customerBO.setLastName(	customer.getLastName());
				customerBO.setDateOfBirth(customer.getDateOfBirth());
				listCustomerBO.add(customerBO);
			});
		}
		return listCustomerBO;
	}

	@Override
	public boolean emailValidations(CustomerBO customerBO) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(customerBO.getCompanyBO().getCompanyId());
		CustomerVO customerVO=new CustomerVO();
		customerVO.setCompanyVO(companyVO);
		customerVO.setEmailId(customerBO.getEmailId());
		customerVO.setIsDelete(false);
		return customerDao.emailValidations(customerVO);
	}

	@Override
	public boolean mobileValidations(CustomerBO customerBO) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(customerBO.getCompanyBO().getCompanyId());
		CustomerVO customerVO=new CustomerVO();
		customerVO.setCompanyVO(companyVO);
		customerVO.setMobileNumber(customerBO.getMobileNumber());
		customerVO.setIsDelete(false);
		return customerDao.mobileValidations(customerVO);
	}

	@Override
	public boolean whatsAppValidations(CustomerBO customerBO) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		companyVO.setCompanyId(customerBO.getCompanyBO().getCompanyId());
		CustomerVO customerVO=new CustomerVO();
		customerVO.setCompanyVO(companyVO);
		customerVO.setWhatsappNumber(customerBO.getWhatsappNumber());
		customerVO.setIsDelete(false);
		return customerDao.whatsAppValidations(customerVO);
	}

	@Override
	public boolean emailcheck(String emailId) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		customerVO.setEmailId(emailId);
		
		customerVO.setIsDelete(false);
		
		return customerDao.checking(customerVO);
	}

	/*
	 * @Override public boolean mobilecheck(CustomerBO customerBo) { // TODO
	 * Auto-generated method stub
	 * 
	 * CustomerVO customerVO=new CustomerVO();
	 * customerVO.setMobileNumber(customerBo.getMobileNumber());
	 * customerVO.setIsDelete(false); 
	 * return customerDao.mobileChecking(customerVO);
	 * }
	 */

	/*
	 * @Override public boolean whatsappCheck(CustomerBO customerBo) { // TODO
	 * Auto-generated method stub
	 * 
	 * CustomerVO customerVO=new CustomerVO();
	 * customerVO.setWhatsappNumber(customerBo.getWhatsappNumber());
	 * customerVO.setIsDelete(false); return
	 * customerDao.whatsappChecking(customerVO);
	 * 
	 * }
	 */

	@Override
	public boolean mobilecheck(Long mobileNumber) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		customerVO.setMobileNumber(mobileNumber);
		customerVO.setIsDelete(false);
		
		return customerDao.mobileNumberChecking(customerVO);
	}

	@Override
	public boolean whatsappCheck(Long whatsappNumber) {
		// TODO Auto-generated method stub
	
		CustomerVO customerVO=new CustomerVO();
		customerVO.setWhatsappNumber(whatsappNumber);
		customerVO.setIsDelete(false);
		
		return customerDao.whatsappNumberChecking(customerVO);
	}

	@Override
	public boolean emailIdCheck(CustomerBO customerBO) {
		// TODO Auto-generated method stub
		CustomerVO customerVO=new CustomerVO();
		
		customerVO.setEmailId(customerBO.getEmailId());
		customerVO.setIsDelete(false);
		
		
		return customerDao.emailIdCheck(customerVO);
	}

	@Override
	public boolean mobileNumberCheck(CustomerBO customerBO) {
		// TODO Auto-generated method stub
CustomerVO customerVO=new CustomerVO();
		
		customerVO.setMobileNumber(customerBO.getMobileNumber());
		customerVO.setIsDelete(false);
		
		
		return customerDao.mobileNumberCheck(customerVO);
		
	}

	@Override
	public boolean whatsappNumberCheck(CustomerBO customerBO) {
		// TODO Auto-generated method stub
		
		CustomerVO customerVO=new CustomerVO();
		customerVO.setWhatsappNumber(customerBO.getWhatsappNumber());
		customerVO.setIsDelete(false);
		
		
		return customerDao.whatsappNumberCheck(customerVO);
		
	}

	}



