/*******************************************************************************
 * * Copyright 2019 T Mobile, Inc. or its affiliates. All Rights Reserved.
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
package com.tmobile.ct.codeless.test.postman;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private String id;
    private String name;
    private List<Value> values = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value getValue(String valueName) {
        if (values.indexOf(valueName) == -1) {
            return null;
        }
        
        return values.get(values.indexOf(valueName));
    }

    public List<Value> getValues() {
        return values;
    }

    public void addValue(Value value) {
        this.values.add(value);
    }

    public void replaceValue(Value value) {
        if (values.contains(value.getKey())) {
            removeValue(value.getKey());
        }

        addValue(value);
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void removeValue(String valueKey) {
        this.values.remove(valueKey);
    }
}
