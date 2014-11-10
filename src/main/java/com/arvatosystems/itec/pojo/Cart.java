package com.arvatosystems.itec.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart
{
	private List<CartEntry> entries = new ArrayList<>();
	private boolean calculated = false;
	private BigDecimal totalPrice;

	public List<CartEntry> getEntries()
	{
		return entries;
	}

	public void setEntries(final List<CartEntry> entries)
	{
		this.entries = entries;
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public boolean isCalculated()
	{
		return calculated;
	}

	public void setCalculated(final boolean calculated)
	{
		this.calculated = calculated;
	}
}
