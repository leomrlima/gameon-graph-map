package org.jnosql.javaone.gameon.map;

import org.jnosql.artemis.graph.EdgeEntity;

public interface SiteCreator {

    SiteFromCreator create(Site site) throws NullPointerException;


    interface SiteFromCreator {
        SiteFromCreator from(Site site) throws NullPointerException;
    }

    interface SiteDestination {
        EdgeEntity north();

        EdgeEntity south();

        EdgeEntity west();

        EdgeEntity east();
    }
}
