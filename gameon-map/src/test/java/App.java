import gameon.map.graph.Coordinate;
import gameon.map.graph.Direction;
import gameon.map.graph.Site;
import gameon.map.graph.SiteService;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

public class App {

    public static void main(String[] args) {


        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            SiteService siteService = container.select(SiteService.class).get();


            siteService.save(createRoot());
            siteService.save(secondRoom());

            Site root = siteService.findByName("First ROOM").get();
            Site secondRoom = siteService.findByName("Second ROOM").get();

            siteService.edge(root, Direction.NORTH, secondRoom);
            Optional<Site> result = siteService.goTo(secondRoom, Direction.SOUTH);
            Optional<Site> nextAvialable = siteService.getRecentRoom();
            System.out.println(result);
            System.out.println(nextAvialable);
        }
    }

    private static Site createRoot() {
        Site site = new Site();
        site.setOwner("_leo");
        site.setCoordinate(Coordinate.EMPTY);
        site.setName("First ROOM");
        site.setFullName("The First ROOM");
        site.setDescription("A helpful room with doors in every possible direction.");
        site.setConnectionTarget("wss://secondroom:9008/barn/ws");
        site.setConnectionToken("A-totally-arbitrary-really-long-string");
        site.setConnectionType("websocket");
        site.setEmpty(false);
        return site;
    }

    private static Site secondRoom() {
        Site site = new Site();
        site.setOwner("_leo");
        site.setCoordinate(Coordinate.builder().withX(1L).builder());
        site.setDoorAvaiable(true);
        site.setName("Second ROOM");
        site.setFullName("The Second ROOM");
        site.setDescription("A helpful room with doors in every possible direction.");
        site.setConnectionTarget("wss://secondroom:9008/barn/ws");
        site.setConnectionToken("A-totally-arbitrary-really-long-string");
        site.setConnectionType("websocket");
        site.setEmpty(false);
        return site;
    }

}
