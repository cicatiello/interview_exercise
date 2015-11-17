package org.cicatiello.interview_exercise.model.builder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.ProductClassification;
import org.cicatiello.interview_exercise.model.SalesTax;

public class ProductBuilder {

	private String description;
	private boolean imported;
	private ProductClassification classification;
	private double price;
	private Set<SalesTax> taxRows = new HashSet<>();

	public static ProductBuilder create() {
		return new ProductBuilder();
	}

	public ProductBuilder description(String description) {
		this.description = description;
		return this;
	}

	public ProductBuilder isImported(boolean imported) {
		this.imported = imported;
		return this;
	}

	public ProductBuilder classification(ProductClassification classification) {
		this.classification = classification;
		return this;
	}

	public ProductBuilder price(double price) {
		this.price = price;
		return this;
	}

	public ProductBuilder newSalesTax(SalesTax salesTax) {
		this.taxRows.add(salesTax);
		return this;
	}

	public Product build() {
		Product product = new Product();
		product.setDescription(description);
		product.setImported(imported);
		product.setBasePrice(BigDecimal.valueOf(price));
		product.setProductClassification(classification);
		product.getTaxRows().addAll(taxRows);
		return product;
	}

}
