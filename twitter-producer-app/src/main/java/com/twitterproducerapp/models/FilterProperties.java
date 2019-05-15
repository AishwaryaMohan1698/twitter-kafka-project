package com.twitterproducerapp.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilterProperties {

	@Value("${keywords:mi,csk}")
	private String[] keywords;

	@Value("${locations:68.116667, 8.066667}")
	private String locationsString;

	@Value("${languages:en}")
	private String[] languages;

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public String getLocationsString() {
		return locationsString;
	}

	public void setLocationsString(String locationsString) {
		this.locationsString = locationsString;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

}
