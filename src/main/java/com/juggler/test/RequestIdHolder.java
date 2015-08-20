package com.juggler.test;

import java.util.UUID;

public class RequestIdHolder {
	String id;

	public RequestIdHolder() {
		UUID uniqueKey = UUID.randomUUID();
		setId(uniqueKey.toString());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RequestIdHolder [id=" + id + "]";
	}

}
