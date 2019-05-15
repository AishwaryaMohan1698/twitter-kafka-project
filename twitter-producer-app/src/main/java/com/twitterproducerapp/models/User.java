package com.twitterproducerapp.models;

import org.springframework.stereotype.Component;

@Component
public class User {

	String screenName;
	String location;
	long id;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "\nscreenName: " + this.screenName + "\nLocation: " + this.location + "\nID:" + this.id;
	}
}
