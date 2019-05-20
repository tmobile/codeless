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
package com.tmobile.ct.codeless.service.model.postman;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanCollection;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanEnvironment;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The Class PostmanReader.
 *
 * @author Rob Graff
 */
public class PostmanReader {
	
	/** The om. */
	ObjectMapper om;
	
	/**
	 * Instantiates a new postman reader.
	 */
	public PostmanReader() {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * Read collection file classpath.
	 *
	 * @param fileOnClasspath the file on classpath
	 * @return the postman collection
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PostmanCollection readCollectionFileClasspath(String fileOnClasspath) throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileOnClasspath.substring(fileOnClasspath.indexOf(":")+1);
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		
		PostmanCollection collection = om.readValue(stream, PostmanCollection.class);
		stream.close();
		return collection;
	}
	
	/**
	 * Read environment file classpath.
	 *
	 * @param fileOnClasspath the file on classpath
	 * @return the postman environment
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PostmanEnvironment readEnvironmentFileClasspath(String fileOnClasspath) throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileOnClasspath.substring(fileOnClasspath.indexOf(":")+1);
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		
		PostmanEnvironment env = om.readValue(stream, PostmanEnvironment.class);
		stream.close();
		return env;
	}
	
	/**
	 * Read collection file.
	 *
	 * @param filePath the file path
	 * @return the postman collection
	 * @throws Exception the exception
	 */
	public PostmanCollection readCollectionFile(String filePath) throws Exception {
		if (filePath.startsWith("classpath:")) {
			return readCollectionFileClasspath(filePath);
		}

		InputStream stream = new FileInputStream(new File(filePath));
		PostmanCollection collection = om.readValue(stream, PostmanCollection.class);
		stream.close();
		return collection;
	}

	/**
	 * Read environment file.
	 *
	 * @param filePath the file path
	 * @return the postman environment
	 * @throws Exception the exception
	 */
	public PostmanEnvironment readEnvironmentFile(String filePath) throws Exception {
		if (filePath == null) {
			return new PostmanEnvironment();
		}
		if (filePath.startsWith("classpath:")) {
			return readEnvironmentFileClasspath(filePath);
		}
		InputStream stream = new FileInputStream(new File(filePath));
		PostmanEnvironment env = om.readValue(stream, PostmanEnvironment.class);
		stream.close();
		return env;
	}
}
