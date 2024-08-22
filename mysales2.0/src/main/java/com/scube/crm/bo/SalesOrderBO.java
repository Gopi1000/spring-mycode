package com.scube.crm.bo;
import java.util.List;

import com.scube.crm.vo.Customer;

public class SalesOrderBO extends BaseBO {
	private static final long serialVersionUID = -1858124723519342841L;
	private long salesOrderId;
		private ClientBO clientBO;
		private ProductServiceBO product;
		private double grandTotal;
		private	List<SalesOrderBO> salesOrderBO;
		 private long invoiceId;
		 private String salesOrderNo;
		 private long sNo;
		public List<SalesOrderBO> getSalesOrderBO() {
			return salesOrderBO;
		}
		public void setSalesOrderBO(List<SalesOrderBO> salesOrderBO) {
			this.salesOrderBO = salesOrderBO;
		}
		public double getGrandTotal() {
			return grandTotal;
		}
		public void setGrandTotal(double grandTotal) {
			this.grandTotal = grandTotal;
		}
		public ProductServiceBO getProduct() {
			return product;
		}
		public void setProduct(ProductServiceBO product) {
			this.product = product;
		}
		private double price;
		private double totalPrice;
		private long quantity;
		private double totalInvoice;
		
		private GstBO gstBO;
		
		public long getQuantity() {
			return quantity;
		}
		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}
		
		
		public ClientBO getClientBO() {
			return clientBO;
		}
		public void setClientBO(ClientBO clientBO) {
			this.clientBO = clientBO;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		
		
		public long getSalesOrderId() {
			return salesOrderId;
		}
		public void setSalesOrderId(long salesOrderId) {
			this.salesOrderId = salesOrderId;
		}
		public double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public GstBO getGstBO() {
			return gstBO;
		}
		public void setGstBO(GstBO gstBO) {
			this.gstBO = gstBO;
		}
		public double getTotalInvoice() {
			return totalInvoice;
		}
		public void setTotalInvoice(double totalInvoice) {
			this.totalInvoice = totalInvoice;
		}
		public long getInvoiceId() {
			return invoiceId;
		}
		public void setInvoiceId(long invoiceId) {
			this.invoiceId = invoiceId;
		}
		public String getSalesOrderNo() {
			return salesOrderNo;
		}
		public void setSalesOrderNo(String salesOrderNo) {
			this.salesOrderNo = salesOrderNo;
		}
		public long getsNo() {
			return sNo;
		}
		public void setsNo(long sNo) {
			this.sNo = sNo;
		}}