package ru.kpfu.itis.ibragimovaidar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.Account;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final RowMapper<Account> accountRowMapper = (ResultSet rs, int rowNum) ->
			Account.builder()
				.id(rs.getInt("id"))
				.firstName(rs.getString("first_name"))
				.lastName(rs.getString("last_name"))
				.email(rs.getString("email"))
				.password(rs.getString("password"))
				.build();

	@Autowired
	public AccountRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	//language=SQL
	private static final String SQL_FIND_ALL = "SELECT id, first_name, last_name, email, password FROM account";

	@Override
	public List<Account> findAll() {
		return namedParameterJdbcTemplate.query(SQL_FIND_ALL, accountRowMapper);
	}

	//language=SQL
	private static final String SQL_FIND_BY_EMAIL = "SELECT id, first_name, last_name, email, password FROM account " +
			"WHERE email = :email";

	@Override
	public Optional<Account> findByEmail(String email) {
		try {
			return Optional.ofNullable(namedParameterJdbcTemplate
					.queryForObject(SQL_FIND_BY_EMAIL, Collections.singletonMap("email", email), accountRowMapper));
		} catch (EmptyResultDataAccessException e){
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO account(first_name, last_name, email, password) " +
			"VALUES (:firstName, :lastName, :email, :password)";

	@Override
	public Account save(Account account) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("firstName", account.getFirstName());
		parameterSource.addValue("lastName", account.getLastName());
		parameterSource.addValue("email", account.getEmail());
		parameterSource.addValue("password", account.getPassword());
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(SQL_SAVE, parameterSource, keyHolder, new String[]{"id"});
		account.setId(keyHolder.getKeyAs(Integer.class));
		return account;
	}
}
