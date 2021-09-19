package ru.kpfu.itis.ibragimovaidar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.model.StatsRecord;
import ru.kpfu.itis.ibragimovaidar.model.WordStat;
import ru.kpfu.itis.ibragimovaidar.repository.StatsRecordRepository;
import ru.kpfu.itis.ibragimovaidar.repository.WordStatsRepository;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordStatsServiceImpl implements WordStatsService {

	private final WordStatsRepository wordStatsRepository;
	private final StatsRecordRepository statsRecordRepository;

	@Autowired
	public WordStatsServiceImpl(WordStatsRepository wordStatsRepository, StatsRecordRepository statsRecordRepository) {
		this.wordStatsRepository = wordStatsRepository;
		this.statsRecordRepository = statsRecordRepository;
	}
	@Override
	public StatsRecord calculateStats(String[] filePaths){
		Map<String, Integer> wordValueMap = new HashMap<>();

		for (String filePath: filePaths){
			String[] words = readFile(filePath).split("\\W+");
			for (String word: words) {
				if (!wordValueMap.containsKey(word)){
					wordValueMap.put(word, 0);
				}
				wordValueMap.put(word, wordValueMap.get(word)+1);
			}
		}
		return persistStats(wordValueMap);
	}
	@Override
	public StatsRecord calculateStats(String directoryPath, String[] filenames){
		String[] filePaths = Arrays.stream(filenames)
				.map(filename -> directoryPath + File.separator + filename)
				.toArray(String[]::new);
		return calculateStats(filePaths);
	}

	@Override
	public List<WordStat> findAllByStatsRecordIdDescLimit(int statsRecordId, int limit) {
		return wordStatsRepository.findAllByStatsRecordIdDescLimit(statsRecordId, limit);
	}

	private String readFile(String filePath){
		try (DataInputStream inputStream = new DataInputStream(new FileInputStream(filePath))) {
			byte[] bytes = new byte[inputStream.available()];
			inputStream.readFully(bytes);
			return new String(bytes);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private StatsRecord persistStats(Map<String, Integer> wordValueMap){
		StatsRecord statsRecord = statsRecordRepository.save(new StatsRecord("fskd;l"));
		wordValueMap.forEach(
				(key,value) -> wordStatsRepository.save(new WordStat(key, value, statsRecord))
		);
		return statsRecord;
	}
}
