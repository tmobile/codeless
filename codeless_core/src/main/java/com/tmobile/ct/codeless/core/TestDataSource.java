package com.tmobile.ct.codeless.core;

/**
 * The Class MainTest.
 *
 * @author Fikreselam Elala
 */

public interface TestDataSource {

	String fullfill();

	void setAccessor(Accessor accessor);

	Accessor getAccessor();

}
