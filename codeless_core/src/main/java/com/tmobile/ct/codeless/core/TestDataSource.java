package com.tmobile.ct.codeless.core;

public interface TestDataSource {

	String fullfill();

	void setAccessor(Accessor accessor);

	Accessor getAccessor();

}
