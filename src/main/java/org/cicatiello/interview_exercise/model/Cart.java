package org.cicatiello.interview_exercise.model;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class Cart {

	private Set<CartEntry> entries = new LinkedHashSet<>();
	private BigDecimal subTotal;
	private BigDecimal totalTax;
	private BigDecimal total;

	public Set<CartEntry> getEntries() {
		return entries;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
