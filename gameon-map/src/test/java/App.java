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

            siteService.direction(root, Direction.NORTH, secondRoom);
            Optional<Site> result = siteService.goTo(secondRoom, Direction.SOUTH);
            Optional<Site> nextAvialable = siteService.getRecentRoom();
            System.out.println(result);
            System.out.println(nextAvialable);
        }
    }

    private static Site createRoot() {
        return Site.builder()
                .withOwner("_leo")
                .withCoordinate(Coordinate.EMPTY)
                .withName("First ROOM")
                .withFullName("The First ROOM")
                .withDescription("A helpful room with doors in every possible direction.")
                .withConnectionTarget("wss://secondroom:9008/barn/ws")
                .withConnectionToken("A-totally-arbitrary-really-long-string")
                .withConnectionType("websocket")
                .withEmpty(false)
                .build();

    }

    private static Site secondRoom() {
        return Site.builder()
                .withOwner("_leo")
                .withCoordinate(Coordinate.builder().withX(1L).builder())
                .withDoorAvailable(true)
                .withName("Second ROOM")
                .withFullName("The Second ROOM")
                .withDescription("A helpful room with doors in every possible direction.")
                .withConnectionTarget("wss://secondroom:9008/barn/ws")
                .withConnectionToken("A-totally-arbitrary-really-long-string")
                .withConnectionType("websocket")
                .withEmpty(false).build();
    }

}
