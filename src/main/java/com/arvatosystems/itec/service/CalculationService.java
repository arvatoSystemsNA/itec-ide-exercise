package com.arvatosystems.itec.service;

import com.arvatosystems.itec.pojo.Cart;

public interface CalculationService
{
	void calculateCart(final Cart cart);

	void calculateCartAsynchronously(final Cart cart);

}
