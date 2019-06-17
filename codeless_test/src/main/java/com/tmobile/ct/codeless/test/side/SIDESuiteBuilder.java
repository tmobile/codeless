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
package com.tmobile.ct.codeless.test.side;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.SuiteBuilder;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.test.config.ConfigProperties;
import com.tmobile.ct.codeless.test.side.data.SIDEFile;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;

/**
 * The Class SIDESuiteBuilder.
 *
 * @author Sai Chandra Korpu
 */
public class SIDESuiteBuilder implements SuiteBuilder {

	private static Logger logger = LoggerFactory.getLogger(SIDESuiteBuilder.class);

	private Suite suite;

	private TestData testData;

	public Suite build(String suitePath) {

		try {
			SIDEFile sideFile = SIDEFileReader.readSIDEFile(suitePath);
			suite = buildSuite(sideFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return suite;
	}

	public Suite buildSuite(SIDEFile sideFile) {
		suite = new SuiteImpl(sideFile.getSuites().get(0).getName());
		suite.setConfig(ConfigProperties.getProperties());

		sideFile.getTests().forEach(sideTest -> {
			Test test = new SIDETestBuilder().build(suite, sideTest, testData);
			suite.addTest(test);
		});
		return suite;
	}

	@Override
	public Suite getSuite() {
		return this.suite;
	}

}
