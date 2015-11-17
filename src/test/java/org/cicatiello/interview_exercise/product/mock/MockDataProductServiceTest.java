package org.cicatiello.interview_exercise.product.mock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.builder.ProductBuilder;
import org.cicatiello.interview_exercise.model.builder.ProductClassificationBuilder;
import org.cicatiello.interview_exercise.model.mock.DataInitializer;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MockDataProductServiceTest {

	MockDataProductService mockDataProductService = new MockDataProductService();
	
	@Before
	public void prepareProductData() throws Exception {
		Product productTest = ProductBuilder.create()
				.description("test_product1")
				.price(1.0)
				.classification(ProductClassificationBuilder.create().code("food").build())
				.build();
		Product productTestWithDifferentDescription = ProductBuilder.create()
				.description("test_product2")
				.price(1.0)
				.classification(ProductClassificationBuilder.create().code("food").build())
				.build();
		Product productTestWithDifferentPrice = ProductBuilder.create()
				.description("test_product1")
				.price(100.0)
				.classification(ProductClassificationBuilder.create().code("food").build())
				.build();
		Product productTestWithDifferentImportStatus = ProductBuilder.create()
				.description("test_product1")
				.price(1.0)
				.isImported(true)
				.classification(ProductClassificationBuilder.create().code("food").build())
				.build();
		DataInitializer dataInitializer = createMock(DataInitializer.class);
		Set<Product> mockProducts = new HashSet<>();
		mockProducts.add(productTest);
		mockProducts.add(productTestWithDifferentDescription);
		mockProducts.add(productTestWithDifferentPrice);
		mockProducts.add(productTestWithDifferentImportStatus);
		expect(dataInitializer.getProductData()).andReturn(mockProducts);
		replay(dataInitializer);
		mockDataProductService.setDataInitializer(dataInitializer);
		mockDataProductService.afterPropertiesSet();
	}

	@Test
	public void testGet1() throws ProductServiceException {
		Product product = mockDataProductService.getProduct("test_product1", 1.00, false);
		Assert.assertNotNull(product);
		Assert.assertEquals(BigDecimal.valueOf(1.0), product.getBasePrice());
		Assert.assertEquals("test_product1", product.getDescription());
		Assert.assertEquals(false, product.isImported());
	}

	@Test
	public void testGet2() throws ProductServiceException {
		Product product = mockDataProductService.getProduct("test_product2", 1.00, false);
		Assert.assertNotNull(product);
		Assert.assertEquals(BigDecimal.valueOf(1.0), product.getBasePrice());
		Assert.assertEquals("test_product2", product.getDescription());
		Assert.assertEquals(false, product.isImported());
	}

	@Test
	public void testGet3() throws ProductServiceException {
		Product product = mockDataProductService.getProduct("test_product1", 100.00, false);
		Assert.assertNotNull(product);
		Assert.assertEquals(BigDecimal.valueOf(100.0), product.getBasePrice());
		Assert.assertEquals("test_product1", product.getDescription());
		Assert.assertEquals(false, product.isImported());
	}

	@Test
	public void testGet4() throws ProductServiceException {
		Product product = mockDataProductService.getProduct("test_product1", 1.00, true);
		Assert.assertNotNull(product);
		Assert.assertEquals(BigDecimal.valueOf(1.0), product.getBasePrice());
		Assert.assertEquals("test_product1", product.getDescription());
		Assert.assertEquals(true, product.isImported());
	}

}
