package org.cicatiello.interview_exercise.cart.impl;

import java.util.Optional;
import java.util.Set;

import org.cicatiello.interview_exercise.cart.CalculationService;
import org.cicatiello.interview_exercise.cart.CartService;
import org.cicatiello.interview_exercise.model.CartEntry;
import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.builder.CartEntryBuilder;
import org.cicatiello.interview_exercise.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCartService implements CartService {

	private SessionService sessionService;

	private CalculationService calculationService;

	@Override
	public void addProductToCart(Product product, int quantity) {
		CartEntry entry = findAndGetCartEntryByProduct(product);
		if (entry == null) {
			createAndAddEntryToCart(product, quantity);
		} else {
			adjustCartEntryQuantity(entry, quantity);
		}
		getCalculationService().recalculate();
	}

	private void adjustCartEntryQuantity(CartEntry entry, int quantity) {
		entry.setQuantity(entry.getQuantity() + quantity);
	}

	private CartEntry findAndGetCartEntryByProduct(Product product) {
		Set<CartEntry> entries = getSessionService().getSessionCart().getEntries();
		Optional<CartEntry> hasFoundEntry = entries.stream().filter(entry -> entry.getProduct() == product).findFirst();
		return hasFoundEntry.orElse(null);
	}

	private void createAndAddEntryToCart(Product product, int quantity) {
		CartEntry entry = CartEntryBuilder.create().product(product).quantity(quantity).build();
		addEntryToCart(entry);
	}

	private void addEntryToCart(CartEntry entry) {
		getSessionService().getSessionCart().getEntries().add(entry);
	}

	protected CalculationService getCalculationService() {
		return calculationService;
	}

	@Autowired
	public void setCalculationService(CalculationService calculationService) {
		this.calculationService = calculationService;
	}

	protected SessionService getSessionService() {
		return sessionService;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

}
