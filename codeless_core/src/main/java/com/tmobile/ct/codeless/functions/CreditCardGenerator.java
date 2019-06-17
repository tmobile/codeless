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

import java.security.SecureRandom;

public class CreditCardGenerator {

    private String type = null;
    private static String types[] = {"AMEX","VISA","MASTERCARD","DISCOVER","JCB"};
    private static char all[] = {'0','1','2','3','4','5','6','7','8','9'};


    public CreditCardGenerator(){
        this.type = null;
    }

    public CreditCardGenerator(String type){
        this.type = type.toUpperCase();
    }

    public String generate(){
        String number = "";
        if (this.type == null){
            this.type = types[new SecureRandom().nextInt(types.length)];
        }
        switch (this.type){
            case "AMEX":
                char[] possible = {'4','7'};
                number += "3" + possible[new SecureRandom().nextInt(possible.length)];
                number += new NumberGenerator().generate(12);
                number += generateCheckDigit(number);
                return number;
            case "VISA":
                number += "4" + all[new SecureRandom().nextInt(all.length)];
                break;
            case "MASTERCARD":
                number += "5" + all[new SecureRandom().nextInt(all.length)];
                break;
            case "DISCOVER":
                number += "6" +all[new SecureRandom().nextInt(all.length)];
                break;
            case "JCB":
                number += "35";
                break;
        }
        number += new NumberGenerator().generate(13);
        number += generateCheckDigit(number);
        return number;
    }

    private int generateCheckDigit(String number) {
        int[] ints = new int[number.length()];
        for(int i = 0;i< number.length(); i++){
            ints[i] = Integer.parseInt(number.substring(i, i+1));
        }
        for(int i = ints.length-1; i>=0; i=i-2){
            int j = ints[i];
            j = j*2;
            if(j>9){
                j = j%10 + 1;
            }
            ints[i]=j;
        }
        int sum=0;
        for(int i = 0;i< ints.length; i++){
            sum+=ints[i];
        }
        if(sum%10==0){
            return 0;
        }else return 10-(sum%10);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toUpperCase();
    }
}
