package gameon.map;


import com.steelbridgelabs.oss.neo4j.structure.Neo4JElementIdProvider;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import static org.neo4j.driver.v1.GraphDatabase.driver;

@ApplicationScoped
public class GraphProducer {


    private static final String URL = "bolt://localhost:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "admin";
    private static final AuthToken BASIC = AuthTokens.basic(USER, PASSWORD);
    private Neo4JGraph graph;

    private Driver driver;


    @PostConstruct
    public void init() {
        driver = driver(URL, BASIC);
        Neo4JElementIdProvider<?> vertexIdProvider = new Neo4JNativeElementIdProvider();
        Neo4JElementIdProvider<?> edgeIdProvider = new Neo4JNativeElementIdProvider();
        this.graph = new Neo4JGraph(driver, vertexIdProvider, edgeIdProvider);
        graph.setProfilerEnabled(true);
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