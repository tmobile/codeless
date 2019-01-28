package com.tmobile.ct.codeless.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.tmobile.ct.codeless.core.Config;

/**
 * The Class BasicConfig.
 *
 * @author Rob Graff
 */
public class BasicConfig extends BasicSourcedDataMap implements Config{

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Config#asProperties()
	 */
	@Override
	public Properties asProperties() {
		Properties props = new Properties();
		super.keySet().forEach(key ->{
			props.put(key, super.get(key));
		});
		return props;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Config#asMap()
	 */
	@Override
	public Map<String,String> asMap(){
		HashMap<String,String> map = new HashMap<>();
		super.keySet().forEach(key ->{
			map.put(key, super.get(key).fullfill());
		});
		return map;
	}

}
