package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.StatsRecord;

import java.util.Optional;

public interface StatsRecordRepository {

	Optional<StatsRecord> findById(Integer id);
	StatsRecord save(StatsRecord statsRecord);
}
