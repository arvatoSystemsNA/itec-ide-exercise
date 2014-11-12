package com.arvatosystems.us.hybris.core.lang;


public abstract class Option<T>
{
	protected T value = null;

	public abstract boolean isSome();

	public abstract boolean isNone();

	public abstract T get();

	public T getOrDefault(final T defaultValue)
	{
		return isSome() ? get() : defaultValue;
	}

	public T getOrFail(final String message, final Object... args)
	{
		if (isSome())
		{
			return get();
		}
		else
		{
			if (args != null)
			{
				throw new DBCException(String.format(message, args));
			}
			else
			{
				throw new DBCException(message);
			}
		}
	}
}
