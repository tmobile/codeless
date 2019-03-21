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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class SamConfig.
 *
 * @author Rob Graff
 */
public class SamConfig {

	/** The action. */
	public Map<String,String> action = new HashMap<>();
	
	/** The click. */
	public Map<String,String> click = new HashMap<>();
	
	/** The send. */
	public Map<String,String> send = new HashMap<>();
	
	/** The select. */
	public Map<String,String> select = new HashMap<>();
	
	/** The move. */
	public Map<String,String> move = new HashMap<>();
	
	/** The navigate. */
	public Map<String,String> navigate = new HashMap<>();
	
	/** The window. */
	public Map<String,String> window = new HashMap<>();
	
	/**
	 * Merge.
	 *
	 * @param config the config
	 * @return the sam config
	 */
	public SamConfig merge(SamConfig config){
		action.putAll(removeBlankValue(config.action));
		click.putAll(removeBlankValue(config.click));
		send.putAll(removeBlankValue(config.send));
		select.putAll(removeBlankValue(config.select));
		move.putAll(removeBlankValue(config.move));
		navigate.putAll(removeBlankValue(config.navigate));
		window.putAll(removeBlankValue(config.window));
		
		return this;
	}
	
	/**
	 * Removes the blank value.
	 *
	 * @param map the map
	 * @return the map
	 */
	public Map<String,String> removeBlankValue(Map<String,String> map){
		map.values().removeIf(StringUtils::isBlank);
		return map;
	}
}
