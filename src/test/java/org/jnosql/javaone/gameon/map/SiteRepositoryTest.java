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
import org.jnosql.artemis.DatabaseType;
import org.jnosql.javaone.gameon.map.infrastructure.CDIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(CDIExtension.class)
public class SiteRepositoryTest {

    private static final String SITE_NAME = "main";

    @Inject
    @Database(DatabaseType.GRAPH)
    private SiteRepository siteRepository;


    @Test
    public void shouldSave() {
        Site mainRoom = Site.builder().withName(SITE_NAME).withFullName("main room").build();
        siteRepository.save(mainRoom);
    }

    @Test
    public void shouldFindByName() {
        Site mainRoom = Site.builder().withName(SITE_NAME).withFullName("main room").build();
        siteRepository.save(mainRoom);
        assertTrue(siteRepository.findByName(SITE_NAME).isPresent());
    }

    @Test
    public void shouldDeleteByName() {
        Site mainRoom = Site.builder().withName(SITE_NAME).withFullName("main room").build();
        siteRepository.save(mainRoom);
        assertTrue(siteRepository.findByName(SITE_NAME).isPresent());
        siteRepository.deleteByName(SITE_NAME);
        assertFalse(siteRepository.findByName(SITE_NAME).isPresent());
    }

}