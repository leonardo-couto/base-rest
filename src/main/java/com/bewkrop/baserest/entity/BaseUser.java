package com.bewkrop.baserest.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.bewkrop.auth.user.Credential;
import com.bewkrop.auth.user.User;

@Entity
public class BaseUser implements User {
	
	private static final String CSV_REGEX = "[\\,|;]";
	
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
	public Credential credential() {
		final String original = this.password;
		return new Credential() {
			
			@Override
			public boolean check(char[] password) {
				String credential = new String(password);
				return original.equals(credential);
			}
		};
	}
	
	@Override
	public String key() {
		return this.email;
	}

	@Override
	public List<String> roles() {
		String[] roles = this.roles.split(CSV_REGEX);
		return Arrays.asList(roles);
	}
	
}
