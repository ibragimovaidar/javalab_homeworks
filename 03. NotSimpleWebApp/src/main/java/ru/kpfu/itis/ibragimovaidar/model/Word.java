package ru.kpfu.itis.ibragimovaidar.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class Word {

	private Integer id;
	private String word;
	private Integer value;

	public Word() {
	}

	public Word(String word, Integer value) {
		this.word = word;
		this.value = value;
	}

	public Word(Integer id, String word, Integer value) {
		this.id = id;
		this.word = word;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Word word = (Word) o;
		return id.equals(word.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Word.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("word='" + word + "'")
				.add("value='" + value + "'")
				.toString();
	}

	public static class ComparatorByValue implements Comparator<Word> {

		@Override
		public int compare(Word o1, Word o2) {
			return Integer.compare(o1.getValue(), o2.getValue());
		}
	}
}
