package com.tmobile.ct.codeless.testdata;

import com.tmobile.ct.codeless.core.Accessor;

/**
 * The StaticTestDataSource.
 *
 * @author Fikreselam Elala
 */

import com.tmobile.ct.codeless.core.TestDataSource;

public class StaticTestDataSource implements TestDataSource {

	private String key;
	private String value;
	private Accessor accessor;

	public StaticTestDataSource(String key, String value) {
		super();
		this.value = value;
	}

	@Override
	public String fullfill() {
		return value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void setAccessor(Accessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public Accessor getAccessor() {
		return accessor;
	}

}
