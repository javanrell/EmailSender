package com.primerajunta.emailsender.model;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
	
	private String name;
	
	private String email;
	
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Class<? extends com.primerajunta.emailsender.model.Entity> getEntityClass() {
		return User.class;
	}

}
