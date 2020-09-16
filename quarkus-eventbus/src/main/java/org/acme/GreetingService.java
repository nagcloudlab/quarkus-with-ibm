package org.acme;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

	@ConsumeEvent("greeting")
	public String consume(String name) {
		return name.toUpperCase();
	}
}