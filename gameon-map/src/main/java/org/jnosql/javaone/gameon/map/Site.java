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


    @Column
    private Connection connection;

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
    private DoorDescription doorDescription;

    @Column
    private Coordinate coordinate;

    @Column
    private SiteAvailability siteAvailability;


    Site() {
    }

    Site(String name, Connection connection, String fullName,
         String description, String owner,
         Coordinate coordinate,
         SiteAvailability siteAvailability,
         DoorDescription doorDescription) {

        this.name = Name.of(name);
        this.connection = connection;
        this.fullName = fullName;
        this.description = description;
        this.owner = owner;
        this.coordinate = coordinate;
        this.siteAvailability = siteAvailability;
        this.doorDescription = doorDescription;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public Connection getConnection() {
        return connection;
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
        return siteAvailability.getEmpty();
    }

    public boolean isDoorAvailable() {
        return siteAvailability.getDoorAvailable();
    }


    public long getWeight() {
        return ofNullable(coordinate).map(Coordinate::getWeight).orElse(0L);
    }

    public DoorDescription getDoorDescription() {
        return doorDescription;
    }

    void empty() {
        this.siteAvailability.empty();
    }

    public void replaceWith(Site site) {
        this.connection = ofNullable(this.connection).orElse(site.connection);
        this.fullName = ofNullable(this.fullName).orElse(site.fullName);
        this.description = ofNullable(this.description).orElse(site.description);
        this.owner = ofNullable(this.owner).orElse(site.owner);
        this.doorDescription = ofNullable(this.doorDescription).orElse(site.doorDescription);

        this.siteAvailability.full();
    }

    public void merge(Site site) {

        if (Objects.isNull(id)) {
            this.id = site.id;
        }
        this.connection = ofNullable(this.connection).orElse(site.connection);

        this.fullName = ofNullable(this.fullName).orElse(site.fullName);
        this.description = ofNullable(this.description).orElse(site.description);
        this.owner = ofNullable(this.owner).orElse(site.owner);

        this.siteAvailability = ofNullable(this.siteAvailability).orElse(site.siteAvailability);
        this.coordinate = ofNullable(this.coordinate).orElse(site.coordinate);
        this.doorDescription = ofNullable(this.doorDescription).orElse(site.doorDescription);
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    void doorUnavailable() {
        this.siteAvailability.doorUnavailable();
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
        return Objects.equals(name, site.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Site{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", connection=").append(connection);
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", doorDescription=").append(doorDescription);
        sb.append(", coordinate=").append(coordinate);
        sb.append(", siteAvailability=").append(siteAvailability);
        sb.append('}');
        return sb.toString();
    }

}
