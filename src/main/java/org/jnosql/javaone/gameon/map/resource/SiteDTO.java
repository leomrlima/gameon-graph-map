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

package org.jnosql.javaone.gameon.map.resource;

import org.jnosql.javaone.gameon.map.Site;
import org.jnosql.javaone.gameon.map.SiteBuilder;
import org.jnosql.javaone.gameon.map.validation.ValidName;

import java.io.Serializable;

public class SiteDTO implements Serializable {

    @ValidName
    private String name;

    private String fullName;

    private String description;

    private String owner;

    private CoordinateDTO coordinate;

    private boolean empty;

    private boolean doorAvailable = true;

    private DoorDescriptionDTO doorDescription;

    private ConnectionDTO connection;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConnectionDTO getConnection() {
        return connection;
    }

    public void setConnection(ConnectionDTO connection) {
        this.connection = connection;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CoordinateDTO getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateDTO coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isDoorAvailable() {
        return doorAvailable;
    }

    public void setDoorAvailable(boolean doorAvailable) {
        this.doorAvailable = doorAvailable;
    }

    public DoorDescriptionDTO getDoorDescription() {
        return doorDescription;
    }

    public void setDoorDescription(DoorDescriptionDTO doorDescription) {
        this.doorDescription = doorDescription;
    }

    public static SiteDTO of(Site site) {
        SiteDTO dto = new SiteDTO();
        dto.name = site.getName();
        dto.connection = ConnectionDTO.of(site.getConnection());
        dto.fullName = site.getFullName();
        dto.description = site.getDescription();
        dto.owner = site.getOwner();
        dto.coordinate = CoordinateDTO.of(site.getCoordinate());
        dto.empty = site.isEmpty();
        dto.doorAvailable = site.isDoorAvailable();
        dto.doorDescription = DoorDescriptionDTO.of(site.getDoorDescription());
        return dto;
    }

    public Site toSite() {
        SiteBuilder builder = Site.builder()
                .withName(name)
                .withFullName(fullName)
                .withDescription(description)
                .withOwner(owner)
                .withCoordinate(CoordinateDTO.from(coordinate))
                .withEmpty(empty)
                .withDoorAvailable(doorAvailable)
                .withDoorDescription(DoorDescriptionDTO.of(doorDescription));

        if (connection != null) {
            builder.withConnectionType(connection.getType())
                    .withConnectionTarget(connection.getTarget())
                    .withConnectionToken(connection.getToken());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SiteDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", connection=").append(connection);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", coordinate=").append(coordinate);
        sb.append(", empty=").append(empty);
        sb.append(", doorAvailable=").append(doorAvailable);
        sb.append(", doorDescription=").append(doorDescription);
        sb.append('}');
        return sb.toString();
    }
}
