package com.arvatosystems.us.hybris.core.lang;

public abstract class Option<T>
{
	private T value = null;

	public abstract boolean isSome();

	public abstract boolean isNone();

	public abstract T get();

	public T getOrDefault(final T defaultValue)
	{
		return isSome() ? get() : defaultValue;
	}

	public T getOrFail(final String message)
	{
		if (isSome())
		{
			return get();
		}
		else
		{
			throw new DBCException(message);
		}
	}

	protected T getValue()
	{
		return this.value;
	}

	protected void setValue(final T value)
	{
		this.value = value;
	}
}
