package com.twitterproducerapp.models;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import twitter4j.GeoLocation;

@Component
public class Tweet {

	String tweetText;
	ArrayList<String> hashtags;
	ArrayList<String> keyWords;

	String language;
	GeoLocation geoLocation;
	Date createdAt;

	User user;

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}

	public ArrayList<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "\nTweet:" + "\ntweetText : " + this.tweetText + "\nhashtags  : " + this.hashtags + "\nkeyWords  : "
				+ this.keyWords + "\nlanguage  : " + this.language + "\ngeoLocation: " + this.geoLocation
				+ "\ncreatedAt : " + this.createdAt + "\nuser : " + this.user.toString();
	}
}
