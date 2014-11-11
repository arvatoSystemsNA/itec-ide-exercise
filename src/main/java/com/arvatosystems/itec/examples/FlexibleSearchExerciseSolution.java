package com.arvatosystems.itec.examples;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.arvatosystems.itec.pojo.Order;
import com.arvatosystems.itec.service.BaseDao;
import com.arvatosystems.itec.service.FlexibleSearchService;
import com.arvatosystems.itec.service.SearchResult;
import com.arvatosystems.us.hybris.core.lang.None;
import com.arvatosystems.us.hybris.core.lang.Option;
import com.arvatosystems.us.hybris.core.lang.Some;

public class FlexibleSearchExerciseSolution
{
	@Resource
	private FlexibleSearchService searchService;

	@Resource
	private BaseDao asyBaseDao;

	/**
	 * Allows the customer to lookup an order by entering the number and phone number. This version is SQL injection safe, because
	 * query parameters are used and the flexible search service automatically escapes those.
	 *
	 * @param orderNo The order number
	 * @param phoneNo The phone number
	 */
	public Option<Order> lookupOrderByNumberAndZip(final String orderNo, final String phoneNo)
	{
		final Map<String, Object> queryParams = new HashMap<>(2);
		queryParams.put("code", orderNo);
		queryParams.put("phoneNo", phoneNo);

		final SearchResult<Order> result = searchService.search(
				"SELECT {pk} FROM {Order} WHERE {code} = ?code AND {phoneNo} =?phoneNo", queryParams);

		return CollectionUtils.isNotEmpty(result.getResult()) ? new Some<>(result.getResult().get(0)) : new None<Order>();
	}

	/**
	 * Returns all orders placed since the given day. If <code>net</code> is true, only net orders are returned, else only gross
	 * orders are returned. Makes use of parameters and truncates the date so the query can be cached.
	 *
	 * @param day The day
	 * @param netOnly Whether to only return net orders
	 * @return A list of orders or an empty list if none was found
	 */
	public List<Order> findOrdersSince(final Date day, final boolean net)
	{
		final Map<String, Object> queryParams = new HashMap<>(2);

		// using the truncate date operation to cache results for the same day
		queryParams.put("date", asyBaseDao.truncateDate(day, TimeUnit.DAYS));
		queryParams.put("net", Boolean.valueOf(net));

		// use query parameters instead
		return asyBaseDao.find("SELECT {pk} FROM {Order} WHERE {creationtime} > ?day AND {net} = ?net", queryParams);
	}

}
