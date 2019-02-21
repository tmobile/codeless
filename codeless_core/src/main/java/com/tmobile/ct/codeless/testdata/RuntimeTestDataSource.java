package com.tmobile.ct.codeless.testdata;

import com.tmobile.ct.codeless.core.Accessor;

/**
 * The RuntimeTestDataSource.
 *
 * @author Fikreselam Elala
 */

import com.tmobile.ct.codeless.core.TestDataSource;

public class RuntimeTestDataSource implements TestDataSource {

	private Accessor accessor;

	public RuntimeTestDataSource(Accessor accessor) {
		super();
		this.accessor = accessor;
	}

	@Override
	public String fullfill() {
		return accessor.getActual().toString();
	}

	public Accessor getValue() {
		return accessor;
	}

	public void setValue(Accessor value) {
		this.accessor = value;
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
