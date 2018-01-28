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

import static org.jnosql.javaone.gameon.map.Site.builder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Assertions.assertTrue(siteService.findByName(site.getName()).isPresent());
    }

    @Test
    public void shouldReturnErrorWhenThereIsDoubleRoom() {
        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            siteService.create(site);
        });

        Assertions.assertEquals("The site name already does exist: main", exception.getMessage());
    }


    @Test
    public void shouldUpdateSite() {
        String newFullName = "update Main room site";

        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);

        siteService.update(builder().withName("main").withFullName(newFullName).build());

        Site updatedSite = siteService.findByName("main").orElseThrow(() -> new NullPointerException("Cannot be null"));
        Assertions.assertEquals(newFullName, updatedSite.getFullName());

    }

    @Test
    public void shouldCreateNewSite() {
        assertNotNull(siteService.getNewSiteCreator());
    }

}
