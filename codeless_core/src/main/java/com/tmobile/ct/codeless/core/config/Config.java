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
package com.tmobile.ct.codeless.core.config;

public class Config {

	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int Three = 3;
	public static final int Four = 4;
	public static final int Five = 5;
	public static final String Export = "export";
	public static final String Assert = "assert";
	public static final String OVERRIDE_INPUT_START = "{{";
	public static final String OVERRIDE_INPUT_END = "}}";
	public static final String EMPTY = "";

	public static final String TESTDATA_FILENAME = "testdata.filename";
	public static final String TESTDATA_SHEETNAME = "testdata.sheetname";
	public static final String EXCEL_EXTENSION = ".xlsx";
	public static final String CSV_EXTENSION = ".csv";
	public static final String ENV_TESTDATA_SHEETNAME = "ENV_TESTDATA_SHEET";
	public static final String DEFAULT_TEST_DATA = "DATASHEET";

	public static final String UI_ACTION_LOG_ENABLE = "uiactionlog.details.enabled";
	public static final String LOGGING_DETAILS_ENABLED = "logging.details.enabled";

	public static final String TEST_SCREENSHOT_POLICY = "test.screenshot.policy";
	public static final String FAILURE_ONLY = "FAILUREONLY";
	public static final String ALL_STEPS = "ALLSTEPS";
	
	public static final String TESTDATA = "testData";
	public static final String TCDS = "tcds";
	
	public static final String TEST_RUN_PARALLEL = "test.runparallel";
	public static final String TEST_RUN_PARALLEL_THREADCOUNT = "test.runparallel.threadcount";
	
	public static final String PLATFORM_TYPE = "platform-type";
	public static final String WEBDRIVER_RUN_LOCAL = "webdriver.runlocal";

	public static final String BUILDMODE = "buildMode";
}
