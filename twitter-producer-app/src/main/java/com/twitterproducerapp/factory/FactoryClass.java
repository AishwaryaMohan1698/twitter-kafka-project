package com.twitterproducerapp.factory;

import java.util.ArrayList;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.twitterproducerapp.models.Tweet;
import com.twitterproducerapp.utils.DataStructuringUtils;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class FactoryClass {

	@Autowired
	NewTopic newTopic;

	@Autowired
	KafkaTemplate<String, Tweet> kafkaTemplate;

	@Autowired
	DataStructuringUtils dataUtilObject;

	public Twitter getTwitterInstance() {
		return TwitterFactory.getSingleton();
	}

	public TwitterStream getTwitterStreamInstance() {
		return new TwitterStreamFactory().getInstance();
	}

	public StatusListener getListener() {
		return new StatusListener() {
			@Override
			public void onStatus(Status status) {
				try {
					System.out.println("......................");

					// check for keywords
					ArrayList<String> keywordsMatched = dataUtilObject.matchKeywords(status.getText());

					// check for language
					boolean isLanguageMatched = dataUtilObject.isLanguageMatched(status);
					
					// if matched, create object, set matched keywords and send
					if (keywordsMatched.size() > 0 && isLanguageMatched) {
						Tweet tweet = dataUtilObject.setTweetData(status);
						tweet.setKeyWords(keywordsMatched);
						kafkaTemplate.send(newTopic.name(), tweet);
					}

					System.out.println("......................");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}

		};
	}

}
