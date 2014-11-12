package com.arvatosystems.itec.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Emulates asycore's BaseDao, but avoids dependencies to Hybris classes
 */
public interface BaseDao
{
	public <M extends Object> List<M> findAllByAttributes(final Map<String, ? extends Object> attribs, final Class<M> modelClass);

	public <M> List<M> find(final String query, final Map<String, Object> attributes);

	public Date truncateDate(final Date date, final TimeUnit unit);
}
