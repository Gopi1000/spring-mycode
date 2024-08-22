package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.vo.CustomerVO;

public interface CustomerDao {

	CustomerVO saveCustomer(CustomerVO customerVO);

	//List<CustomerBo> retriveCustomerDao();

	CustomerVO editcustomerdetails(CustomerVO customerVO);

	Boolean updateCustomer(CustomerVO customerVO);

	Boolean deleteCustomer(CustomerVO customerVO);

	List<CustomerVO> retriveCustomerDao(CustomerVO customerVO);

	CustomerBO viewCustomerDetails(CustomerVO customerVO);

	CustomerVO checkCustomer(CustomerVO customerVO);

	//List<CustomerVO> getListOfComapnyCustomer(CustomerVO customerVO);

	List<CustomerVO> listOfPageCustomer(CustomerVO customerVO);

	List<CustomerVO> searchCustomer(CustomerVO customerVO);

	List<CustomerVO> totalSearchCustomer(CustomerVO customerVO);

	boolean emailValidations(CustomerVO customerVO);

	boolean mobileValidations(CustomerVO customerVO);

	boolean whatsAppValidations(CustomerVO customerVO);

	boolean checking(CustomerVO customerVO);

	boolean mobileNumberChecking(CustomerVO customerVO);

	boolean whatsappNumberChecking(CustomerVO customerVO);

	boolean emailIdCheck(CustomerVO customerVO);

	boolean mobileNumberCheck(CustomerVO customerVO);

	boolean whatsappNumberCheck(CustomerVO customerVO);

	//List<CustomerVO> getListSpecialization(CustomerVO customerVO);

}
