package io.alissonlima.com.resteasy;

import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/hello-resteasy")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    //This property is automatically loaded from application.properties
    @ConfigProperty(name = "greeting.message")
    String message;

    @Inject
    Logger log;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("Chegou no controller");
        return "Hello";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/config")
    public String config() {
        return message;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return greetingService.greeting(name);
    }

}