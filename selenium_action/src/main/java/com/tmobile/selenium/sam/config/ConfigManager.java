/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.selenium.sam.config;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.types.WaitType;

/**
 * The Class ConfigManager.
 *
 * @author Rob Graff
 */
public class ConfigManager {

	/** The Constant DEFAULT_PROP_FILE. */
	private static final String DEFAULT_PROP_FILE = "sam-config.yaml";
	
	/** The config container. */
	private static ThreadLocal<SamConfig> configContainer = new ThreadLocal();
	
	/** The master config. */
	private static SamConfig masterConfig;
	
	/** The master action config. */
	private static ActionConfig masterActionConfig;

	/** The mapper. */
	private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	/**
	 * Inits the.
	 */
	private static void init() {

		SamConfig samConfigDefault = null;
		try (InputStream systemResource = ConfigManager.class.getClassLoader().getResourceAsStream(DEFAULT_PROP_FILE)) {

			if (systemResource != null) {
				samConfigDefault = mapper.readValue(systemResource, SamConfig.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		SamConfig userConfig = null;
		try {
			File userFile = Paths.get(System.getProperty("user.dir"), DEFAULT_PROP_FILE).toFile();
			if (userFile != null && userFile.exists()) {
				userConfig = mapper.readValue(userFile, SamConfig.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Properties properties = null;

		try {
			properties = CodelessConfiguration.getProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}

		masterConfig = mergeConfigSources(samConfigDefault, userConfig,properties);
	}

	/**
	 * Merge config sources.
	 *
	 * @param samConfigDefault the sam config default
	 * @param userConfig the user config
	 * @return the sam config
	 */
	public static SamConfig mergeConfigSources(SamConfig samConfigDefault, SamConfig userConfig, Properties globalproperties) {

		if (samConfigDefault == null) {
			samConfigDefault = new SamConfig();
		}

		if (userConfig != null) {
			samConfigDefault.merge(userConfig);
		}

		if (globalproperties != null) {
			samConfigDefault = samConfigDefault.mergeGlobalProperties(samConfigDefault,globalproperties);
		}
		return samConfigDefault;
	}

	/**
	 * Gets the action config.
	 *
	 * @return the action config
	 */
	public static ActionConfig getActionConfig() {
		if(masterActionConfig == null){
			initActionConfig();
		}
		return masterActionConfig;
	}
	
	/**
	 * Inits the action config.
	 */
	public static void initActionConfig(){
		ActionConfig config = new ActionConfig();
		SamConfig masterConfig = getMasterConfig();
		config.type = ActionType.valueOf(masterConfig.action.get("actionType"));
		config.message = masterConfig.action.get("message");
		config.waitType = WaitType.valueOf(masterConfig.action.get("waitType"));
		config.waitTime = Integer.valueOf(masterConfig.action.get("waitTime"));
		config.screenshot = Boolean.valueOf(masterConfig.action.get("screenshot"));
		config.optional = Boolean.valueOf(masterConfig.action.get("optional"));
		
		config.clickType = ClickType.valueOf(masterConfig.action.get("clickType"));
		config.selectType = SelectType.valueOf(masterConfig.action.get("selectType"));
		config.sendKeysType = SendKeysType.valueOf(masterConfig.action.get("sendKeysType"));
		config.sendKeysDelay = Long.valueOf(masterConfig.action.get("sendKeysDelay"));
		
		masterActionConfig = config;
		
	}
	
	/**
	 * Gets the master config.
	 *
	 * @return the master config
	 */
	private static SamConfig getMasterConfig(){
		if (masterConfig == null){
			init();
		}
		return masterConfig;
	}
}
