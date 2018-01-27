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

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Coordinate implements Serializable {

    public static final Coordinate EMPTY = new Coordinate();

    @Column
    private long x;

    @Column
    private long y;

    /**
     * Weight for private Site placement algorithm
     */
    @Column
    private long weight;

    Coordinate() {
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, weight);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coordinate{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    public static CoordinateBuilder builder() {
        return new CoordinateBuilder();
    }

    public static class CoordinateBuilder {

        private long x;

        private long y;

        private CoordinateBuilder() {
        }

        public CoordinateBuilder withX(long x) {
            this.x = x;
            return this;
        }

        public CoordinateBuilder withY(long y) {
            this.y = y;
            return this;
        }

        public Coordinate builder() {
            Coordinate coordinate = new Coordinate();
            coordinate.x = x;
            coordinate.y = y;
            coordinate.weight = y + x;
            return coordinate;
        }
    }
}
