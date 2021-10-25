package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.ibragimovaidar.model.Account;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AccountDTO {

	private String email;
	private String firstName;
	private String lastName;

	public static AccountDTO from(Account account){
		return AccountDTO.builder()
				.email(account.getEmail())
				.firstName(account.getFirstName())
				.lastName(account.getLastName())
				.build();
	}
}
