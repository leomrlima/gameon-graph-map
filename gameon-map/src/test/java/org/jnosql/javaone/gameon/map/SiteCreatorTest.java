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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.jnosql.javaone.gameon.map.Site.builder;

@ExtendWith(CDIExtension.class)
public class SiteCreatorTest {

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
    public void shouldReturnErrorWhenRoomAlreadyExist() {
        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);


        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            siteService.getNewSiteCreator().to(site);
        });
    }

    @Test
    public void shouldReturnErrorWhenFromSiteDoesNotExist() {
        Site site = builder().withName("second").withFullName("Main room site").build();
        SiteCreator.SiteFromCreator siteFromCreator = siteService.getNewSiteCreator().to(site);
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            siteFromCreator.from("not_found");
        });

    }

    @Test
    public void shouldCreateToNorth() {
        String forward = "north gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").by(Direction.NORTH);

        main = siteService.findByName("main").get();
        Site northSite = siteService.findByName("second").get();

        Coordinate coordinate = northSite.getCoordinate();
        Assertions.assertEquals(1, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());


    }


    @Test
    public void shouldCreateToSouth() {
        String forward = "south gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").by(Direction.SOUTH);

        Site southSite = siteService.findByName("second").get();

        Coordinate coordinate = southSite.getCoordinate();
        Assertions.assertEquals(-1, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());

    }

    @Test
    public void shouldCreateToWest() {
        String forward = "west gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").by(Direction.WEST);

        Site westSite = siteService.findByName("second").get();

        Coordinate coordinate = westSite.getCoordinate();
        Assertions.assertEquals(0, coordinate.getY());
        Assertions.assertEquals(-1, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());
    }

    @Test
    public void shouldCreateToEast() {
        String forward = "east gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        SiteCreator siteCreator = siteService.getNewSiteCreator();
        siteCreator.to(site).from("main").by(Direction.EAST);

        Site eastSite = siteService.findByName("second").get();

        Coordinate coordinate = eastSite.getCoordinate();
        Assertions.assertEquals(0, coordinate.getY());
        Assertions.assertEquals(1, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());
    }

    @Test
    public void shouldReturnErrorWhenUseCreatorTwice() {
        String forward = "east gate description";
        String backward = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        SiteCreator siteCreator = siteService.getNewSiteCreator();
        siteCreator.to(site).from("main").by(Direction.WEST);

        Site third = builder().withName("third").withFullName("Main room site").build();
        Assertions.assertThrows(IllegalStateException.class, () ->{
            siteCreator.to(third).from("main").by(Direction.EAST);
        });
    }

    @DisplayName("Check")
    @Test
    public void shouldCheckNextRoom() {

    }


}