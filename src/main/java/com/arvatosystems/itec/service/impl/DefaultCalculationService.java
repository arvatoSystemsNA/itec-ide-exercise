package com.arvatosystems.itec.service.impl;

import java.math.BigDecimal;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartEntry;
import com.arvatosystems.itec.service.CalculationService;
import com.arvatosystems.itec.service.PersistenceService;
import com.arvatosystems.us.hybris.core.concurrent.JaloExecutorService;
import com.arvatosystems.us.hybris.core.lang.DBC;
import com.google.common.base.Throwables;

public class DefaultCalculationService implements CalculationService
{
	private PersistenceService persitenceService;
	private JaloExecutorService jaloExecutorService;

	@Override
	public void calculateCart(final Cart cart)
	{
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
		// just some demo code to demonstrate asynchronous functionality.
		jaloExecutorService.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(3000); // simulate expensive operation
					calculateCart(cart);
				}
				catch (final InterruptedException e)
				{
					Throwables.propagate(e);
				}

			}
		});
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
