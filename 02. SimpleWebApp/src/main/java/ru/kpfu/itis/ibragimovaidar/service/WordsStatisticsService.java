package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.commons.exceptions.ServiceException;
import ru.kpfu.itis.ibragimovaidar.model.StatisticsRecord;

import java.util.List;
import java.util.Map;

public interface WordsStatisticsService {

	List<StatisticsRecord> getMostFrequentWordsLimit(String folder, int limit) throws ServiceException;
}
