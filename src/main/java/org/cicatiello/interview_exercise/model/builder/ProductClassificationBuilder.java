package org.cicatiello.interview_exercise.model.builder;

import org.cicatiello.interview_exercise.model.ProductClassification;

public class ProductClassificationBuilder {

	private String code;

	public static ProductClassificationBuilder create() {
		return new ProductClassificationBuilder();
	}

	public ProductClassificationBuilder code(String code) {
		this.code = code;
		return this;
	}

	public ProductClassification build() {
		ProductClassification classification = new ProductClassification();
		classification.setCode(code);
		return classification;
	}

}
