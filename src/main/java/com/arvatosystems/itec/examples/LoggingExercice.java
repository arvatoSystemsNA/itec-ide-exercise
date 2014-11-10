package com.arvatosystems.itec.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExercice
{
	private static final Logger LOG = LoggerFactory.getLogger(LoggingExercice.class);

	public void logSomething()
	{
		final String someString = "ITEC";
		LOG.debug("Welcome to " + someString);
	}

}
