package com.arvatosystems.itec.examples;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.arvatosystems.itec.pojo.CartModel;
import com.arvatosystems.itec.pojo.CartModificationData;
import com.arvatosystems.itec.pojo.CommerceCartModification;
import com.arvatosystems.itec.pojo.CommerceCartModificationException;
import com.arvatosystems.itec.pojo.HttpServletRequest;
import com.arvatosystems.itec.pojo.ProductModel;
import com.arvatosystems.itec.pojo.SKUVariantProductModel;
import com.arvatosystems.itec.pojo.StockLevelModel;
import com.arvatosystems.itec.service.CartModificationConverter;
import com.arvatosystems.itec.service.CartService;
import com.arvatosystems.itec.service.CommerceCartService;
import com.arvatosystems.itec.service.ModelService;
import com.arvatosystems.itec.service.ProductService;

public class MethodSeparationExample
{
	@Resource
	private ModelService modelService;

	@Resource
	private CommerceCartService commerceCartService;

	public CartModificationData addToCart(final String code, final long quantity, final HttpServletRequest request)
	{
		final ProductModel product = getProductService().getProductForCode(code);
		final CartModel cartModel = getCartService().getSessionCart();

		if (StringUtils.isBlank(cartModel.getCartUuid()))
		{
			cartModel.setCartUuid(UUID.randomUUID().toString());
			modelService.save(cartModel);
		}

		if ((((SKUVariantProductModel) this.getProductService().getProductForCode(code)).getBaseProduct())
.getStatus().equals(
				"INACTIVE"))
		{
			throw new CommerceCartModificationException("Product " + code + " is inactive. Not added to cart");
		}

		final Set<StockLevelModel> stockLevelSet = ((SKUVariantProductModel) product).getStockLevels();
		// only expecting one StockLevelModel to be returned in the set of Stock
		// Levels
		for (final StockLevelModel stockLevel : stockLevelSet)
		{
			if (stockLevel.getAvailable() > 0)
			{
				final CommerceCartModification modification = this.commerceCartService.addCart(cartModel, product,
						quantity, product.getUnit(), false);

				return this.getCartModificationConverter().convert(modification);
			}
		}

		if (request != null)
		{
			request.getSession().setAttribute("noInventory", "true");
		}

		throw new CommerceCartModificationException("Product " + code + " has no inventory. Not added to cart");
	}

	private CartModificationConverter getCartModificationConverter()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private CartService getCartService()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private ProductService getProductService()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
