package org.cicatiello.interview_exercise.model.mock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.ProductClassification;
import org.cicatiello.interview_exercise.model.SalesTax;
import org.cicatiello.interview_exercise.model.builder.ProductBuilder;
import org.cicatiello.interview_exercise.model.builder.ProductClassificationBuilder;
import org.cicatiello.interview_exercise.model.builder.SalesTaxBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

@Component
public class DataInitializer implements InitializingBean {

	private Set<Product> productData = new HashSet<>();
	private Map<String, ProductClassification> productClassifications = new HashMap<>();
	private Map<String, SalesTax> salesTaxes = new HashMap<>();

	public void initSalesTaxes() {
		SalesTax importTax = SalesTaxBuilder.create().code("imported").rate(5).build();
		SalesTax standardRateTax = SalesTaxBuilder.create().code("standard").rate(10).build();
		SalesTax reducedRateTax = SalesTaxBuilder.create().code("reduced").rate(0).build();
		getSalesTaxes().put("imported", importTax);
		getSalesTaxes().put("standard", standardRateTax);
		getSalesTaxes().put("reduced", reducedRateTax);
	}

	public void initProductClassifications() {
		ProductClassification book = ProductClassificationBuilder.create().code("book").build();
		ProductClassification medical = ProductClassificationBuilder.create().code("medical").build();
		ProductClassification food = ProductClassificationBuilder.create().code("food").build();
		ProductClassification other = ProductClassificationBuilder.create().code("other").build();
		productClassifications.put("book", book);
		productClassifications.put("medical", medical);
		productClassifications.put("food", food);
		productClassifications.put("other", other);
	}

	public void initProducts() {
		productData.add(ProductBuilder.create()
				.description("book")
				.price(12.49)
				.classification(productClassifications.get("book"))
				.newSalesTax(getSalesTaxes().get("reduced"))
				.build());
		productData.add(ProductBuilder.create()
				.description("music CD")
				.price(14.99)
				.classification(productClassifications.get("other"))
				.newSalesTax(getSalesTaxes().get("standard"))
				.build());
		productData.add(ProductBuilder.create()
				.description("chocolate bar")
				.price(0.85)
				.classification(productClassifications.get("food"))
				.newSalesTax(getSalesTaxes().get("reduced"))
				.build());
		productData.add(ProductBuilder.create()
				.description("imported box of chocolates")
				.price(10.00)
				.isImported(true)
				.classification(productClassifications.get("food"))
				.newSalesTax(getSalesTaxes().get("imported"))
				.newSalesTax(getSalesTaxes().get("reduced"))
				.build());
		productData.add(ProductBuilder.create()
				.description("imported bottle of perfume")
				.price(47.50)
				.isImported(true)
				.classification(productClassifications.get("other"))
				.newSalesTax(getSalesTaxes().get("imported"))
				.newSalesTax(getSalesTaxes().get("standard"))
				.build());
		productData.add(ProductBuilder.create()
				.description("imported bottle of perfume")
				.price(27.99)
				.isImported(true)
				.classification(productClassifications.get("other"))
				.newSalesTax(getSalesTaxes().get("imported"))
				.newSalesTax(getSalesTaxes().get("standard"))
				.build());
		productData.add(ProductBuilder.create()
				.description("bottle of perfume")
				.price(18.99)
				.classification(productClassifications.get("other"))
				.newSalesTax(getSalesTaxes().get("standard"))
				.build());
		productData.add(ProductBuilder.create()
				.description("packet of headache pills")
				.price(9.75)
				.classification(productClassifications.get("medical"))
				.newSalesTax(getSalesTaxes().get("reduced"))
				.build());
		productData.add(ProductBuilder.create()
				.description("imported box of chocolates")
				.price(11.25)
				.isImported(true)
				.classification(productClassifications.get("food"))
				.newSalesTax(getSalesTaxes().get("imported"))
				.newSalesTax(getSalesTaxes().get("reduced"))
				.build());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSalesTaxes();
		initProductClassifications();
		initProducts();
	}

	public Set<Product> getProductData() {
		return ImmutableSet.copyOf(productData);
	}

	public Map<String, ProductClassification> getProductClassificationData() {
		return ImmutableMap.copyOf(productClassifications);
	}

	public Map<String, SalesTax> getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(Map<String, SalesTax> salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

}
