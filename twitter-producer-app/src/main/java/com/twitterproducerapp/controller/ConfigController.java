package com.twitterproducerapp.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterproducerapp.models.FilterProperties;
import com.twitterproducerapp.utils.DataStructuringUtils;
import com.twitterproducerapp.utils.TwitterUtils;

@RefreshScope
@RequestMapping("/config")
@RestController
public class ConfigController {

	@Autowired
	TwitterUtils twitterUtilsObject;

	@Autowired
	DataStructuringUtils dataUtilsObject;

	@Autowired
	FilterProperties filterProperties;

	@GetMapping("/properties")
	public String printProperties() {
		double[][] locations = dataUtilsObject.fetchLocations(filterProperties.getLocationsString());
		return "properties:\nlocation: " + Arrays.deepToString(locations) + "\nlanguages: "
				+ Arrays.deepToString(filterProperties.getLanguages()) + "\nkeywords: "
				+ Arrays.deepToString(filterProperties.getKeywords());
	}

	@GetMapping("publish/variableStreamFilter")
	public String variableStreamFilter() {
		double[][] locations = dataUtilsObject.fetchLocations(filterProperties.getLocationsString());
		String[] keywords = filterProperties.getKeywords();
		String[] languages = filterProperties.getLanguages();

		twitterUtilsObject.configurableStreamFilter(locations, keywords, languages);
		return "streaming for" + "\nlocation: " + Arrays.deepToString(locations) + "\nlanguages: "
				+ Arrays.deepToString(languages) + "\nkeywords: " + Arrays.deepToString(keywords);
	}
}