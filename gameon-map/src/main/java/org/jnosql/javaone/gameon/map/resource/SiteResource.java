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

import org.jnosql.javaone.gameon.map.Site;
import org.jnosql.javaone.gameon.map.SiteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Supplier;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@ApplicationScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("sites")
public class SiteResource {


    private static final Supplier<WebApplicationException> NOT_FOUND_SUPPLIER =
            () -> new WebApplicationException(Response.status(NOT_FOUND).build());

    @Inject
    private SiteService siteService;

    @GET
    @Path("/{id}")
    public SiteDTO findById(@PathParam("id") String id) {
        Site site = siteService.findByName(id)
                .orElseThrow(NOT_FOUND_SUPPLIER);

        return SiteDTO.of(site);
    }


    @POST
    public void create(@Valid SiteDTO dto) {
        try {
            siteService.create(dto.toSite());
        } catch (IllegalArgumentException ex) {
            throw new WebApplicationException(ex.getMessage(), BAD_REQUEST);
        }

    }

    @Path("/{id}")
    @PUT
    public void update(@PathParam("id") String id, SiteDTO dto) {
        Site site = siteService.findByName(id)
                .orElseThrow(NOT_FOUND_SUPPLIER);

        site.merge(dto.toSite());
        siteService.save(site);
    }

}
