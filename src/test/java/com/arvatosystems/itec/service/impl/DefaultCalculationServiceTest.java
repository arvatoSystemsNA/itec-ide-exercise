package com.arvatosystems.itec.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartEntry;

public class DefaultCalculationServiceTest
{
	@Resource
	private DefaultCalculationService calculationService;


	public void shouldThrowExceptionWhenCartIsNull()
	{
		calculationService.calculateCart(null);
	}

	@Test
	public void shouldCalculateTotals()
	{
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		calculationService.calculateCart(cart);
	}

	@Test
	public void shouldCalculateTotalsAsynchronously()
	{
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		calculationService.calculateCartAsynchronously(cart);
	}
}
