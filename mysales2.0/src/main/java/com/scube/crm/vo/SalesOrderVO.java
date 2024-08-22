package com.scube.crm.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.SalesOrderBO;
@Entity
@Table(name = "sales_order")
public class SalesOrderVO extends BasicEntity {

	/**
	 */
	private static final long serialVersionUID = 1L; 
    private long salesOrderId;
	private Customer customer;
	private ProductServiceVO product;
	private double price;
	private double totalPrice;
	private long quantity;
	private double totalInvoice;
	private double grandTotal;;
	private GstVO gstVO;
	private List<SalesOrderVO> salesOrderVO;
	 private long invoiceId;
	 private String salesOrderNo;
	
	@Id
	@GeneratedValue
	@Column(name="sales_id")
	public long getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	@OneToOne
	@JoinColumn(name="customer_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
		@OneToOne
	@JoinColumn(name="product_id")
	public ProductServiceVO getProduct() {
		return product;
	}
	public void setProduct(ProductServiceVO product) {
		this.product = product;
	}
@Transient
	/* @Column(name="total_invoice") */
	public double getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(double totalInvoice) {
		this.totalInvoice = totalInvoice;
	}
	@OneToOne
	@JoinColumn(name="gst_id")
	public GstVO getGstVO() {
		return gstVO;
	}
	public void setGstVO(GstVO gstVO) {
		this.gstVO = gstVO;
	}
	@Transient
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	@Transient
	public List<SalesOrderVO> getSalesOrderVO() {
		return salesOrderVO;
	}
	public void setSalesOrderVO(List<SalesOrderVO> salesOrderVO) {
		this.salesOrderVO = salesOrderVO;
	}
	@Transient
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	/* @Column(name="total_price") */
	@Transient
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	 @Column(name="quantity") 
		public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	 @Column(name="invoice_id") 
	public long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}
	 @Column(name="sales_order_no") 
	public String getSalesOrderNo() {
		return salesOrderNo;
	}
	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}
	
}
