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

package org.jnosql.javaone.gameon.map.graph;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.Optional;

import static java.util.Comparator.comparing;
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
    public void save(Site site) {
        Optional<Site> siteByName = repository.findByName(site.getName());
        siteByName.ifPresent(site::merge);
        repository.save(site);
    }



    public Optional<Site> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Site> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public void direction(Site siteA, Direction direction, Site siteB) {
        template.edge(siteA, direction.get(), siteB);
        template.edge(siteB, direction.getReversal(), siteA);
    }

    public Optional<Site> goTo(Site secondRoom, Direction direction) {
        return template.getTraversalVertex(secondRoom.getId()).out(direction.get()).<Site>next();
    }


    public Optional<Site> getRecentRoom() {

        return template.getTraversalVertex().has("doorAvailable", true)
                .<Site>stream()
                .sorted(ORDER_WEIGHT)
                .findFirst();
    }

}
