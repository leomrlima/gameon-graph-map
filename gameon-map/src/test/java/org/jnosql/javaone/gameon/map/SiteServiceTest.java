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

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.javaone.gameon.map.infrastructure.CDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.apache.tinkerpop.gremlin.structure.Direction.OUT;
import static org.jnosql.javaone.gameon.map.Site.builder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(CDIExtension.class)
public class SiteServiceTest {

    @Inject
    private SiteService siteService;

    @Inject
    private Graph graph;

    @Inject
    private GraphTemplate template;

    @BeforeEach
    public void before() {
        graph.traversal().V().toList().forEach(Vertex::remove);
        graph.traversal().E().toList().forEach(Edge::remove);
    }

    @Test
    public void shouldCreate() {

        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);
        assertTrue(siteService.findByName(site.getName()).isPresent());
    }

    @Test
    public void shouldReturnErrorWhenThereIsDoubleRoom() {
        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            siteService.create(site);
        });

        assertEquals("The site name already does exist: main", exception.getMessage());
    }


    @Test
    public void shouldUpdateSite() {
        String newFullName = "update Main room site";

        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);

        siteService.update(builder().withName("main").withFullName(newFullName).build());

        Site updatedSite = siteService.findByName("main").orElseThrow(() -> new NullPointerException("Cannot be null"));
        assertEquals(newFullName, updatedSite.getFullName());

    }

    @Test
    public void shouldCreateNewSite() {
        assertNotNull(siteService.createSite());
    }

    @Test
    public void shouldGoTo() {
        String forward = "west gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.createSite().to(site).from("main").by(Direction.WEST);

        Site eastSite = siteService.findByName("second").get();
        main = siteService.findByName("main").get();
        Site result = siteService.goTo(main, Direction.WEST).get();
        Site resultB = siteService.goTo(eastSite, Direction.EAST).get();

        assertEquals(eastSite.getId(), result.getId());
        assertEquals(main.getId(), resultB.getId());
    }

    @Test
    public void shouldReturnMainRoom() {
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);

        Optional<Site> recentRoom = siteService.getRecentRoom();
        Assertions.assertEquals("main", recentRoom.map(Site::getName).orElse(""));
    }

    @Test
    public void shouldCreateNextRoom() {
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);

        main = siteService.findByName("main").get();

        Site second = builder().withName("second").withFullName("second room").build();
        siteService.place(second);

        Site site = siteService.goTo(main, Direction.NORTH).get();
        Assertions.assertEquals(site.getName(), second.getName());
    }

    @Test
    public void shouldDoMap() {

        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);

        Site second = builder().withName("second").withFullName("second room").build();
        Site third = builder().withName("third").withFullName("third room").build();
        Site fourth = builder().withName("fourth").withFullName("fourth room").build();
        Site fifth = builder().withName("fifth").withFullName("fifth room").build();


        Set<Site> sites = new HashSet<>(Arrays.asList(second, third, fourth, fifth));

        siteService.place(second);
        siteService.place(third);
        siteService.place(fourth);
        siteService.place(fifth);

        main = siteService.findByName(main.getName()).get();

        for (Direction direction : Direction.values()) {
            Optional<Site> site = siteService.goTo(main, direction);
            Assertions.assertTrue(site.isPresent());
            sites.remove(site.get());
        }

        Assertions.assertTrue(sites.isEmpty());

    }

    @Test
    public void shouldBecameUnavailable() {
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);

        Site second = builder().withName("second").withFullName("second room").build();
        Site third = builder().withName("third").withFullName("third room").build();
        Site fourth = builder().withName("fourth").withFullName("fourth room").build();
        Site fifth = builder().withName("fifth").withFullName("fifth room").build();

        siteService.place(second);
        siteService.place(third);
        siteService.place(fourth);
        siteService.place(fifth);

        main = siteService.findByName("main").get();

        Assertions.assertFalse(main.isDoorAvailable());

    }


    @Test
    public void shouldGoLevelTwo() {

        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);

        for (int index = 0; index < 12; index++) {
            Site site = builder().withName("room_" + index).withFullName("room_" + index).build();
            siteService.place(site);
            site = siteService.findByName(site.getName()).get();
        }

        Coordinate[][] coordinates = new Coordinate[5][5];
        Site[][] sites = new Site[5][5];
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                coordinates[x + 2][y + 2] = Coordinate.builder().withX(x).withY(y).build();
                Optional<Site> siteXY = siteService.findByCoordinate(coordinates[x + 2][y + 2]);
                if (siteXY.isPresent()) {
                    sites[x + 2][y + 2] = siteXY.get();
                }
            }
        }

        //y = -2
        Assertions.assertNull(sites[0][0]);
        Assertions.assertNull(sites[1][0]);
        Assertions.assertNotNull(sites[2][0]);
        Assertions.assertNull(sites[3][0]);
        Assertions.assertNull(sites[4][0]);

        //y = -1
        Assertions.assertNull(sites[0][1]);
        Assertions.assertNotNull(sites[1][1]);
        Assertions.assertNotNull(sites[2][1]);
        Assertions.assertNotNull(sites[3][1]);
        Assertions.assertNull(sites[4][1]);

        //y = -0
        Assertions.assertNotNull(sites[0][2]);
        Assertions.assertNotNull(sites[1][2]);
        Assertions.assertNotNull(sites[2][2]);
        Assertions.assertNotNull(sites[3][2]);
        Assertions.assertNotNull(sites[4][2]);

        //y = 1
        Assertions.assertNull(sites[0][3]);
        Assertions.assertNotNull(sites[1][3]);
        Assertions.assertNotNull(sites[2][3]);
        Assertions.assertNotNull(sites[3][3]);
        Assertions.assertNull(sites[4][3]);

        //y = 2
        Assertions.assertNull(sites[0][4]);
        Assertions.assertNull(sites[1][4]);
        Assertions.assertNotNull(sites[2][4]);
        Assertions.assertNull(sites[3][4]);
        Assertions.assertNull(sites[4][4]);

        //x = 0, y = 1
        {
            Site zp1 = sites[2][3];
            Collection<EdgeEntity> edges_zp1 = template.getEdges(zp1, OUT);
            Assertions.assertEquals(4, edges_zp1.size());
            Assertions.assertEquals(sites[1][3], siteService.goTo(zp1, Direction.WEST).get());
            Assertions.assertEquals(sites[3][3], siteService.goTo(zp1, Direction.EAST).get());
            Assertions.assertEquals(sites[2][4], siteService.goTo(zp1, Direction.NORTH).get());
            Assertions.assertEquals(sites[2][2], siteService.goTo(zp1, Direction.SOUTH).get());
        }

        //x = 0, y = 2
        {
            Site zp2 = sites[2][4];
            Collection<EdgeEntity> edges_zp2 = template.getEdges(zp2, OUT);
            Assertions.assertEquals(1, edges_zp2.size());
            Assertions.assertEquals(sites[2][3], siteService.goTo(zp2, Direction.SOUTH).get());
            assertFalse(siteService.goTo(zp2, Direction.NORTH).isPresent());
            assertFalse(siteService.goTo(zp2, Direction.EAST).isPresent());
            assertFalse(siteService.goTo(zp2, Direction.WEST).isPresent());
        }

        //x = -1, y = -1
        {
            Site m1m1 = sites[1][1];
            Collection<EdgeEntity> edges_m1m1 = template.getEdges(m1m1, OUT);
            Assertions.assertEquals(2, edges_m1m1.size());
            Assertions.assertEquals(sites[1][2], siteService.goTo(m1m1, Direction.NORTH).get());
            Assertions.assertEquals(sites[2][1], siteService.goTo(m1m1, Direction.EAST).get());
            assertFalse(siteService.goTo(m1m1, Direction.WEST).isPresent());
            assertFalse(siteService.goTo(m1m1, Direction.SOUTH).isPresent());
        }

    }

}
