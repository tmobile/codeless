package com.tmobile.ct.codeless.test.config;

import java.util.HashMap;
import java.util.Map;

public class CloneConfig {
	
	public static Map<String, String> getConfig(Map<String, String> config) {
		Map<String, String> clonedConfig = new HashMap<>();
		for (String propKey : config.keySet()) {
			String propValue = config.get(propKey);
			clonedConfig.put(propKey, propValue);
		}

		return clonedConfig;
	}

}
