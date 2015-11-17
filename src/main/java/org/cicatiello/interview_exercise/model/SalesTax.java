package org.cicatiello.interview_exercise.model;

import java.math.BigDecimal;

public class SalesTax {

	private String code;
	private BigDecimal taxRate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

}
