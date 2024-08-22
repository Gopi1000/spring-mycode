package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.SalesOrderBO;

public interface SalesOrderService {

	List<SalesOrderBO> retriveSalesOrders();

	List<SalesOrderBO> getSalesOrderList(SalesOrderBO salesOrder);

}
