package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/developer")
public class DeveloperResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeveloper(Developer developer) {
        //...
        developer.persist();
        URI uri = UriBuilder
                .fromResource(DeveloperResource.class)
                .path(Long.toString(developer.getId()))
                .build();
        return Response.created(uri)
                .entity(developer)
                .build();
    }

    public static class Developer {

        static long counter = 0;

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void persist() {
            this.id = (int)counter++;
        }
    }

}
