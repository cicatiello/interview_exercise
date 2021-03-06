package org.cicatiello.interview_exercise.cart.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.model.CartEntry;
import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.builder.CartBuilder;
import org.cicatiello.interview_exercise.model.builder.CartEntryBuilder;
import org.cicatiello.interview_exercise.product.ProductService;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.cicatiello.interview_exercise.session.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@DirtiesContext
public class DefaultCalculationServiceTest {

	DefaultCalculationService calculationService = new DefaultCalculationService();

	@Autowired
	@Qualifier("mockDataProductService")
	ProductService productService;

	Product book;
	Product musicCD;
	Product chocolateBar;
	Product importedBoxOfChocolates10;
	Product importedBottleOfPerfume47;
	Product importedBottleOfPerfume27;
	Product bottleOfPerfume;
	Product packetOfHeadachePills;
	Product importedBoxOfChocolates11;
	Cart cart;

	@Before
	public void prepareProducts() throws ProductServiceException {
		book = productService.getProduct("book", 12.49, false);
		musicCD = productService.getProduct("music CD", 14.99, false);
		chocolateBar = productService.getProduct("chocolate bar", 0.85, false);
		importedBoxOfChocolates10 = productService.getProduct("imported box of chocolates", 10.00, true);
		importedBottleOfPerfume47 = productService.getProduct("imported bottle of perfume", 47.50, true);
		importedBottleOfPerfume27 = productService.getProduct("imported bottle of perfume", 27.99, true);
		bottleOfPerfume = productService.getProduct("bottle of perfume", 18.99, false);
		packetOfHeadachePills = productService.getProduct("packet of headache pills", 9.75, false);
		importedBoxOfChocolates11 = productService.getProduct("imported box of chocolates", 11.25, true);
		CartEntry entryBook = CartEntryBuilder.create().product(book).quantity(1).build();
		CartEntry entryMusicCD = CartEntryBuilder.create().product(musicCD).quantity(1).build();
		CartEntry entryChocolateBar = CartEntryBuilder.create().product(chocolateBar).quantity(1).build();
		cart = CartBuilder.build();
		cart.getEntries().add(entryBook);
		cart.getEntries().add(entryMusicCD);
		cart.getEntries().add(entryChocolateBar);
		SessionService sessionService = createMock(SessionService.class);
		expect(sessionService.getSessionCart()).andReturn(cart);
		replay(sessionService);
		calculationService.setSessionService(sessionService);
	}

	@Test
	public void testEntryBook() {
		CartEntry entry = CartEntryBuilder.create().product(book).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(12.49), entry.getTotal());
	}

	@Test
	public void testEntryMusicCD() {
		CartEntry entry = CartEntryBuilder.create().product(musicCD).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(16.49), entry.getTotal());
	}

	@Test
	public void testEntryChocolateBar() {
		CartEntry entry = CartEntryBuilder.create().product(chocolateBar).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(0.85), entry.getTotal());
	}

	@Test
	public void testEntryImportedBoxOfChocolate() {
		CartEntry entry = CartEntryBuilder.create().product(importedBoxOfChocolates10).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(10.50).setScale(2, BigDecimal.ROUND_HALF_UP), entry.getTotal());
	}

	@Test
	public void testEntryImportedBottleOfPerfume47() {
		CartEntry entry = CartEntryBuilder.create().product(importedBottleOfPerfume47).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(54.65), entry.getTotal());
	}

	@Test
	public void testEntryImportedBottleOfPerfume27() {
		CartEntry entry = CartEntryBuilder.create().product(importedBottleOfPerfume27).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(32.19), entry.getTotal());
	}

	@Test
	public void testEntryBottleOfPerfume() {
		CartEntry entry = CartEntryBuilder.create().product(bottleOfPerfume).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(20.89), entry.getTotal());
	}

	@Test
	public void testEntryPacketOfHeadachePills() {
		CartEntry entry = CartEntryBuilder.create().product(packetOfHeadachePills).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(9.75), entry.getTotal());
	}

	@Test
	public void testEntryboxOfImportedChocolates() {
		CartEntry entry = CartEntryBuilder.create().product(importedBoxOfChocolates11).quantity(1).build();
		calculationService.recalculateEntry(entry);
		assertEquals(BigDecimal.valueOf(11.85), entry.getTotal());
	}

	@Test
	public void testTotalForSampleCart() {
		calculationService.recalculate();
		assertEquals(BigDecimal.valueOf(29.83), cart.getTotal());
	}

}
