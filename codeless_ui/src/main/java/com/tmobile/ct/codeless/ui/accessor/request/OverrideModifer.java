package com.tmobile.ct.codeless.ui.accessor.request;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.testdata.RequestModifier;

public class OverrideModifer implements RequestModifier<String, String> {

	/** The key. */
	private String key;

	/** The response accessor. */
	private Accessor accessor;

	public OverrideModifer(String key, Accessor accessor) {
		this.key = key;
		this.accessor = accessor;
	}

	@Override
	public void modify(String input) {
		input = accessor.getActual();
	}

}
