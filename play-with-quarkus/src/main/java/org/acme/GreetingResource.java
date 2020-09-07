package org.acme;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Path("/hello")
public class GreetingResource {

    @Inject
    Config config;

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.uppercase", defaultValue = "true")
    boolean uppercase;

    @ConfigProperty(name = "greeting.suffix")
    List<String> suffixes;


    @Path("/config")
    @GET
    public String helloConfig() {
//        config.getPropertyNames().forEach(System.out::println);
        return config.getValue("greeting.message", String.class);
    }


    @GET
    public String hello() {
        return message;
    }

    @Path("/optional")
    @GET()
    public String helloOptional() {
        return uppercase ? message.toUpperCase() : message;
    }

    @Path("/list")
    @GET()
    public String helloList() {
        return message + "" + suffixes.get(1);
    }

    public static enum Order {
        desc, asc
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//        return "hola";
//    }


    @LOCK
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String lockResource(@PathParam("id") long id) {
        return id + " locked";
    }


    @GET
    @Path("/params")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWithParams(@QueryParam("order") Order order, @HeaderParam("authorization") String authorization, @Context UriInfo uriInfo) {
        return String.format("URI %s Order %s - Authorization - %s", uriInfo.getAbsolutePath(), order, authorization);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void create(String message) {
        System.out.println("create..");
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String message) {
        System.out.println("update..");
        return message;
    }

    @DELETE
    public void delete() {
        System.out.println("delete");
    }


}