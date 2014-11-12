package com.arvatosystems.us.hybris.core.lang;


public class Some<T> extends Option<T>
{
	public Some(final T value)
	{
		super();
		this.value = value;
	}

	@Override
	public boolean isSome()
	{
		return true;
	}

	@Override
	public boolean isNone()
	{
		return false;
	}

	@Override
	public T get()
	{
		return value;
	}
}
