package com.scube.crm.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.SalesOrderBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.CustomerService;
import com.scube.crm.service.CustomerServiceImpl;
import com.scube.crm.service.SalesOrderService;

import java.util.List;

@Controller
public class SalesOrderController extends ControllerUtils{
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SalesOrderService salesOrderService;
	String price=null;;
	String productName=null;
	long productIds;
	String grandTotal=null;
	double overAllgrandTotal=0;
	long employerId;
	long gstId;
	String salesOrderNo;
 List<ClientBO> clientBO;
	List<SalesOrderBO> salesOrderBO=null;
	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(SalesOrderController.class);
	@RequestMapping(value="/create-sales-order",method=RequestMethod.GET)
	public String createSalesOrder(HttpServletRequest request,Model model) throws MyClientsException {
		LOGGER.entry();
		 price=null;;
		 productName=null;
		 grandTotal=null;
		 overAllgrandTotal=0;
		 productIds=0;
		 employerId=0;
		 salesOrderBO=new ArrayList<SalesOrderBO>();
		
		
		model.addAttribute("salesOrderBO", new SalesOrderBO());
		salesOrderNo= customerService. getSalesOrderNo();
		if(null!=salesOrderNo) {
			model.addAttribute("salesno",salesOrderNo);
		}
		clientBO=customerService.retriveClients();	
		//clientBO=customerService.retriveCustomer(clientBO);
		
		if(null!=clientBO) {
			model.addAttribute("listClients", clientBO);
		}
			List<ProductServiceBO> productServiceBO=new ArrayList<>();
	productServiceBO=customerService.getProductList();
	if(null==productServiceBO) {
	return "create-sales-orders";
	
	}
	GstBO gst=	customerService.getGstValues();
	
	if(null==gst) {
		return "create-sales-orders";
		
		}
	model.addAttribute("cgst", gst.getCgst());
	model.addAttribute("sgst", gst.getSgst());
		model.addAttribute("productBOList", productServiceBO);
		return "create-sales-orders";
		
	}
	
	@RequestMapping(value = "/create-sales-order", method = RequestMethod.POST)
	public String createSalesOrder(@Valid @ModelAttribute("salesOrderBO") SalesOrderBO salesOrderBOS, BindingResult bindingResult,
			Model model, HttpServletRequest request, HttpSession session) throws IOException {
		if(null!=salesOrderBO&&salesOrderBO.size()>0&&!salesOrderBO.isEmpty()) {
			ClientBO clientBO=new ClientBO();
			GstBO gstBO=new GstBO();
			clientBO.setsNo(employerId);
			salesOrderBOS.setClientBO(clientBO);
			//ProductServiceBO productServiceBO=new ProductServiceBO();
			//productServiceBO.setServiceId(productIds);
			//salesOrderBOS.setProduct(productServiceBO);
			gstBO.setGstId(gstId);
			salesOrderBOS.setGstBO(gstBO);
			//salesOrderBOS.setSalesOrderNo(salesOrderNo);
			salesOrderBOS.setSalesOrderBO(salesOrderBO);
			long id=customerService.createSalesOrder(salesOrderBOS);
		}
		System.out.println("hi");
		System.out.println("hi");
		System.out.println("hi");
		System.out.println("hi");
		System.out.println("hi");
				return "redirect:/create-sales-order";
	
}
	
	
		@RequestMapping(value="/convert-sales-order",method=RequestMethod.GET)
		public String convertSalesOrder(HttpServletRequest request,Model model) {
			LOGGER.entry();
			List<ProductServiceBO> productServiceBO=new ArrayList<>();
			 salesOrderBO=new ArrayList<SalesOrderBO>();
		final String id = request.getParameter("id");
		//long employerId = 0;
		ClientBO registerBO = new ClientBO();
		SalesOrderBO salesOrderBO=new SalesOrderBO(); 
		try {
		if (null != id) {
			employerId = Long.parseLong(id);
			registerBO.setId(employerId);
		}
		 salesOrderNo= customerService. getSalesOrderNo();
		if(null!=salesOrderNo) {
			salesOrderBO.setSalesOrderNo(salesOrderNo);
		}
		
		productServiceBO=customerService.getProductList();
		if(null==productServiceBO&&productServiceBO.size()==0) {
			return "create-sales-orders";
		}
		registerBO = this.customerService.retriveCustomerById(registerBO);
			salesOrderBO.setClientBO(registerBO);
		} catch (MyClientsException | SQLException e) {
			e.printStackTrace();
		}
		clientBO=customerService.retriveClients();	
		
		if(null!=clientBO) {
			model.addAttribute("listClients", clientBO);
		}
		model.addAttribute("productBOList", productServiceBO);
			model.addAttribute("salesOrderBO", salesOrderBO);
			return "create-sales-orders";
			
		}
		

