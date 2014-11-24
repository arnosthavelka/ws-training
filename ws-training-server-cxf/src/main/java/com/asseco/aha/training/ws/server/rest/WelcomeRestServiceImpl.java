package com.asseco.aha.training.ws.server.rest;

import javax.ws.rs.PathParam;

import org.springframework.stereotype.Component;

@Component("welcomeRest")
@Deprecated
/**
 * @Deprecated currently not working (disabled from configuration) 
 */
public class WelcomeRestServiceImpl implements WelcomeRestService {

    /*
     * (non-Javadoc)
     * 
     * @see com.asseco.aha.training.ws.server.rest.WelcomeRestService#welcome(java.lang.String)
     */
    @Override
    public String welcome(@PathParam("name") String name) {
        return String.format("Welcome %s!", name);
    }

}
