package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/hello")
public class GreetingResource {

    public static enum Order{
        desc,asc
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//        return "hola";
//    }


    @LOCK
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public  String lockResource(@PathParam("id")long id){
        return  id+" locked";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("order") Order order, @HeaderParam("authorization") String authorization,@Context UriInfo uriInfo) {
        return String.format("URI %s Order %s - Authorization - %s",uriInfo.getAbsolutePath(),order,authorization);
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