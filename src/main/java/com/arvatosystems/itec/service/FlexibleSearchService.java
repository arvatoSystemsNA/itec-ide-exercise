package com.arvatosystems.itec.service;

import java.util.Map;


public interface FlexibleSearchService
{
	/**
	 * Simplest search available.
	 *
	 * @param query the query
	 * @return the search result< t>
	 */
	<T> SearchResult<T> search(final String query);

	/**
	 * Convenience method which internally delegates to {@link #search(FlexibleSearchQuery)}.
	 *
	 * @param query query string in flexible search syntax
	 * @param queryParams additional query parameters; null permitted
	 * @return the search result< t>
	 */
	<T> SearchResult<T> search(final String query, final Map<String, ? extends Object> queryParams);
}
