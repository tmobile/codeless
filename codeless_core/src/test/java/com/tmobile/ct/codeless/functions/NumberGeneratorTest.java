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

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class NumberGeneratorTest {
    @Test
    public void testNumberGenerator(){
        NumberGenerator numberGenerator = new NumberGenerator();
        BigInteger number;
        for (int i = 0;i<500;i++) {
            number = numberGenerator.generate(10);
            Assert.assertNotNull(number);
            int comp = number.compareTo(new BigInteger("999999999"));   //lower bound
            int comp2 = number.compareTo(new BigInteger("10000000001"));    //upper obund
            Assert.assertTrue(comp == 1);       //assert greater than min range
            Assert.assertTrue(comp2 == -1);     //assert smaller than max range
        }
    }

    @Test
    public void testException(){
        NumberGenerator gen = new NumberGenerator();
        try{
            gen.generate("4000","500");
        }catch(IllegalArgumentException e)
        {
            return;
        }
        Assert.fail("Illegal Argument Exception was not thrown");
    }
}
