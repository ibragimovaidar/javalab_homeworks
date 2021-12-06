package ru.kpfu.itis.ibragimovaidar.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.dto.ProductDto;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryJdbcTemplateImpl implements ProductRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<Product> productRowMapper = (ResultSet rs, int rowNum) -> {
		try {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int amount = rs.getInt("amount");
			String description = rs.getString("description");
			return new Product(id, name, description, price, amount);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("productRowMapper error");
		}
	};

	//language=SQL
	private static final String SQL_FIND_ALL = "SELECT id, name, description, price, amount FROM product";

	@Override
	public List<Product> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, productRowMapper);
	}

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, name, description, price, amount FROM product " +
			"WHERE id = :id";

	@Override
	public Optional<Product> findById(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, paramSource, productRowMapper));
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO product(name, description, price, amount) " +
			"VALUES (:name, :description, :price, :amount)";

	@Override
	public Product save(Product product) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", product.getName());
		paramSource.addValue("description", product.getDescription());
		paramSource.addValue("price", product.getPrice());
		paramSource.addValue("amount", product.getAmount());
		int affectedRows = jdbcTemplate.update(SQL_SAVE, paramSource, keyHolder, new String[]{"id"});
		if (affectedRows != 1){
			throw new RuntimeException();
		}
		product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return product;
	}

	//language=SQL
	private static final String SQL_SEARCH = "SELECT * FROM product WHERE name LIKE :name";

	@Override
	public List<Product> search(String query) {
		return jdbcTemplate.query(SQL_SEARCH,
				Collections.singletonMap("name", "%" + query + "%"),
				productRowMapper);
	}
}
