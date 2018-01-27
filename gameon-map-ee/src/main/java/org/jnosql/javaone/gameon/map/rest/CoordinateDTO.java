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

import org.jnosql.javaone.gameon.map.Coordinate;

import java.io.Serializable;
import java.util.Objects;

public class CoordinateDTO implements Serializable {

    private long x;

    private long y;

    private long weight;

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public static CoordinateDTO of(Coordinate coordinate) {
        if (Objects.isNull(coordinate)) {
            return null;
        }

        CoordinateDTO dto = new CoordinateDTO();
        dto.x = coordinate.getX();
        dto.y = coordinate.getY();
        dto.weight = coordinate.getWeight();

        return dto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CoordinateDTO{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    public static Coordinate from(CoordinateDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }
        return Coordinate.builder().withX(dto.x).withY(dto.y).builder();
    }
}
