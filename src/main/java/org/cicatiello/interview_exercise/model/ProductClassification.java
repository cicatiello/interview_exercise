package org.cicatiello.interview_exercise.model;

public class ProductClassification {

    String description;

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return description.equals(obj);
    }
}
