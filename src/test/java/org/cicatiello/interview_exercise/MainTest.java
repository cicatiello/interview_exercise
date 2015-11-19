package org.cicatiello.interview_exercise;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.cicatiello.interview_exercise.cart.CartService;
import org.cicatiello.interview_exercise.model.Cart;
import org.cicatiello.interview_exercise.model.Product;
import org.cicatiello.interview_exercise.session.SessionService;
import org.cicatiello.interview_exercise.ui.ParserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MainTest {

	private final static Logger LOG = LoggerFactory.getLogger(MainTest.class);

	@Autowired
	CartService cartService;

	@Autowired
	ParserService parserService;

	@Autowired
	SessionService sessionService;

	@Test
	public void testInput1() throws Exception {
		String input = readFile("input1.txt");
		String output = readFile("output1.txt");
		LOG.info(input);
		Arrays.stream(input.split("\n"), 1, 4).forEach(cartLine -> {
			try {
				Product parsedProduct = parserService.parseProductFromString(cartLine);
				Integer parsedQuantity = parserService.parseQuantityFromString(cartLine);
				cartService.addProductToCart(parsedProduct, parsedQuantity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		Cart cart = sessionService.getSessionCart();
		String expectedOutput = output.split("\n", 2)[1];
		assertEquals(expectedOutput, cart.toString());
		LOG.info(output);
	}

	@Test
	public void testInput2() throws Exception {
		String input = readFile("input2.txt");
		String output = readFile("output2.txt");
		LOG.info(input);
		Arrays.stream(input.split("\n"), 1, 3).forEach(cartLine -> {
			try {
				Product parsedProduct = parserService.parseProductFromString(cartLine);
				Integer parsedQuantity = parserService.parseQuantityFromString(cartLine);
				cartService.addProductToCart(parsedProduct, parsedQuantity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		Cart cart = sessionService.getSessionCart();
		String expectedOutput = output.split("\n", 2)[1];
		assertEquals(expectedOutput, cart.toString());
		LOG.info(output);
	}

	@Test
	public void testInput3() throws Exception {
		String input = readFile("input3.txt");
		String output = readFile("output3.txt");
		LOG.info(input);
		Arrays.stream(input.split("\n"), 1, 5).forEach(cartLine -> {
			try {
				Product parsedProduct = parserService.parseProductFromString(cartLine);
				Integer parsedQuantity = parserService.parseQuantityFromString(cartLine);
				cartService.addProductToCart(parsedProduct, parsedQuantity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		Cart cart = sessionService.getSessionCart();
		String expectedOutput = output.split("\n", 2)[1];
		assertEquals(expectedOutput, cart.toString());
		LOG.info(output);
	}

	private String readFile(String path) throws IOException, URISyntaxException {
		ClassPathResource classPathResource = new ClassPathResource(path);
		byte[] encoded = Files.readAllBytes(Paths.get(classPathResource.getURI()));
		return new String(encoded, "UTF-8");
	}

}
