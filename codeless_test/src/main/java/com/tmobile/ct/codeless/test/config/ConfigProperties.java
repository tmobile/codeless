package com.tmobile.ct.codeless.test.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Optional;
import com.tmobile.ct.codeless.configuration.CodelessConfiguration;

public class ConfigProperties {

	public static Map<String, String> getProperties() {

		Properties properties = CodelessConfiguration.getProperties();
		Enumeration<Object> keys = properties.keys();
		Map<String, String> config = new HashMap<>();

		while (keys.hasMoreElements()) {

			String key = keys.nextElement().toString();
			String value = Optional.fromNullable(properties.getProperty(key))
					.or(com.tmobile.ct.codeless.core.config.Config.EMPTY);
			config.put(key, value);
		}

		return config;
	}

}
