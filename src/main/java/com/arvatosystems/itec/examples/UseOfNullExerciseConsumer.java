package com.arvatosystems.itec.examples;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arvatosystems.itec.pojo.ProductModel;

public class UseOfNullExerciseConsumer
{
	@Resource
	private UseOfNullExercise useOfNullExercise;

	private static final Logger LOG = LoggerFactory.getLogger(UseOfNullExerciseConsumer.class);

	/**
	 * Query findFirstByAttributes and use Result
	 */
	public void consumeUseOfNullExercise()
	{
		final ProductModel result = useOfNullExercise.findFirstByAttributes(new HashMap<String, Object>(),
				new HashMap<String, Boolean>(), ProductModel.class);

		LOG.info(result.getStatus());
	}
}
