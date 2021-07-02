package io.alissonlima.com.resteasy;

import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class LogginFilter implements ContainerRequestFilter {

    @Inject
    Logger log;

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {

        final String method = context.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();

        context.setProperty("reqId", UUID.randomUUID().toString());

        log.infof("Request %s %s from IP %s", method, path, address);
    }

}
