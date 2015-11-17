package org.cicatiello.interview_exercise.ui;

import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.ui.exception.ParseException;

public interface ParserService {

	Integer parseQuantityFromString(String line) throws ParseException;

	Product parseProductFromString(String line) throws ParseException;

}