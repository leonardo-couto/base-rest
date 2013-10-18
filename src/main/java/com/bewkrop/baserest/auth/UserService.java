package com.bewkrop.baserest.auth;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.bewkrop.auth.user.User;
import com.bewkrop.auth.user.UserRepository;
import com.bewkrop.baserest.context.EntityManagerFactory;
import com.bewkrop.baserest.entity.BaseUser;

public class UserService implements UserRepository {

	@Override
	public User get(String key) {
		EntityManager entityManager = EntityManagerFactory.createEntityManager();
		User user = null;

		try {
			user = entityManager.find(BaseUser.class, key);

		} finally {
			entityManager.close();
		}

		return user;
	}

	@Override
	public boolean save(User user) {
		EntityManager entityManager = EntityManagerFactory.createEntityManager();
		boolean success = false;

		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			BaseUser base = new BaseUser(user.key());
			base.setPassword(user.hash());
			base.setRoles(user.roles());

			entityManager.merge(base);

			entityTransaction.commit();
			success = true;

		} finally {
			entityManager.close();
		}

		return success;
	}

}
