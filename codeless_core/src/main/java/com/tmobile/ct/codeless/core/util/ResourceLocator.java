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
package com.tmobile.ct.codeless.core.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ResourceLocator {
    /**
     * Returns the loaded resource from classpath or filesyste,
     * @param orginalFileName
     * @return
     */
    public static Optional<File> getFile(String orginalFileName) {
        File file = new File(orginalFileName);
        if (!file.exists()) {
            if (!orginalFileName.contains(".")) {
                // its a folder, create it if it doesn't exist
                file.mkdirs();
            }

            if (!file.exists()) {
                // try this way
                URL resource = ResourceLocator.class.getClassLoader().getResource(orginalFileName);
                String filePath = resource.getPath().replace("%25", "%");

                if (resource != null) {
                    file = new File(filePath);
                }
            }
        }

        return Optional.of(file).filter(currFile -> currFile.exists());
    }

    public static String getRequestFromFile(String fileLocation) throws Exception {
		File file = null;
		String filePath = fileLocation;
		try {
			file = getFile(filePath).get();
			String requestBody = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

			return requestBody;
		} catch (Exception e) {
			throw e;
		}
	}

}
