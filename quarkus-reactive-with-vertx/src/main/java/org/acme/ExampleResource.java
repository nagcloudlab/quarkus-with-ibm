package org.acme;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
//import io.vertx.axle.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;

//import io.vertx.reactivex.core.Vertx;

import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

//import io.vertx.core.Vertx;

@Path("/hello")
public class ExampleResource {

	@Inject
	Vertx vertx;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {

		// Bare Vert.x:
//		vertx.fileSystem().readFile("lorem-ipsum.txt", ar -> {
//			if (ar.succeeded()) {
//				System.out.println("Content:" + ar.result().toString("UTF-8"));
//			} else {
//				System.out.println("Cannot read the file: " + ar.cause().getMessage());
//			}
//		});

		// Mutiny Vert.x:
//		vertx.fileSystem().readFile("lorem-ipsum.txt")
//		    .onItem().transform(buffer -> buffer.toString("UTF-8"))
//		    .subscribe()
//		    .with(
//		            content -> System.out.println("Content: " + content),
//		            err -> System.out.println("Cannot read the file: " + err.getMessage())
//		    );

		// Rx Java 2 Vert.x
//		vertx.fileSystem().rxReadFile("lorem-ipsum.txt")
//		    .map(buffer -> buffer.toString("UTF-8"))
//		    .subscribe(
//		            content -> System.out.println("Content: " + content),
//		            err -> System.out.println("Cannot read the file: " + err.getMessage())
//		    );

//		vertx.fileSystem().readFile("lorem-ipsum.txt")
//	    .thenApply(buffer -> buffer.toString("UTF-8"))
//	    .whenComplete((content, err) -> {
//	        if (err != null) {
//	            System.out.println("Cannot read the file: " + err.getMessage());
//	        } else {
//	            System.out.println("Content: " + content);
//	        }
//	    });
//		

		return "hello";
	}

	@GET
	@Path("/lorem")
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> doSomethingAsync() {
		// Mimic an asynchronous computation.
//		return Uni
//				.createFrom()
//				.item(() -> "Hello!")
//				.onItem()
//				.delayIt().by(Duration.ofMillis(10));

		return vertx.fileSystem()
				.readFile("/META-INF/resources/lorem.txt")
				.onItem()
				.transform(b -> b.toString("UTF-8"));
	}	
	
 	@GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/stream/{name}")
    public Multi<String> greeting(@PathParam("name") String name) {
        // TODO: create a Reactive Streams publisher or a Mutiny Multi
 		return vertx.periodicStream(2000).toMulti()
                .map(l -> String.format("Hello %s! (%s)%n", name, new Date()));
    }
 	
 	
 	@GET
    @Path("{name}/object")
    public JsonObject jsonObject(@PathParam("name") String name) {
        return new JsonObject().put("Hello", name);
    }

    @GET
    @Path("{name}/array")
    public JsonArray jsonArray(@PathParam("name") String name) {
        return new JsonArray().add("Hello").add(name);
    }
    
    
    

    private WebClient client;

    @PostConstruct
    void initialize() {
        this.client = WebClient.create(vertx,
                new WebClientOptions().setDefaultHost("fruityvice.com")
                    .setDefaultPort(443).setSsl(true).setTrustAll(true));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fruit/{name}")
    public Uni<JsonObject> getFruitData(@PathParam("name") String name) {
        return client.get("/api/fruit/" + name)
                .send()
                .onItem().transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject();
                    } else {
                        return new JsonObject()
                                .put("code", resp.statusCode())
                                .put("message", resp.bodyAsString());
                    }
                });
    }

}