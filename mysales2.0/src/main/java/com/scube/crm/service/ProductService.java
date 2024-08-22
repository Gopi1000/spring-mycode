package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.ProductServiceBO;

public interface ProductService {

	ProductServiceBO createServices(ProductServiceBO productServiceBO);

	List<ProductServiceBO> listservice(ProductServiceBO serviceBO);

	ProductServiceBO getServiceObject(ProductServiceBO productServiceBo);

	Boolean serviceUpdate(ProductServiceBO productServiceBo);

	Boolean deleteService(ProductServiceBO service);

	boolean isValidServiceName(ProductServiceBO productServiceBO);



	


}
