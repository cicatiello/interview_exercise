package org.cicatiello.interview_exercise.model.builder;

import java.math.BigDecimal;

import org.cicatiello.interview_exercise.model.SalesTax;

public class SalesTaxBuilder {

	private String code;
	private double rate;

	public static SalesTaxBuilder create() {
		return new SalesTaxBuilder();
	}

	public SalesTaxBuilder code(String code) {
		this.code = code;
		return this;
	}

	public SalesTaxBuilder rate(double rate) {
		this.rate = rate;
		return this;
	}

	public SalesTax build() {
		SalesTax salesTax = new SalesTax();
		salesTax.setCode(code);
		BigDecimal taxRate = new BigDecimal(rate).setScale(2, BigDecimal.ROUND_FLOOR);
		salesTax.setTaxRate(taxRate);
		return salesTax;
	}

}
