package org.cicatiello.interview_exercise.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Product {

	private String description;
	private BigDecimal basePrice;
	private boolean imported;
	private ProductClassification productClassification;
	private Set<SalesTax> taxRows = new HashSet<>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public ProductClassification getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(ProductClassification productClassification) {
		this.productClassification = productClassification;
	}

	public Set<SalesTax> getTaxRows() {
		return taxRows;
	}

	public void setTaxRows(Set<SalesTax> taxRows) {
		this.taxRows = taxRows;
	}

	@Override
	public int hashCode() {
		return (description != null ? description.hashCode() : 0) + (basePrice != null ? basePrice.hashCode() : 0)
				+ (Boolean.valueOf(imported).hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			return (description != null ? description.equals(((Product) obj).getDescription()) : false)
					&& (basePrice != null ? basePrice.equals(((Product) obj).getBasePrice()) : false)
					&& (imported == ((Product) obj).isImported());
		}
		return false;
	}

}
