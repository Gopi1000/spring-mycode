package com.scube.springcrm.service;

import java.util.List;

import com.scube.springcrm.model.ProductBo;

public interface ProductService {

	String insertprodectdetails(ProductBo prodect);

	List<ProductBo> getprodectDetails();

	ProductBo getParticularValue(int ids);

	String updateProduct(ProductBo bo);

	String deleteProduct(int ids);

	

	

	

	

	

	

}
