package ru.kpfu.itis.ibragimovaidar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kpfu.itis.ibragimovaidar.config.AppConfig;
import ru.kpfu.itis.ibragimovaidar.model.StatsRecord;
import ru.kpfu.itis.ibragimovaidar.model.WordStat;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		WordStatsServiceImpl wordStatsService = context.getBean(WordStatsServiceImpl.class);
		StatsRecord statsRecord = wordStatsService.calculateStats(new String[]{
				"C:\\Users\\ibrag\\Documents\\education\\ibragimov_itis_2021_lab\\01. SimpleSpringApp\\src\\main\\resources\\test.txt",
				"C:\\Users\\ibrag\\Documents\\education\\sample1.txt"});

		List<WordStat> result = wordStatsService.findAllByStatsRecordIdDescLimit(statsRecord.getId(), 10);
		System.out.println(result);
	}
}
