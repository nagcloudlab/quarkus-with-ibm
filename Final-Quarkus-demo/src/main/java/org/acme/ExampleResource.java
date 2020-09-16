
package org.acme;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.ext.web.client.WebClient;

@Path("/hello")
public class ExampleResource {

	

	@Inject
	Vertx vertx;
	
	@Inject 
	FileService fileService;
	
	@Inject EventBus eventBus;
	
	//-------------------------------------------------------------------------
	

	@GET
	@Path("/{name}/upper")
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> helloWithNameUpper(@PathParam("name") String name) {
		return eventBus.request("greeting.name.upper", name)
				       .onItem()
				       .transform(message->(String)message.body());
	}
	
	// -------------------------------------------------------------------------

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		System.out.println(Thread.currentThread());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello";
	}

	// -------------------------------------------------------------------------

	@GET
	@Path("/async")
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> asyncHello() {
		// Mimic an asynchronous computation.
		return Uni.createFrom().item(() -> "Hello!").onItem().delayIt().by(Duration.ofSeconds(5));
	}

	// -------------------------------------------------------------------------

	
	@GET
	@Path("/async-stream")
//	@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public Multi<String> stream() {
		return Multi
				.createFrom()
				.ticks()
				.every(Duration.ofSeconds(1))
				.onItem()
				.transform(n -> String.format("hello %s - %d", "ibm", n))
				.transform()
				.byTakingFirstItems(10);
	}
	
	
	// -------------------------------------------------------------------------
	
	//  IO ==> file-system/db/nw-calls
	// -------------------------------------------------------------------------
	
	@GET
	@Path("/lorem")
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> getFile() {
		return fileService.readFile();
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
                new WebClientOptions()
                   .setDefaultHost("fruityvice.com")
                    .setDefaultPort(443)
                    .setSsl(true)
                    .setTrustAll(true));
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