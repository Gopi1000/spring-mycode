package com.scube.springcrm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scube.springcrm.model.CustomerBo;
import com.scube.springcrm.service.CustomerService;

@Controller
public class UpdateCustomer  {
	
	@Autowired
	CustomerService co;
	
	
	@RequestMapping(value = "/edit-value", method = RequestMethod.GET)
	public String retriveCustomer(Model model, HttpServletRequest request) throws IOException, ServletException {

		String ids = request.getParameter("id");
		int editId = Integer.parseInt(ids);

		CustomerBo bo = co.getParticularValue(editId);
		CustomerBo boss = new CustomerBo();

		if (null != bo) {
			model.addAttribute("particular", bo);
			model.addAttribute("customer", boss);

			return "customerparticular";
		}

		return null;
	}
	 
	
	     @RequestMapping(value="/change-customer", method = RequestMethod.POST)
         public String changeCustomerDetails(@ModelAttribute CustomerBo bo,Model model,HttpServletRequest request) throws IOException, ServletException {
    	 
		   String response=null;
	       String id=(request.getParameter("id"));
		   int ids=Integer.parseInt(id);
		   
    if(null!=bo) {
			   
		   bo.setCustomerId(ids);
		   response =co.updateCustomer(bo);		
     }	
		
    if(null!=response) {		
    	
		   List<CustomerBo> car=new ArrayList<CustomerBo>();
		   car=co.getCustomerDetails();
		   
    if(null!=car&& car.size()>0) {	
    	
    	   System.out.println("value updated");
    	   model.addAttribute("carlist",car);	
		   
		}
    
		}                                                                              
			
		   return "customer-list";
		
	    }
		
    
      @RequestMapping(value="/delete-value", method = RequestMethod.GET)
      public String deleteCustomerDetails(@ModelAttribute CustomerBo bo,Model model,HttpServletRequest request) throws IOException, ServletException {
    	
    	   String response=null;
    	   String id=(request.getParameter("id"));
	       int ids=Integer.parseInt(id);
	    
	  if(null!=bo) {
		  
	    	bo.setCustomerId(ids);
	    	response=co.deleteCustomer(ids);
	     }
	
	  if(null!=response) {
	    	
	    	System.out.println("value deleted");
		    List<CustomerBo> car=new ArrayList<CustomerBo>();
		    car=co.getCustomerDetails();
		    model.addAttribute("carlist",car);
	     }
	  
	      return "customer-list";
    }
}

    




	
	
	
	
	


