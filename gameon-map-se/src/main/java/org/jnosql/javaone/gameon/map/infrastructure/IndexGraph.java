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

package org.jnosql.javaone.gameon.map.infrastructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Collections.emptyList;

class IndexGraph {

    static final Predicate<Map.Entry<String, Object>> FILTER = k -> k.getKey().startsWith("Index_");

    private final Map<String, List<String>> indexes = new HashMap<>();

    public Map<String, List<String>> getIndexes() {
        return indexes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndexGraph that = (IndexGraph) o;
        return Objects.equals(indexes, that.indexes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(indexes);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexGraph{");
        sb.append("indexes=").append(indexes);
        sb.append('}');
        return sb.toString();
    }

    public void add(Map.Entry<String, Object> entry) {
        String key = entry.getKey().replace("Index_", "");
        String[] properties = entry.getValue().toString().split(",");
        indexes.put(key, Arrays.asList(properties));

    }

    public Set<String> getLabels() {
        return indexes.keySet();
    }

    public List<String> getProperties(String label) {
        return indexes.getOrDefault(label, emptyList());
    }
}
