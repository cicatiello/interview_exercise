package org.cicatiello.interview_exercise.ui.exception;

public class ParseException extends Exception {

	private static final long serialVersionUID = 468013898710942541L;

	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

}
