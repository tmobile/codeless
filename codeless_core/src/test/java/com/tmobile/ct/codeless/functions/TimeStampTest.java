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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeStampTest {

    @Test
    public void testCurrent(){
        String format = "yyyy,MMM,dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        TimeStamp timeStamp = new TimeStamp();
        String output = timeStamp.current(format);
        Assert.assertEquals(output, sdf.format(Calendar.getInstance().getTime()));
    }
}
