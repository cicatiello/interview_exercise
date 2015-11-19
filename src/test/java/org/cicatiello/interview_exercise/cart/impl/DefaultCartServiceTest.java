package org.cicatiello.interview_exercise.cart.impl;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cicatiello.interview_exercise.cart.CalculationService;
import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.model.CartEntry;
import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.product.ProductService;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.cicatiello.interview_exercise.session.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@DirtiesContext
public class DefaultCartServiceTest {

	DefaultCartService cartService = new DefaultCartService();

	@Autowired
	SessionService sessionService;

	@Autowired
	ProductService productService;

	Product book;
	Product musicCD;
	Product chocolateBar;

	@Before
	public void prepareCartAndProducts() throws ProductServiceException {
		book = productService.getProduct("book", 12.49, false);
		musicCD = productService.getProduct("music CD", 14.99, false);
		chocolateBar = productService.getProduct("chocolate bar", 0.85, false);
		CalculationService calculationService = createMock(CalculationService.class);
		cartService.setSessionService(sessionService);
		cartService.setCalculationService(calculationService);
	}

	@Test
	public void preTestCartIsEmpty() {
		Cart sessionCart = sessionService.getSessionCart();
		assertNotNull(sessionCart.getEntries());
		assertEquals(0, sessionCart.getEntries().size());
	}

	@Test
	public void testAddCart() {
		cartService.addProductToCart(book, 2);
		Cart sessionCart = sessionService.getSessionCart();
		assertNotNull(sessionCart.getEntries());
		assertEquals(1, sessionCart.getEntries().size());
		CartEntry entry = sessionCart.getEntries().iterator().next();
		assertEquals(Integer.valueOf(2), entry.getQuantity());
		assertEquals(book, entry.getProduct());
	}

}
