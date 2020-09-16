package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class GreetingService {
	
	@ConsumeEvent("greeting.name.upper")
	public String upperService(String name) {
		return name.toUpperCase();
	}
	

}
