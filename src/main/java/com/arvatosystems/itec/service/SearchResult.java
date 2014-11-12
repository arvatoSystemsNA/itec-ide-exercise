package com.arvatosystems.itec.service;

import java.util.List;

public interface SearchResult<E>
{
	/**
	 * Gets size of result collection. Same as {@link #getRequestedCount()} or less (if less elements are found)
	 */
	int getCount();

	/**
	 * Gets size of collection without using range. Attention: When {@link FlexibleSearchQuery#isNeedTotal()}= <code>false</code>
	 * (default) than this total count is the same as {@link #getRequestedCount()} or less. It is not the total count of all
	 * elements in the db. (only when isNeedTotal=true)
	 */
	int getTotalCount();

	/**
	 * Gets the search result. Returned collection uses lazy translation approach, on first access on an collection element the
	 * element will be translated to an model. <b>In case the entity was removed between gathering of search result and translation
	 * of specific element the returned collection will have a <code>null</code> value at this position.</b>
	 */
	List<E> getResult();

	/**
	 * Gets requested start index. Same value as set in {@link FlexibleSearchQuery#setStart(int)}
	 */
	int getRequestedStart();

	/**
	 * Gets requested result count. Same value as set in {@link FlexibleSearchQuery#setCount(int)}
	 */
	int getRequestedCount();
}
