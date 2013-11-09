package com.bewkrop.baserest.auth;

import javax.persistence.EntityManager;

import com.bewkrop.auth.user.User;
import com.bewkrop.auth.user.UserRepository;
import com.bewkrop.baserest.context.EntityManagerFactory;
import com.bewkrop.baserest.entity.BaseUser;

public class UserService implements UserRepository {

	@Override
	public User get(String key) {
		EntityManager entityManager = EntityManagerFactory.get();
		User user = entityManager.find(BaseUser.class, key);

		return user;
	}

	@Override
	public boolean save(String key, String password) {
		EntityManager entityManager = EntityManagerFactory.get();

		BaseUser base = new BaseUser(key);
		base.setPassword(password);
		base.setRoles("AUTH");

		entityManager.merge(base);

		return true;
	}

}
