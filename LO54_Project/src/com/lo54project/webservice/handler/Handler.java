package com.lo54project.webservice.handler;

import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Handler {

    protected static final ObjectMapper mapper = new ObjectMapper(); 
    protected static final ClientConfig cConfig = new DefaultClientConfig();
    protected static final com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(cConfig);
    protected static final WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/LO54_Project").build());    
    
}
