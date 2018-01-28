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

public interface SiteCreator {

    SiteFromCreator to(Site site) throws NullPointerException, IllegalStateException;


    interface SiteFromCreator {
        SiteDestination from(String name) throws NullPointerException, IllegalStateException;
    }

    interface SiteDestination {

        void north(String forward, String rollback) throws NullPointerException;

        void  south(String forward, String rollback) throws NullPointerException;

        void  west(String forward, String rollback) throws NullPointerException;

        void  east(String forward, String rollback) throws NullPointerException;
    }
}
