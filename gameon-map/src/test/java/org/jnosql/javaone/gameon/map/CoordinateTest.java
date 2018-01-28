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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinateTest {

    @Test
    public void shouldMainBeZero() {
        Coordinate main = Coordinate.MAIN;
        Assertions.assertEquals(0, main.getX());
        Assertions.assertEquals(0, main.getY());
        Assertions.assertEquals(0, main.getWeight());
    }

    @Test
    public void shouldGoNorth() {
        Coordinate north = Coordinate.MAIN.toNorth();
        Assertions.assertEquals(0, north.getX());
        Assertions.assertEquals(1, north.getY());
        Assertions.assertEquals(1, north.getWeight());
    }

    @Test
    public void shouldGoSouth() {
        Coordinate south = Coordinate.MAIN.toSouth();
        Assertions.assertEquals(0, south.getX());
        Assertions.assertEquals(-1, south.getY());
        Assertions.assertEquals(-1, south.getWeight());
    }

    @Test
    public void shouldGoEast() {
        Coordinate east = Coordinate.MAIN.toEast();
        Assertions.assertEquals(1, east.getX());
        Assertions.assertEquals(0, east.getY());
        Assertions.assertEquals(1, east.getWeight());
    }

    @Test
    public void shouldGoWest() {
        Coordinate west = Coordinate.MAIN.toWest();
        Assertions.assertEquals(-1, west.getX());
        Assertions.assertEquals(-0, west.getY());
        Assertions.assertEquals(-1, west.getWeight());
    }

}