package ru.kpfu.itis.ibragimovaidar.model;

import java.util.StringJoiner;

public class WordStat {

	private Integer id;
	private String word;
	private Integer value;
	private StatsRecord statsRecord;

	public WordStat(Integer id, String word, Integer value, StatsRecord statsRecord) {
		this.id = id;
		this.word = word;
		this.value = value;
		this.statsRecord = statsRecord;
	}

	public WordStat(String word, Integer value, StatsRecord statsRecord) {
		this.word = word;
		this.value = value;
		this.statsRecord = statsRecord;
	}

	public Integer getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public Integer getValue() {
		return value;
	}

	public StatsRecord getStatsRecord() {
		return statsRecord;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void setStatsRecord(StatsRecord statsRecord) {
		this.statsRecord = statsRecord;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", WordStat.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("word='" + word + "'")
				.add("value=" + value)
				.add("statsRecord=" + statsRecord)
				.toString();
	}
}
