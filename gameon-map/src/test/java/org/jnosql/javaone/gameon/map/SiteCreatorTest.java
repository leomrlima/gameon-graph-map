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
        String forward = "north gate description";
        String rollback = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").north(forward, rollback);

        main = siteService.findByName("main").get();
        Site northSite = siteService.findByName("second").get();

        Coordinate coordinate = northSite.getCoordinate();
        Assertions.assertEquals(1, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());


    }


    @Test
    public void shouldCreateToSouth() {
        String forward = "south gate description";
        String rollback = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").south(forward, rollback);

        Site southSite = siteService.findByName("second").get();

        Coordinate coordinate = southSite.getCoordinate();
        Assertions.assertEquals(-1, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getX());
        Assertions.assertEquals(-1, coordinate.getWeight());

    }

    @Test
    public void shouldCreateToWest() {
        String forward = "west gate description";
        String rollback = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").west(forward, rollback);

        Site westSite = siteService.findByName("second").get();

        Coordinate coordinate = westSite.getCoordinate();
        Assertions.assertEquals(0, coordinate.getY());
        Assertions.assertEquals(-1, coordinate.getX());
        Assertions.assertEquals(-1, coordinate.getWeight());
    }

    @Test
    public void shouldCreateToEast() {
        String forward = "east gate description";
        String rollback = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        siteService.getNewSiteCreator().to(site).from("main").east(forward, rollback);

        Site eastSite = siteService.findByName("second").get();

        Coordinate coordinate = eastSite.getCoordinate();
        Assertions.assertEquals(0, coordinate.getY());
        Assertions.assertEquals(1, coordinate.getX());
        Assertions.assertEquals(1, coordinate.getWeight());
    }

    @Test
    public void shouldReturnErrorWhenUseCreatorTwice() {
        String forward = "east gate description";
        String rollback = "back gate description";
        Site main = builder().withName("main").withFullName("Main room site").withCoordinate(Coordinate.MAIN).build();
        siteService.create(main);
        Site site = builder().withName("second").withFullName("Main room site").build();
        SiteCreator siteCreator = siteService.getNewSiteCreator();
        siteCreator.to(site).from("main").east(forward, rollback);

        Site third = builder().withName("third").withFullName("Main room site").build();
        Assertions.assertThrows(IllegalStateException.class, () ->{
            siteCreator.to(third).from("main").east(forward, rollback);
        });
    }


}