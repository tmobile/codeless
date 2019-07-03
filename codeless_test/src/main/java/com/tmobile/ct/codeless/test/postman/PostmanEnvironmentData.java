/*******************************************************************************
 * * Copyright 2019 T Mobile, Inc. or its affiliates. All Rights Reserved.
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
package com.tmobile.ct.codeless.test.postman;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.test.testdata.TestDataReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PostmanEnvironmentData.
 *
 * @author Rob Graff
 */
public class PostmanEnvironmentData {

	private static Logger log = LoggerFactory.getLogger(PostmanEnvironmentData.class);

	private TestData testData;

	private Suite suite;

	private DataFormatter formatter = new DataFormatter();

	private ObjectMapper om;

	public PostmanEnvironmentData(Suite suite, TestData testData) {
		super();
		this.suite = suite;
		this.testData = testData;
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public void parsePostmanEnvironment(String path) {
		// read in json, turn it into a json object and pass it to "create"
		File environmentCollection = new File(ClassPathUtil.getAbsolutePath(path));
		try {
			Environment environmentData = om.readValue(environmentCollection, Environment.class);
			createTestData(environmentData, path);
		} catch (IOException ex) {
			log.error("Exception reading postman environment collection.");
			log.error(ex.getMessage());
		}
	}

	private void createTestData(Environment environmentData, String source) {

		for (Value value : environmentData.getValues()) {
			TestDataReader.createStaticTestData(value.getKey(), value.getValue(), testData, source);
		}
	}
}
