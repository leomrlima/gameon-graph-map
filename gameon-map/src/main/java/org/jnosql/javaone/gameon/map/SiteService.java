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

import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;
import static org.apache.tinkerpop.gremlin.structure.Direction.OUT;
import static org.jnosql.artemis.DatabaseType.GRAPH;

@ApplicationScoped
public class SiteService {

    private static final Comparator<Site> ORDER_WEIGHT = comparing(Site::getWeight);

    @Inject
    @Database(GRAPH)
    private SiteRepository repository;


    @Inject
    private GraphTemplate template;


    @Transactional
    public void update(Site site) {
        requireNonNull(site, "site is required");
        Optional<Site> siteByName = repository.findByName(site.getName());
        siteByName.ifPresent(site::merge);
        repository.save(site);
    }


    @Transactional
    public void create(Site site) {
        requireNonNull(site, "site is required");
        repository.findByName(site.getName()).ifPresent(c -> {
            throw new IllegalArgumentException("The site name already does exist: " + site.getName());
        });
        repository.save(site);
    }


    @Transactional
    public void deleteByName(String name) {
        requireNonNull(name, "name is required");
        repository.deleteByName(Name.of(name).get());
    }

    public Optional<Site> findByName(String name) {
        requireNonNull(name, "name is required");
        return repository.findByName(Name.of(name).get());
    }

    public Optional<Site> goTo(Site site, Direction direction) {
        requireNonNull(site, "site is required");
        requireNonNull(direction, "direction is required");
        return template.getTraversalVertex(site.getId()).out(direction).<Site>next();
    }


    public SiteCreator getNewSiteCreator() {
        return new DefaultSiteCreator(this, template);
    }


    public Optional<Site> getRecentRoom() {

        Predicate<Site> isEmpty = Site::isEmpty;
        Predicate<Site> isDoorAvailable = Site::isDoorAvailable;


        return template.getTraversalVertex()
                .orderBy("weight").asc()
                .has("siteAvailabilityStatus", SiteAvailability.IS_AVIALABLE)
                .limit(1L)
                .filter(isEmpty.or(isDoorAvailable))
                .<Site>stream()
                .findFirst();
    }


    @Transactional
    public void place(Site site) {
        Site recentRoom = getRecentRoom().get();

        if (recentRoom.isEmpty()) {
            recentRoom.replaceWith(site);
            repository.save(recentRoom);
            return;
        }

        Collection<EdgeEntity> edges = template.getEdges(recentRoom, OUT);
        EnumSet<Direction> directions = EnumSet.allOf(Direction.class);
        edges.stream().map(EdgeEntity::getLabel).map(Direction::valueOf)
                .forEach(directions::remove);

        Direction next = directions.iterator().next();

        getNewSiteCreator().to(site).from(recentRoom.getName()).by(next);

        if (directions.size() == 1) {
            recentRoom.doorUnavailable();
            repository.save(recentRoom);
        }


    }

    public Optional<Site> findByCoordinate(Coordinate coordinate) {
        return template.getTraversalVertex()
                .has("x", coordinate.getX())
                .has("y", coordinate.getY()).next();
    }
}
