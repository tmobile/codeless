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
package com.tmobile.ct.codeless.service.model.postman.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class PostmanVariables.
 *
 * @author Rob Graff
 */
public class PostmanVariables {
	
	/** The Constant POSTMAN_EXP. */
	public static final String POSTMAN_EXP = "\\{\\{[^\\}]+\\}\\}";
	
	/** The Constant GUID. */
	public static final String GUID = "{{$guid}}";
	
	/** The Constant TIMESTAMP. */
	public static final String TIMESTAMP = "{{$timestamp}}";
	
	/** The Constant RANDOMINT. */
	public static final String RANDOMINT = "{{$randomInt}}";
	
	/** The r. */
	private Random r = new Random(1000);
	
	/** The env. */
	private PostmanEnvironment env;

	/**
	 * Instantiates a new postman variables.
	 *
	 * @param env the env
	 */
	public PostmanVariables(PostmanEnvironment env) {
		this.env = env;
	}

	/**
	 * Gets the constant val.
	 *
	 * @param exp the exp
	 * @return the constant val
	 */
	private String getConstantVal(String exp) {
		if (exp.equalsIgnoreCase(GUID)) {
			return UUID.randomUUID().toString();
		} else if (exp.equalsIgnoreCase(TIMESTAMP)) {
			return Long.toString(System.currentTimeMillis() / 1000);
		} else if (exp.equalsIgnoreCase(RANDOMINT)) {
			return Integer.toString(r.nextInt(1000));
		} else {
			throw new IllegalArgumentException(
					"Invalid POSTMAN dynamic variable " + exp);
		}
	}
	
	/**
	 * Gets the val.
	 *
	 * @param name the name
	 * @return the val
	 */
	private String getVal(String name) {
		if (name.startsWith("{{$")) {
			return getConstantVal(name);
		}
		
		String key = name.substring(2, name.length()-2);
		PostmanEnvValue val = this.env.lookup.get(key);
		if (val == null) {
			//throw new IllegalArgumentException("Invalid dynamic variable: " + name);
			return "UNDEFINED";
		}
		return val.value;
	}

	/**
	 * Replace all {{dynamic variable}} in orig string with values
	 * found in the environment. If variable is not found, replace it
	 * with constant string "UNDEFINED".
	 *
	 * @param orig the orig
	 * @return the string
	 */
	public String replace(String orig) {
		if (orig == null || orig.isEmpty()) {
			return orig;
		}
		// Get all the dynamic variables
		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile(POSTMAN_EXP).matcher(orig);
		while (m.find()) {
			allMatches.add(m.group());
		}

		//TODO: this is not the most efficient way to do it
		// but it is the simplest in term of code and this is not
		// production code anyway.
		String result = orig;
		for (String var : allMatches) {
			String varVal = getVal(var);
			//System.out.println(var + " ==> " + varVal);
			result = result.replace((CharSequence) var, (CharSequence) varVal);
		}
		
		return result;
	}
	
	/**
	 * Gets the env.
	 *
	 * @return the env
	 */
	public PostmanEnvironment getEnv() {
		return env;
	}
}
