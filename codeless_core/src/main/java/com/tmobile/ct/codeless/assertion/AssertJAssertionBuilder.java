package com.tmobile.ct.codeless.assertion;

import com.tmobile.ct.codeless.core.Assertion;

/**
 * The Class AssertJAssertionBuilder.
 *
 * @author Rob Graff
 */
public class AssertJAssertionBuilder implements AssertionBuilder{

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.assertion.AssertionBuilder#build(java.lang.Class)
	 */
	@Override
	public <T> Assertion<T> build(Class<T> type) {
		return new AssertJAssertion<T>();
	}

}
