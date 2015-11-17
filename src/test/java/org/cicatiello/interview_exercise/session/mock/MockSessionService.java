package org.cicatiello.interview_exercise.session.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.model.builder.CartBuilder;
import org.cicatiello.interview_exercise.session.SessionService;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;

@Service
public class MockSessionService implements SessionService {

	Map<String, Object> sessionState = new ConcurrentHashMap<>();

	private final static String CART_ID = "session_cart";

	@Override
	public void setAttribute(String paramString, Object paramObject) {
		sessionState.put(paramString, paramObject);
	}

	@Override
	public Object getAttribute(String paramString) {
		return sessionState.get(paramString);
	}

	@Override
	public Map<String, Object> getAllAttributes() {
		return ImmutableMap.copyOf(sessionState);
	}

	@Override
	public void removeAttribute(String paramString) {
		sessionState.remove(paramString);
	}

	@Override
	public Cart getSessionCart() {
		Cart cart = (Cart) getAttribute(CART_ID);
		if (cart == null) {
			cart = CartBuilder.build();
			setAttribute(CART_ID, cart);
		}
		return cart;
	}

}
