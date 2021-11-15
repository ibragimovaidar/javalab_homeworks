package ru.kpfu.itis.ibragimovaidar.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ProductRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final RowMapper<Product> productRowMapper = (ResultSet rs, int rowNum) -> {
		long id = rs.getLong("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		long price = rs.getLong("price");
		int availableAmount = rs.getInt("available_amount");
		return new Product(id, name, price, description, availableAmount);
	};

	//language=SQL
	private static final String SQL_FIND_ALL = "SELECT id, name, price, description, available_amount " +
			"FROM product";


	@Override
	public List<Product> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, productRowMapper);
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO product(name, price, description, available_amount) " +
			"VALUES (:name, :price, :description, :available_amount)";

	@Override
	public Product save(Product product) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", product.getName());
		paramSource.addValue("price", product.getPrice());
		paramSource.addValue("description", product.getDescription());
		paramSource.addValue("available_amount", product.getAvailableAmount());

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		int affectedRows = jdbcTemplate.update(SQL_SAVE, paramSource, keyHolder, new String[]{"id"});
		if (affectedRows != 1){
			throw new RuntimeException("Save error");
		}
		product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return product;
	}
}
