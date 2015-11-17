package org.cicatiello.interview_exercise.product;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;

public interface ProductService {

	Product getProduct(String description, double price, boolean imported) throws ProductServiceException;

}
