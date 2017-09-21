package gameon.map;


import com.steelbridgelabs.oss.neo4j.structure.Neo4JElementIdProvider;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.jnosql.artemis.ConfigurationUnit;
import org.neo4j.driver.v1.Driver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class GraphProducer {


    private static final String SITE = "Site";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String DOOR_AVAIABLE = "doorAvaiable";

    private Neo4JGraph graph;

    @Inject
    @ConfigurationUnit
    private Driver driver;


    @PostConstruct
    public void init() {
        Neo4JElementIdProvider<?> vertexIdProvider = new Neo4JNativeElementIdProvider();
        Neo4JElementIdProvider<?> edgeIdProvider = new Neo4JNativeElementIdProvider();
        this.graph = new Neo4JGraph(driver, vertexIdProvider, edgeIdProvider);
        graph.setProfilerEnabled(true);
        createIndex();
    }

    private void createIndex() {
        Transaction transaction = graph.tx();
        graph.createIndex(SITE, NAME);
        graph.createIndex(SITE, ID);
        graph.createIndex(SITE, DOOR_AVAIABLE);

        transaction.commit();
    }

    @Produces
    @ApplicationScoped
    public Graph getGraph() {
        return graph;
    }

    public void close(@Disposes Graph graph) throws Exception {
        graph.close();
        driver.close();
    }
}