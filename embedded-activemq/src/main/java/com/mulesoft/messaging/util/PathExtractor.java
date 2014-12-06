package com.mulesoft.messaging.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathExtractor {

	private static final Logger logger = LoggerFactory.getLogger(PathExtractor.class);
	
	public String getAbsolutePath(String resource) throws IOException {
		String absolutePath = this.getClass().getResource(resource).getPath();
		logger.info("Set resource: " + absolutePath);
		return absolutePath;
	}
	
}
