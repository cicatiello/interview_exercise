package org.cicatiello.interview_exercise.cart;

import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.model.CartEntry;

public interface CalculationService {

	void recalculate();

	void recalculateEntry(CartEntry entry);

	void calculateTotals(Cart cart);

}
