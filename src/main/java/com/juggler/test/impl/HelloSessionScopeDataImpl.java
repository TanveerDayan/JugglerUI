package com.juggler.test.impl;

import java.util.Date;

import com.juggler.test.beans.HelloSessionScopeData;

public class HelloSessionScopeDataImpl implements HelloSessionScopeData {
	private final Date date;

	public HelloSessionScopeDataImpl(Date date) {
		this.date = date;
	}

	public String getDate() {
		return date.toString();
	}


}
