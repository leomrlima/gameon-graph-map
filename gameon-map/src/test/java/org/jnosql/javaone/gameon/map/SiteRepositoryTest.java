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