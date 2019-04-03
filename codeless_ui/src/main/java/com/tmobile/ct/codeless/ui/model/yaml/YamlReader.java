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
package com.tmobile.ct.codeless.ui.model.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.ui.model.ControlElement;

import javax.annotation.Resource;

/**
 * The Class YamlReader.
 *
 * @author Rob Graff
 */
public class YamlReader {

	/** The Controls. */
	private static Map<String, ControlElement> Controls;

	/**
	 * Instantiates a new yaml reader.
	 */
	private YamlReader() {};

	/**
	 * Read control.
	 *
	 * @param basePath the model name
	 * @return the map
	 */
	public static Map<String, ControlElement> ReadControl(String basePath) {


		return	initializeYaml(basePath);

	}

	/**
	 * Initialize yaml.
	 *
	 * @param basePath the basepath
	 * @return the map
	 */
	private static Map<String, ControlElement> initializeYaml(String basePath) {
		Yaml yaml = new Yaml();
		Map<String, Map<String, String>> mapyaml = null;
		Map<String, ControlElement> yamldata = new HashMap<String, ControlElement>();
		ControlElement ctrlElem = null;
		try {
			File path = new File(ClassPathUtil.path(basePath));
			if (path.exists()) {
				InputStream ios = new FileInputStream(path);
				mapyaml = (Map<String, Map<String, String>>) yaml.load(ios);
				if (mapyaml != null) {

					for (Map.Entry<String, Map<String, String>> itEntry : mapyaml.entrySet()) {
						Map<String, String> ctrlData = itEntry.getValue();
						if (ctrlData != null) {
							ctrlElem = new ControlElement();
							if (ctrlElem != null) {
								ctrlElem.setBy(ctrlData.get("by"));
								ctrlElem.setLocator(ctrlData.get("locator"));
							}
							yamldata.put(itEntry.getKey(), ctrlElem);
						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Controls = yamldata;
		return Controls;
	}

}
