package com.arvatosystems.itec.examples;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class LanguagePitfallsExercise
{
	public BigDecimal divide(final double inputA, final double inputB)
	{
		// construct big decimal
		final BigDecimal dividend = new BigDecimal(inputA);
		final BigDecimal divisor = new BigDecimal(inputB);

		// divide
		return dividend.divide(divisor);
	}

	public void copy(final File inputFile, final File outputFile) throws IOException
	{
		// uncomment me to demo

		// final FileInputStream is = new FileInputStream(inputFile);
		// final FileOutputStream out = new FileOutputStream(outputFile);
		//
		// final byte[] buffer = new byte[1024];
		// int len = is.read(buffer);
		// while (len != -1)
		// {
		// out.write(buffer, 0, len);
		// len = is.read(buffer);
		// }
	}

	public void rename(final File inputFile)
	{
		final File targetFile = new File(inputFile.getParent(), inputFile.getName() + ".backup");

		// rename original file with backup extension
		inputFile.renameTo(targetFile);
	}

	public void swap(final String str1, final String str2)
	{
		// uncomment me to demo

		// final String tmp = str1;
		// str1 = str2;
		// str2 = tmp;
	}

	public static void main(final String... args)
	{
		System.out.println("Good: " + new BigDecimal("0.1"));
		System.out.println("Bad: " + new BigDecimal(0.1));

		final BigDecimal one = BigDecimal.ONE;
		final BigDecimal three = BigDecimal.valueOf(3);

		System.out.println("Good: " + one.divide(three, 2, RoundingMode.HALF_UP));
		System.out.println("Bad: " + one.divide(three));
	}

}
