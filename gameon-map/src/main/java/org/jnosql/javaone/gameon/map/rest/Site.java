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

package org.jnosql.javaone.gameon.map.rest;

import java.util.Objects;

public class Site {

    /**
     * Site id
     */
    private String id;

    private RoomInfo info;

    /**
     * Owner
     */
    private String owner;

    private Coordinates coord;

    private RoomType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomInfo getInfo() {
        return info;
    }

    public void setInfo(RoomInfo info) {
        this.info = info;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Site)) {
            return false;
        }
        Site site = (Site) o;
        return Objects.equals(id, site.id) &&
                Objects.equals(info, site.info) &&
                Objects.equals(owner, site.owner) &&
                Objects.equals(coord, site.coord) &&
                type == site.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info, owner, coord, type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Site{");
        sb.append("id='").append(id).append('\'');
        sb.append(", info=").append(info);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", coord=").append(coord);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
