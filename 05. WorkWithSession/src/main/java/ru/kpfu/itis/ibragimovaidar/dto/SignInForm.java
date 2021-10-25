package ru.kpfu.itis.ibragimovaidar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SignInForm {

	private String email;
	private String password;
}
