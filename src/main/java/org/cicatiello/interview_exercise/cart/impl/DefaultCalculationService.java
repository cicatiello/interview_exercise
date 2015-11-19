package org.cicatiello.interview_exercise.cart.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.cicatiello.interview_exercise.cart.CalculationService;
import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.model.CartEntry;
import org.cicatiello.interview_exercise.model.SalesTax;
import org.cicatiello.interview_exercise.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCalculationService implements CalculationService {

	private SessionService sessionService;

	@Override
	public void recalculate() {
		Cart sessionCart = sessionService.getSessionCart();
		resetCartValues(sessionCart);
		sessionCart.getEntries().forEach(entry -> recalculateEntry(entry));
		calculateTotals(sessionCart);
	}

	@Override
	public void recalculateEntry(CartEntry entry) {
		resetCartEntryValues(entry);
		BigDecimal subTotal = entry.getProduct().getBasePrice().multiply(BigDecimal.valueOf(entry.getQuantity()));
		entry.setSubTotal(subTotal);
		BigDecimal total = new BigDecimal(0).add(subTotal);
		entry.getProduct().getTaxRows().forEach(taxRow -> {
			BigDecimal calculateTaxValue = calculateTaxValue(subTotal, taxRow);
			entry.getTaxValues().add(calculateTaxValue);
		});
		for (BigDecimal taxValues : entry.getTaxValues()) {
			total = total.add(taxValues);
		}
		entry.setTotal(total);
	}

	@Override
	public void calculateTotals(Cart cart) {
		cart.getEntries().forEach(entry -> {
			cart.getSubTotal().add(entry.getSubTotal());
			entry.getTaxValues().forEach(taxValue -> {
				cart.setTotalTax(cart.getTotalTax().add(taxValue));
			});
			cart.setTotal(cart.getTotal().add(entry.getTotal()));
		});
		cart.getTotalTax().setScale(2, RoundingMode.HALF_EVEN);
		cart.getTotal().setScale(2, RoundingMode.HALF_EVEN);
	}

	private BigDecimal calculateTaxValue(BigDecimal subTotal, SalesTax tax) {
		BigDecimal taxValue = subTotal.multiply(tax.getTaxRate()).divide(new BigDecimal("100"));
		return roundTax(taxValue);
	}

	private BigDecimal roundTax(BigDecimal tax) {
		BigDecimal scale = new BigDecimal("0.05");
		return tax.divide(scale, 0, RoundingMode.UP).multiply(scale);
	}

	private void resetCartValues(Cart cart) {
		cart.setSubTotal(new BigDecimal(0));
		cart.setTotal(new BigDecimal(0));
		cart.setTotalTax(new BigDecimal(0));
	}

	private void resetCartEntryValues(CartEntry entry) {
		entry.setTotal(new BigDecimal(0));
		entry.setTaxValues(new ArrayList<>());
	}

	protected SessionService getSessionService() {
		return sessionService;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

}
