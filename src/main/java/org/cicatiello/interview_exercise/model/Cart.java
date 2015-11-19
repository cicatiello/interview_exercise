package org.cicatiello.interview_exercise.model;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class Cart {

	private static final MessageFormat entryFormat = new MessageFormat("{0} {1}: {2,number,#0.00}\n", Locale.US);
	private static final MessageFormat totalsFormat = new MessageFormat(
			"Sales Taxes: {0,number,#0.00}\nTotal: {1,number,#0.00}\n", Locale.US);

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

	@Override
	public String toString() {
		StringBuilder cartRepresentation = new StringBuilder();
		for (CartEntry cartEntry : entries) {
			cartRepresentation.append(entryFormat.format(new Object[] { cartEntry.getQuantity(),
					cartEntry.getProduct().getDescription(), cartEntry.getTotal() }));
		}
		cartRepresentation.append(totalsFormat.format(new Object[] { this.getTotalTax(), this.getTotal() }));
		return cartRepresentation.toString();
	}

}
