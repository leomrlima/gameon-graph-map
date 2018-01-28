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

import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.GraphTemplate;

import static java.util.Objects.requireNonNull;
import static org.jnosql.javaone.gameon.map.Direction.EAST;
import static org.jnosql.javaone.gameon.map.Direction.NORTH;
import static org.jnosql.javaone.gameon.map.Direction.SOUTH;
import static org.jnosql.javaone.gameon.map.Direction.WEST;

class DefaultSiteCreator implements SiteCreator, SiteCreator.SiteFromCreator, SiteCreator.SiteDestination {

    private Site to;

    private Site from;

    private final SiteService siteService;
    private final GraphTemplate graphTemplate;

    DefaultSiteCreator(SiteService siteService, GraphTemplate graphTemplate) {
        this.siteService = siteService;
        this.graphTemplate = graphTemplate;
    }


    @Override
    public SiteFromCreator to(Site site) throws NullPointerException {
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
        requireNonNull(name, "name is required");

        this.from = siteService.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Site does not found" + name));
        return this;
    }

    @Override
    public void north(String forward, String rollback) throws NullPointerException {
        requireNonNull(forward, "description is required");
        requireNonNull(rollback, "rollback is required");

        Coordinate coordinate = from.getCoordinate();
        to.setCoordinate(coordinate.toNorth());
        siteService.create(to);
        to = siteService.findByName(to.getName()).get();
        EdgeEntity edgeForward = graphTemplate.edge(from, NORTH, to);
        EdgeEntity edgeRollback = graphTemplate.edge(to, NORTH.getReversal(), from);


        edgeForward.add("description", forward);
        edgeRollback.add("description", rollback);

    }

    @Override
    public void south(String forward, String rollback) throws NullPointerException {
        requireNonNull(forward, "description is required");
        requireNonNull(rollback, "rollback is required");

        Coordinate coordinate = from.getCoordinate();
        to.setCoordinate(coordinate.toSouth());
        siteService.create(to);
        to = siteService.findByName(to.getName()).get();
        EdgeEntity edgeForward = graphTemplate.edge(from, SOUTH, to);
        EdgeEntity edgeRollback = graphTemplate.edge(to, SOUTH.getReversal(), from);


        edgeForward.add("description", forward);
        edgeRollback.add("description", rollback);
    }

    @Override
    public void west(String forward, String rollback) throws NullPointerException {
        requireNonNull(forward, "description is required");
        requireNonNull(rollback, "rollback is required");

        Coordinate coordinate = from.getCoordinate();
        to.setCoordinate(coordinate.toWest());
        siteService.create(to);
        to = siteService.findByName(to.getName()).get();
        EdgeEntity edgeForward = graphTemplate.edge(from, WEST, to);
        EdgeEntity edgeRollback = graphTemplate.edge(to, WEST.getReversal(), from);


        edgeForward.add("description", forward);
        edgeRollback.add("description", rollback);
    }

    @Override
    public void east(String forward, String rollback) throws NullPointerException {
        requireNonNull(forward, "description is required");
        requireNonNull(rollback, "rollback is required");

        Coordinate coordinate = from.getCoordinate();
        to.setCoordinate(coordinate.toEast());
        siteService.create(to);
        to = siteService.findByName(to.getName()).get();
        EdgeEntity edgeForward = graphTemplate.edge(from, EAST, to);
        EdgeEntity edgeRollback = graphTemplate.edge(to, EAST.getReversal(), from);


        edgeForward.add("description", forward);
        edgeRollback.add("description", rollback);
    }
}
