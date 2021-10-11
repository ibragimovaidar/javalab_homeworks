package ru.kpfu.itis.ibragimovaidar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.model.WordsStatistics;
import ru.kpfu.itis.ibragimovaidar.repository.WordsStatisticsRepository;
import ru.kpfu.itis.ibragimovaidar.model.Word;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordsStatisticsServiceImpl implements WordsStatisticsService{

	private final Logger LOGGER = LoggerFactory.getLogger(WordsStatisticsServiceImpl.class);

	private final WordsStatisticsRepository wordsStatisticsRepository;

	@Autowired
	public WordsStatisticsServiceImpl(WordsStatisticsRepository wordsStatisticsRepository) {
		this.wordsStatisticsRepository = wordsStatisticsRepository;
	}


	@Override
	public List<WordsStatistics> getMostFrequentWords(String folder) throws ServiceException {
		List<WordsStatistics> wordsStatisticsList = new ArrayList<>();
		List<Path> paths = getFilePaths(folder);
		for (Path path: paths) {
			String[] words = getFileContent(path).split("\\W+");
			Map<String, Integer> wordValueMap = new HashMap<>();
			for (String word: words){
				wordValueMap.putIfAbsent(word, 0);
				wordValueMap.put(word, wordValueMap.get(word) + 1);
			}
			// persist
			List<Word> wordList = new ArrayList<>();
			wordValueMap.forEach((key, value) ->  wordList.add(new Word(key, value)));
			wordList.sort(new Word.ComparatorByValue());
			WordsStatistics wordsStatistics = new WordsStatistics(
					folder,
					path.getFileName().toString(),
					LocalDateTime.now(),
					wordList
			);
			wordsStatisticsRepository.save(wordsStatistics);
			wordsStatisticsList.add(wordsStatistics);
		}
		return wordsStatisticsList;
	}

	@Override
	public List<WordsStatistics> getMostFrequentWordsLimit(String folder, int limit) throws ServiceException {
		List<WordsStatistics> wordsStatistics = getMostFrequentWords(folder);
		wordsStatistics.forEach(ws ->
				ws.setWords(
					ws.getWords().stream()
							.sorted((w1, w2) ->Integer.compare(w2.getValue(), w1.getValue())) // ???
							.limit(limit)
							.collect(Collectors.toList())
				)
		);
		return wordsStatistics;
	}

	private List<Path> getFilePaths(String folder) throws ServiceException {
		try {
			return Files.walk(Paths.get(folder), 1)
					.filter(Files::isRegularFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.error("Wrong folder path", e);
			throw new ServiceException("Wrong folder path", e);
		}
	}

	private String getFileContent(Path filepath) throws ServiceException {
		try (DataInputStream dataInputStream = new DataInputStream(
				Files.newInputStream(filepath, StandardOpenOption.READ))
		){
			byte[] bytes = new byte[dataInputStream.available()];
			dataInputStream.readFully(bytes);
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error("Error getting file content", e);
			throw new ServiceException(e);
		}
	}
}
