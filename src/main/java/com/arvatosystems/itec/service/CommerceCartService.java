package com.arvatosystems.itec.service;

import com.arvatosystems.itec.pojo.CartModel;
import com.arvatosystems.itec.pojo.CommerceCartModification;
import com.arvatosystems.itec.pojo.ProductModel;

public interface CommerceCartService
{

	CommerceCartModification addCart(CartModel cartModel, ProductModel product, long quantity, Object unit, boolean b);

}
