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
package com.tmobile.selenium.sam.action.log;

/**
 * The Class UiActionLogContainer.
 *
 * @author Sai Chandra Korpu
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.UiActionLog;

public class UiActionLogContainer {

	private static final Logger log = LoggerFactory.getLogger(UiActionLogContainer.class);
	
	private List<UiActionLog> uiActionLogs = new ArrayList<>();
	private AtomicInteger order = new AtomicInteger();
	
	public UiActionLogContainer(){
	}
	
	public List<UiActionLog> getUiAcitonLogs() {
		return uiActionLogs;
	}

	public void setUiAcitonLogs(List<UiActionLog> uiAcitonLogs) {
		this.uiActionLogs = uiAcitonLogs;
	}
	
	public void addUiActionLog(UiActionLog uiActionLog){
		uiActionLog.setOrder(order.getAndIncrement());
		uiActionLogs.add(uiActionLog);
		log.info("uiActionLog[{}]",uiActionLog);
	}
}