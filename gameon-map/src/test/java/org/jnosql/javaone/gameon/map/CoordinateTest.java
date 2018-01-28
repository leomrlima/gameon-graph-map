package org.jnosql.javaone.gameon.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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