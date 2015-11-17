package org.cicatiello.interview_exercise.model.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.Set;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.ProductClassification;
import org.cicatiello.interview_exercise.model.SalesTax;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
public class DataInitializerTest {

	public static final Logger LOG = LoggerFactory.getLogger(DataInitializerTest.class);

	@Autowired
	private DataInitializer dataInitializer;

	@Test
	public void testSalesTaxes() {
		Map<String, SalesTax> salesTaxes = dataInitializer.getSalesTaxes();
		assertNotNull(salesTaxes);
		assertEquals(3, salesTaxes.size());
	}

	@Test
	public void testProductClassifications() {
		Map<String, ProductClassification> productClassificationData = dataInitializer.getProductClassificationData();
		assertNotNull(productClassificationData);
		assertEquals(4, productClassificationData.size());
	}

	@Test
	public void testProducts() {
		Set<Product> productData = dataInitializer.getProductData();
		assertNotNull(productData);
		assertEquals(9, productData.size());
	}

}
