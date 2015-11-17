package org.cicatiello.interview_exercise.session;

import java.util.Map;

import org.cicatiello.interview_exercise.model.Cart;

public interface SessionService {

	Cart getSessionCart();

	void setAttribute(String paramString, Object paramObject);

	Object getAttribute(String paramString);

	Map<String, Object> getAllAttributes();

	void removeAttribute(String paramString);

}
