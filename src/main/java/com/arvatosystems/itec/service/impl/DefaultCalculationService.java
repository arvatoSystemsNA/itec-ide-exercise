package com.arvatosystems.itec.service.impl;

import java.math.BigDecimal;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartEntry;
import com.arvatosystems.itec.service.CalculationService;
import com.arvatosystems.itec.service.PersistenceService;
import com.arvatosystems.us.hybris.core.lang.DBC;

public class DefaultCalculationService implements CalculationService
{
	private PersistenceService persitenceService;

	@Override
	public void calculateCart(final Cart cart)
	{
		DBC.checkNotNull(cart);

		BigDecimal total = BigDecimal.ZERO;
		for (final CartEntry entry : cart.getEntries()){
			total = total.add(entry.getUnitPrice().multiply(BigDecimal.valueOf(entry.getQuantity())));
		}

		cart.setTotalPrice(total);
		cart.setCalculated(true);
		persitenceService.save(cart);

	}

	public PersistenceService getPersitenceService()
	{
		return persitenceService;
	}

	public void setPersitenceService(final PersistenceService persitenceService)
	{
		this.persitenceService = persitenceService;
	}

}
