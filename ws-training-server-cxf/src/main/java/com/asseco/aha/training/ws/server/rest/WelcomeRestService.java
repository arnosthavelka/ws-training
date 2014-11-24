package com.asseco.aha.training.ws.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface WelcomeRestService {

    @GET
    @Path("/welcome/{name}/")
    public abstract String welcome(String name);

}