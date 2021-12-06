package ru.kpfu.itis.ibragimovaidar.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;
import ru.kpfu.itis.ibragimovaidar.service.ProductService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceImplTest {

	private static final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

	private static final ProductService productService = new ProductServiceImpl(productRepository);

	@Test
	void findAll() {
		long expectedId = 1L;
		String expectedName = "test product";
		String expectedDesc = "test desc";
		int expectedPrice = 1;
		int expectedAmount = 1;

		Mockito.when(productRepository.findAll()).thenReturn(
				Collections.singletonList(
						Product.builder()
							.id(expectedId)
							.name(expectedName)
							.description(expectedDesc)
							.price(expectedPrice)
							.amount(expectedAmount)
							.build()
				)
		);

		assertEquals(1, productService.findAll().size());
		assertEquals(expectedName, productService.findAll().get(0).getName());
		assertEquals(expectedDesc, productService.findAll().get(0).getDescription());
		assertEquals(expectedPrice, productService.findAll().get(0).getPrice());
	}
}