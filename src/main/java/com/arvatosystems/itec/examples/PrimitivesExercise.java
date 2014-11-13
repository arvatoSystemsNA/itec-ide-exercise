package com.arvatosystems.itec.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arvatosystems.itec.pojo.Address;

public class PrimitivesExercise
{
	private static final Logger LOG = LoggerFactory.getLogger(PrimitivesExercise.class);

	public void runALoop()
	{
		final long startTime = System.currentTimeMillis();
		Long sum = Long.valueOf(0L);
		for (long i = 0; i <= Integer.MAX_VALUE; i++)
		{
			sum += i;
		}
		final long endTime = System.currentTimeMillis();
		LOG.info("Total time : {}", Long.valueOf((endTime - startTime) / 1000));
		LOG.info("Total sum : {}", sum);
	}


	public void runEqualityCases()
	{
		// The people who guess right get a special prize...
		// 1. 0011
		// 2. 1111
		// 3. 0000
		// 4. 0010
		// 5. 1010
		// 6. 1000
		System.out.println(new Integer(5) == new Integer(5)); // ?
		System.out.println(new Integer(500) == new Integer(500)); // ?

		System.out.println(Integer.valueOf(5) == Integer.valueOf(5)); // ?
		System.out.println(Integer.valueOf(500) == Integer.valueOf(500)); // ?
	}

	public void runTestOnBoolean()
	{
		final Address address = new Address();
		if (address.getShipping())
		{
			LOG.info("address shipping ? {}", address.getShipping());
		}
	}

	public static void main(final String[] args)
	{
		// final PrimitivesExercise exercise = new PrimitivesExercise();
		// exercise.runALoop();
		// exercise.runEqualityCases();
		// exercise.runTestOnBoolean();
	}
}
