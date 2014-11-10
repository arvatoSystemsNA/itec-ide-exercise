package com.arvatosystems.itec.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arvatosystems.itec.pojo.Address;

public class PrimitivesSolution
{
	private static final Logger LOG = LoggerFactory.getLogger(PrimitivesSolution.class);

	public void runALoopTheBadWay()
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

	public void runALoopTheRightWay()
	{
		final long startTime = System.currentTimeMillis();
		long sum = 0L;
		for (long i = 0; i <= Integer.MAX_VALUE; i++)
		{
			sum += i;
		}
		final long endTime = System.currentTimeMillis();
		LOG.info("Total time : {}", Long.valueOf((endTime - startTime) / 1000));
		LOG.info("Total sum : {}", Long.valueOf(sum));
	}

	public void runEqualityCases()
	{
		System.out.println(new Integer(5) == new Integer(5)); // false
		System.out.println(new Integer(500) == new Integer(500)); // false

		System.out.println(Integer.valueOf(5) == Integer.valueOf(5)); // true (what?! ;-))
		System.out.println(Integer.valueOf(500) == Integer.valueOf(500)); // false
	}

	public void runTestTheWrongWay()
	{
		final Address address = new Address();
		if (address.getShipping())
		{
			// do stuff
		}
	}

	public void runTestABetterWay()
	{
		final Address address = new Address();
		if (Boolean.TRUE.equals(address.getShipping()))
		{
			// do stuff
		}
	}


	public static void main(final String[] args)
	{
		final PrimitivesSolution solution = new PrimitivesSolution();
		solution.runALoopTheBadWay();
		solution.runALoopTheRightWay();
	}
}
