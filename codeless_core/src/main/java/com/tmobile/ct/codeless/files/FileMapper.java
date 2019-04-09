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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileMapper {
	
	private Path baseDir;
	private String qualifier;
	private ConcurrentHashMap<String,FileDotIdentifier> files = new ConcurrentHashMap<>();

	public FileMapper(Path baseDir, String qualifier) {
		this.baseDir = baseDir;
		this.qualifier = qualifier;
	}

	public ConcurrentHashMap<String, FileDotIdentifier> map() {

	    search("", "");
	    return files;
	}
	
	public void search(String directoryName, String currentDot) {
		File directory = Paths.get(baseDir.toAbsolutePath().toString(), directoryName).toFile();


		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList == null || fList.length == 0) {
			return;
		}

		for (File file : fList) {
			if (file.isFile()) {
				String key = currentDot + "." + FilenameUtils.removeExtension(file.getName());
				FileDotIdentifier fdId = new FileDotIdentifier(key, file.getAbsolutePath(),
						FilenameUtils.getExtension(file.getName()), qualifier, file.getName());

				files.put(key, fdId);
			} else if (file.isDirectory()) {
				String dot = "";
				if(StringUtils.isBlank(currentDot)) {
					dot = file.getName();
				}else {
					dot = currentDot + "." + file.getName();
				}
				search(directoryName + File.separator + file.getName(), dot);
			}
		}

	}
}
