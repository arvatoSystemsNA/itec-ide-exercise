package com.arvatosystems.us.hybris.core.lang;



public class Some<T> extends Option<T>
{
	public Some(final T value)
	{
		super();
		setValue(value);
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
		return getValue();
	}
}
