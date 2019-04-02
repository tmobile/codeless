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
package com.tmobile.selenium.sam.action.actions;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Interface IAction.
 *
 * @author Rob Graff
 */
public interface IAction {
	
	/**
	 * Type.
	 *
	 * @return the action type
	 */
	ActionType type();
	
	/**
	 * Pre.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean pre() throws Exception;
	
	/**
	 * Post.
	 *
	 * @return true, if successful
	 */
	boolean post();
	
//	boolean execute();
	
	/**
 * Main action.
 *
 * @throws Exception the exception
 */
void mainAction() throws Exception;
	
}
