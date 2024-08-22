/**
 * 
 */
package com.scube.crm.service;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.ContactBO;
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.SalesOrderBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.Customer;
import com.scube.crm.vo.LoginStatusVO;

/**
 * @author Administrator
 * 
 */
public interface CustomerService {

	AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException;

	boolean sendClientMail(ClientBO employerRegisterBO);

	boolean addLoginStatus(String username) throws MyClientsException;

	public boolean createAccessLog(AccessLogBO logBO);

	boolean editLoginStatus(LoginStatusVO loginStatusVO);

	List<AdminUserBO> retrieveUser() throws MyClientsException;

	boolean mobileNoVerification(String mobileNo);

	ClientBO createCustomer(ClientBO profileBO);
	
	Customer createCustomer(Customer customer)throws MyClientsException;

	AdminUserBO deleteProfile(AdminUserBO deleteProfile) throws MyClientsException;

	ClientBO updateEmployer(ClientBO registerBO);

	ClientBO retriveCustomer(ClientBO reteriveprofile) throws MyClientsException;

	boolean findEmployerEmail(String emailAddress);

	ClientBO deleteCustomer(ClientBO deleteProfile)  throws MyClientsException;

	boolean userStatus(AdminUserBO userBO) throws MyClientsException;

	ClientBO deleteProfile(ClientBO deleteProfile);

	ClientBO retriveCustomerById(ClientBO registerBO) throws MyClientsException, SerialException, SQLException;

	ClientBO saveTracking(ClientBO bO);

	List<ClientBO> searchRetrieveTracking(ClientBO registerBO);
	List<ProductServiceBO> getProductList();

	ProductServiceBO getProductPrice(Long price);

	GstBO getGstValues();

	long createSalesOrder(SalesOrderBO salesOrderBOS);

	String getSalesOrderNo();

	List<ClientBO> retriveClients();
    
	
	// mine
	ContactBO insertCustomers(ContactBO contactbo); 

	List<ContactBO> retriveContact(List<ContactBO> contactbo1); 

	ContactBO getParticularContact(long editId); 

	String deleteContact(long deleteId);

	//ContactBO updateContact(ContactBO contactbo);

	String updateContact(ContactBO contactbo); 

	//ContactBO updateContact(ContactBO contactbo);

	

}
