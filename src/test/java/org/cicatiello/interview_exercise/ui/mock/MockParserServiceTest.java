package org.cicatiello.interview_exercise.ui.mock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.builder.ProductBuilder;
import org.cicatiello.interview_exercise.product.ProductService;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.cicatiello.interview_exercise.ui.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

public class MockParserServiceTest {

	MockParserService service = new MockParserService();

	@Before
	public void prepareProductService() throws ProductServiceException {
		ProductService productService = createMock(ProductService.class);
		Product sampleReturn = ProductBuilder.create().description("book").price(12.49).isImported(false).build();
		expect(productService.getProduct("book", 12.49, false)).andReturn(sampleReturn);
		replay(productService);
		service.setProductService(productService);
	}

	@Test
	public void testProductLineParser() throws ParseException {
		String line = "1 book at 12.49";
		Product product = service.parseProductFromString(line);
		assertNotNull(product);
		assertEquals(BigDecimal.valueOf(12.49), product.getBasePrice());
		assertEquals(false, product.isImported());
	}

	@Test
	public void testQuantityLineParser() throws ParseException {
		String line = "1 book at 12.49";
		Integer quantity = service.parseQuantityFromString(line);
		assertNotNull(quantity);
		assertEquals(Integer.valueOf(1), quantity);
	}

}
