package com.twitterproducerapp.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitterproducerapp.factory.FactoryClass;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterStream;

@Component
public class TwitterUtils {

	@Autowired
	FactoryClass factory;

	Twitter twitter = null;
	StatusListener listener = null;
	TwitterStream twitterStream = null;

	@PostConstruct
	public void init() {
		twitter = factory.getTwitterInstance();
		listener = factory.getListener();
		twitterStream = factory.getTwitterStreamInstance();
		twitterStream.addListener(listener);
	}

	public void printStream() {
		twitterStream.sample();
	}

	public void stopStream() {
		twitterStream.cleanUp(); // shutdown internal stream consuming thread
		twitterStream.shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
		System.out.println("Stream ended");
	}

	public void streamFilter() {

		double locations[][] = { { 68.116667, 8.066667, }, { 97.416667, 37.100000 } };
		String[] keywordsArray = { "csk" };
		String[] languages = { "en" };

		try {
			FilterQuery filter = new FilterQuery();

			filter.track(keywordsArray); // search for keywords
			filter.locations(locations); // search for location
			filter.language(languages); // search for language

			twitterStream.filter(filter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void configurableStreamFilter(double locations[][], String[] keywords, String[] languages) {
		try {
			FilterQuery filter = new FilterQuery();

//			filter.track(keywords);
			filter.locations(locations);
//			filter.language(languages);

			twitterStream.filter(filter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
