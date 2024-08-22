package com.scube.techboot.service;

import java.util.List;

import com.scube.techboot.bo.CustomerBO;

public interface CustomerService {

	CustomerBO saveCustomerDetails(CustomerBO customerBo);

	List<CustomerBO> retriveCustomer(CustomerBO customerBo);

	CustomerBO editCustomer(CustomerBO customer);

	Boolean updateCustomer(CustomerBO customerBo);

	Boolean deleteCustomer(CustomerBO customer);

	CustomerBO viewCustomerDetails(CustomerBO customer);

	//List<CustomerBO> retriveCompanyCustomer(CustomerBO customerBo);

	List<CustomerBO> listOfPageCustomer(CustomerBO customerBO);

	List<CustomerBO> searchCustomer(CustomerBO customerBo);

	List<CustomerBO> totalSearchCustomer(CustomerBO customerBo);

	boolean emailValidations(CustomerBO customerBO);

	boolean mobileValidations(CustomerBO customerBO);

	boolean whatsAppValidations(CustomerBO customerBO);

	boolean emailcheck(String emailId);

	boolean mobilecheck(Long mobileNumber);

	boolean whatsappCheck(Long whatsappNumber);

	boolean emailIdCheck(CustomerBO customerBO);

	boolean mobileNumberCheck(CustomerBO customerBO);

	boolean whatsappNumberCheck(CustomerBO customerBO);

	//st<CustomerBO> getSpecification(String value);

	

	

}
