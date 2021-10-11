package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.model.WordsStatistics;

import java.util.List;

public interface WordsStatisticsService {

	List<WordsStatistics> getMostFrequentWords(String folder) throws ServiceException;
	List<WordsStatistics> getMostFrequentWordsLimit(String folder, int limit) throws ServiceException;
}
