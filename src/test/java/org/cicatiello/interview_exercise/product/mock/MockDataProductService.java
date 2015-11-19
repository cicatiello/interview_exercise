package org.cicatiello.interview_exercise.product.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.model.mock.DataInitializer;
import org.cicatiello.interview_exercise.product.ProductService;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockDataProductService implements ProductService, InitializingBean {

	private DataInitializer dataInitializer;

	private Set<Product> products;

	@Override
	public void afterPropertiesSet() throws Exception {
		products = getDataInitializer().getProductData();
	}

	@Override
	public Product getProduct(String description, double price, boolean imported) throws ProductServiceException {
		Stream<Product> filteredProducts = products.stream().filter(product -> {
			return (product.getDescription() != null ? product.getDescription().equals(description) : false)
					&& (product.getBasePrice() != null ? product.getBasePrice().equals(BigDecimal.valueOf(price))
							: false)
					&& (product.isImported() == imported);
		});
		List<Product> foundList = new ArrayList<>();
		filteredProducts.forEach(product -> foundList.add(product));
		if (foundList.size() > 1) {
			throw new ProductServiceException("Found more and 1 product");
		}
		return foundList.size() == 1 ? foundList.get(0) : null;
	}

	protected DataInitializer getDataInitializer() {
		return dataInitializer;
	}

	@Autowired
	public void setDataInitializer(DataInitializer dataInitializer) {
		this.dataInitializer = dataInitializer;
	}
}
