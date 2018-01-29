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
import org.jnosql.artemis.Embeddable;

@Embeddable
public class DoorDescription {

    @Column
    private String northDescription;

    @Column
    private String southDescription;

    @Column
    private String westDescription;

    @Column
    private String eastDescription;

    DoorDescription() {
    }

    private DoorDescription(String northDescription, String southDescription,
                            String westDescription, String eastDescription) {
        this.northDescription = northDescription;
        this.southDescription = southDescription;
        this.westDescription = westDescription;
        this.eastDescription = eastDescription;
    }

    public String getNorth() {
        return northDescription;
    }

    public String getSouth() {
        return southDescription;
    }

    public String getWest() {
        return westDescription;
    }

    public String getEast() {
        return eastDescription;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DoorDescription{");
        sb.append("north='").append(northDescription).append('\'');
        sb.append(", south='").append(southDescription).append('\'');
        sb.append(", west='").append(westDescription).append('\'');
        sb.append(", east='").append(eastDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class DoorDescriptionBuilder {

        private String north;

        private String south;

        private String west;

        private String east;

        private DoorDescriptionBuilder() {
        }

        public DoorDescriptionBuilder withNorth(String north) {
            this.north = north;
            return this;
        }

        public DoorDescriptionBuilder withSouth(String south) {
            this.south = south;
            return this;
        }

        public DoorDescriptionBuilder withWest(String west) {
            this.west = west;
            return this;
        }

        public DoorDescriptionBuilder withEast(String east) {
            this.east = east;
            return this;
        }

        public DoorDescription build() {
            return new DoorDescription(north, south, west, east);
        }
    }

}
