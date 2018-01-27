/*
 * Copyright 2018 Elder Moreas and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jnosql.javaone.gameon.map.infrastructure;


import org.jnosql.artemis.ConfigurationReader;
import org.neo4j.driver.v1.Driver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.logging.Logger;


@ApplicationScoped
class DriverProducer {

    private static final Logger LOGGER = Logger.getLogger(DriverProducer.class.getName());

    @Inject
    private ConfigurationReader configurationReader;

    private Driver driver;


    @Produces
    Driver getDriver(InjectionPoint injectionPoint) {

        if (driver == null) {
            LOGGER.info(String.format("Loading driver configuration from"));
            this.driver = Neo4JConfiguration.DEFAULT.getDriver();
        }

        return driver;
    }

}