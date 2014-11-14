package com.arvatosystems.itec.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arvatosystems.us.hybris.core.lang.Pair;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;

public class LanguagePitfallsSolution
{
	private static final Logger LOG = LoggerFactory.getLogger(LanguagePitfallsSolution.class);

	/**
	 * Improved version avoiding two common BigDecimal pitfalls.
	 *
	 * @param inputA dividend
	 * @param inputB divisor
	 * @return result of the division
	 */
	public BigDecimal divide(final double inputA, final double inputB)
	{
		// construct big decimal using valueOf function or String constructor
		// do NOT use the double constructor
		final BigDecimal dividend = BigDecimal.valueOf(inputA);
		final BigDecimal divisor = new BigDecimal(Double.toString(inputB));

		// always provide a rounding mode, otherwise you might cause an ArithmeticException
		return dividend.divide(divisor, 2, RoundingMode.HALF_UP);
	}

	/**
	 * Improved version using the Java 7 try with resource
	 *
	 * @param inputFile The input file
	 * @param outputFile The output file
	 * @throws IOException If the input or output file can't be accessed.
	 */
	public void copyInToOut(final File inputFile, final File outputFile) throws IOException
	{
		try (final FileInputStream is = new FileInputStream(inputFile);
				final FileOutputStream out = new FileOutputStream(outputFile))
		{
			final byte[] buffer = new byte[1024];
			int len = is.read(buffer);
			while (len != -1)
			{
				out.write(buffer, 0, len);
				len = is.read(buffer);
			}
		}

		// or: using 3rd party libraries
		Files.copy(inputFile, outputFile);
	}

	/**
	 * Demonstrates how to rename a file and check its result
	 *
	 * @param inputFile The input file
	 */
	public void rename(final File inputFile)
	{
		final File targetFile = new File(inputFile.getParent(), inputFile.getName() + ".backup");

		// fail fast if rename fails
		Preconditions.checkArgument(inputFile.renameTo(targetFile), "Could not rename file %s to %s", inputFile, targetFile);

		// or alternatively at least log
		if (!inputFile.renameTo(targetFile))
		{
			LOG.warn("Could not rename file {} to {}. Will continue anyway...", inputFile.getAbsolutePath(),
					targetFile.getAbsolutePath());
		}
	}

	/**
	 * Usage: <code>y = swap(x, x=y);</code>
	 *
	 * @param a a value
	 * @param b another value
	 * @return always returns the first value
	 */
	public int swap(final int a, final int b)
	{
		return a;
	}

	/**
	 * Usage:
	 *
	 * <pre>
	 * {@code
	 * Pair<Integer, Integer> original = new Pair<>(1, 2);
	 * Pair<Integer, Integer> swapped = new LanguagePitfallsSolution().swap(original);
	 * }
	 * </pre>
	 *
	 * @param pair The original key and value
	 * @return the swapped key and value
	 */
	public Pair<Integer, Integer> swap(final Pair<Integer, Integer> pair)
	{
		return new Pair<>(pair.getValue(), pair.getKey());
	}

}