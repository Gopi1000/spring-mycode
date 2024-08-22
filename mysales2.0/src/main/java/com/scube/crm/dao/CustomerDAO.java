package com.scube.crm.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.ContactBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.Contact;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Customer;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.FollowUp;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.SalesOrderVO;

/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface CustomerDAO {

	User authendicate(String string, String emailAddress) throws MyClientsException;

	public boolean createAccessLog(AccessLogVO logVO);

	/**
	 * @param adminVO
	 * @return
	 */
	long createuser(User adminVO) throws MyClientsException;

	List<AdminUserBO> retrieveUser() throws MyClientsException;

	User userStatus(User userVO) throws MyClientsException;

	Customer deleteProfile(Customer vo) throws MyClientsException;

	User updateuser(User loginVO) throws MyClientsException;

	User getuserId(AdminUserBO adminBO) throws MyClientsException;
	
	boolean findEmployerEmail(String emailAddress);

	
	EmailAccess saveEmailList(List<EmailAccess> accessVOList);

	
	List<String> getImageName();

	LoginStatusVO editLoginStatus(LoginStatusVO loginStatusVO);

	long addLoginStatus(LoginStatusVO loginStatus);


	boolean mobileNoVerification(String mobileNo);

	int deleteCustomer(Customer customerVO);

	Customer createCustomer(Customer customerVO);

	ClientBO retriveCustomerById(ClientBO registerBO) throws MyClientsException,  SQLException;

	ClientBO retriveCustomer(ClientBO reteriveprofile) throws MyClientsException,  SQLException;

	Customer getuserId(ClientBO registerBO);

	Customer updateEmployer(Customer employerVO);

	FollowUp saveTracking(FollowUp vO);

	List<FollowUp> retrieveTracking(long customerId);
	//List<Contact> retrieveTracking(long customerId);

	List<FollowUp> searchRetrieveTracking(FollowUp leadsFollowup);
	List<ProductServiceVO> getProductList();

	ProductServiceVO getProductPrice(long staffId);

	GstVO getGstValues();

	long getProductServiceId(String serviceName);

	long createSalesOrder(SalesOrderVO salesOrderVO);

	String getSalesOrderNo();

	List<ClientBO> retriveClients();

	

	// mine

	Contact insertCustomers(Contact contactvo); 

	List<Contact> retriveContact(); 

	Contact getParticularValue(long Id); 

	Contact getParticularContact(long editId); 

	String deleteContact(Contact contactvo); 

	String updateContact(Contact contactvo); 

	
}
