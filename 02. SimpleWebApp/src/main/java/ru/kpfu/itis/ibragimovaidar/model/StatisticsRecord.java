package ru.kpfu.itis.ibragimovaidar.model;

import java.util.Map;
import java.util.StringJoiner;

public class StatisticsRecord {

	private final String filename;
	private final  Map<String, Integer> wordValueMap;

	public StatisticsRecord(String filename, Map<String, Integer> wordValueMap) {
		this.filename = filename;
		this.wordValueMap = wordValueMap;
	}

	public String getFilename() {
		return filename;
	}

	public Map<String, Integer> getWordValueMap() {
		return wordValueMap;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", StatisticsRecord.class.getSimpleName() + "[", "]")
				.add("filename='" + filename + "'")
				.add("wordValueMap=" + wordValueMap)
				.toString();
	}
}
