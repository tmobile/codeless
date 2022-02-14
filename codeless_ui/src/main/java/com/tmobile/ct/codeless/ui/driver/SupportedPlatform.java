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
package com.tmobile.ct.codeless.ui.driver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Represents the list of platforms that are supported for testing. Represents all the platforms like iPhone, android
 * etc. and browser types like safari, firefox etc.
 *
 * @author Fikreselam Elala
 */
public enum SupportedPlatform {

    /** The iPhone. */
    iPhone(new DesiredCapabilities(),  RemoteWebDriver.class),

    /** The iPad. */
    iPad(new DesiredCapabilities(),  RemoteWebDriver.class),

    /** The android. */
    android(new DesiredCapabilities(),  RemoteWebDriver.class),

    /** The chrome. */
    chrome(new DesiredCapabilities(), RemoteWebDriver.class),

    /** The firefox. */
    firefox(new DesiredCapabilities(), RemoteWebDriver.class),

    /** The edge. */
    edge(new DesiredCapabilities(), RemoteWebDriver.class),

    /** The opera blink. */
    opera(new DesiredCapabilities(),RemoteWebDriver.class),

    /** The safari. */
    safari(new DesiredCapabilities(),  RemoteWebDriver.class),

    /** The default remote. */
    defaultRemote(new DesiredCapabilities(), RemoteWebDriver.class);


    /** The hub enabled driver. */
    private final Class<? extends WebDriver> hubEnabledDriver;

    /** The platform name. */
    private final String platformName;

    /** The capabilities. */
    private final DesiredCapabilities capabilities;

    /**
     * Initialize with platform name.
     *
     * @param template the template
     * @param hubEnabledDriverClass the hub enabled driver class
     */
    private SupportedPlatform(DesiredCapabilities template,
            Class<? extends WebDriver> hubEnabledDriverClass) {
        this.platformName = name();
        this.capabilities = template;
        this.hubEnabledDriver = hubEnabledDriverClass;

    }

    /**
     * Returns the platform matching the platform name. If not driver of the specified platform name is found then
     * remote web driver is returned.
     *
     * @param platformName            name of the platform.
     * @return the supported platform
     */
    public static SupportedPlatform findFor(String platformName) {
        SupportedPlatform retPlatform = SupportedPlatform.defaultRemote;
        for (SupportedPlatform platform : SupportedPlatform.values()) {
            if (platform.platformName.equals(platformName)) {
                retPlatform = platform;
                break;
            }
        }
        return retPlatform;
    }

    /**
     * Returns the names of all the supported platform.
     * @return list of names.
     */
    public static List<String> names() {
    	List<String> names = new ArrayList<String>();
    	for (SupportedPlatform platform : SupportedPlatform.values()) {
    		names.add(platform.name());
    	}
    	return names;
    }

    /**
     * Clones the desired capabilities from the template.
     *
     * @return the desired capabilities
     */
    public DesiredCapabilities createCapabilities() {
        return new DesiredCapabilities(capabilities.asMap());
    }

    /**
     * Gets the driver class.
     *
     * @return the driverClass
     */
    public Class<? extends WebDriver> getDriverClass() {
        return hubEnabledDriver;
    }

    /**
     * Gets the platform name.
     *
     * @return the platformName
     */
    public String getPlatformName() {
        return platformName;
    }
}
