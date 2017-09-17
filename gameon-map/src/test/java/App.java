import java.util.Optional;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import gameon.map.graph.Site;
import gameon.map.graph.SiteService;

public class App {

    public static void main(String[] args) {


        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            SiteService siteService = container.select(SiteService.class).get();

            Site root = createRoot();
            System.out.println(root);
            siteService.save(root);

            Optional<Site> byName = siteService.findByName(root.getName());
            Optional<Site> result = siteService.findById(byName.get().getId());
            System.out.println(result.get());
        }
    }

    private static Site createRoot() {
        Site site = new Site();
        site.setOwner("_leo");
        site.setX(0);
        site.setY(0);
        site.setName("First ROOM");
        site.setFullName("The First ROOM");
        site.setDescription("A helpful room with doors in every possible direction.");
        site.setConnectionTarget("wss://secondroom:9008/barn/ws");
        site.setConnectionToken("A-totally-arbitrary-really-long-string");
        site.setConnectionType("websocket");
        site.setEmpty(false);
        return site;
    }

}
