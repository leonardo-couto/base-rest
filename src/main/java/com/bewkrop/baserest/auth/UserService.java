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
	public boolean save(User user) {
		EntityManager entityManager = EntityManagerFactory.get();

		BaseUser base = new BaseUser(user.key());
		base.setPassword(user.hash());
		base.setRoles(user.roles());

		entityManager.merge(base);

		return true;
	}

}
