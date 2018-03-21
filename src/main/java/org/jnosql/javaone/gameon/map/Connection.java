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

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Embeddable;

import java.util.Objects;

@Embeddable
public class Connection {

    /**
     * Connection type
     */
    @Column
    private String connectionType;

    /**
     * Connection target, usually a URL
     */
    @Column
    private String connectionTarget;

    /**
     * (Optional) A token used for mutual identification between the room and
     * the mediator during the initial handshake when the connection is
     * established
     */
    @Column
    private String connectionToken;


    Connection() {
    }

    private Connection(String connectionType, String connectionTarget, String connectionToken) {
        this.connectionType = connectionType;
        this.connectionTarget = connectionTarget;
        this.connectionToken = connectionToken;
    }

    public String getType() {
        return connectionType;
    }

    public String getTarget() {
        return connectionTarget;
    }

    public String getToken() {
        return connectionToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Connection)) {
            return false;
        }
        Connection that = (Connection) o;
        return Objects.equals(connectionType, that.connectionType) &&
                Objects.equals(connectionTarget, that.connectionTarget) &&
                Objects.equals(connectionToken, that.connectionToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionType, connectionTarget, connectionToken);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Connection{");
        sb.append("connectionType='").append(connectionType).append('\'');
        sb.append(", connectionTarget='").append(connectionTarget).append('\'');
        sb.append(", connectionToken='").append(connectionToken).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static ConnectionBuilder builder() {
        return new ConnectionBuilder();
    }

    public static class ConnectionBuilder {

        private String connectionType;

        private String connectionTarget;

        private String connectionToken;

        private ConnectionBuilder() {

        }

        public ConnectionBuilder withConnectionType(String connectionType) {
            this.connectionType = connectionType;
            return this;
        }

        public ConnectionBuilder withConnectionTarget(String connectionTarget) {
            this.connectionTarget = connectionTarget;
            return this;
        }

        public ConnectionBuilder withConnectionToken(String connectionToken) {
            this.connectionToken = connectionToken;
            return this;
        }

        public Connection build() {
            return new Connection(connectionType, connectionTarget, connectionToken);
        }

    }
}
