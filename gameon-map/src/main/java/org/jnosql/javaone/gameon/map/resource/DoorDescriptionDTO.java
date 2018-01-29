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

import org.jnosql.javaone.gameon.map.DoorDescription;

import java.io.Serializable;

public class DoorDescriptionDTO implements Serializable {

    private String north;

    private String south;

    private String west;

    private String east;

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public DoorDescription toDoorDescription() {
        return DoorDescription.builder().
                withEast(east)
                .withWest(west)
                .withNorth(north)
                .withSouth(south).build();
    }

    public static DoorDescription of(DoorDescriptionDTO dto) {
        if (dto == null) {
            return null;
        }
        return dto.toDoorDescription();
    }

    public static DoorDescriptionDTO of(DoorDescription description) {
        if (description == null) {
            return null;
        }

        DoorDescriptionDTO dto = new DoorDescriptionDTO();
        dto.setNorth(description.getNorth());
        dto.setSouth(description.getSouth());
        dto.setEast(description.getEast());
        dto.setWest(description.getWest());
        return dto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DoorDescriptionDTO{");
        sb.append("north='").append(north).append('\'');
        sb.append(", south='").append(south).append('\'');
        sb.append(", west='").append(west).append('\'');
        sb.append(", east='").append(east).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
