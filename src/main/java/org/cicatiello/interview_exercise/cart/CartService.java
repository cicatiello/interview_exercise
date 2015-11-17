package org.cicatiello.interview_exercise.cart;

import org.cicatiello.interview_exercise.model.Product;

public interface CartService {

	void addProductToCart(Product product, int quantity);

}