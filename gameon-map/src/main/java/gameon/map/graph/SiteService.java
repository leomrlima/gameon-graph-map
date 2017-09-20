package gameon.map.graph;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Comparator;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static org.jnosql.artemis.DatabaseType.GRAPH;

@ApplicationScoped
public class SiteService {

    private static final Comparator<Site> ORDER_WEIGHT = comparing(Site::getWeight);

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
    public void edge(Site siteA, Direction direction, Site siteB) {
        template.edge(siteA, direction.get(), siteB);
        template.edge(siteB, direction.getReversal(), siteA);
    }

    public Optional<Site> goTo(Site secondRoom, Direction direction) {
        return template.getTraversalVertex(secondRoom.getId()).out(direction.get()).<Site>next();
    }


    public Optional<Site> getRecentRoom() {

        return template.getTraversalVertex().has("doorAvaiable", true)
                .<Site>stream()
                .sorted(ORDER_WEIGHT)
                .findFirst();
    }

}
