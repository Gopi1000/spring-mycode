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

import com.scube.springcrm.model.ProductBo;
import com.scube.springcrm.service.ProductService;

@Controller
public class UpdateProduct  {
	
	@Autowired
	ProductService co;
	
        @RequestMapping(value="/edit-value", method = RequestMethod.GET)
        public String retriveProduct(Model model,HttpServletRequest request) throws IOException, ServletException{
	
	         String ids=request.getParameter("id");
	         int editId=Integer.parseInt(ids);
	
	         ProductBo bo=co.getParticularValue(editId);
	         ProductBo boss=new ProductBo();
	
	    if(null!=bo) {
		     model.addAttribute("particular",bo);
		     model.addAttribute("product", boss);
		     
		return "productparticular";	
	}
	    
	    return null;
    }
	
	     @RequestMapping(value="/change-product", method = RequestMethod.POST)
         public String changeProductDetails(@ModelAttribute ProductBo bo,Model model,HttpServletRequest request) throws IOException, ServletException {
    	 
		   String response=null;
	       String id=(request.getParameter("id"));
		   int ids=Integer.parseInt(id);
		   
    if(null!=bo) {
			   
		   bo.setProductId(ids);
		   response =co.updateProduct(bo);		
     }	
		
    if(null!=response) {		
    	
		   List<ProductBo> car=new ArrayList<ProductBo>();
		   car=co.getprodectDetails();
		   
    if(null!=car&& car.size()>0) {	
    	
    	   System.out.println("product value updated");
    	   model.addAttribute("carlist",car);	
		   
		}
    
		}                                                                              
			
		   return "product-list";
		
	    }
		
    
      @RequestMapping(value="/delete-value", method = RequestMethod.GET)
      public String deleteProductDetails(@ModelAttribute ProductBo bo,Model model,HttpServletRequest request) throws IOException, ServletException {
    	
    	   String response=null;
    	   String id=(request.getParameter("id"));
	       int ids=Integer.parseInt(id);
	    
	  if(null!=bo) {
		  
	    	bo.setProductId(ids);
	    	response=co.deleteProduct(ids);
	     }
	
	  if(null!=response) {
	    	
	    	System.out.println("product value deleted");
		    List<ProductBo> car=new ArrayList<ProductBo>();
		    car=co.getprodectDetails();
		    model.addAttribute("carlist",car);
	     }
	  
	      return "product-list";
    }
}

    




	
	
	
	
	


