/*
 * Copyright (c) 2017 Otávio Santana, Leonardo Lima and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana, Leonardo Lima
 */

package org.jnosql.javaone.gameon.map;

import org.jnosql.javaone.gameon.map.SiteAvailability.SiteAvailabilityBuilder;

public class SiteBuilder {

    private String name;

    private Connection.ConnectionBuilder connectionBuilder = Connection.builder();

    private String fullName;

    private String description;

    private String owner;

    private Coordinate coordinate;

    private SiteAvailabilityBuilder siteAvailabilityBuilder = SiteAvailability.builder();

    private DoorDescription doorDescription;

    SiteBuilder() {
    }

    public SiteBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SiteBuilder withConnectionType(String connectionType) {
        this.connectionBuilder.withConnectionType(connectionType);
        return this;
    }

    public SiteBuilder withConnectionTarget(String connectionTarget) {
        this.connectionBuilder.withConnectionTarget(connectionTarget);
        return this;
    }

    public SiteBuilder withConnectionToken(String connectionToken) {
        this.connectionBuilder.withConnectionTarget(connectionToken);
        return this;
    }

    public SiteBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public SiteBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SiteBuilder withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public SiteBuilder withCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public SiteBuilder withEmpty(boolean empty) {
        this.siteAvailabilityBuilder.withEmpty(empty);
        return this;
    }

    public SiteBuilder withDoorAvailable(boolean doorAvailable) {
        this.siteAvailabilityBuilder.withDoorAvailable(doorAvailable);
        return this;
    }

    public SiteBuilder withDoorDescription(DoorDescription doorDescription) {
        this.doorDescription = doorDescription;
        return this;
    }

    public Site build() {
        return new Site(name, connectionBuilder.build(), fullName, description, owner, coordinate, siteAvailabilityBuilder.build(), doorDescription);
    }
}