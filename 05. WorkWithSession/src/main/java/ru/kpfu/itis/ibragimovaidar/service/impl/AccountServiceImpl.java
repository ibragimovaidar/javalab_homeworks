package ru.kpfu.itis.ibragimovaidar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.AccountDTO;
import ru.kpfu.itis.ibragimovaidar.repository.AccountRepository;
import ru.kpfu.itis.ibragimovaidar.service.AccountService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public List<AccountDTO> findAll() {
		return accountRepository.findAll().stream()
				.map(AccountDTO::from)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<AccountDTO> findByEmail(String email) {
		return accountRepository.findByEmail(email).map(AccountDTO::from);
	}
}
