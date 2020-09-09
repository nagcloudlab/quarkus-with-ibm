// tag::adocApplication[]
package io.quarkus.workshop.superheroes.villain;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class VillainApplication extends Application {
}
