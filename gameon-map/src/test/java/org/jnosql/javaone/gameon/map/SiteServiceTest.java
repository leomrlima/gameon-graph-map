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
import org.jnosql.javaone.gameon.map.infrastructure.CDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.jnosql.javaone.gameon.map.Site.builder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(CDIExtension.class)
public class SiteServiceTest {

    @Inject
    private SiteService siteService;

    @Inject
    private Graph graph;

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
        assertNotNull(siteService.getNewSiteCreator());
    }

    @Test
    public void shouldGoTo() {
        String forward = "west gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").west(forward, backward);

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

}
