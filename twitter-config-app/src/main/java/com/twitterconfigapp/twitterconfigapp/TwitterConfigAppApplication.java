package com.twitterconfigapp.twitterconfigapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TwitterConfigAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterConfigAppApplication.class, args);
	}

}
