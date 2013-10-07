package com.bewkrop.baserest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BaseUser implements com.bewkrop.auth.user.User {
	
	/**
	 * Use it to create a new entity
	 */
	public BaseUser(String email) {
		this.email = email;
		this.roles = null;
	}
	
	public BaseUser() {
		this.roles = null;
	}
	
	@Id
	private String email;
	private String password;
	private String roles;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String key() {
		return this.email;
	}

	@Override
	public String hash() {
		return this.password;
	}

	@Override
	public String roles() {
		return this.roles;
	}

}
