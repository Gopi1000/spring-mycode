package com.scube.springcrm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scube.springcrm.model.ProductBo;
import com.scube.springcrm.service.ProductService;


  @Controller
  public class ProductController {
	  
	@Autowired
	ProductService co;
	
	@RequestMapping(value="/product-create", method = RequestMethod.POST)
	public String createCustomer(@Valid @ModelAttribute("product") ProductBo product,BindingResult result,Model model) throws IOException, ServletException {	
		
		if (result.hasErrors()) {
			return "product-create";
		}
		String response=null;
	
		if(null!=product) {
		 response =co.insertprodectdetails(product);		
			}	
		
		if(null!=response) {			
		    System.out.println("Register Successful");			
			List<ProductBo> car=new ArrayList<ProductBo>();
			car=co.getprodectDetails();
			
		if(null!=car&& car.size()>0) {	
				System.out.println("product value retrieved");
				model.addAttribute("carlist",car);				
			}
		}                                                                              
			
		return "product-list";
		
	   }
	
	
	@RequestMapping(value="/product-create", method = RequestMethod.GET)	
	public String createProduct(Model model) throws IOException, ServletException{		
		
		ProductBo bo=new ProductBo();		
		model.addAttribute("product", bo);
		return "product-create";
	}
			
	
}



