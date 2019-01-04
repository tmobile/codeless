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
