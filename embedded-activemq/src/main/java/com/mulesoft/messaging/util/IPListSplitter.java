package com.mulesoft.messaging.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPListSplitter {

	private static final Logger logger = LoggerFactory.getLogger(IPListSplitter.class);

	public String[] getList(String ipList) {
		String[] emptyList = { "" };
		String[] ipWhitleist = ipList == null ? emptyList : ipList.split(",");
		logger.info("IP withelist: " + Arrays.asList(ipWhitleist));
		return ipWhitleist;
	}

}
