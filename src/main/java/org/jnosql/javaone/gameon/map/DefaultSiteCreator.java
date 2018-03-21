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

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.graph.GraphTemplate;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;

class DefaultSiteCreator implements SiteCreator, SiteCreator.SiteFromCreator, SiteCreator.SiteDestination {

    private Site to;

    private Site from;

    private final SiteService siteService;

    private final GraphTemplate graphTemplate;

    private boolean isValid = true;

    DefaultSiteCreator(SiteService siteService, GraphTemplate graphTemplate) {
        this.siteService = siteService;
        this.graphTemplate = graphTemplate;
    }


    @Override
    public SiteFromCreator to(Site site) throws NullPointerException {
        check();
        requireNonNull(site, "iste is required");
        siteService.findByName(site.getName())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Site Already does exist: " + site.getName());
                });

        this.to = site;
        return this;
    }

    @Override
    public SiteDestination from(String name) throws NullPointerException {
        check();
        requireNonNull(name, "name is required");

        this.from = siteService.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Site does not found" + name));
        return this;
    }


    @Override
    public void by(Direction direction) {
        requireNonNull(direction, "direction is required");
        check();

        Coordinate coordinate = from.getCoordinate();
        to.setCoordinate(coordinate.to(direction));
        siteService.create(to);
        graphTemplate.getTransaction().commit();
        to = siteService.findByName(to.getName()).get();
        graphTemplate.edge(from, direction, to);
        graphTemplate.edge(to, direction.getReverse(), from);


        Set<Direction> directions = EnumSet.allOf(Direction.class);
        directions.remove(direction.getReverse());

        for (Direction d : directions) {
            Optional<Site> neighbor = siteService.findByCoordinate(to.getCoordinate().to(d));
            neighbor.ifPresent(site -> {
                graphTemplate.edge(site, d.getReverse(), to);
                graphTemplate.edge(to, d, site);
            });
        }

        this.isValid = false;

    }


    private void check() {
        if (!isValid) {
            throw new IllegalStateException("This creation flow has finished, you need to create a new one.");
        }
    }
}
