package ru.kpfu.itis.ibragimovaidar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.StatsRecord;
import ru.kpfu.itis.ibragimovaidar.model.WordStat;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WordStatsRepositoryImpl implements WordStatsRepository {

	private final JdbcTemplate jdbcTemplate;

	//language=SQL
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM word_stat WHERE id = ?";

	//language=SQL
	private static final String SQL_SELECT_BY_WORD = "SELECT * FROM word_stat WHERE word = ?";

	//language=SQL
	private static final String SQL_SELECT_ALL_BY_DESC_LIMIT = "SELECT * FROM word_stat " +
			"WHERE stats_record_id = ? ORDER BY value DESC LIMIT ?";

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO word_stat(word, value, stats_record_id) VALUES (?, ?, ?)";

	private final static ResultSetExtractor<List<WordStat>> wordStatListResultExtractor = resultSet -> {
		List<WordStat> result = new ArrayList<>();
		while (resultSet.next()){
			int id = resultSet.getInt("id");
			String word = resultSet.getString("word");
			int value = resultSet.getInt("value");
			// TODO
			StatsRecord statsRecord = new StatsRecord(resultSet.getInt("stats_record_id"), null);
			result.add(new WordStat(id, word, value, statsRecord));
		}
		return result;
	};

	@Autowired
	public WordStatsRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<WordStat> findById(Integer id) {
		List<WordStat> wordStatList = jdbcTemplate.query(SQL_SELECT_BY_ID,
				wordStatListResultExtractor, id);
		if (wordStatList == null || wordStatList.size() != 1){
			throw new RuntimeException();
		}
		return Optional.of(wordStatList.get(0));
	}

	@Override
	public Optional<WordStat> findByWord(String word) {
		List<WordStat> wordStatList = jdbcTemplate.query(SQL_SELECT_BY_WORD,
				wordStatListResultExtractor, word);
		if (wordStatList == null || wordStatList.size() != 1){
			throw new RuntimeException();
		}
		return Optional.of(wordStatList.get(0));
	}

	@Override
	public List<WordStat> findAllByStatsRecordIdDescLimit(int statsRecordId, int limit) {
		return jdbcTemplate.query(SQL_SELECT_ALL_BY_DESC_LIMIT,
				wordStatListResultExtractor, statsRecordId, limit);
	}

	@Override
	public WordStat save(WordStat wordStat) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, wordStat.getWord());
			ps.setInt(2, wordStat.getValue());
			ps.setInt(3, wordStat.getStatsRecord().getId());
			return ps;
		}, keyHolder);
		wordStat.setId((Integer) keyHolder.getKeys().get("id"));
		return wordStat;
	}
}
