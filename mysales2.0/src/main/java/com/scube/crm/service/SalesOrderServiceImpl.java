package com.scube.crm.service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.SalesOrderBO;
import com.scube.crm.dao.SalesOrderDAO;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.SalesOrderVO;

@Service
@Transactional
public class SalesOrderServiceImpl implements SalesOrderService {
	@Autowired
	SalesOrderDAO salesOrderDAO;
	CustomerService customerService;
	String sNo="check";
	double overAllTotal;
	public List<SalesOrderBO>retriveSalesOrders() {
		
		AtomicInteger ref=new AtomicInteger();
		
		List<SalesOrderBO> list=new ArrayList<>();
		 List <SalesOrderVO> salesOrderVO=salesOrderDAO.retriveSalesOrders();
		 if(null!=salesOrderVO&&!salesOrderVO.isEmpty()&&salesOrderVO.size()>0) {
		salesOrderVO.forEach(salesOrder->{
			
			if(null!=salesOrder.getSalesOrderNo()&&!sNo.equals(salesOrder.getSalesOrderNo())) {
				
				sNo=salesOrder.getSalesOrderNo();
				ClientBO client=new ClientBO();
				SalesOrderBO SalesOrderbo=new SalesOrderBO();
				SalesOrderbo.setSalesOrderNo(salesOrder.getSalesOrderNo());
				SalesOrderbo.setSalesOrderId(salesOrder.getSalesOrderId());
				if(null!=salesOrder&&null!=salesOrder.getCustomer()&&null!=salesOrder.getCustomer().getFirstName()) {
				client.setFirstName(salesOrder.getCustomer().getFirstName());}
				//SalesOrderbo.setsNo(++sno);
				//SalesOrderbo.setC	salesOrder.getCustomer().getFirstName();
				SalesOrderbo.setsNo(ref.incrementAndGet());
				SalesOrderbo.setClientBO(client);
				list.add(SalesOrderbo);
			}
		});
		 }
	
	sNo="check";
	
return list;}
	public List<SalesOrderBO> getSalesOrderList(SalesOrderBO salesOrders){
		 List <SalesOrderVO> salesOrderVO=salesOrderDAO.getSalesOrderList(salesOrders);
		 AtomicInteger ref=new AtomicInteger(0);
		 List<SalesOrderBO> list=new ArrayList<>();
		 if(null!=salesOrderVO&&!salesOrderVO.isEmpty()&&salesOrderVO.size()>0) {
				salesOrderVO.forEach(salesOrder->{
					
					if(null!=salesOrder.getSalesOrderNo()) {
						sNo=salesOrder.getSalesOrderNo();
						ClientBO client=new ClientBO();
						GstBO gstBO=new GstBO();
						SalesOrderBO SalesOrderbo=new SalesOrderBO();
						client.setFirstName(salesOrder.getCustomer().getFirstName());
						client.setCompanyName(salesOrder.getCustomer().getCompanyName());
						client.setAddress(salesOrder.getCustomer().getAddress());
						client.setContactNo(salesOrder.getCustomer().getContactNumber());
						client.setMobileNo(salesOrder.getCustomer().getMobileNumber());
						SalesOrderbo.setClientBO(client);
						SalesOrderbo.setsNo(ref.incrementAndGet());
						SalesOrderbo.setQuantity(salesOrder.getQuantity());
						SalesOrderbo.setTotalPrice(SalesOrderbo.getTotalPrice());
						ProductServiceBO productService=new ProductServiceBO();
						productService.setServiceName(salesOrder.getProduct().getServiceName());
						double price=salesOrder.getProduct().getFees();
						productService.setFees(price);
					Double quantity=Double.valueOf(salesOrder.getQuantity());
					double totalPrice=price*quantity;
					//gstBO=salesOrderDAO.getGstById(salesOrder.getGstVO().getGstId());
					
					 SalesOrderbo.setTotalPrice(price*quantity);
					 
					 
						double cGstFinalRate =0;
						double sGstFinalRate=0;
						double overAllgrandTotal=0;
					 if(null!=salesOrder.getGstVO()) {
							double percentage=100;
							String removepercentageCgst=salesOrder.getGstVO().getCgst().replace("%", "");
							double cGst=Double.valueOf(removepercentageCgst);
							double cGstValue;
							
							cGstValue=cGst/percentage;
							cGstFinalRate=cGstValue*totalPrice;
							gstBO.setCgst(String.valueOf(cGstFinalRate));
					 }
					 if(null!=salesOrder.getGstVO()) {
							double percentage=100;
							String removepercentageCgst=salesOrder.getGstVO().getSgst().replace("%", "");
							double cGst=Double.valueOf(removepercentageCgst);
							double sGstValue;
						String sgst=salesOrder.getGstVO().getSgst();
						String cgst=salesOrder.getGstVO().getCgst();
						gstBO.setCgst(cgst);
						gstBO.setSgst(sgst);
						SalesOrderbo.setGstBO(gstBO);
							sGstValue=cGst/percentage;
							sGstFinalRate=sGstValue*totalPrice;
							//gstBO.setSgst(String.valueOf(sGstFinalRate));
							
							overAllgrandTotal=totalPrice+sGstFinalRate+cGstFinalRate;
							double value=overAllgrandTotal;
					 }
					/* if(0.0!=overAllTotal) {*/
					 overAllTotal=overAllgrandTotal+overAllTotal;
					SalesOrderbo.setTotalInvoice(overAllTotal);/* } */
					 SalesOrderbo.setGrandTotal(overAllgrandTotal);
						productService.setFees(salesOrder.getProduct().getFees());
						SalesOrderbo.setProduct(productService);
						SalesOrderbo.setSalesOrderNo(salesOrder.getSalesOrderNo());
						
						SalesOrderbo.setSalesOrderId(salesOrder.getSalesOrderId());
						if(null!=salesOrder&&null!=salesOrder.getCustomer()&&null!=salesOrder.getCustomer().getFirstName()) {
						client.setFirstName(salesOrder.getCustomer().getFirstName());}
						SalesOrderbo.setSalesOrderNo(salesOrder.getSalesOrderNo());
						SalesOrderbo.setClientBO(client);
						list.add(SalesOrderbo);
						
					}
				});
		 }
		 overAllTotal=0.0;
		return list;
		
	}
}
