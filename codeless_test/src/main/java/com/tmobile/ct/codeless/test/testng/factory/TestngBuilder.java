/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.test.testng.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.google.common.base.Optional;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.config.Config;

/**
 * The Class TestngBuilder.
 *
 * @author Sai Chandra Korpu
 */
public class TestngBuilder {

	TestNG testng = new TestNG();
	List<XmlSuite> suites = new ArrayList<>();
	
	public TestngBuilder() {
	}
	
	public TestngBuilder addSuite(XmlSuite suite){
		suites.add(suite);
		return this;
	}
	
	public TestngBuilder addSuite(Suite suite, String testName, String testClass){
		
		Map<String,String> params = suite.getConfig();
		
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName(suite.getName());

		XmlTest xmlTest = new XmlTest();
		xmlTest.setName(testName);
		xmlTest.setSuite(xmlSuite);

		List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
		xmlClasses.add(new XmlClass(testClass));

		xmlTest.getClasses().addAll(xmlClasses);
		xmlSuite.addTest(xmlTest);
		params.put(Config.CODELESS_SUITE_ID, suite.getId());		//this param is always null. suiteId is set later
		xmlSuite.setParameters(params);
		setParallelExecution(xmlSuite, params);
		
		addSuite(xmlSuite);

		return this;
	}
	
	public TestNG build(){
		testng.setXmlSuites(suites);
		return testng;
	}
	
	private void setParallelExecution(XmlSuite xmlSuite, Map<String, String> params) {
		if (params.containsKey(Config.TEST_RUN_PARALLEL)) {

			boolean runParallel = Optional.fromNullable(Boolean.parseBoolean(params.get(Config.TEST_RUN_PARALLEL)))
					.or(false);
			if (runParallel) {

				xmlSuite.setListeners(
						Arrays.asList("com.tmobile.ct.codeless.test.testng.listeners.AnnotationTransformer"));
				setThreadCount(xmlSuite, params);
			}
		}
	}

	private void setThreadCount(XmlSuite xmlSuite, Map<String, String> params) {

		if (params.containsKey(Config.TEST_RUN_PARALLEL_THREADCOUNT)) {
			String threadCount = Optional.fromNullable(params.get(Config.TEST_RUN_PARALLEL_THREADCOUNT))
					.or("2");

			if (threadCount != null) {
				xmlSuite.setDataProviderThreadCount(Integer.parseInt(threadCount));
			}
		}
	}
}
