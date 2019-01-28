package com.tmobile.ct.codeless.ui.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;

public class AssertionModifer implements RequestModifier<String, UiAssertionBuilder> {

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	public AssertionModifer(String key, TestDataSource dataSource) {
		this.key = key;
		this.dataSource = dataSource;
	}

	@Override
	public void modify(UiAssertionBuilder input) {
		if(input == null)
			return;
		input.setExpectedValue(dataSource.fullfill());
	}

}
