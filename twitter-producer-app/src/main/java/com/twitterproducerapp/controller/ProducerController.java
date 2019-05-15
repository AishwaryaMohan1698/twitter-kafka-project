package com.twitterproducerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterproducerapp.utils.TwitterUtils;

@RestController
@RequestMapping("kafka")
public class ProducerController {

	@Autowired
	TwitterUtils twitterUtilsObject;

	@GetMapping("/publish/stream")
	public String printStream() {
		twitterUtilsObject.printStream();
		return "streaming";
	}

	@GetMapping("/publish/streamFilter")
	public String streamFilter() {
		twitterUtilsObject.streamFilter();
		return "streaming streamFilter";
	}

	@GetMapping("/publish/stopStream")
	public String stopStream() {
		twitterUtilsObject.stopStream();
		return "stream ended";
	}
}
