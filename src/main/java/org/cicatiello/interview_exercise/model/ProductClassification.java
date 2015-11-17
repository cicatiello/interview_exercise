package org.cicatiello.interview_exercise.model;

public class ProductClassification {

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return (code != null ? code.hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductClassification) {
			return (code != null ? code.equals(((ProductClassification) obj).getCode()) : false);
		}
		return false;
	}

	@Override
	public String toString() {
		return code != null ? code : "";
	}
}
