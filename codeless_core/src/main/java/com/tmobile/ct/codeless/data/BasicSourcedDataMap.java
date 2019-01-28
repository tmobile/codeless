package com.tmobile.ct.codeless.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.tmobile.ct.codeless.core.SourcedData;
import com.tmobile.ct.codeless.core.TestDataSource;


/**
 * The Class BasicSourcedDataMap.
 *
 * @author Rob Graff
 */
public class BasicSourcedDataMap extends HashMap<String, SourcedDataItem<String,TestDataSource>> implements SourcedData{

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#getConfig()
	 */
	@Override
	public synchronized Map<String, SourcedDataItem<String,TestDataSource>> getConfig() {
		return this;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#setConfig(java.util.Map)
	 */
	@Override
	public synchronized void setConfig(Map<String, SourcedDataItem<String,TestDataSource>> config) {
		this.clear();
		this.putAll(config);
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public SourcedDataItem<String,TestDataSource> put(String key, SourcedDataItem<String,TestDataSource> item) {
		return super.put(key, item);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#get(java.lang.String)
	 */
	@Override
	public TestDataSource get(String key) {
		return super.get(key).getValue().getValue();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#getSourcedValue(java.lang.String)
	 */
	@Override
	public SourcedDataItem<String, TestDataSource> getSourcedValue(String key) {
		return super.get(key);
	}

	@Override
	public Optional<String> getOptional(String key) {
		return Optional.ofNullable(Optional.ofNullable(super.get(key)).map(x -> x.getValue().getValue().fullfill()).orElse(null));
	}
}
