package com.scube.springcrm.model;

import org.hibernate.validator.constraints.NotEmpty;

//import javax.validation.constraints.NotEmpty;

public class ProductBo {
	
	private int productId;
	
	@NotEmpty(message="please insert value")
	private String productType;
	
	@NotEmpty(message="please insert value")
	private String brandName;
	
	@NotEmpty(message="please insert value")
    private String modelNo;
	
	@NotEmpty(message="please insert value")
	private String quantity;
	
	@NotEmpty(message="please insert value")
	private String price;
	
	@NotEmpty(message="please insert value")
    private String discountPrice;

	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	
	

}
