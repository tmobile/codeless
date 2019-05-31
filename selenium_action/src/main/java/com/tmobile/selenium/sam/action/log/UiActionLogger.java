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

import java.util.List;
import com.tmobile.ct.codeless.core.UiActionLog;

/**
 * The Class UiActionLogger.
 *
 * @author Sai Chandra Korpu
 */
public class UiActionLogger {
		
		private static ThreadLocal<UiActionLogContainer> bucket = new InheritableThreadLocal<>();
		
		private UiActionLogger(){}
		
		private static UiActionLogContainer getBucket(){
			if(null == bucket.get()){
				bucket.set(new UiActionLogContainer());
			}
			return bucket.get();
		}
		
		public static void init(){
			bucket.remove();
			bucket.set(new UiActionLogContainer());
		}
		
		public static void add(UiActionLog uiActionLog){
			getBucket().addUiActionLog(uiActionLog);
		}
		
		public static List<UiActionLog> get(){
			return getBucket().getUiActionLogs();
		}
		
		public static void destroy(){
			bucket.remove();
		}

}
