package com.scube.techboot.service;

import java.util.List;

import com.scube.techboot.bo.ProductServiceBO;

public interface ProductService {

	ProductServiceBO createServices(ProductServiceBO productServiceBO);

	List<ProductServiceBO> listservice(ProductServiceBO serviceBO);

	Boolean serviceUpdate(ProductServiceBO productServiceBo);

	Boolean deleteService(ProductServiceBO service);
	List<ProductServiceBO> listofpageservice(ProductServiceBO serviceBo);
	

	int searchPageService(ProductServiceBO productServiceBo);

	boolean isValidServiceName(ProductServiceBO productServiceBO);

	ProductServiceBO getServiceObject(ProductServiceBO productServiceBo);


	


}
