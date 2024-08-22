package com.scube.techboot.dao;

import java.util.List;

import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.vo.ProductServiceVO;


public interface ProductServiceDao {

	ProductServiceBO createServices(ProductServiceVO productServiceVO);

	List<ProductServiceVO> listViewService(ProductServiceVO productServiceVO);

	ProductServiceVO getServiceObject(ProductServiceVO productServiceVO);

	Boolean serviceUpdateDao(ProductServiceVO productServiceVo);

	Boolean deleteService(ProductServiceVO productServiceVo);

	ProductServiceVO retrieveService(ProductServiceVO productServiceVo);

	List<ProductServiceVO> listofpageservice(ProductServiceVO productServiceVO);

	int searchPageService(ProductServiceVO productServiceVO);

	boolean isValidServiceName(ProductServiceVO productServiceVO);

	ProductServiceVO getserviceObject(ProductServiceVO productServiceVO);

	

}
