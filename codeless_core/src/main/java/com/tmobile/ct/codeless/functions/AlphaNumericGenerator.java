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

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * The class that generates an AlphaNumeric Value
 * @author Julio Zevallos
 */
public class AlphaNumericGenerator {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    public String format = null;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;


    public AlphaNumericGenerator(int length, String symbols){
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = new SecureRandom();
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }


    public AlphaNumericGenerator(int length){
        this(length, alphanum);
    }

    public AlphaNumericGenerator(String format){
        this(format.length());
        this.format = format;
    }

    public String generate(){
        for (int i = 0;i < buf.length; ++i) {
            if (format != null){
                switch (format.charAt(i)){
                    case 'D':
                        buf[i] = digits.charAt(random.nextInt(digits.length()));
                        break;
                    case 'L':
                        buf[i] = lower.charAt(random.nextInt(lower.length()));
                        break;
                    case 'U':
                        buf[i] = upper.charAt(random.nextInt(upper.length()));
                        break;
                    case '?':
                        buf[i] = alphanum.charAt((random.nextInt(alphanum.length())));
                        break;
                    default:
                        throw new RuntimeException("Improper format value");
                }
            }
            else
                buf[i] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }
}
