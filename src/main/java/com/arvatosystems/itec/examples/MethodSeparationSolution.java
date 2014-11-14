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
import com.arvatosystems.us.hybris.core.lang.None;
import com.arvatosystems.us.hybris.core.lang.Option;
import com.arvatosystems.us.hybris.core.lang.Some;

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
		final Option<String> productActiveResult = validateIfProductInactive(skuModel);
		if (productActiveResult.isSome())
		{
			throwException(productActiveResult);
		}

		final Option<String> productInStockResult = validateIfStockIsAvailable(skuModel);
		if (productInStockResult.isSome())
		{
			request.getSession().setAttribute("noInventory", "true");
			throwException(productInStockResult);
		}

		// Proceed with Add to cart
		final CommerceCartModification result = addToCartInternal(quantity, cartModel, skuModel);
		return this.getCartModificationConverter().convert(result);
	}

	/**
	 * @throws CommerceCartModificationException
	 */
	private void throwException(final Option<String> result)
	{
		throw new CommerceCartModificationException(result.get());
	}

	private CommerceCartModification addToCartInternal(final long quantity, final CartModel cartModel,
			final SKUVariantProductModel skuModel)
	{
		setUuidAndSaveCartIfNotExisting(cartModel);

		final CommerceCartModification modification = this.commerceCartService.addCart(cartModel, skuModel, quantity,
				skuModel.getUnit(), false);
		return modification;
	}

	private Option<String> validateIfStockIsAvailable(final SKUVariantProductModel skuModel)
	{
		final Set<StockLevelModel> stockLevelSet = skuModel.getStockLevels();
		// only expecting one StockLevelModel to be returned in the set of Stock Levels
		for (final StockLevelModel stockLevel : stockLevelSet)
		{
			if (stockLevel.getAvailable() > 0)
			{
				return new None<>();
			}
		}

		return new Some<>("Product " + skuModel.getCode() + " has no inventory. Not added to cart");
	}

	private Option<String> validateIfProductInactive(final SKUVariantProductModel skuModel)
	{
		if ("INACTIVE".equals(skuModel.getBaseProduct().getStatus()))
		{
			return new Some<>("Product " + skuModel.getCode() + " is inactive. Not added to cart");
		}
		return new None<>();
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
