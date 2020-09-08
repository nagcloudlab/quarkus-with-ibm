package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/computer")
public class ComputerResource {

    @Consumes(MediaType.APPLICATION_XML)
    public Response addComputer(Computer computer){
        //..
        return  Response.ok().build();
    }

}
