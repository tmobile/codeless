package com.tmobile.ct.codeless.test.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.plugin.ConfigPluginParser;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public class PluginBuilder implements ConfigPluginParser{

	private static final String PLUGINS_KEY = "codeless.plugins";
	
	@Override
	public List<Plugin> getPlugins(Config config) {
		Optional<String> plugins = config.getOptional(PLUGINS_KEY);
		return plugins.map(x -> parsePluginString(x))
			.map(x -> instanciatePlugins(x))
			.orElse(null);
	}
	
	public List<Plugin> instanciatePlugins(List<String> classes){
		List<Plugin> plugins = new ArrayList<>(classes.size());
		for(String clazz : classes){
			try{
				Class<?> obj = Class.forName(clazz);
				
				if(ClassUtils.isAssignable(obj, Plugin.class)){
					plugins.add((Plugin) obj.newInstance());
				}
			}catch(Exception e){
				
			}
		}
		return plugins;
	}

	public List<String> parsePluginString(String plugins){
		String[] classes = plugins.split(",");
		return Arrays.asList(classes).stream()
				.map(String::trim)
				.collect(Collectors.toList());
	}
}
