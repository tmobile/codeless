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
package com.tmobile.ct.codeless.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class CodelessConfiguration.
 *
 * @author Rob Graff
 */
public final class CodelessConfiguration {
	
	/** The model dir. */
	private static String modelDir = File.separator + "models";
	
	/** The suite dir. */
	private static String suiteDir = File.separator + "suites";

	/**The testdata dir. */
	private static String testDataDir = File.separator + "testdata";

	private static Properties properties;

	/**
	 * Load.
	 */
	public static void load() {
		 properties = new Properties();

		try (InputStream configStream = new FileInputStream("." + File.separator + "codeless.config.properties")){
			properties.load(configStream);
			modelDir = properties.getProperty("model.dir", File.separator + "model");
			suiteDir = properties.getProperty("suites.dir", File.separator + "suites");
			testDataDir = properties.getProperty("testdata.dir",File.separator + "testdata");
		} catch (Exception e) {
			// No config file present, default
			System.out.println("************************************************************************");
			System.out.println("No valid codeless.config.properties file found, defaulting configuration");
			System.out.println("************************************************************************");
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		CodelessConfiguration.properties = properties;
	}

	/**
	 * Gets the model dir.
	 *
	 * @return the model dir
	 */
	public static String getModelDir() {
		return modelDir;
	}

	/**
	 * Gets the suite dir.
	 *
	 * @return the suite dir
	 */
	public static String getSuiteDir() {
		return suiteDir;
	}

	/**
	 * Gets the testdata dir.
	 * @return the testdata dir
	 */
	public static String getTestDataDir() {
		return testDataDir;
	}
}
