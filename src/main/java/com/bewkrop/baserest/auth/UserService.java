package com.bewkrop.baserest.auth;

import com.bewkrop.auth.user.User;
import com.bewkrop.auth.user.UserRepository;
import com.bewkrop.baserest.entity.BaseUser;

public class UserService implements UserRepository {

	@Override
	public User get(String key) {
		BaseUser user = new BaseUser("leonardo.couto@gmail.com");
		user.setPassword("abcdef");
		user.setRoles("ADMIN");
		return user;
	}

	@Override
	public boolean save(User user) {
		return true;
	}

}
