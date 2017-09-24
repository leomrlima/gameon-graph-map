package gameon.map.graph;

import org.jnosql.artemis.Repository;

import java.util.Optional;

public interface SiteRepository extends Repository<Site, String> {


    Optional<Site> findByName(String name);
}
