package com.juggler.test.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.juggler.test.RequestIdHolder;
import com.juggler.test.beans.HelloRequestScopeData;

public class HelloRequestScopeDataImpl implements HelloRequestScopeData {

	@Autowired
	RequestIdHolder requestIdHolder;

	public String getDate() {
		return requestIdHolder.getId().toString();
	}
}
