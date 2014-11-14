package com.arvatosystems.itec.examples;

import com.arvatosystems.itec.pojo.CustomerValidationException;
import com.arvatosystems.itec.pojo.Order;
import com.arvatosystems.itec.pojo.ValidationResult;
import com.arvatosystems.itec.service.ExternalService;
import com.arvatosystems.us.hybris.core.lang.Either;
import com.arvatosystems.us.hybris.core.lang.Left;
import com.arvatosystems.us.hybris.core.lang.Right;

public class UseOfEitherExample
{
	private final ExternalService externalService = new ExternalService();

	public Either<Order, ValidationResult> createCustomer(final String customerId)
	{
		try
		{
			final Order newOrder = externalService.createCustomer(customerId);
			return new Left<>(newOrder);
		}
		catch (final CustomerValidationException err)
		{
			return new Right<>(new ValidationResult(err));
		}
	}

}
