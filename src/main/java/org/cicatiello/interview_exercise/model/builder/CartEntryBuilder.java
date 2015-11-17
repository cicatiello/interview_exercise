package org.cicatiello.interview_exercise.model.builder;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.cicatiello.interview_exercise.model.CartEntry;
import org.cicatiello.interview_exercise.model.Product;

public class CartEntryBuilder {

	private Product product;
	private Integer quantity;

	public static CartEntryBuilder create() {
		return new CartEntryBuilder();
	}

	public CartEntryBuilder product(Product product) {
		this.product = product;
		return this;
	}

	public CartEntryBuilder quantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public CartEntry build() {
		CartEntry entry = new CartEntry();
		entry.setProduct(this.product);
		entry.setQuantity(this.quantity);
		entry.setTaxValues(new ArrayList<>());
		entry.setSubTotal(BigDecimal.ZERO);
		entry.setTotal(BigDecimal.ZERO);
		return entry;
	}

}
