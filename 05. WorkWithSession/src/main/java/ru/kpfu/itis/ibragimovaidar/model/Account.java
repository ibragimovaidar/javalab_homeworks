package ru.kpfu.itis.ibragimovaidar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
}
