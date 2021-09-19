package ru.kpfu.itis.ibragimovaidar;

import ru.kpfu.itis.ibragimovaidar.model.StatsRecord;
import ru.kpfu.itis.ibragimovaidar.model.WordStat;

import java.util.List;

public interface WordStatsService {

	StatsRecord calculateStats(String[] filePaths);
	StatsRecord calculateStats(String directoryPath, String[] fileNames);
	List<WordStat> findAllByStatsRecordIdDescLimit(int statsRecordId, int limit);
}