		@RequestMapping(value = "/getStaffDetails", method = RequestMethod.GET)
		 @ResponseBody
		 public ProductServiceBO getEmployeeDetails(@RequestParam String staffId,HttpSession session)  {
			ProductServiceBO ProductServiceBO=new ProductServiceBO();
			List<ProductServiceBO> productServiceBO=new ArrayList<>();
			Long productId=Long.valueOf(staffId);
			productIds=productId;
			ProductServiceBO=customerService.getProductPrice(productId);
		
			if(null!=ProductServiceBO) {
				price=String.valueOf(ProductServiceBO.getFees());
				productName=ProductServiceBO.getServiceName();
			}
	
	return ProductServiceBO;
		
		}
		
		
		
		
		
		

		@RequestMapping(value = "/getTotalDetails", method = RequestMethod.GET)
		 @ResponseBody
		 public SalesOrderBO getTotalPrice(@RequestParam String quantityId,HttpSession session)  {
			SalesOrderBO salesOrderBO=new SalesOrderBO();
			List<ProductServiceBO> productServiceBO=new ArrayList<>();
			double QuantityValue=Double.valueOf(quantityId);
			double priceValue=Double.valueOf(price);
			double totalPrice=priceValue*QuantityValue;
			salesOrderBO.setTotalPrice(totalPrice);
			return salesOrderBO;
}
		
		
		@RequestMapping(value = "/getGrandTotalDetails", method = RequestMethod.GET)
		 @ResponseBody
		 public SalesOrderBO getGrandTotalPrice(@RequestParam String quantityId,HttpSession session)  {
			SalesOrderBO salesOrderBO=new SalesOrderBO();
			salesOrderBO.setGrandTotal(overAllgrandTotal);
			GstBO gstBO=new GstBO();
			GstBO gst=	customerService.getGstValues();
			
				if(null!=gst) {
					gstId=gst.getGstId();
				}
			
			
		/* CGst calculation */
			if(null!=gst) {
				double percentage=100;
				String removepercentageCgst=gst.getCgst().replace("%", "");
				double cGst=Double.valueOf(removepercentageCgst);
				double cGstValue;
				double cGstFinalRate;
				cGstValue=cGst/percentage;
				cGstFinalRate=cGstValue*overAllgrandTotal;
				gstBO.setCgst(String.valueOf(cGstFinalRate));
				
				/* SGst calculation */
				String removepercentageSgst=gst.getSgst().replace("%", "");
				double sGst=Double.valueOf(removepercentageSgst);
				double sGstValue;
				double sGstFinalRate;
				sGstValue=sGst/percentage;
				sGstFinalRate=sGstValue*overAllgrandTotal;
				gstBO.setSgst(String.valueOf(sGstFinalRate));
				salesOrderBO.setGstBO(gstBO);
				
				salesOrderBO.setTotalInvoice(overAllgrandTotal+sGstFinalRate+cGstFinalRate);
				
				}
			return salesOrderBO;
}
		@RequestMapping(value="/addAgreement", method = RequestMethod.GET)
		@ResponseBody
		public List<SalesOrderBO> addAgreementDetails(Model model, HttpServletRequest request, 
				@RequestParam String product, String price, String quantityId,String totalId){

			HttpSession session = request.getSession();
			SalesOrderBO salesOrder = new SalesOrderBO();
			GstBO gst=	customerService.getGstValues();
			gstId=gst.getGstId();
			if(null!=gst) {
				gstId=gst.getGstId();
			}
		
			salesOrder.setGstBO(gst);
			salesOrder.setQuantity(Long.valueOf(quantityId));
			salesOrder.setPrice(Double.valueOf(price));
			salesOrder.setTotalPrice(Long.valueOf(totalId));
			salesOrder.setGrandTotal(Long.valueOf(totalId));
			ProductServiceBO productServiceBO=new ProductServiceBO();
			productServiceBO.setServiceName(productName);
			productServiceBO.setServiceId(productIds);
			salesOrder.setProduct(productServiceBO);
			salesOrder.setSalesOrderNo(salesOrderNo);
			salesOrderBO.add(salesOrder);
		/*
		 * agreementBO.setAllowance_name(allowance); Double val =
		 * Double.parseDouble(amount); BigDecimal amt = BigDecimal.valueOf(val);
		 * agreementBO.setAmt(amt); agreementBO.setTaxable(taxable);
		  agreementList.add(agreementBO); session.setAttribute("agreementList",
		 agreementList);
		 */grandTotal=String.valueOf(salesOrder.getGrandTotal());
		 overAllgrandTotal= overAllgrandTotal+(Double.valueOf(grandTotal));
			session.setAttribute("grandTotal",grandTotal);
			 session.setAttribute("salesOrderBO",
					 salesOrderBO);
			return salesOrderBO;
		}
		@RequestMapping(value="/search-customer",method =RequestMethod.POST)
		public String getSeperateCustomer(@Valid@ModelAttribute("salesOrderBO")SalesOrderBO salesOrderBO,HttpServletRequest req,Model model) throws MyClientsException, SerialException, SQLException {
			SalesOrderBO salesOrder=new SalesOrderBO();
			String value=salesOrderBO.getClientBO().getFirstName();
			long id=Long.parseLong(value);
			employerId=id;
			ClientBO clientBOs=new ClientBO();
			List<ProductServiceBO> productServiceBO=new ArrayList<>();
			clientBOs.setId(id);
			ClientBO client	=customerService.retriveCustomerById(clientBOs);
			if(null!=client) {
				salesOrder.setClientBO(client);
				model.addAttribute("salesOrderBO", salesOrder);
				model.addAttribute("firstname", salesOrder.getClientBO().getFirstName());
			}
			clientBO=customerService.retriveClients();	
			//clientBO=customerService.retriveCustomer(clientBO);
			
			if(null!=clientBO) {
				model.addAttribute("listClients", clientBO);
			}
			salesOrderNo= customerService. getSalesOrderNo();
			if(null!=salesOrderNo) {
				model.addAttribute("salesno",salesOrderNo);
			}
			
			
			productServiceBO=customerService.getProductList();
			if(null!=productServiceBO) {
				model.addAttribute("productBOList", productServiceBO);
			}
						return "create-sales-orders";
			
			
		}
		
