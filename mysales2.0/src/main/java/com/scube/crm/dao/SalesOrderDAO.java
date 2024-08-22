package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.SalesOrderBO;
import com.scube.crm.vo.SalesOrderVO;

public interface SalesOrderDAO {

	List<SalesOrderVO> retriveSalesOrders();

	List<SalesOrderVO> getSalesOrderList(SalesOrderBO salesOrder);

	GstBO getGstById(long gstId);

}
