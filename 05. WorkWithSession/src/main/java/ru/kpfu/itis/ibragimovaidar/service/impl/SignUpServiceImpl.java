package ru.kpfu.itis.ibragimovaidar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.dto.SignUpForm;
import ru.kpfu.itis.ibragimovaidar.model.Account;
import ru.kpfu.itis.ibragimovaidar.repository.AccountRepository;
import ru.kpfu.itis.ibragimovaidar.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

	private final AccountRepository accountRepository;

	@Autowired
	public SignUpServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void signUp(SignUpForm signUpForm) {
		Account account = Account.builder()
				.email(signUpForm.getEmail())
				.password(signUpForm.getPassword())
				.firstName(signUpForm.getFirstName())
				.lastName(signUpForm.getLastName())
				.build();
		accountRepository.save(account);
	}
}
