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

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Convert;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;
import org.jnosql.javaone.gameon.map.infrastructure.NameConverter;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Optional.ofNullable;

@Entity
public class Site implements Serializable {

    @Id
    private Long id;

    @Column
    @Convert(NameConverter.class)
    private Name name;

    /**
     * Connection type
     */
    @Column
    private String connectionType;

    /**
     * Connection target, usually a URL
     */
    @Column
    private String connectionTarget;

    /**
     * (Optional) A token used for mutual identification between the room and
     * the mediator during the initial handshake when the connection is
     * established
     */
    @Column
    private String connectionToken;

    /**
     * (Optional) Human-friendly room name
     */
    @Column
    private String fullName;

    /**
     * (Optional) Player-friendly room description (140 characters)
     */
    @Column
    private String description;

    @Column
    private String owner;


    @Column
    private Coordinate coordinate;

    @Column
    private Boolean empty;

    @Column
    private Boolean doorAvailable;

    Site() {
    }

    Site(String name, String connectionType,
         String connectionTarget,
         String connectionToken, String fullName,
         String description, String owner,
         Coordinate coordinate, boolean empty,
         boolean doorAvailable) {

        this.name = Name.of(name);
        this.connectionType = connectionType;
        this.connectionTarget = connectionTarget;
        this.connectionToken = connectionToken;
        this.fullName = fullName;
        this.description = description;
        this.owner = owner;
        this.coordinate = coordinate;
        this.empty = empty;
        this.doorAvailable = doorAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getConnectionTarget() {
        return connectionTarget;
    }

    public String getConnectionToken() {
        return connectionToken;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isDoorAvailable() {
        return doorAvailable;
    }

    public long getWeight() {
        return ofNullable(coordinate).map(Coordinate::getWeight).orElse(0L);
    }


    public void merge(Site site) {

        if (Objects.isNull(id)) {
            this.id = site.id;
        }
        this.connectionType = ofNullable(this.connectionType).orElse(site.connectionType);
        this.connectionTarget = ofNullable(this.connectionTarget).orElse(site.connectionTarget);
        this.connectionToken = ofNullable(this.connectionToken).orElse(site.connectionToken);

        this.fullName = ofNullable(this.fullName).orElse(site.fullName);
        this.description = ofNullable(this.description).orElse(site.description);
        this.owner = ofNullable(this.owner).orElse(site.owner);

        this.doorAvailable = ofNullable(this.doorAvailable).orElse(site.doorAvailable);
        this.coordinate = ofNullable(this.coordinate).orElse(site.coordinate);
        this.empty = ofNullable(this.empty).orElse(site.empty);
        this.doorAvailable = ofNullable(this.doorAvailable).orElse(site.doorAvailable);
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public static SiteBuilder builder() {
        return new SiteBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        return Objects.equals(name, site.name) && Objects.equals(owner, site.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Site{");
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
