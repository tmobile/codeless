package com.tmobile.ct.codeless.test;

import com.tmobile.ct.codeless.files.ClassPathUtil;

/**
 * The Class TestManager.
 *
 * @author Rob Graff
 */
public class TestManager {

	/**
	 * Builds the suite.
	 *
	 * @param resource the resource
	 */
	public void buildSuite(String resource){
		ClassPathUtil.getAbsolutePath(resource);
	}
}
