package com.arvatosystems.itec.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartEntry;
import com.arvatosystems.itec.service.PersistenceService;

public class DefaultCalculationServiceSolutionTest
{

	private final DefaultCalculationService calculationService = new DefaultCalculationService();

	@Mock
	private PersistenceService persistenceService;

	@Before
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
		calculationService.setPersitenceService(persistenceService);
	}



	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenCartIsNull()
	{
		calculationService.calculateCart(null);
	}

	@Test
	public void checkTotals()
	{
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		calculationService.calculateCart(cart);

		assertEquals(BigDecimal.valueOf(70), cart.getTotalPrice());
		verify(persistenceService).save(cart);
	}
}
