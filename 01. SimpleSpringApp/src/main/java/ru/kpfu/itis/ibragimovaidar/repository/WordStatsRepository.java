package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.WordStat;

import java.util.List;
import java.util.Optional;

public interface WordStatsRepository {

	Optional<WordStat> findById(Integer id);
	Optional<WordStat> findByWord(String word);
	List<WordStat> findAllByStatsRecordIdDescLimit(int statsRecordId, int limit);
	WordStat save(WordStat wordStat);
}
