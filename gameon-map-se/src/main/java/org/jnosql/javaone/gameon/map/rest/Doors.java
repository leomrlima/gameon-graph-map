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

package org.jnosql.javaone.gameon.map.rest;

import java.util.Objects;

public class Doors {

    /**
     * North door (140 characters)
     */
    private String n;

    /**
     * West door (140 characters)
     */
    private String w;

    /**
     * South door (140 characters)
     */
    private String s;

    /**
     * East door (140 characters)
     */
    private String e;

    /**
     * Door in the ceiling (Up) (140 characters)
     */
    private String u;

    /**
     * Door in the floor (Down)(140 characters)
     */
    private String d;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doors)) {
            return false;
        }
        Doors doors = (Doors) o;
        return Objects.equals(n, doors.n) &&
                Objects.equals(w, doors.w) &&
                Objects.equals(s, doors.s) &&
                Objects.equals(e, doors.e) &&
                Objects.equals(u, doors.u) &&
                Objects.equals(d, doors.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, w, s, e, u, d);
    }
}
