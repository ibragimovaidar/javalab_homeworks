package ru.kpfu.itis.ibragimovaidar.service;

import ru.kpfu.itis.ibragimovaidar.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {

	List<AccountDTO> findAll();
	Optional<AccountDTO> findByEmail(String email);
}
