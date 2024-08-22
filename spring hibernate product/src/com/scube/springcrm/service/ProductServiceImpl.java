package com.scube.springcrm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.springcrm.dao.ProductDao;
//import com.scube.springcrm.model.CustomerBo;
import com.scube.springcrm.model.ProductBo;
//import com.scube.springcrm.vo.CustomerVO;
import com.scube.springcrm.vo.ProductVO;

@Service
@Transactional 
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao dao;
	
    public String insertprodectdetails(ProductBo prodect) {
    	
    	ProductVO productvo=new ProductVO();
    	
    	productvo.setProductId(prodect.getProductId());
    	productvo.setProductType(prodect.getProductType());
    	productvo.setBrandName(prodect.getBrandName());
    	productvo.setModelNo(prodect.getModelNo());
    	productvo.setQuantity(prodect.getQuantity());
    	productvo.setPrice(prodect.getPrice());
    	productvo.setDiscountPrice(prodect.getDiscountPrice());
    	
	return dao.insertprodectdetails(productvo);
	
	}
	
	public List<ProductBo> getprodectDetails() {
		
	     List<ProductBo> cars=new ArrayList<ProductBo>();
	
	     List<ProductVO> car=new ArrayList<ProductVO>();
	
	     car=dao.getprodectDetails();
	
     for(ProductVO ProductVO:car) {
		 ProductBo ProductBo=new ProductBo();
		
		ProductBo.setProductId(ProductVO.getProductId());
		ProductBo.setProductType(ProductVO.getProductType());
		ProductBo.setBrandName(ProductVO.getBrandName());
		ProductBo.setModelNo(ProductVO.getModelNo());
		ProductBo.setQuantity(ProductVO.getQuantity());
		ProductBo.setPrice(ProductVO.getPrice());
		ProductBo.setDiscountPrice(ProductVO.getDiscountPrice());
		
		cars.add(ProductBo);
	}
	return cars;
	
	
	}
	
	public	ProductBo getParticularValue(int ids) {
		
		ProductVO productVO=new ProductVO();
		ProductBo productBo=new ProductBo();
		
		productVO=dao.getParticularValue(ids);
		 if(null!=productVO) {
			 
		     productBo.setProductId(productVO.getProductId());
			 productBo.setProductType(productVO.getProductType());
			 productBo.setBrandName(productVO.getBrandName());
			 productBo.setModelNo(productVO.getModelNo());
			 productBo.setQuantity(productVO.getQuantity());
			 productBo.setPrice(productVO.getPrice());
			 productBo.setDiscountPrice(productVO.getDiscountPrice());
	 } 
	    return productBo;
	 }
    
	public String updateProduct(ProductBo bo) {
		ProductVO productvo=new ProductVO();
		
		productvo.setProductId(bo.getProductId());
    	productvo.setProductType(bo.getProductType());
    	productvo.setBrandName(bo.getBrandName());
    	productvo.setModelNo(bo.getModelNo());
    	productvo.setQuantity(bo.getQuantity());
    	productvo.setPrice(bo.getPrice());
    	productvo.setDiscountPrice(bo.getDiscountPrice());
    	
    	String respnse= dao.updateProduct(productvo);
    	return "respnse";
	}

	public String deleteProduct(int ids) {
		ProductVO productvo=new ProductVO();
		
		productvo.setProductId(ids);
		String product=dao.deleteProduct(productvo);
		
	    return product;
		}
	}

