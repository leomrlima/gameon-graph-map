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
package org.jnosql.javaone.gameon.map.resource;

import org.jnosql.javaone.gameon.map.Connection;

import java.io.Serializable;

public class ConnectionDTO implements Serializable {


    private String type;

    private String target;

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

    public static ConnectionDTO of(Connection connection) {
        ConnectionDTO dto = new ConnectionDTO();
        dto.setTarget(connection.getTarget());
        dto.setToken(connection.getToken());
        dto.setType(connection.getType());
        return dto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConnectionDTO{");
        sb.append("type='").append(type).append('\'');
        sb.append(", target='").append(target).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
