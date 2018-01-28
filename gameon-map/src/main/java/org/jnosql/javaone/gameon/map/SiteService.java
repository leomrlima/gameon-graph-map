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
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.Objects.isNull;
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
        if (isNull(site.getId())) {
            Optional<Site> siteByName = repository.findByName(site.getName());
            siteByName.ifPresent(site::merge);
        }
        repository.save(site);
    }


    @Transactional
    public void create(Site site) {
        repository.findByName(site.getName()).ifPresent(c ->{
            throw new IllegalArgumentException("The site name already does exist: " + site.getName());
        });
        repository.save(site);
    }



    @Transactional
    public void deleteByName(String name) {
        repository.deleteByName(Name.of(name).get());
    }

    public Optional<Site> goTo(Site site, Direction direction) {
        return template.getTraversalVertex(site.getId()).out(direction).<Site>next();
    }

    public Optional<Site> findByName(String name) {
        return repository.findByName(Name.of(name).get());
    }


    public Optional<Site> getRecentRoom() {

        return template.getTraversalVertex().has("doorAvailable", true)
                .<Site>stream()
                .sorted(ORDER_WEIGHT)
                .findFirst();
    }



}
