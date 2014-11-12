package com.arvatosystems.itec.examples;

import static com.arvatosystems.us.hybris.core.lang.DBC.checkArgument;
import static com.arvatosystems.us.hybris.core.lang.DBC.checkNotNull;

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

public class MethodSeparationSolution
{
	@Resource
	private ModelService modelService;

	@Resource
	private CommerceCartService commerceCartService;

	public CartModificationData addToCart(final String code, final long quantity, final HttpServletRequest request)
	{
		checkNotNull(request);
		checkArgument(StringUtils.isNotBlank(code), "code must be provided");

		final CartModel cartModel = getCartService().getSessionCart();
		final ProductModel productModel = this.getProductService().getProductForCode(code);
		checkArgument(productModel instanceof SKUVariantProductModel, "Product must be a SKU");
		final SKUVariantProductModel skuModel = (SKUVariantProductModel) productModel;

		// Validate data state
		validateIfProductInactive(skuModel);
		validateIfStockIsAvailable(skuModel, request);

		// Proceed with Add to cart
		final CommerceCartModification result = addToCartInternal(quantity, cartModel, skuModel);
		return this.getCartModificationConverter().convert(result);
	}

	private CommerceCartModification addToCartInternal(final long quantity, final CartModel cartModel,
			final SKUVariantProductModel skuModel)
	{
		setUuidAndSaveCartIfNotExisting(cartModel);

		final CommerceCartModification modification = this.commerceCartService.addCart(cartModel, skuModel, quantity,
				skuModel.getUnit(), false);
		return modification;
	}

	/**
	 * @throws CommerceCartModificationException in case no stock is available
	 */
	private void validateIfStockIsAvailable(final SKUVariantProductModel skuModel, final HttpServletRequest request)
	{
		final Set<StockLevelModel> stockLevelSet = skuModel.getStockLevels();
		// only expecting one StockLevelModel to be returned in the set of Stock Levels
		for (final StockLevelModel stockLevel : stockLevelSet)
		{
			if (stockLevel.getAvailable() > 0)
			{
				return;
			}
		}

		request.getSession().setAttribute("noInventory", "true");
		throw new CommerceCartModificationException("Product " + skuModel.getCode() + " has no inventory. Not added to cart");
	}

	/**
	 * @throws CommerceCartModificationException in case the product is INACTIVE
	 */
	private void validateIfProductInactive(final SKUVariantProductModel skuModel)
	{
		if (skuModel.getBaseProduct().getStatus().equals("INACTIVE"))
		{
			throw new CommerceCartModificationException("Product " + skuModel.getCode() + " is inactive. Not added to cart");
		}
	}

	private void setUuidAndSaveCartIfNotExisting(final CartModel cartModel)
	{
		if (StringUtils.isBlank(cartModel.getCartUuid()))
		{
			cartModel.setCartUuid(UUID.randomUUID().toString());
			modelService.save(cartModel);
		}
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
