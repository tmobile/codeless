package com.tmobile.ct.codeless.testdata;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.core.TestDataSource;

public class TcdsTestDataSource implements TestDataSource<JSONTestData> {

	private String key;
	private JSONTestData value;

	public TcdsTestDataSource(JSONTestData value) {
		super();
		this.value = value;
	}

	@Override
	public JSONTestData fullfill() {
		return value;
	}

	@Override
	public void setAccessor(Accessor accessor) {}

	@Override
	public Accessor getAccessor() {return null;}

	@Override
	public void setValue(JSONTestData Value) {
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

}
