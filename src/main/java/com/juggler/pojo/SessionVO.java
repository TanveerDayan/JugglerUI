package com.juggler.pojo;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class SessionVO {
	String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SessionVO [userId=" + userId + "]";
	}

}
