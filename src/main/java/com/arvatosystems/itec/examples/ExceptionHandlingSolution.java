package com.arvatosystems.itec.examples;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartDTO;
import com.arvatosystems.itec.pojo.OrderStatus;
import com.arvatosystems.itec.service.PersistenceService;
import com.arvatosystems.us.hybris.core.io.IOUtils;
import com.arvatosystems.us.hybris.core.lang.DBC;
import com.arvatosystems.us.hybris.core.lang.Option;
import com.arvatosystems.us.hybris.core.lang.Some;
import com.google.common.base.Throwables;

public class ExceptionHandlingSolution
{
	@Resource
	private PersistenceService persistenceService;

	/**
	 * Solution
	 *
	 * @param cart The cart
	 */
	public void exportCartToFile(final Cart cart)
	{
		// convert cart model to DTO
		final CartDTO dto = convertCart(cart);

		// create temporary file
		File tempFile = null;
		try
		{
			tempFile = File.createTempFile("temp", ".txt");
		}
		catch (final IOException e)
		{
			Throwables.propagate(e);
		}

		// write DTO to file
		marshall(dto, tempFile);

		// update order status
		cart.setStatus(OrderStatus.PROCESSING);
		persistenceService.save(cart);
	}

	/**
	 * Another solution using asycore's IOUtils
	 *
	 * @param cart The cart
	 */
	public void exportCartToFileUsingIOUtils(final Cart cart)
	{
		// convert cart model to DTO
		final CartDTO dto = convertCart(cart);

		// creat temporary file
		final File tempFile = IOUtils.createTemporaryFile("temp", ".txt");

		// write DTO to file
		marshall(dto, tempFile);
	}

	/**
	 * Improved solution the fails if state is inconsistent rather than ignoring it.
	 *
	 * @param cartId ID of the cart to update
	 * @param status The status to set
	 */
	public void updateCartStatus(final String cartId, final OrderStatus status)
	{
		final Option<Cart> cartResult = findCartById(cartId);

		// this will throw an exception if the order is not found
		final Cart order = cartResult.getOrFail("Cart with code %s not found", cartId);

		// this will throw an exception if the order is not valid
		DBC.checkArgument(order.isValid(), "Cart with code %s is not valid", cartId);

		order.setStatus(status);
	}



	protected CartDTO convertCart(final Cart cart)
	{
		// just a stub
		return new CartDTO();
	}

	protected void marshall(final CartDTO dto, final File outputFile)
	{
		// just a stub
	}

	protected Option<Cart> findCartById(final String cartId)
	{
		// just a stub
		return new Some<>(new Cart());
	}
}
