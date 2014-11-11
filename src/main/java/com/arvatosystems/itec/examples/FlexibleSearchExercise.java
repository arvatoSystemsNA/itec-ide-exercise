package com.arvatosystems.itec.examples;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.arvatosystems.itec.pojo.Order;
import com.arvatosystems.itec.service.FlexibleSearchService;
import com.arvatosystems.itec.service.SearchResult;
import com.arvatosystems.us.hybris.core.lang.None;
import com.arvatosystems.us.hybris.core.lang.Option;
import com.arvatosystems.us.hybris.core.lang.Some;

public class FlexibleSearchExercise
{
	@Resource
	private FlexibleSearchService searchService;

	/**
	 * Allows the customer to lookup an order by entering the number and phone number.
	 *
	 * @param orderNo The order number
	 * @param phoneNo The phone number
	 */
	public Option<Order> lookupOrderByNumberAndZip(final String orderNo, final String phoneNo)
	{
		final SearchResult<Order> result = searchService.search("SELECT {pk} FROM {Order} WHERE {code} = '" + orderNo
				+ "' AND {phoneNo} ='" + phoneNo + "'");
		return CollectionUtils.isNotEmpty(result.getResult()) ? new Some<>(result.getResult().get(0)) : new None<Order>();
	}

	/**
	 * Returns all orders placed since the given day. If <code>net</code> is true, only net orders are returned, else only gross
	 * orders are returned.
	 *
	 * @param day The day
	 * @param netOnly Whether to only return net orders
	 * @return A list of orders or an empty list if none was found
	 */
	public List<Order> findOrdersSince(final Date day, final boolean net)
	{
		final String dateStr = new SimpleDateFormat("DD-MM-YY").format(day);
		final String trueOrFalse = net ? "TRUE" : "FALSE";

		final SearchResult<Order> result = searchService.search("SELECT {pk} FROM {Order} WHERE {creationtime} > to_date('"
				+ dateStr + "','DD-MM-YY') AND {net} = " + trueOrFalse);
		return result.getResult();
	}
}
