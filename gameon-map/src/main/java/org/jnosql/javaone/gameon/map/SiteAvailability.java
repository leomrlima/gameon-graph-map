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
public class SiteAvailability {

    @Column
    private boolean empty;

    @Column
    private boolean doorAvailable;

    @Column
    private String siteAvailabilityStatus;

    SiteAvailability() {
    }

    private SiteAvailability(boolean empty, boolean doorAvailable) {
        this.empty = empty;
        this.doorAvailable = doorAvailable;
        avialabilityStatus();
    }

    public boolean getEmpty() {
        return empty;
    }

    public boolean getDoorAvailable() {
        return doorAvailable;
    }

    void full() {
        this.empty = false;
        avialabilityStatus();
    }

    void empty() {
        this.empty = true;
        avialabilityStatus();
    }

    void doorAvailable() {
        this.doorAvailable = true;
        avialabilityStatus();
    }

    void doorUnavailable() {
        this.doorAvailable = true;
        avialabilityStatus();
    }

    private void avialabilityStatus() {
        this.siteAvailabilityStatus = Boolean.toString(empty) + '_' + Boolean.toString(doorAvailable);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SiteAvailability)) {
            return false;
        }
        SiteAvailability that = (SiteAvailability) o;
        return Objects.equals(empty, that.empty) &&
                Objects.equals(doorAvailable, that.doorAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empty, doorAvailable);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SiteAvailability{");
        sb.append("empty=").append(empty);
        sb.append(", doorAvailable=").append(doorAvailable);
        sb.append('}');
        return sb.toString();
    }

    public static SiteAvailabilityBuilder builder(){
        return new SiteAvailabilityBuilder();
    }

    public static class SiteAvailabilityBuilder {

        private boolean empty = false;

        private boolean doorAvailable = true;

        private SiteAvailabilityBuilder(){

        }

        public void withEmpty(boolean empty) {
            this.empty = empty;
        }

        public void withDoorAvailable(boolean doorAvailable) {
            this.doorAvailable = doorAvailable;
        }

        public SiteAvailability build() {
            return new SiteAvailability(empty, doorAvailable);
        }
    }
}
