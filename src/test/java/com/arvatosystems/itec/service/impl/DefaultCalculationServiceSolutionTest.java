package com.arvatosystems.itec.service.impl;

import static com.arvatosystems.us.hybris.core.concurrent.ConcurrencyUtils.assertThatAfter;
import static com.arvatosystems.us.hybris.core.constants.AsycoreConstants.PER_MIL;
import static com.arvatosystems.us.hybris.core.util.ConditionTemplate.matchCondition;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.arvatosystems.itec.pojo.Cart;
import com.arvatosystems.itec.pojo.CartEntry;
import com.arvatosystems.itec.service.PersistenceService;
import com.arvatosystems.us.hybris.core.util.Executable;
public class DefaultCalculationServiceSolutionTest
{

	private final DefaultCalculationService calculationService = new DefaultCalculationService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private PersistenceService persistenceService;

	@Before
	public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
		calculationService.setPersitenceService(persistenceService);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenCartIsNull()
	{
		calculationService.calculateCart(null);
	}

	@Test
	public void shouldThrowExceptionWhenCartIsNullWithRule()
	{
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("The cart can not be null");
		calculationService.calculateCart(null);
	}

	@Test
	public void checkTotals()
	{
		// Given
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		// When
		calculationService.calculateCart(cart);

		// Then
		assertEquals(BigDecimal.valueOf(70), cart.getTotalPrice());
		verify(persistenceService).save(cart);
	}

	@Test
	@Ignore
	public void checkAsynchronousTotalsFailing()
	{
		// This is a badly written test that is going to fail.
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		calculationService.calculateCartAsynchronously(cart);

		assertEquals(BigDecimal.valueOf(70), cart.getTotalPrice());
		verify(persistenceService).save(cart);
	}

	@Test
	@Ignore
	public void checkAsynchronousTotalsTheWrongWay() throws InterruptedException
	{
		// This is a badly written test that is going to fail.
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		calculationService.calculateCartAsynchronously(cart);

		Thread.sleep(5000);

		assertThat(cart.getTotalPrice(), closeTo(BigDecimal.valueOf(70), PER_MIL));
		verify(persistenceService).save(cart);
	}

	@Test
	public void checkAsynchronousTotals()
	{
		// This is a badly written test that is going to fail.
		// Given
		final Cart cart = new Cart();
		cart.getEntries().add(new CartEntry("product1", 1, BigDecimal.valueOf(10)));
		cart.getEntries().add(new CartEntry("product2", 1, BigDecimal.valueOf(20)));
		cart.getEntries().add(new CartEntry("product3", 2, BigDecimal.valueOf(20)));

		// When - then
		assertThatAfter(10000, matchCondition(closeTo(BigDecimal.valueOf(70), PER_MIL), new Executable<BigDecimal>()
		{
			@Override
			public BigDecimal execute() throws Exception
			{
				calculationService.calculateCartAsynchronously(cart);
				return cart.getTotalPrice();
			}
		}));
	}
}
