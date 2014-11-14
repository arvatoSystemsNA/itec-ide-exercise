package com.arvatosystems.itec.service.impl;

import java.math.BigDecimal;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartEntry;
import com.arvatosystems.itec.service.CalculationService;
import com.arvatosystems.itec.service.PersistenceService;
import com.arvatosystems.us.hybris.core.lang.DBC;
import com.google.common.base.Throwables;

public class DefaultCalculationService implements CalculationService
{
	private PersistenceService persitenceService;

	@Override
	public void calculateCart(final Cart cart)
	{
		// I reallly want an illegal argument exception if cart is null
		DBC.checkArgument(cart != null, "The cart can not be null");
		BigDecimal total = BigDecimal.ZERO;
		for (final CartEntry entry : cart.getEntries())
		{
			total = total.add(entry.getUnitPrice().multiply(BigDecimal.valueOf(entry.getQuantity())));
		}

		cart.setTotalPrice(total);
		cart.setCalculated(true);
		persitenceService.save(cart);

	}

	@Override
	public void calculateCartAsynchronously(final Cart cart)
	{
		// just some mock code to demonstrate asynchronous testing. DO NOT WRITE THAT KIND OF CODE IN PRODUCTION!
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					Thread.sleep(1000);
					calculateCart(cart);
				}
				catch (final InterruptedException e)
				{
					Throwables.propagate(e);
				}

			}
		}).start();

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
