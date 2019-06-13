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

import org.apache.commons.lang3.StringUtils;

/**
 * The Class that checks for functions
 * @author Julio Zevallos
 */
public class CheckFunction {

    public static final String FUNCTION_START = "(#";
    public static final String FUNCTION_END = ")";
    public static final String FUNCTION_RANDOM_NUMBER = "randNum";
    public static final String FUNCTION_TIMESTAMP = "timeStamp";
    public static final String FUNCTION_RANDOM_ALPHANUMERIC = "randAlphaNum";
    public static final String FUNCTION_RANDOM_NAME = "randName";
    public static final String FUNCTION_RANDOM_DOB = "randDOB";

    public String parse(String value){
        String[] functions = StringUtils.substringsBetween(value, FUNCTION_START,FUNCTION_END);
        if (functions != null && functions.length > 0){
            for (String function: functions) {
                String [] params = function.split("\\|\\|");
                String newVal = "";
                String replaceFunction = "";
                if (params != null || params.length > 0){
                    for (int i=0;i<params.length;i++){
                        replaceFunction += params[i] + "\\|\\|";
                    }
                }
                replaceFunction = replaceFunction.substring(0,replaceFunction.length()-4);
                if (function.contains(FUNCTION_RANDOM_NUMBER)){
                    NumberGenerator numberGenerator = new NumberGenerator();
                    if (params.length == 2){
                        newVal = numberGenerator.generate(Integer.parseInt(params[1])).toString();
                    }
                    else if (params.length == 3){
                        newVal = numberGenerator.generate(params[1],params[2]).toString();
                    }
                }
                else if (function.contains(FUNCTION_TIMESTAMP)){
                    TimeStamp timeStamp = new TimeStamp();
                    if (params.length == 2)
                        newVal = timeStamp.current(params[1]);
                    else if (params.length == 3)
                        newVal = timeStamp.generate(params[1],params[2]);
                    else if (params.length == 4)
                        newVal = timeStamp.generate(params[1],params[2],params[3]);
                }
                else if (function.contains(FUNCTION_RANDOM_ALPHANUMERIC)){
                    AlphaNumericGenerator alphaNumericGenerator;
                    if (params.length == 2){
                        try {           //if parameter is size
                            int size = Integer.parseInt(params[1]);
                            alphaNumericGenerator = new AlphaNumericGenerator(size);
                            newVal = alphaNumericGenerator.generate();
                        }catch (NumberFormatException e){       //if parameter is format
                            alphaNumericGenerator = new AlphaNumericGenerator(params[1]);
                            newVal = alphaNumericGenerator.generate();
                        }
                    }
                }
                else if (function.contains(FUNCTION_RANDOM_NAME)){
                    NameGenerator nameGenerator = new NameGenerator();
                    if (params.length == 1)
                        newVal = nameGenerator.randomFullName();
                    else if (params.length == 2){
                        newVal = nameGenerator.generate(params[1]);
                    }
                }
                else if (function.contains(FUNCTION_RANDOM_DOB)){
                    DOBGenerator dobGenerator = new DOBGenerator();
                    switch (params.length){
                        case 1:
                            newVal = dobGenerator.generate();
                            break;
                        case 2:
                            try {
                                int age = Integer.parseInt(params[1]);
                                newVal = dobGenerator.generate(age);
                            }catch (NumberFormatException e){
                                newVal = dobGenerator.generate(params[1]);
                            }
                            break;
                        case 3:
                            try {
                                int min = Integer.parseInt(params[1]);
                                int max = Integer.parseInt(params[2]);
                                newVal = dobGenerator.generate(min,max);
                            }catch (NumberFormatException e){
                                int age = Integer.parseInt(params[2]);
                                newVal = dobGenerator.generate(params[1],age);
                            }
                            break;
                        case 4:
                            int min = Integer.parseInt(params[2]);
                            int max = Integer.parseInt(params[3]);
                            newVal = dobGenerator.generate(params[1],min,max);
                            break;
                        default:
                            throw new RuntimeException("Invalid number of function parameters");
                    }
                }

                String replace = "\\(#" + replaceFunction + "\\)";
                value = value.replaceFirst(replace,newVal);
            }
        }
        return value;
    }
}
