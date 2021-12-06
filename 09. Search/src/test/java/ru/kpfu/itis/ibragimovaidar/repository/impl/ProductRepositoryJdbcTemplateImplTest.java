package ru.kpfu.itis.ibragimovaidar.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.kpfu.itis.ibragimovaidar.model.Product;

import javax.sql.DataSource;
import java.util.List;

class ProductRepositoryJdbcTemplateImplTest {

	private static DataSource dataSource =
			new EmbeddedDatabaseBuilder()
					.setType(EmbeddedDatabaseType.H2)
					.addScript("classpath:sql/schema.sql")
					.addScript("classpath:sql/data.sql")
					.build();

	private static NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	private static ProductRepositoryJdbcTemplateImpl productRepository = new ProductRepositoryJdbcTemplateImpl(jdbcTemplate);

	@Test
	void findAll() {
		List<Product> productList = productRepository.findAll();
		System.out.println(productRepository.findAll());
		Assertions.assertFalse(productList.isEmpty());
	}

	@Test
	void findById() {
		Assertions.assertTrue(productRepository.findById(1L).isPresent());
	}

	@Test
	void save() {
		Product product = Product.builder()
				.name("test")
				.description("test")
				.price(100)
				.amount(1)
				.build();
		Product save = productRepository.save(product);
		Assertions.assertTrue(productRepository.findAll().contains(save));
	}

	@Test
	void search() {
		Product product = Product.builder()
				.name("test")
				.description("test")
				.price(100)
				.amount(100)
				.build();
		productRepository.save(product);
		Assertions.assertTrue(productRepository.search("test").contains(product));
	}
}