package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;

@ApplicationScoped
public class FileService {
	
	@Inject
	Vertx vertx;

	
	public Uni<String> readFile() {
		return vertx.fileSystem()
				.readFile("/META-INF/resources/lorem.txt")
				.onItem()
				.transform(b -> b.toString("UTF-8"));
	}
	
}
