package org.cicatiello.interview_exercise.ui.impl;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.ui.ParserService;
import org.cicatiello.interview_exercise.ui.exception.ParseException;
import org.springframework.stereotype.Service;

@Service
public class DefaultParserService implements ParserService {

	@Override
	public Integer parseQuantityFromString(String line) throws ParseException {
		throw new UnsupportedOperationException("This class cannot be implemented until a proper cli is developed");
	}

	@Override
	public Product parseProductFromString(String line) throws ParseException {
		throw new UnsupportedOperationException("This class cannot be implemented until a proper cli is developed");
	}

}
