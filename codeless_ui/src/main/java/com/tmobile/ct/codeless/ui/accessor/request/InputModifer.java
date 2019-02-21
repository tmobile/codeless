package com.tmobile.ct.codeless.ui.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.ui.action.UiAction;

public class InputModifer implements RequestModifier<String, UiAction> {

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	public InputModifer(String key, TestDataSource dataSource) {
		this.key = key;
		this.dataSource = dataSource;
	}

	@Override
	public void modify(UiAction input) {
		if(input == null)
			return;
		input.setText(dataSource.fullfill());
	}

}
