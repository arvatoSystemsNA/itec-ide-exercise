package com.arvatosystems.itec.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExercise
{
	private static final Logger LOG = LoggerFactory.getLogger(LoggingExercise.class);

	public void logSomething()
	{
		final String someString = "ITEC";
		LOG.debug("Welcome to " + someString);
	}

}