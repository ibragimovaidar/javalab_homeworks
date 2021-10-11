package ru.kpfu.itis.ibragimovaidar.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class WordsStatistics {

	private Integer id;
	private String folder;
	private String filename;
	private LocalDateTime createdAt;
	private List<Word> words;

	public WordsStatistics() {
	}

	public WordsStatistics(String folder, String filename, LocalDateTime createdAt, List<Word> words) {
		this.folder = folder;
		this.filename = filename;
		this.createdAt = createdAt;
		this.words = words;
	}

	public WordsStatistics(Integer id, String folder, String filename, LocalDateTime createdAt, List<Word> words) {
		this.id = id;
		this.folder = folder;
		this.filename = filename;
		this.createdAt = createdAt;
		this.words = words;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WordsStatistics that = (WordsStatistics) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", WordsStatistics.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("folder='" + folder + "'")
				.add("filename='" + filename + "'")
				.add("createdAt=" + createdAt)
				.toString();
	}
}
