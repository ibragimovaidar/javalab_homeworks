package ru.kpfu.itis.ibragimovaidar;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.kpfu.itis.ibragimovaidar.model.Account;
import ru.kpfu.itis.ibragimovaidar.model.AccountPersonalData;
import ru.kpfu.itis.ibragimovaidar.model.Category;

import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate\\hibernate.cfg.xml");
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
			 Session session = sessionFactory.openSession()
		)
		{
			session.getTransaction().begin();
			Account account = Account.builder()
					.personalData(AccountPersonalData.builder()
							.firstName("Aidar")
							.lastName("Ibragimov")
							.dateOfBirth(LocalDate.of(2002, 07, 30))
							.build())
					.email("ibragimovaidarp@gmail.com")
					.passwordHash("123456")
					.build();
			session.saveOrUpdate(account);
			session.getTransaction().commit();
			log.info("Account saved: {}", account);

			session.getTransaction().begin();
			Category chocolateCategory = Category.builder()
					.name("Chocolate")
					.description("chocolate")
					.build();
			Category meatCategory = Category.builder()
					.name("Meat")
					.description("Meat category")
					.build();
			Category foodCategory = Category.builder()
					.name("Food")
					.description("Food category")
					.child(Arrays.asList(meatCategory, chocolateCategory))
					.build();
			session.save(foodCategory);
			session.getTransaction().commit();
			log.info("Food category saved: {}", foodCategory);
		}
	}
}
