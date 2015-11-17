package org.cicatiello.interview_exercise.model.builder;

import java.math.BigDecimal;

import org.cicatiello.interview_exercise.model.Cart;

public class CartBuilder {

	public static CartBuilder create() {
		return new CartBuilder();
	}

	public static Cart build() {
		Cart cart = new Cart();
		cart.setSubTotal(BigDecimal.ZERO);
		cart.setTotal(BigDecimal.ZERO);
		cart.setTotalTax(BigDecimal.ZERO);
		return cart;
	}

}
