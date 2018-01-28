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
import org.jnosql.javaone.gameon.map.validation.Name;

import java.io.Serializable;

public class SiteDTO implements Serializable {

    private Long id;

    @Name
    private String name;

    private String connectionType;

    private String connectionTarget;

    private String connectionToken;

    private String fullName;

    private String description;

    private String owner;

    private CoordinateDTO coordinate;

    private boolean empty;

    private boolean doorAvailable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getConnectionTarget() {
        return connectionTarget;
    }

    public void setConnectionTarget(String connectionTarget) {
        this.connectionTarget = connectionTarget;
    }

    public String getConnectionToken() {
        return connectionToken;
    }

    public void setConnectionToken(String connectionToken) {
        this.connectionToken = connectionToken;
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


    public static SiteDTO of(Site site) {
        SiteDTO dto = new SiteDTO();
        dto.id = site.getId();
        dto.name = site.getName();
        dto.connectionType = site.getConnectionType();
        dto.connectionTarget = site.getConnectionTarget();
        dto.connectionToken = site.getConnectionToken();
        dto.fullName = site.getFullName();
        dto.description = site.getDescription();
        dto.owner = site.getOwner();
        dto.coordinate = CoordinateDTO.of(site.getCoordinate());
        dto.empty = site.isEmpty();
        dto.doorAvailable = site.isDoorAvailable();
        return dto;
    }

    public Site toSite() {
        return Site.builder().withId(id)
                .withName(name)
                .withConnectionType(connectionType)
                .withConnectionTarget(connectionTarget)
                .withConnectionToken(connectionToken)
                .withFullName(fullName)
                .withDescription(description)
                .withOwner(owner)
                .withCoordinate(CoordinateDTO.from(coordinate))
                .withEmpty(empty)
                .withDoorAvailable(doorAvailable).build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SiteDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", connectionType='").append(connectionType).append('\'');
        sb.append(", connectionTarget='").append(connectionTarget).append('\'');
        sb.append(", connectionToken='").append(connectionToken).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", coordinate=").append(coordinate);
        sb.append(", empty=").append(empty);
        sb.append(", doorAvailable=").append(doorAvailable);
        sb.append('}');
        return sb.toString();
    }
}
