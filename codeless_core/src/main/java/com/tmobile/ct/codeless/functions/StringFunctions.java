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
package com.tmobile.ct.codeless.functions;

import io.swagger.models.auth.In;

import java.lang.reflect.Method;

/**
 * The String functions class
 * @author Julio Zevallos
 */
public class StringFunctions {

    private String theString;

    public StringFunctions(String value){
        this.theString = value;
    }

    public String lowerCase(){
        return this.theString.toLowerCase();
    }

    public String upperCase(){
        return this.theString.toUpperCase();
    }

    public String charAt(String param){
        return "" +this.theString.charAt(Integer.valueOf(param));
    }

    public String contain(String param){
        return "" +this.theString.contains(param);
    }

    public String compare(String param){
        int result = this.theString.compareTo(param);
        if (result == 0)
            return "Equal";
        else
            return "Not Equal";
    }

    public String substring(String index1,String index2){
        return this.theString.substring(Integer.valueOf(index1), Integer.valueOf(index2));
    }

    public Method getMethod(String method) throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod(method,null);
    }

    public Method getMethod(String method, Class<?>... param) throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod(method,param);
    }



}
