package org.cicatiello.interview_exercise.model;

import java.math.BigDecimal;
import java.util.List;

public class CartEntry {

	private Product product;
	private Integer quantity;
	private List<BigDecimal> taxValues;
	private BigDecimal subTotal;
	private BigDecimal total;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<BigDecimal> getTaxValues() {
		return taxValues;
	}

	public void setTaxValues(List<BigDecimal> taxValues) {
		this.taxValues = taxValues;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
