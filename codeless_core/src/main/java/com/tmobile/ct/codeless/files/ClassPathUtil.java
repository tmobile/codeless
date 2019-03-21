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
package com.tmobile.ct.codeless.files;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * The Class ClassPathUtil.
 *
 * @author Rob Graff
 */
public class ClassPathUtil {

	/**
	 * Gets the absolute path.
	 *
	 * @param file the file
	 * @return the absolute path
	 */
	public static String getAbsolutePath(String file){

		if(Boolean.parseBoolean(System.getProperty("EXEC.JAR"))){
            return System.getProperty("EXEC.ABSOLUTE.DIR") + File.separator + file;
		}

		try {
			file = file.replace("\\", "/");
			return Paths.get(ClassPathUtil.class.getResource(file).toURI()).toAbsolutePath().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Exists.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public static boolean exists(String file){

		if(Boolean.parseBoolean(System.getProperty("EXEC.JAR"))){
            return Paths.get(System.getProperty("EXEC.ABSOLUTE.DIR") + File.separator + file).toAbsolutePath().toFile().exists();
		}

		try {
			file = file.replace("\\", "/");
			return Paths.get(ClassPathUtil.class.getResource(file).toURI()).toAbsolutePath().toFile().exists();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Path.
	 *
	 * @param file the file
	 * @return the string
	 */
	public static String path(String file){
		return ClassPathUtil.getAbsolutePath(file);
	}
}
