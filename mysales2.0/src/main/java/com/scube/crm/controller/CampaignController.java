package com.scube.crm.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.CampaignService;
import com.scube.crm.service.CustomerService;
import com.scube.crm.service.ProductService;
import com.scube.crm.utils.UserRoles;
import com.scube.crm.vo.User;

@Controller
@Scope("session")
@SessionAttributes("/admin")
public class CampaignController extends ControllerUtils implements Serializable {

	private static final long serialVersionUID = -5857977996611066292L;

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CampaignController.class);

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private ProductService productService;
	@Autowired
	private MessageSource messageSource; 

	@Autowired
	ServletContext servletContext;

	@Autowired
	private CustomerService customerService;

	List<CampaignBO> pagecampaignlist;
	List<LeadsBO> pageLeadsList;
	List<AdminUserBO> adminEmployeeList;

	@RequestMapping(value = "/create-campaign", method = RequestMethod.GET)
	public String getCampaign(Model model, HttpServletRequest request, HttpSession session)
			throws MyClientsException, NumberFormatException, FileNotFoundException, SerialException, SQLException {
		LOGGER.entry();
		CampaignBO campaign = new CampaignBO();
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}else {
			model.addAttribute("campaignBO", campaign);
		}
		CampaignBO campaignBO = new CampaignBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId=getUserSecurity().getLoginId();		
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && !userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			campaignBO.setAdminLoginBO(adminLoginBO);
		}
		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			ProductServiceBO serviceBO=new ProductServiceBO();
			List<ProductServiceBO>  serviceList = productService.listservice(serviceBO);
			model.addAttribute("productList",serviceList);
		}

		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			List<AdminUserBO> userBOList = campaignService.retrieveUser();
			model.addAttribute("userBOList", userBOList);
		}
		if (0 < loginId &&!userType.equalsIgnoreCase("ROLE_ADMIN")) {
			List<AdminUserBO> user=new ArrayList<>();
			AdminUserBO userBOList = campaignService.retrieveParticularUser(loginId);
			user.add(userBOList);
			model.addAttribute("userBOList", user);
			ProductServiceBO serviceBO=new ProductServiceBO();
			List<ProductServiceBO>  serviceList = productService.listservice(serviceBO);
			model.addAttribute("productList",serviceList);
		}
		return "create-campaign";
	}

	@RequestMapping(value = "/create-campaign", method = RequestMethod.POST)
	public String postCampaign(@Valid @ModelAttribute("campaignBO") CampaignBO campaignBO, BindingResult bindingResult,
			Model model, HttpServletRequest request, HttpSession session) throws IOException {
		LOGGER.entry();
		if (bindingResult.hasErrors()) {
			return "create-campaign";
		}
		// userLoginId
		long loginId = (long) getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole().toString();
          
		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
		//if (0 < loginId && !userType.equalsIgnoreCase("admin")) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			campaignBO.setAdminLoginBO(adminLoginBO);
		} else {
			if(null!=campaignBO.getCampaignOwner()) {
				String adminId = campaignBO.getCampaignOwner();
				long userId = 0;
				if (null != adminId) {
					userId = Long.parseLong(adminId);
				}

				AdminLoginBO adminLoginBO = new AdminLoginBO();
				adminLoginBO.setId(userId);
				campaignBO.setAdminLoginBO(adminLoginBO);
			}
		}
		if (null != campaignBO) {
			AdminUserBO userBOList = campaignService.retrieveParticularUser(campaignBO.getAdminLoginBO().getId());
			campaignBO.setCampaignOwner(userBOList.getName());
			campaignBO = campaignService.saveCompaign(campaignBO);

			if (0!= campaignBO.getCampaignId()) {
				model.addAttribute("successMessage",messageSource.getMessage("Campaign.Creation",null,null));
				return "redirect:/view-campaign";
			}
		}
		return "redirect:/view-campaign";
	}


	@RequestMapping(value = "view-campaign", method = RequestMethod.GET)
	public String viewCampaign(Model model, HttpServletRequest request, HttpSession session)
			throws FileNotFoundException, MyClientsException {
        LOGGER.entry();
		List<CampaignBO> pagecampaignlist = new ArrayList<CampaignBO>();
		CampaignBO campaignBO = new CampaignBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		campaignBO.setUserId(loginId);
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			campaignBO.setAdminLoginBO(adminLoginBO);
		}
		pagecampaignlist = campaignService.listOfCampaign(campaignBO);
		if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
			model.addAttribute("listcampaign", pagecampaignlist);
		}
		//campaign search dd
				ProductServiceBO productBO = new ProductServiceBO();
				if(0 < loginId &&!userType.equalsIgnoreCase("ROLE_ADMIN")) {
					adminLoginBO.setId(loginId);
					adminLoginBO.setUserType(userType);
					productBO.setAdminLoginBO(adminLoginBO);
				List<ProductServiceBO> productList = productService.listservice(productBO);
				if(null!=productList && 0<productList.size()) {
					model.addAttribute("productList",productList);
				}
				}
				if(0 < loginId &&userType.equalsIgnoreCase("ROLE_ADMIN")) {
					adminLoginBO.setId(loginId);
					adminLoginBO.setUserType(userType);
					productBO.setAdminLoginBO(adminLoginBO);
					List<ProductServiceBO> productList = productService.listservice(productBO);
					if(null!=productList && 0<productList.size()) {
						model.addAttribute("productList",productList);
					}
					}
		model.addAttribute("searchCampaign",new CampaignBO());
		if(null!=request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		return "view-campaign";
	}

	@RequestMapping(value = "edit-campaign", method = RequestMethod.GET)
	public String editCampaign(Model model, HttpServletRequest request, HttpSession session)
			throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		CampaignBO campaignBO = new CampaignBO();
		String campaignId = request.getParameter("campaignid");
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(loginId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			List<AdminUserBO> userBOList = campaignService.retrieveUser();
			model.addAttribute("userBOList", userBOList);
			model.addAttribute("usrId", campaignBO.getUserId());
			model.addAttribute("userName", campaignBO.getUserName());
			campaignBO = campaignService.getObject(campaignId);
		}
		if (0 < loginId && userType.equalsIgnoreCase((UserRoles.ROLE_ADMIN.toString()))) {
			List<AdminUserBO> userBOList = campaignService.retrieveUser();
			model.addAttribute("userBOList", userBOList);
			model.addAttribute("usrId", campaignBO.getUserId());
			model.addAttribute("userName", campaignBO.getUserName());
			campaignBO = campaignService.getObject(campaignId);
		}
		if (0 < loginId && userType.equalsIgnoreCase((UserRoles.ROLE_ADMIN.toString()))) {
			ProductServiceBO serviceBO=new ProductServiceBO();
			List<ProductServiceBO>  serviceList = productService.listservice(serviceBO);
			if(null!=serviceList && 0<serviceList.size()) {
				model.addAttribute("productList",serviceList);
			}
		}if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())){
			ProductServiceBO serviceBO=new ProductServiceBO();
			List<ProductServiceBO>  serviceList = productService.listservice(serviceBO);
			if(null!=serviceList && 0<serviceList.size()) {
				model.addAttribute("productList",serviceList);
			}
		}
		if (null != campaignBO) {
			model.addAttribute("campaignBO", campaignBO);
			if(null!=campaignBO.getCampaignOwner()&&!campaignBO.getCampaignOwner().isEmpty()) {
				model.addAttribute("userName", campaignBO.getCampaignOwner());
				model.addAttribute("serviceId",campaignBO.getProductServiceBO().getServiceId());
				model.addAttribute("serviceName",campaignBO.getProductServiceBO().getServiceName());
			}
			else {
				model.addAttribute("userName", campaignBO.getUserName());
			}
			model.addAttribute("edit", "campaginEdit");
		}
		return "edit-campaign";

	}

	@RequestMapping(value = "edit-campaign", method = RequestMethod.POST)
	public String editCampaign(@Valid @ModelAttribute("campaignBO") CampaignBO campaignBO, BindingResult bindingResult,
			Model model, HttpServletRequest request, HttpSession session) throws FileNotFoundException {
		LOGGER.entry();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();

		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			campaignBO.setAdminLoginBO(adminLoginBO);
		} else {
			long adminId=loginId;
			long userId = 0;
			if (0 != adminId) {
				userId =loginId;
			}
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(userId);
			campaignBO.setAdminLoginBO(adminLoginBO);
		}
		boolean status = campaignService.updateCampaign(campaignBO);
		if (status) {
			model.addAttribute("successMessage",messageSource.getMessage("Campaign.Update",null,null));
			return "redirect:/view-campaign";
		}
		else {
			model.addAttribute("errorMessage", "Doesnot Exists");
		}

		model.addAttribute("campaignBO", campaignBO);
		return "edit-campaign";
	}

	@RequestMapping(value = "delete-campaign", method = RequestMethod.GET)
	public String deleteCampaign(Model model, HttpServletRequest request, HttpSession session)
			throws FileNotFoundException {
		LOGGER.entry();
		String campaignId = request.getParameter("campaignid");
		CampaignBO campaignBO = campaignService.getObject(campaignId);
		if (null != campaignBO) {
			boolean status = campaignService.deleteCampaign(campaignId);
			if (status) {
				model.addAttribute("successMessage",messageSource.getMessage("Campaign.Delete",null,null));
				return "redirect:/view-campaign";
			}
		}
		return "redirect:/view-campaign";
	}



	@RequestMapping(value = "/search-campaign", method = RequestMethod.POST)
	public String searchCampaign( @ModelAttribute("searchCampaign") CampaignBO campaignBO,
			HttpServletRequest request,HttpSession session, Model model) throws MyClientsException, SerialException, SQLException {
		LOGGER.entry();
		try {
		model.addAttribute("searchCampaign",campaignBO);
		long loginId=getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		campaignBO.setUserId(loginId);
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		ProductServiceBO productBO=new ProductServiceBO();
		List<AdminUserBO> userBOList = customerService.retrieveUser();
		if (null != userBOList && 0 != userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
		}
		productBO.setServiceName(campaignBO.getProductServiceBO().getServiceName());
		if(null!=campaignBO.getProductServiceBO()&& null!=campaignBO.getProductServiceBO().getServiceName()
				&& !campaignBO.getProductServiceBO().getServiceName().isEmpty()) {
		Integer productId=Integer.parseInt(campaignBO.getProductServiceBO().getServiceName());
		productBO.setServiceId(productId);
		campaignBO.setProductServiceBO(productBO);
		}
		pagecampaignlist = campaignService.listOfCampaign(campaignBO);
		if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
			model.addAttribute("listcampaign", pagecampaignlist);
		}
		ProductServiceBO productBo = new ProductServiceBO();
		List<ProductServiceBO> productList = productService.listservice(productBo);
		if(null!=productList && 0<productList.size()) {
			model.addAttribute("productList",productList);
		}
		else {
			model.addAttribute("errorMessage","No Records Found");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view-campaign";
	}




}