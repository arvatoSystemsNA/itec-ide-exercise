package com.arvatosystems.itec.examples;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartDTO;
import com.arvatosystems.itec.pojo.OrderStatus;
import com.arvatosystems.itec.service.PersistenceService;

public class ExceptionHandlingExercise
{
	@Resource
	private PersistenceService persistenceService;

	/**
	 * First example: Exporting a cart object to a temporary file
	 *
	 * @param cart The cart
	 */
	public void exportCartToFile(final Cart cart)
	{
		try
		{
			// create temporary file
			final File tempFile = File.createTempFile("temp", ".txt");

			// convert cart model to DTO
			final CartDTO dto = convertCart(cart);

			// write DTO to file
			marshall(dto, tempFile);

			// update order status
			cart.setStatus(OrderStatus.PROCESSING);
			persistenceService.save(cart);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Second example: Updating the cart status
	 *
	 * @param cartId ID of the cart to update
	 * @param status The status to set
	 */
	public void updateCartStatus(final String cartId, final OrderStatus status)
	{
		final Cart cart = findCartById(cartId);

		if (cart != null && cart.isValid())
		{
			cart.setStatus(status);
		}
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

	protected Cart findCartById(final String cartId)
	{
		// just a stub
		return new Cart();
	}
}
