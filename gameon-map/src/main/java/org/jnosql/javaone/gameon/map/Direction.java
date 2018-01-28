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

package org.jnosql.javaone.gameon.map;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Supplier;

public enum Direction implements Supplier<String>, Reversible {

    NORTH {
        @Override
        public String getReverse() {
            return SOUTH.get();
        }

        @Override
        public String get() {
            return "North";
        }
    }, SOUTH {
        @Override
        public String getReverse() {
            return NORTH.get();
        }

        @Override
        public String get() {
            return "South";
        }
    }, EAST {
        @Override
        public String getReverse() {
            return WEST.get();
        }

        @Override
        public String get() {
            return "East";
        }
    }, WEST {
        @Override
        public String getReverse() {
            return EAST.get();
        }

        @Override
        public String get() {
            return "West";
        }
    };

}
