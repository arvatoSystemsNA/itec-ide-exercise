package com.arvatosystems.itec.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSolution
{
	private static final Logger LOG = LoggerFactory.getLogger(LoggingSolution.class);

	public void logSomethingTheWrongWay()
	{
		final String someString = "ITEC";
		LOG.debug("Welcome to " + someString);
	}

	public void logSomethingSlightlyTheBetterWay()
	{
		final String someString = "ITEC";
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Welcome to " + someString);
		}
	}

	public void logSomethingTheRightWay()
	{
		final String someString = "ITEC";
		LOG.debug("Welcome to {}", someString);
	}

	public void logSomethingExpensiveTheWrongWay()
	{
		LOG.debug("Welcome to {}", returnSomethingVeryCostly());
	}

	public void logSomethingExpensiveTheRighWay()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Welcome to {}", returnSomethingVeryCostly());
		}
	}

	private Integer returnSomethingVeryCostly()
	{
		LOG.info("This is a very expensive operation");
		final Integer iAmExpensive = Integer.valueOf(500);
		return iAmExpensive;
	}

	public static void main(final String[] args)
	{
		final LoggingSolution example = new LoggingSolution();
		example.logSomethingTheRightWay();
		example.logSomethingSlightlyTheBetterWay();
		example.logSomethingTheRightWay();
		example.logSomethingExpensiveTheWrongWay();
		example.logSomethingExpensiveTheRighWay();
	}
}
