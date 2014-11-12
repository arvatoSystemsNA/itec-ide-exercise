package com.arvatosystems.us.hybris.core.lang;



public class None<T> extends Option<T>
{
	@Override
	public boolean isSome()
	{
		return false;
	}

	@Override
	public boolean isNone()
	{
		return true;
	}

	@Override
	public T get()
	{
		throw new IllegalStateException("Called get on None!");
	}
}
