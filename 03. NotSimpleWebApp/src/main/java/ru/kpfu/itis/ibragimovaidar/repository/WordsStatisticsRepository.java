package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.WordsStatistics;

import java.util.Optional;

public interface WordsStatisticsRepository {

	Optional<WordsStatistics> findById(Integer id);
	WordsStatistics save(WordsStatistics wordsStatistics);
}
