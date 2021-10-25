package ru.kpfu.itis.ibragimovaidar.repository;

import ru.kpfu.itis.ibragimovaidar.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

	List<Account> findAll();
	Optional<Account> findByEmail(String email);
	Account save(Account account);
}
