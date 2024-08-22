package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.vo.ProductServiceVO;


public interface ProductServiceDao {

	ProductServiceBO createServices(ProductServiceVO productServiceVO);

	List<ProductServiceVO> listViewService(ProductServiceVO productServiceVO);

	ProductServiceVO getServiceObject(ProductServiceVO productServiceVO);

	Boolean serviceUpdateDao(ProductServiceVO productServiceVo);

	Boolean deleteService(ProductServiceVO productServiceVo);

	ProductServiceVO retrieveService(ProductServiceVO productServiceVo);

	boolean isValidServiceName(ProductServiceVO productServiceVO);

	

}
