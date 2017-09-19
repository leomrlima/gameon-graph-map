package gameon.map.graph;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.GraphTemplate;
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

    @Inject
    private GraphTemplate template;


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


    @Transactional
    public void edge(Site site, Direction direction, Site siteB) {
        template.edge(site, direction.get(), siteB);
        template.edge(siteB, direction.getReversal(), site);

    }


    public Optional<Site> goTo(Site secondRoom, Direction direction) {
        return template.getTraversalVertex(secondRoom.getId()).out(direction.get()).<Site>next();
    }
}
