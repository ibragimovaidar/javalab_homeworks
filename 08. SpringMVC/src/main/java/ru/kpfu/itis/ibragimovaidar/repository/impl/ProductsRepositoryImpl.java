package ru.kpfu.itis.ibragimovaidar.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.Product;
import ru.kpfu.itis.ibragimovaidar.repository.ProductsRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<Product> productRowMapper = (ResultSet rs, int rowNum) -> {
		try {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int availableAmount = rs.getInt("available_amount");
			String description = rs.getString("description");
			return new Product(id, name, price, availableAmount, description);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("productRowMapper error");
		}
	};

	@Autowired
	public ProductsRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	//language=SQL
	private static final String SQL_FIND_ALL = "SELECT id, name, price, available_amount, description FROM product";

	@Override
	public List<Product> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, productRowMapper);
	}

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, name, price, available_amount, description FROM product " +
			"WHERE id = :id";

	@Override
	public Optional<Product> findById(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, paramSource, productRowMapper));
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO product(name, price, available_amount, description) " +
			"VALUES (:name, :price, :availableAmount, :description)";

	@Override
	public Product save(Product product) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", product.getName());
		paramSource.addValue("price", product.getPrice());
		paramSource.addValue("availableAmount", product.getAvailableAmount());
		paramSource.addValue("description", product.getDescription());
		int affectedRows = jdbcTemplate.update(SQL_SAVE, paramSource, keyHolder, new String[]{"id"});
		if (affectedRows != 1){
			throw new RuntimeException();
		}
		product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return product;
	}
}
