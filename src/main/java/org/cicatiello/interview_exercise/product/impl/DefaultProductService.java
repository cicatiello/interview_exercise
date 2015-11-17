package org.cicatiello.interview_exercise.product.impl;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.product.ProductService;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductService implements ProductService {

	@Override
	public Product getProduct(String description, double price, boolean imported) throws ProductServiceException {
		throw new UnsupportedOperationException(
				"This class cannot be implemented until a data structure becomes available");
	}

}
