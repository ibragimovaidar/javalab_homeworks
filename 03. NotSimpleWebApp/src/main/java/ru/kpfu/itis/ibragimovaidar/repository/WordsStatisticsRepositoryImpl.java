package ru.kpfu.itis.ibragimovaidar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.ibragimovaidar.model.Word;
import ru.kpfu.itis.ibragimovaidar.model.WordsStatistics;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Repository
public class WordsStatisticsRepositoryImpl implements WordsStatisticsRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public WordsStatisticsRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<WordsStatistics> findById(Integer id) {
		throw new RuntimeException("Not implemented");
	}


	//language=SQL
	private static final String SQL_SAVE_WORD = "INSERT INTO word(word, value, words_statistics_id) VALUES (?, ?, ?)";

	//language=SQL
	private static final String SQL_SAVE_WORDS_STATISTICS = "INSERT INTO words_statistics(folder, filename, created_at) VALUES (?, ?, ?)";

	@Override
	public WordsStatistics save(WordsStatistics wordsStatistics) {
		GeneratedKeyHolder wordsStatisticsIdKeyholder = new GeneratedKeyHolder();
		jdbcTemplate.update((connection) ->{
			PreparedStatement statement = connection.prepareStatement(SQL_SAVE_WORDS_STATISTICS,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, wordsStatistics.getFolder());
			statement.setString(2, wordsStatistics.getFilename());
			statement.setTimestamp(3, Timestamp.valueOf(wordsStatistics.getCreatedAt()));
			return statement;
			}, wordsStatisticsIdKeyholder);

		int wordsStatisticsId = (int) Objects.requireNonNull(wordsStatisticsIdKeyholder.getKeys()).get("id");
		wordsStatistics.setId(wordsStatisticsId);

		for (Word word: wordsStatistics.getWords()){
			GeneratedKeyHolder wordIdKeyholder = new GeneratedKeyHolder();
			jdbcTemplate.update((connection) -> {
				PreparedStatement statement = connection.prepareStatement(SQL_SAVE_WORD, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, word.getWord());
				statement.setInt(2, word.getValue());
				statement.setInt(3, wordsStatisticsId);
				return statement;
			}, wordIdKeyholder);
			word.setId((int) Objects.requireNonNull(wordIdKeyholder.getKeys()).get("id"));
		}
		return wordsStatistics;
	}
}
