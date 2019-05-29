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
 *****************************************************************************/
package com.tmobile.ct.codeless.functions;

import com.tmobile.ct.codeless.core.config.Config;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class that checks for functions
 * @author Julio Zevallos
 */
public class CheckFunction {

    public String parse(String value){
        String[] functions = StringUtils.substringsBetween(value, Config.FUNCTION_START,Config.FUNCTION_END);
        if (functions != null && functions.length > 0){
            for (String function: functions) {
                String [] params = function.split("::");
                String newVal = "";
                if (function.contains(Config.FUNCTION_RANDOM_NUMBER)){
                    NumberGenerator numberGenerator = new NumberGenerator();
                    if (params.length == 2){
                        newVal = numberGenerator.generate(Integer.parseInt(params[1])).toString();
                    }
                    else if (params.length == 3){
                        newVal = numberGenerator.generate(params[1],params[2]).toString();
                    }
                }
                else if (function.contains(Config.FUNCTION_TIMESTAMP)){
                    TimeStamp timeStamp = new TimeStamp();
                    if (params.length == 2)
                        newVal = timeStamp.current(params[1]);
                    else if (params.length == 3)
                        newVal = timeStamp.generate(params[1],params[2]);
                    else if (params.length == 4)
                        newVal = timeStamp.generate(params[1],params[2],params[3]);
                }
                String replace = "\\(#" + function + "\\)";
                value = value.replaceFirst(replace,newVal);
            }
        }
        return value;
    }
}
