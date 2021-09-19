package ru.kpfu.itis.ibragimovaidar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.ibragimovaidar.model.StatsRecord;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class StatsRecordRepositoryImpl implements StatsRecordRepository {

	private final JdbcTemplate jdbcTemplate;

	//language=SQL
	public static final String SQL_FIND_BY_ID = "SELECT * FROM stats_record WHERE id = ?";

	//language=SQL
	private final static String SQL_SAVE = "INSERT INTO stats_record(description) VALUES (?)";

	private final RowMapper<StatsRecord> statsRecordRowMapper = (rs, rowNum) -> {
		int id = rs.getInt("id");
		String description = rs.getString("description");
		return new StatsRecord(id, description);
	};

	@Autowired
	public StatsRecordRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<StatsRecord> findById(Integer id) {
		List<StatsRecord> result = jdbcTemplate.query(SQL_FIND_BY_ID, statsRecordRowMapper, id);
		if (result.size() != 1){
			throw new RuntimeException();
		}
		return Optional.of(result.get(0));
	}

	@Override
	public StatsRecord save(StatsRecord statsRecord) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, statsRecord.getDescription());
			return ps;
		}, keyHolder);
		statsRecord.setId((Integer) keyHolder.getKeys().get("id"));
		return statsRecord;
	}
}
