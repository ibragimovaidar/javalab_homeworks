package ru.kpfu.itis.ibragimovaidar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.SignInForm;
import ru.kpfu.itis.ibragimovaidar.model.Account;
import ru.kpfu.itis.ibragimovaidar.repository.AccountRepository;
import ru.kpfu.itis.ibragimovaidar.service.SignInService;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

	private final AccountRepository accountRepository;

	@Autowired
	public SignInServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public boolean signIn(SignInForm signInForm){
		Optional<Account> accountOptional = accountRepository.findByEmail(signInForm.getEmail());
		return accountOptional
				.map(account -> account.getPassword().equals(signInForm.getPassword()))
				.orElse(false);
	}
}
