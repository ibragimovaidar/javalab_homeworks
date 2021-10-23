package ru.kpfu.itis.ibragimovaidar;

import ru.kpfu.itis.ibragimovaidar.AuthenticationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthenticationServiceHardcodedImpl implements AuthenticationService {

	private static final List<String> USERS;

	static {
		List<String> mutable = new ArrayList<>();
		mutable.add("user1");
		mutable.add("ibragimovaidar");
		mutable.add("user2");
		mutable.add("user3");
		USERS = Collections.unmodifiableList(mutable);
	}

	@Override
	public boolean authenticate(String username, String password){
		return USERS.contains(username);
	}
}
