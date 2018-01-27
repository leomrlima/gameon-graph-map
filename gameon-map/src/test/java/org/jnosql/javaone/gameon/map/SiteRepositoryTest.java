package org.jnosql.javaone.gameon.map;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.javaone.gameon.map.infrastructure.CDIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CDIExtension.class)
public class SiteRepositoryTest {

    @Inject
    @Database(DatabaseType.GRAPH)
    private SiteRepository siteRepository;


    @Test
    public void shouldSave() {
        Site mainRoom = Site.builder().withFullName("main room").build();
        siteRepository.save(mainRoom);
    }

}