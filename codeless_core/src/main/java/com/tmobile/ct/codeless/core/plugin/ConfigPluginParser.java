package com.tmobile.ct.codeless.core.plugin;

import java.util.List;

import com.tmobile.ct.codeless.core.Config;

public interface ConfigPluginParser {

	List<Plugin> getPlugins(Config config);
}
