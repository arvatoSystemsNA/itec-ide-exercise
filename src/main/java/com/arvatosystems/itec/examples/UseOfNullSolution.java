package com.arvatosystems.itec.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arvatosystems.itec.pojo.ItemModel;
import com.arvatosystems.us.hybris.core.lang.None;
import com.arvatosystems.us.hybris.core.lang.Option;
import com.arvatosystems.us.hybris.core.lang.Some;

public class UseOfNullSolution
{
	/**
	 * <p>
	 * Finds a model matching the given attributes. If the query returns more than one model the first model is returned. If no
	 * model was found <code>null</code> is returned.
	 * <p>
	 *
	 * @param attribs A map of attribute names and values
	 * @param orderBy Map providing attribute names that will be included in the <code>order by</code> clause. The boolean value
	 *           determines whether the sort order should be ascending (<code>true</code>) or descending ( <code>false</code>).
	 * @return Either Some Model or None.
	 */
	public <M extends ItemModel> Option<M> findFirstByAttributes(final Map<String, Object> attribs,
			final Map<String, Boolean> orderBy,
			final Class<M> modelClass)
	{
		final List<M> models = findAllByAttributes(attribs, orderBy, -1, 0, modelClass);
		if (models.size() < 1)
		{
			return new None<>();
		}
		return new Some<>(models.get(0));
	}

	public <M extends ItemModel> List<M> findAllByAttributes(final Map<String, ? extends Object> attribs,
			final Map<String, Boolean> orderByMap, final int count, final int start, final Class<M> modelClass)
	{
		// Only a sample, implementation missing
		return new ArrayList<>();
	}
}
