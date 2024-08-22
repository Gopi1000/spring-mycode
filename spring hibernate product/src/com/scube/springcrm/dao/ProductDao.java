package com.scube.springcrm.dao;

import java.util.List;

import com.scube.springcrm.model.ProductBo;
import com.scube.springcrm.vo.ProductVO;

public interface ProductDao {

	String insertprodectdetails(ProductVO productvo);

	List<ProductVO> getprodectDetails();

	ProductVO getParticularValue(int ids);

	String updateProduct(ProductVO productvo);

	String deleteProduct(ProductVO productvo);

	

	

	

}
