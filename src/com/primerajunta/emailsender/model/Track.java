package com.primerajunta.emailsender.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Track extends AbstractEntity {
	
	private Kind kind;
	
	private User user;
	
	private Date date;
	
	public enum Kind {
		OPEN, REGISTER_FROM_EMAIL, ACCESS_FROM_EMAIL, BUY_FROM_EMAIL;
	}

	public Track() {}
	
	public Track(User user, Kind kind) {
		this.user = user;
		this.kind = kind;
		this.date = new Date();
	}
	
	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Class<? extends com.primerajunta.emailsender.model.Entity> getEntityClass() {
		return Track.class;
	};

}
