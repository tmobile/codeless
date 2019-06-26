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

import org.junit.Test;
import org.testng.Assert;

public class StringFunctionsTest {

    @Test
    public void testStringFunctions(){
        StringFunctions stringFunctions = new StringFunctions("mystring");
        Assert.assertNotNull(stringFunctions.upperCase());
        Assert.assertNotNull(stringFunctions.lowerCase());
        Assert.assertNotNull(stringFunctions.charAt("2"));
        Assert.assertNotNull(stringFunctions.contain("my"));
        System.out.println(stringFunctions.contain("my"));
        Assert.assertNotNull(stringFunctions.compare("otherstring"));
        Assert.assertNotNull(stringFunctions.substring("3","8"));
    }
}
