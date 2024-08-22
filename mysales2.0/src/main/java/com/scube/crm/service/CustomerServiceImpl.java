package com.scube.crm.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.rowset.serial.SerialException;

import org.jsoup.select.Evaluator.IsEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.ContactBO;
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.SalesOrderBO;
import com.scube.crm.dao.CustomerDAO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.model.EmailModel;
import com.scube.crm.utils.DateHelper;
import com.scube.crm.utils.SendEmailServiceImpl;
import com.scube.crm.utils.SuccessMsg;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.Contact;
import com.scube.crm.vo.User;

import com.scube.crm.vo.Customer;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.FollowUp;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.SalesOrderVO;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CustomerServiceImpl.class);
	// DAo layer annotations
	@Autowired
	private CustomerDAO customerDAO;
	
	static boolean isApproval = true;
	EmailModel model = new EmailModel();

	@Autowired
	private SendEmailServiceImpl emailManager;

	@Autowired
	private MessageSource messageSource;
	

	@Override
	public AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException {
		CustomerServiceImpl.LOGGER.entry();

		final AdminLoginBO adminLogin = new AdminLoginBO();
		try {
			final User user = this.customerDAO.authendicate("emailAddress", adminLoginBO.getEmailAddress());
			
			if (adminLoginBO.getPassword().equals(user.getPassword())) {
				if (user.isActive()) {
					BeanUtils.copyProperties(user, adminLogin);
					String userName = adminLoginBO.getEmailAddress();
					addLoginStatus(userName);
					adminLogin.setActive(true);
				} else {
					adminLogin.setActive(false);
				}
			} else {
				adminLogin.setActive(false);
			}
			// adminLogin.setPassword(password);

			/* */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return adminLogin;
	}

	@Override
	public boolean editLoginStatus(LoginStatusVO loginStatusVO) {
		customerDAO.editLoginStatus(loginStatusVO);
		return false;

	}

	@Override
	public boolean addLoginStatus(String username) throws MyClientsException {

		LoginStatusVO loginStatus = new LoginStatusVO();
		loginStatus.setUserName(username);
		loginStatus.setType("Admin");
		loginStatus.setStatus(true);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date date = cal.getTime();
		loginStatus.setStartTime(date);
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, 1);
		Date date1 = cal.getTime();
		loginStatus.setEndTime(date1);
		loginStatus.setActivity("login");
		customerDAO.addLoginStatus(loginStatus);

		return false;
	}

	@Override
	public boolean createAccessLog(AccessLogBO logBO) {
		boolean accessStatus = false;
		try {
			AccessLogVO logVO = new AccessLogVO();
			logVO.setAccessId(logBO.getAccessId());
			logVO.setAccessDate(logBO.getAccessDate());
			logVO.setClientIP(logBO.getClientIP());
			logVO.setSessionId(logBO.getSessionId());
			accessStatus = customerDAO.createAccessLog(logVO);
		} catch (Exception e) {

		}
		return accessStatus;
	}

	
	@Override
	public List<AdminUserBO> retrieveUser() throws MyClientsException {
		List<AdminUserBO> BOList = new ArrayList<AdminUserBO>();
		BOList = customerDAO.retrieveUser();
		return BOList;
	}

	
	
	
	
	
	
	
	
	//mine
	
	@Override
	public ContactBO insertCustomers(ContactBO contactbo) {
		
		Contact contactvo=new Contact();
		
		contactvo.setContactid(contactbo.getContactid());
	    contactvo.setPrimaryaddress(contactbo.getPrimaryaddress());
		contactvo.setPermanentcontactnumber(contactbo.getPermanentcontactnumber());
		contactvo.setPermanentaddress(contactbo.getPermanentaddress());
		contactvo.setPrimarycontactnumber(contactbo.getPrimarycontactnumber());
		
		
		if(null!=contactbo.getClientbo()){
			  Customer customer =new Customer();
		long id=contactbo.getClientbo().getId();
			customer.setId(id);  
			contactvo.setCustomerownername(contactbo.getClientbo().getFirstName());
			contactvo.setCustomerVO(customer);
	    }
		
		contactvo= customerDAO.insertCustomers(contactvo); 
		contactbo.setContactid(contactvo.getContactid());
		                                                                            
        return contactbo;
		   
		}
			
	
	
	public List<ContactBO> retriveContact(List<ContactBO> contactbo1) {

		Contact vo = new Contact();

		List<ContactBO> cars = new ArrayList<ContactBO>();

		List<Contact> car = new ArrayList<Contact>();

		car = customerDAO.retriveContact();

		int sno = 1;
		for (Contact contactvo : car) {

			ContactBO contactBo = new ContactBO();

			contactBo.setContactid(contactvo.getContactid());
			contactBo.setPrimaryaddress(contactvo.getPrimaryaddress());
			contactBo.setPermanentaddress(contactvo.getPermanentaddress());
			contactBo.setPrimarycontactnumber(contactvo.getPrimarycontactnumber());
			contactBo.setPermanentcontactnumber(contactvo.getPermanentcontactnumber());
			contactBo.setCustomerownername(contactvo.getCustomerownername());
			contactBo.setsNo(sno++);

			cars.add(contactBo); 
		}

		return cars;

	}
	
	
	public ContactBO getParticularValue(long id) {
		
		
		Contact contactVO=new Contact();
		ContactBO contactBo=new ContactBO();
		
		contactVO=customerDAO.getParticularValue(id);
		
		if(null!=contactVO) {
			
		
		 contactBo.setPrimaryaddress(contactVO.getPrimaryaddress());
		 contactBo.setPermanentaddress(contactVO.getPermanentaddress());
		 contactBo.setPrimarycontactnumber(contactVO.getPrimarycontactnumber());
		 contactBo.setPermanentcontactnumber(contactVO.getPermanentcontactnumber());
		 contactBo.setCustomerownername(contactVO.getCustomerownername());
		}
		 
		return null;                                                                          
	}
	
	
	public ContactBO getParticularContact(long editId) {
		
		Contact contactVO=new Contact();
		ContactBO contactBo=new ContactBO();
		
		contactVO=customerDAO.getParticularContact(editId);
		
		if(null!=contactVO) {
			
	     contactBo.setContactid(contactVO.getContactid());
		 contactBo.setPrimaryaddress(contactVO.getPrimaryaddress());
		 contactBo.setPermanentaddress(contactVO.getPermanentaddress());
		 contactBo.setPrimarycontactnumber(contactVO.getPrimarycontactnumber());
		 contactBo.setPermanentcontactnumber(contactVO.getPermanentcontactnumber());
		 contactBo.setCustomerownername(contactVO.getCustomerownername());
		}        
		
		/*if(null!=contactBo.getClientbo()){
			  Customer customer =new Customer();
		long id=contactBo.getClientbo().getId();
			customer.setId(id);  
		}*/
		return contactBo;
	}
	
	
	
	
	
	
	
	
	public String deleteContact(long deleteId) {
        Contact contactvo=new Contact();
		
        contactvo.setContactid(deleteId);
		String contact=customerDAO.deleteContact(contactvo);
		
	    return contact;                                            
		}
	
	
	
	
	
	
	
	public String updateContact(ContactBO contactbo) {
		
	
		Contact contactvo=new Contact();
		
		contactvo.setContactid(contactbo.getContactid());
	    contactvo.setPrimaryaddress(contactbo.getPrimaryaddress());
		contactvo.setPermanentcontactnumber(contactbo.getPermanentcontactnumber());
		contactvo.setPermanentaddress(contactbo.getPermanentaddress());
		contactvo.setPrimarycontactnumber(contactbo.getPrimarycontactnumber());
		contactvo.setCustomerownername(contactbo.getCustomerownername());
		
		if(null!=contactbo.getClientbo()){
			  Customer customer =new Customer();
		long id=contactbo.getClientbo().getId();
			customer.setId(id);  
			//contactvo.setCustomerownername(contactbo.getClientbo().getFirstName());
			contactvo.setCustomerVO(customer);
	    }
	
		String respnse= customerDAO.updateContact(contactvo);
		contactbo.setContactid(contactvo.getContactid());
		
    	return respnse;                                                                
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean userStatus(AdminUserBO userBO) throws MyClientsException {
		boolean loginChanged = false;

		User userVO = new User();

		if (0 != userBO.getId()) {
			userVO.setId(userBO.getId());
			userVO.setActive(userBO.isActive());
			userVO = customerDAO.userStatus(userVO);
			if (0 != userVO.getId()) {
				loginChanged = true;
			}
		}
		return loginChanged;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.service.AdminService#DeleteProfile(com.scube.crm.bo.
	 * AdminUserBO)
	 */
	@Override
	public AdminUserBO deleteProfile(AdminUserBO deleteProfile) throws MyClientsException {
		CustomerServiceImpl.LOGGER.entry();

		 Customer VO = new Customer();
		try {
			VO.setId(deleteProfile.getId());
			VO.setIsDelete(true);
			VO = customerDAO.deleteProfile(VO);
			
		} catch (MyClientsException e) {
			
			e.printStackTrace();
		}

		return deleteProfile;
	}

	@Override
	public boolean findEmployerEmail(String emailAddress) {
		

		boolean validationEmail = customerDAO.findEmployerEmail(emailAddress);
		return validationEmail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.scube.crm.service.AdminService#sendClientMail(com.scube.crm.bo.ClientBO)
	 */
	@Override
	public boolean sendClientMail(ClientBO employerRegisterBO) {
		boolean alertStatus = false;
		try {
			EmailModel model = new EmailModel();

			final String[] toaddress = employerRegisterBO.getEmailAddress().split(",");
			final String subject = messageSource.getMessage("Validate.RegisterConfirm", null, null);
			for (int i = 0; i < toaddress.length; i++) {
				String bodycontent = "employerVerificationMail";
				model.setUrl("www.myjobkart.com/find-jobs.html?companyId=");
				model.setFirstname(employerRegisterBO.getFirstName());
				model.setEmail(employerRegisterBO.getEmailAddress());
				alertStatus = emailManager.sendEmail(toaddress[i], subject, bodycontent, model);
			}
			if (alertStatus) {
				EmailAccess accessVO = new EmailAccess();
				accessVO.setDate(new Date());
				accessVO.setEmailAddress(employerRegisterBO.getEmailAddress());
				accessVO.setMailedBy(employerRegisterBO.getEmployerId());
				accessVO.setStatus(alertStatus);
				accessVO.setMailTO(employerRegisterBO.getId());
				List<EmailAccess> accessVOList = new ArrayList<EmailAccess>();
				accessVOList.add(accessVO);

				if (null != accessVOList || accessVOList.size() > 0) {
					customerDAO.saveEmailList(accessVOList);
				}
			}

		} catch (final Exception ex) {
		}

		return alertStatus;
	}

	@Override
	public boolean mobileNoVerification(String mobileNo) {

		return customerDAO.mobileNoVerification(mobileNo);
	}

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	@Override
	public ClientBO createCustomer(ClientBO customerBO) {
		Customer customerVO= new Customer();


		if(null != customerBO.getCompanyName() && !customerBO.getCompanyName().isEmpty()){
			customerVO.setCompanyName(customerBO.getCompanyName());
		}
		if(null != customerBO.getFirstName() && !customerBO.getFirstName().isEmpty()){
			customerVO.setFirstName(
					customerBO.getFirstName());
		}
		if(null != customerBO.getLastName() && !customerBO.getLastName().isEmpty()){
			customerVO.setLastName(
					customerBO.getLastName());
		}
		if(null != customerBO.getWebsite() && !customerBO.getWebsite().isEmpty()){
			customerVO.setWebSite(
					customerBO.getWebsite());
		}
		/*
		 * if(null != profileBO.getConfirmEmailAddress() &&
		 * !profileBO.getConfirmEmailAddress().isEmpty()){
		 * customerVO.setConfirmEmailAddress( profileBO.getConfirmEmailAddress()); }
		 */
		if(null != customerBO.getEmailAddress() && !customerBO.getEmailAddress().isEmpty()){
			customerVO.setEmailAddress(
					customerBO.getEmailAddress());
		}
		if(null != customerBO.getMobileNo()){
			customerVO.setMobileNumber(customerBO.getMobileNo());
		}
		if(null != customerBO.getContactNo()){
			customerVO.setContactNumber(		
					customerBO.getContactNo());
		}
		
		if(null != customerBO.getAddress() && !customerBO.getAddress().isEmpty()){
			customerVO.setAddress(
					customerBO.getAddress());
		}
		if(null != customerBO.getIndustryType() && !customerBO.getIndustryType().isEmpty()){
			customerVO.setIndustryType(
					customerBO.getIndustryType());
		}
		if(0 !=customerBO.getEmployerId()){
			customerVO.setAssigned(customerBO.getEmployerId());
		}


		User adminLogin = new User();
		adminLogin.setId(customerBO.getAdminId());
		customerVO.setLoginVO(adminLogin);
		customerVO.setCreatedBy(customerBO.getCreatedBy());
		customerVO.setModifiedBy(customerBO.getModifiedBy());
		customerVO.setIsActive(customerBO.isActive());
		customerVO.setIsDelete(customerBO.isDelete());
		customerVO.setStatus("opened");
		customerVO.setMigrationStatus(false);

		customerVO.setCreated(DateHelper.beginningOfDay(new Date()));
		customerVO.setModified(DateHelper.beginningOfDay(new Date()));

		customerVO.setIsActive(customerBO.isActive());
		customerVO = customerDAO.createCustomer(customerVO);
		if(null!=customerVO){
			BeanUtils.copyProperties(customerVO, customerBO);
		}

		return customerBO;
	}
	@Override
	public ClientBO updateEmployer(ClientBO registerBO) {
		try{
			Customer employerVO=new Customer();
		//	employerVO = customerDAO.getuserId(registerBO);
			if(null !=registerBO.getFirstName()){
				employerVO.setFirstName(registerBO.getFirstName());
			}
			if(null !=registerBO.getLastName()){
				employerVO.setLastName(registerBO.getLastName());
			}
			if(null  !=registerBO.getMobileNo()){
				employerVO.setMobileNumber(registerBO.getMobileNo());
			}
			if(null !=registerBO.getEmailAddress()){
				employerVO.setEmailAddress(registerBO.getEmailAddress());
			}
			if(0 !=registerBO.getsNo()){
				employerVO.setId(registerBO.getsNo());
			}
			/*
			 * if(null !=registerBO.getConfirmEmailAddress()){
			 * employerVO.setConfirmEmailAddress(registerBO.getConfirmEmailAddress()); }
			 */
			if(null !=registerBO.getAddress()){
				employerVO.setAddress(registerBO.getAddress());
			}
			/*
			 * if(null !=registerBO.getPassword()){
			 * employerVO.setPassword(registerBO.getPassword()); }
			 */
			if(null !=registerBO.getCompanyName()){
				employerVO.setCompanyName(registerBO.getCompanyName());
			}
			/*
			 * if(null !=registerBO.getConfirmPassword()){
			 * employerVO.setConfirmPassword(registerBO.getConfirmPassword()); }
			 */
			if(null !=registerBO.getIndustryType()){
				employerVO.setIndustryType(registerBO.getIndustryType());
			}
			if(null !=registerBO.getWebsite()){
				employerVO.setWebSite(registerBO.getWebsite());
			}

			if(null  !=registerBO.getContactNo()){
				employerVO.setContactNumber(registerBO.getContactNo());
			}

			if(null !=registerBO.getStatus()){
				employerVO.setStatus(registerBO.getStatus());
			}
			employerVO.setModifiedBy(registerBO.getModifiedBy());
			employerVO.setModified(getDateWithoutTime(new Date()));
			employerVO.setIsActive(true);
			employerVO.setStatus("opened");
			employerVO.setVersion(0);
			if(null!=registerBO && null!=registerBO.getLoginBO()) {
				long id=registerBO.getLoginBO().getId();
				User adminLoginVO=new User();
				adminLoginVO.setId(id);
				employerVO.setLoginVO(adminLoginVO);
				
			}
			
			
			
			employerVO=customerDAO.updateEmployer(employerVO);

			if(0 != employerVO.getId()){
				registerBO.setId(employerVO.getId());
			}else{
				registerBO = new ClientBO();
			}

			if(null != employerVO){
				registerBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
			}
		} catch(Exception ex){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Update user has failed:" + ex.getMessage());
			}
			LOGGER.info("Update user has failed:" + ex.getMessage());
		}
		return  registerBO;

	}

	@Override
	public ClientBO retriveCustomer(ClientBO clientBO) throws MyClientsException {
		CustomerServiceImpl.LOGGER.entry();
		ClientBO client = null;
		try {
			client = customerDAO.retriveCustomer(clientBO);
			
			if(null!=client) {
			//retrieve Customer tracking details
			List<FollowUp> trackingList= customerDAO.retrieveTracking(clientBO.getId());
			//List<Contact> trackingList= customerDAO.retrieveTracking(clientBO.getId());
			
			client.setCustomerUpdateVOList(trackingList);
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		if(null!=client) {
			return client;
		}
		CustomerServiceImpl.LOGGER.exit();
		return client;
	}

	

	@Override
	public ClientBO deleteProfile(ClientBO deleteProfile) {
		Customer VO = new Customer();
		try {
			VO.setId(deleteProfile.getId());
			VO.setIsDelete(true);
			VO = customerDAO.deleteProfile(VO);
			
		} catch (MyClientsException e) {
			
			e.printStackTrace();
		}

		return deleteProfile;
	}

	@Override
	public ClientBO deleteCustomer(ClientBO deleteProfile) throws MyClientsException {
		
		return null;
	}

	@Override
	public ClientBO retriveCustomerById(ClientBO registerBO) throws MyClientsException, SerialException, SQLException {
		CustomerServiceImpl.LOGGER.entry();
		ClientBO reterive = null;
		reterive = customerDAO.retriveCustomerById(registerBO);
		CustomerServiceImpl.LOGGER.exit();
		return reterive;
	}

	@Override
	public ClientBO saveTracking(ClientBO bo) {
		/* ClientUpdateVO VO=new ClientUpdateVO(); */
		FollowUp VO=new FollowUp();
		Customer customer=new Customer();
		User loginVO=new User();

		customer.setId(bo.getId());
		VO.setCustomer(customer);

		loginVO.setId(bo.getAdminId());
		VO.setUser(loginVO);

		if(null !=bo.getDescription() && !bo.getDescription().isEmpty()){
			VO.setDescription(bo.getDescription());
		}
		if(null !=bo.getDate()){
			VO.setDate(bo.getDate());
		}
		if(null !=bo.getNextAppointmentDate()){
			VO.setNextAppointmentDate(bo.getNextAppointmentDate());
		}
		if(null !=bo.getStatus()){
			customer.setStatus(bo.getStatus());
			VO.setCustomer(customer);
		}
		VO=customerDAO.saveTracking(VO);
		if(0 != VO.getUpdateid()){
			bo.setId(VO.getCustomer().getId());
		}else{
			bo = null;
		}
		return bo;
	}

	@Override
	public Customer createCustomer(Customer customer) throws MyClientsException {
		return customerDAO.createCustomer(customer);
	}

	@Override
	public List<ClientBO> searchRetrieveTracking(ClientBO registerBO) {
		List<FollowUp> leadsFollowuplist = new ArrayList<FollowUp>();
		FollowUp leadsFollowup=new FollowUp();
		List<ClientBO> leadsListBO = new ArrayList<ClientBO>();
		User userVO=new User();
		try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null!=registerBO.getStarDate()) {
		String startDate=df.format(registerBO.getStarDate());
		Date  toDate = df.parse(startDate);
		leadsFollowup.setCreated(toDate);
		leadsFollowup.setModified(null);
		}if(null!=registerBO.getEndDate()) {
			leadsFollowup.setCreated(null);
			String endDate=df.format(registerBO.getEndDate());
			Date  toDate = df.parse(endDate);
			leadsFollowup.setModified(toDate);
		}
		userVO.setId(registerBO.getLoginBO().getId());
		leadsFollowup.setUser(userVO);
		leadsFollowuplist=customerDAO.searchRetrieveTracking(leadsFollowup);
		AtomicInteger sNo=new AtomicInteger(0);
		if (null != leadsFollowuplist && !leadsFollowuplist.isEmpty() && 0<leadsFollowuplist.size()) {
				leadsFollowuplist.forEach(leadsFollow->{
				AdminLoginBO adminLoginBO=new AdminLoginBO();
				ClientBO clientBO = new ClientBO();
				clientBO.setsNo(sNo.incrementAndGet());
				clientBO.setId(leadsFollow.getCustomer().getId());
				clientBO.setName(leadsFollow.getCustomer().getFirstName());
				clientBO.setCompanyName(leadsFollow.getCustomer().getCompanyName());
				clientBO.setEmailAddress(leadsFollow.getCustomer().getEmailAddress());
				SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MM-yyyy");
				clientBO.setCreatedDate(simpleformat.format(leadsFollow.getCustomer().getCreated()));
				clientBO.setModifiedDate(simpleformat.format(leadsFollow.getCustomer().getModified()));
				clientBO.setCreated(leadsFollow.getCreated());
				if(null!=leadsFollow.getCustomer().getLoginVO()) {
					adminLoginBO.setId(leadsFollow.getCustomer().getLoginVO().getId());
					clientBO.setLoginBO(adminLoginBO);
				}
				leadsListBO.add(clientBO);
				});
			}
		}catch (Exception he) {
			he.printStackTrace();
			LeadsServiceImpl.LOGGER.debug(he, he.getMessage());
        }
		return leadsListBO;
	}
	public String getSalesOrderNo() {
		return customerDAO.getSalesOrderNo();
		 
	
	}
	public List<ClientBO> retriveClients() {
		return customerDAO.retriveClients();
	}
	
	public GstBO getGstValues() {
		GstVO gstVO=new GstVO();
		GstBO gstBO=new GstBO();
		try {
		gstVO=customerDAO.getGstValues();
		if (null != gstVO) {
			gstBO.setSgst(gstVO.getSgst());
			gstBO.setCgst(gstVO.getCgst());
			gstBO.setGstId(gstVO.getGstId());
			return gstBO;
		}
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return gstBO;
		
	}
	
	public long createSalesOrder(SalesOrderBO salesOrderBOS) {
		
		
		List<SalesOrderVO> salesOrderVOList=new ArrayList<SalesOrderVO>();
		
		if(null!=salesOrderBOS&&null!=salesOrderBOS.getSalesOrderBO()&&salesOrderBOS.getSalesOrderBO().size()>0) {
			
			for(SalesOrderBO SalesOrderBo:salesOrderBOS.getSalesOrderBO()) {
				SalesOrderVO salesOrderVO=new SalesOrderVO();
				Customer CustomerVO=new Customer();
				ProductServiceVO productServiceVO=new ProductServiceVO();
				GstVO gstVO=new GstVO();
				//salesOrderVO.setGrandTotal(SalesOrderBo.getGrandTotal());
			//	salesOrderVO.setPrice(SalesOrderBo.getPrice());
				salesOrderVO.setQuantity(SalesOrderBo.getQuantity());
				//salesOrderVO.setTotalPrice(SalesOrderBo.getTotalPrice());
				salesOrderVO.setSalesOrderNo(SalesOrderBo.getSalesOrderNo());
			long customerId	=salesOrderBOS.getClientBO().getsNo();
		long productId=SalesOrderBo.getProduct().getServiceId();
		productServiceVO.setServiceId(productId);
		
			long gstId=SalesOrderBo.getGstBO().getGstId();
			gstVO.setGstId(gstId);
			CustomerVO.setId(customerId);
			//productServiceVO.setServiceId(productId);
			salesOrderVO.setCustomer(CustomerVO);
			salesOrderVO.setProduct(productServiceVO);
			//gstVO.setGstId(gstId);
			salesOrderVO.setGstVO(gstVO);
			long Id=customerDAO.createSalesOrder(salesOrderVO);
}
		
			
		}
		
		
		return 0;
		
	}

	public List<ProductServiceBO> getProductList(){
		List<ProductServiceVO> ProductServiceVO=new ArrayList<>();
		List<ProductServiceBO> productServiceBO=new ArrayList<>();
		try {
			ProductServiceVO= customerDAO.getProductList();
		for(ProductServiceVO productServiceVO:ProductServiceVO) {
			ProductServiceBO ProductServiceBo=new ProductServiceBO();
			ProductServiceBo.setServiceName(productServiceVO.getServiceName());
			ProductServiceBo.setServiceId(productServiceVO.getServiceId());
			productServiceBO.add(ProductServiceBo);
		}}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return productServiceBO;
		}

	public ProductServiceBO getProductPrice(Long staffId) {
		ProductServiceVO productServiceVO=new ProductServiceVO();
		ProductServiceBO productServiceBO=new ProductServiceBO();
		try {
			productServiceVO= customerDAO.getProductPrice(staffId);
			productServiceBO.setFees(productServiceVO.getFees());
			productServiceBO.setServiceName(productServiceVO.getServiceName());
		return productServiceBO;}
		catch(Exception ex) {
			ex.printStackTrace();
		}

		return productServiceBO;
	}
	}

