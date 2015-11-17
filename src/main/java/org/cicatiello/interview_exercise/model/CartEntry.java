package org.cicatiello.interview_exercise.model;

import java.math.BigDecimal;
import java.util.List;

public class CartEntry {

    Integer row;
    Product product;
    Integer quantity;
    List<BigDecimal> taxValues;
    BigDecimal total;

}
