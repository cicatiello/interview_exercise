package org.cicatiello.interview_exercise.ui.mock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.product.ProductService;
import org.cicatiello.interview_exercise.product.exception.ProductServiceException;
import org.cicatiello.interview_exercise.ui.ParserService;
import org.cicatiello.interview_exercise.ui.exception.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MockParserService implements ParserService {

	private static final String importedFlag = "imported";

	private ProductService productService;

	@Override
	public Product parseProductFromString(String line) throws ParseException {
		Pattern descriptionAndPricePattern = Pattern.compile("^(\\d+)\\s*(.*?)\\s*at (\\d+\\.\\d+)");
		Matcher matcher = descriptionAndPricePattern.matcher(line);
		if (matcher.find() && matcher.groupCount() == 3) {
			String description = matcher.group(2);
			double price = Double.parseDouble(matcher.group(3));
			boolean isImported = false;
			if (StringUtils.hasText(description)) {
				isImported = description.contains(importedFlag);
				if (isImported) {
					description = description.replaceAll("\\s+imported\\s+", " ");
					description = description.replaceAll("imported\\s+", "");
					description = "imported " + description;
				}
			}
			try {
				return getProductService().getProduct(description, price, isImported);
			} catch (ProductServiceException cause) {
				throw new ParseException(cause);
			}
		}
		throw new ParseException("Invalid Product line");
	}

	@Override
	public Integer parseQuantityFromString(String line) throws ParseException {
		Pattern descriptionAndPricePattern = Pattern.compile("^(\\d+)\\s*(.*?)\\s*at (\\d+\\.\\d+)");
		Matcher matcher = descriptionAndPricePattern.matcher(line);
		if (matcher.find() && matcher.groupCount() == 3) {
			return Integer.parseInt(matcher.group(1));
		}
		throw new ParseException("Invalid Product line");
	}

	protected ProductService getProductService() {
		return productService;
	}

	@Autowired
	@Qualifier("mockDataProductService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
