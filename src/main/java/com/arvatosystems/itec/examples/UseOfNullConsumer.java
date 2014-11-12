package com.arvatosystems.itec.examples;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arvatosystems.itec.pojo.ProductModel;
import com.arvatosystems.us.hybris.core.lang.Option;

public class UseOfNullConsumer
{
	@Resource
	private UseOfNullSolution useOfNullSolution;

	private static final Logger LOG = LoggerFactory.getLogger(UseOfNullConsumer.class);

	/**
	 * Use result or handle the no result use case
	 */
	public void consumeOption1()
	{
		final Option<ProductModel> result = useOfNullSolution.findFirstByAttributes(new HashMap<String, Object>(),
				new HashMap<String, Boolean>(), ProductModel.class);
		if (result.isSome())
		{
			// use the result
			LOG.info(result.get().getStatus());
		}
		else
		{
			// Handle the error
			LOG.error("No result !!");
		}
	}

	/**
	 * Use result or fail if there is no result
	 */
	public void consumeOption2()
	{
		final Map<String, Object> attributes = new HashMap<>();
		final String productCode = "123123";
		attributes.put("code", productCode);
		final ProductModel result = useOfNullSolution.findFirstByAttributes(attributes, new HashMap<String, Boolean>(),
				ProductModel.class).getOrFail("Failed to retrieve object", productCode);

		// use the result
		LOG.info(result.getStatus());
	}

	/**
	 * Use result or default to a default value
	 */
	public void consumeOption3()
	{
		final Map<String, Object> attributes = new HashMap<>();
		final String productCode = "123123";
		final ProductModel productModel = new ProductModel();

		attributes.put("code", productCode);
		final ProductModel result = useOfNullSolution.findFirstByAttributes(attributes, new HashMap<String, Boolean>(),
				ProductModel.class).getOrDefault(productModel);

		// use the result
		LOG.info(result.getStatus());
	}
}
