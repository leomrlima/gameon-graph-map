/*
 * Copyright 2018 Elder Moreas and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jnosql.javaone.gameon.map.infrastructure;

import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.javaone.gameon.map.infrastructure.GraphSupplier;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.interceptor.Interceptor;
import java.io.File;

@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION+10)
public class EmbeddedSupplier implements GraphSupplier {

    private Graph graph = Neo4jGraph.open(new File("").getAbsolutePath() + "/target/jnosql-graph");

    @Override
    @Produces
    public Graph get() {
        return graph;
    }
}
