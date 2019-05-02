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
package com.tmobile.ct.codeless.core;

import java.io.Serializable;

public class UiActionLog implements Serializable {

    private Long id;

    private Integer order;

    private UiAction uiAction;
    
    private Integer duration;

    private String data;
    
    public UiActionLog() {}

	public UiActionLog(UiAction uiAction, String data, Integer duration) {

		this.uiAction = uiAction;
		this.duration = duration;
		this.data = data;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public UiAction getAction() {
		return uiAction;
	}

	public void setAction(UiAction uiAction) {
		this.uiAction = uiAction;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "id=" + id + ", order=" + order + ", uiAction=" + uiAction + ", duration=" + duration
				+ ", data=" + data;
	}
}