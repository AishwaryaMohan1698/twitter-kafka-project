package com.twitterproducerapp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitterproducerapp.models.FilterProperties;
import com.twitterproducerapp.models.Tweet;
import com.twitterproducerapp.models.User;

import twitter4j.HashtagEntity;
import twitter4j.Status;

@Component
public class DataStructuringUtils {

	@Autowired
	FilterProperties filterProperties;

	public ArrayList<String> matchKeywords(String tweetText) {

		String[] keywords = filterProperties.getKeywords();

		ArrayList<String> keywordsMatched = new ArrayList<>();

		tweetText = tweetText.toLowerCase();

		Trie trie = Trie.builder().addKeywords(keywords).build();
		Collection<Emit> emits = trie.parseText(tweetText);
		emits.forEach(System.out::println); // prints first:last index = word matched

		for (String word : keywords) {
			boolean contains = Arrays.toString(emits.toArray()).contains(word);
			if (contains) {
				keywordsMatched.add(word);
				break;
			}
		}

		return keywordsMatched;
	}

	public boolean isLanguageMatched(Status status) {
		String[] languages = filterProperties.getLanguages();
		return Arrays.asList(languages).contains(status.getLang());
	}

	public Tweet setTweetData(Status status) {
		Tweet tweet = new Tweet();

		try {
			tweet.setTweetText(status.getText());
			tweet.setCreatedAt(status.getCreatedAt());
			tweet.setGeoLocation(status.getGeoLocation());
			tweet.setLanguage(status.getLang());

			ArrayList<String> hashtags = new ArrayList<String>();
			for (HashtagEntity hashtag : status.getHashtagEntities()) {
				hashtags.add(hashtag.getText());
			}
			tweet.setHashtags(hashtags);

			User user = new User();
			user.setScreenName(status.getUser().getScreenName());
			user.setLocation(status.getUser().getLocation());
			user.setId(status.getUser().getId());

			tweet.setUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		printTweetInFile(tweet);
		return tweet;
	}

	public void printTweetInFile(Tweet tweet) {
		try {
			FileUtil.writeStringToFile("/home/css/twitterFile/dataFiles/tweetsFile1.txt", tweet.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double[][] fetchLocations(String locationsString) {
		String[] boundingBoxes = locationsString.split(",");
		double[][] locations = new double[boundingBoxes.length / 2][2];

		for (int i = 0, j = 0; i < boundingBoxes.length - 1; i = i + 2, j++) {
//			System.out.println(boundingBoxes[i] + " " + boundingBoxes[i + 1]);
			locations[j][0] = Double.parseDouble(boundingBoxes[i]);
			locations[j][1] = Double.parseDouble(boundingBoxes[i + 1]);
		}
		return locations;
	}

}
