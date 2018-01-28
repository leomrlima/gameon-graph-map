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

import java.util.Objects;

import static org.jnosql.javaone.gameon.map.Direction.NORTH;

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
        Objects.requireNonNull(site, "iste is required");
        siteService.findByName(site.getName())
                .ifPresent(c -> {
                    throw new IllegalStateException("Site Already does exist: " + site.getName());
                });

        this.to = site;
        return this;
    }

    @Override
    public SiteDestination from(String name) throws NullPointerException {
        Objects.requireNonNull(name, "name is required");

        this.from = siteService.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Site does not found" + name));
        return this;
    }

    @Override
    public void north(String forward, String rollback) throws NullPointerException {
        Objects.requireNonNull(forward, "description is required");
        Objects.requireNonNull(rollback, "rollback is required");

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
        Objects.requireNonNull(forward, "description is required");
        Objects.requireNonNull(rollback, "rollback is required");
    }

    @Override
    public void west(String forward, String rollback) throws NullPointerException {
        Objects.requireNonNull(forward, "description is required");
        Objects.requireNonNull(rollback, "rollback is required");
    }

    @Override
    public void east(String forward, String rollback) throws NullPointerException {
        Objects.requireNonNull(forward, "description is required");
        Objects.requireNonNull(rollback, "rollback is required");
    }
}