		@RequestMapping(value="/view-sales-order", method = RequestMethod.GET)
		public String retriveSalesOrders(HttpServletRequest request,Model model) {
			List <SalesOrderBO> SalesOrderlist=new ArrayList<>();
			System.out.println("hi");
			System.out.println("hi");
			System.out.println("hi");
			System.out.println("hi");
			SalesOrderlist=salesOrderService.retriveSalesOrders();
			if(null!=SalesOrderlist&&!SalesOrderlist.isEmpty()&&SalesOrderlist.size()>0) {
				model.addAttribute("listSales", SalesOrderlist);
			}
			
		//	return grandTotal;
			return "view-sales-orders";
			
		}
		@RequestMapping(value="/view-sales-order-list", method = RequestMethod.GET)
		public String viewSalesOrderlist(HttpServletRequest request,Model model) {
			
			List <SalesOrderBO> SalesOrderlist=new ArrayList<>();
			SalesOrderBO SalesOrder=new SalesOrderBO();
			if (null != request.getParameter("salesno")) {
				final String salesOrderNo = request.getParameter("salesno");
				SalesOrder.setSalesOrderNo(salesOrderNo);
			} 

			SalesOrderlist = salesOrderService.getSalesOrderList(SalesOrder);
			if(null!=SalesOrderlist&&!SalesOrderlist.isEmpty()&&SalesOrderlist.size()>0) {
				SalesOrderBO sales=SalesOrderlist.get(0);
				long size=SalesOrderlist.size();
				SalesOrderBO overAllVaue=SalesOrderlist.get((int) (size-1));
				
				model.addAttribute("overAlltotal",overAllVaue.getTotalInvoice());
				model.addAttribute("client", sales.getClientBO());
				
				//model.addAttribute("particularSalesList", SalesOrderlist);
				model.addAttribute("particularSalesList", SalesOrderlist);
			}
			return "view-particular-sales-orders";}
}
