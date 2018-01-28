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

@ExtendWith(CDIExtension.class)
public class SiteCreatorTest {

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
    public void shouldReturnErrorWhenRoomAlreadyExist() {
        Site site = builder().withName("main").withFullName("Main room site").build();
        siteService.create(site);


        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            siteService.getNewSiteCreator().to(site);
        });
    }

    @Test
    public void shouldReturnErrorWhenFromSiteDoesNotExist() {
        Site site = builder().withName("second").withFullName("Main room site").build();
        SiteCreator.SiteFromCreator siteFromCreator = siteService.getNewSiteCreator().to(site);
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            siteFromCreator.from("not_found");
        });

    }

    @Test
    public void shouldCreateToNorth() {
        String description = "noth gate description";
        String descriptionB = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").north(description, descriptionB);

        main = siteService.findByName("main").get();
        Site northSite = siteService.findByName("second").get();

        Coordinate coordinate = northSite.getCoordinate();
        Assertions.assertEquals(1, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());


    }


    @Test
    public void shouldCreateToSouth() {

    }

    @Test
    public void shouldCreateToWest() {

    }

    @Test
    public void shouldCreateToEast() {

    }


}