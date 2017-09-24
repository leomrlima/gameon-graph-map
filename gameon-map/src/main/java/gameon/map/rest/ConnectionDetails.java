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

package gameon.map.rest;

import java.util.Objects;

public class ConnectionDetails {
    /**
     * Connection type
     */
    private String type;

    /**
     * Connection target, usually a URL
     */
    private String target;

    /**
     * (Optional) A token used for mutual identification between the room and
     * the mediator during the initial handshake when the connection is
     * established
     */
    private String token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConnectionDetails)) {
            return false;
        }
        ConnectionDetails that = (ConnectionDetails) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(target, that.target) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, target, token);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConnectionDetails{");
        sb.append("type='").append(type).append('\'');
        sb.append(", target='").append(target).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
