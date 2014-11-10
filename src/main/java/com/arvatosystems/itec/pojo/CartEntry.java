package com.arvatosystems.itec.pojo;

import java.math.BigDecimal;


public class CartEntry
{
	private String productCode;
	private int quantity;
	private BigDecimal unitPrice;

	public CartEntry(final String productCode, final int quantity, final BigDecimal unitPrice)
	{
		super();
		this.productCode = productCode;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(final String productCode)
	{
		this.productCode = productCode;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final int quantity)
	{
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}

	public void setUnitPrice(final BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}
}
