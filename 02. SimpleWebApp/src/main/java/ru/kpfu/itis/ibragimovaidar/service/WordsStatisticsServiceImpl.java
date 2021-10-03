package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.commons.exceptions.ServiceException;
import ru.kpfu.itis.ibragimovaidar.model.StatisticsRecord;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.READ;

public class WordsStatisticsServiceImpl implements WordsStatisticsService {

	@Override
	public List<StatisticsRecord> getMostFrequentWordsLimit(String folder, int limit) throws ServiceException {
		List<StatisticsRecord> statisticsRecords = new ArrayList<>();
		for (Path path: getFilePaths(folder)){
			Map<String, Integer> wordValueMap = new HashMap<>();
			String[] words = getContentOfFile(path).split("[ \n\t,.]+");
			for (String word: words) {
				if (!wordValueMap.containsKey(word)){
					wordValueMap.put(word, 0);
				}
				wordValueMap.put(word, wordValueMap.get(word) + 1);
			}
			statisticsRecords.add(mapToStatisticRecord(wordValueMap, limit, path));
		}
		return statisticsRecords;
	}

	private List<Path> getFilePaths(String folderAbsolutePath) throws ServiceException {
		try {
			return Files.walk(Paths.get(folderAbsolutePath))
					.filter(Files::isRegularFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	private String getContentOfFile(Path path) throws ServiceException {
		try (DataInputStream inputStream = new DataInputStream(Files.newInputStream(path, READ))){
			byte[] bytes = new byte[inputStream.available()];
			inputStream.readFully(bytes);
			System.out.println(new String(bytes, StandardCharsets.UTF_8));
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	private StatisticsRecord mapToStatisticRecord(Map<String, Integer> wordValueMap, int limit, Path path){
		String filename = path.getFileName().toString();
		Map<String, Integer> sortedLimitedMap =
					wordValueMap.entrySet().stream()
							.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
							.limit(limit)
						.collect(Collectors.toMap(
								Map.Entry::getKey, Map.Entry::getValue,
								(e1, e2) -> e1,
								LinkedHashMap::new));
		return new StatisticsRecord(filename, sortedLimitedMap);
	}
}
