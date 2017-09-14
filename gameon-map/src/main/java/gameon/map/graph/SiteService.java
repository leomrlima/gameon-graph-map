package gameon.map.graph;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Optional;

import static org.jnosql.artemis.DatabaseType.GRAPH;

@ApplicationScoped
public class SiteService {

    @Inject
    @Database(GRAPH)
    private SiteRepository repository;


    @Transactional
    public void save(Site site) {
        repository.save(site);
    }


    public Optional<Site> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Site> findByName(String name) {
        return repository.findByName(name);
    }


}
