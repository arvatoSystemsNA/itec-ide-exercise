package com.arvatosystems.itec.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arvatosystems.itec.pojo.Order;
import com.arvatosystems.itec.pojo.ValidationResult;
import com.arvatosystems.us.hybris.core.lang.Either;

public class UseOfEitherExampleConsumer
{
	private final UseOfEitherExample useOfEitherExample = new UseOfEitherExample();
	private static final Logger LOG = LoggerFactory.getLogger(UseOfEitherExampleConsumer.class);

	public void consumeEither()
	{
		final String customerId = "123";
		final Either<Order, ValidationResult> result = useOfEitherExample.createCustomer(customerId);
		if (result.isLeft())
		{
			// success case
			LOG.info(result.left().getOrderCode());
		}
		else
		{
			// validation case
			LOG.info(result.right().getValidationMessage());
		}
	}
}
