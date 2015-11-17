package org.cicatiello.interview_exercise.session.impl;

import java.util.Map;

import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.session.SessionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultSessionService implements SessionService {

	@Override
	public Cart getSessionCart() {
		throw new UnsupportedOperationException(
				"This class cannot be implemented until a proper application scope is in place");
	}

	@Override
	public void setAttribute(String paramString, Object paramObject) {
		throw new UnsupportedOperationException(
				"This class cannot be implemented until a proper application scope is in place");
	}

	@Override
	public Object getAttribute(String paramString) {
		throw new UnsupportedOperationException(
				"This class cannot be implemented until a proper application scope is in place");
	}

	@Override
	public Map<String, Object> getAllAttributes() {
		throw new UnsupportedOperationException(
				"This class cannot be implemented until a proper application scope is in place");
	}

	@Override
	public void removeAttribute(String paramString) {
		throw new UnsupportedOperationException(
				"This class cannot be implemented until a proper application scope is in place");
	}

}
