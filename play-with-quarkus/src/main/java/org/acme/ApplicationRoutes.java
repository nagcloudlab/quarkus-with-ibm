package org.acme;

import io.quarkus.vertx.http.runtime.filters.Filters;
import io.vertx.ext.web.Router;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationRoutes {

    public void routes(@Observes Router router){
        router.get("/ok")
                .handler(rc->rc.response().end("OK from Route"));
    }

    public void filters(@Observes Filters filters){
        filters.register(rc->{
            rc.response().putHeader("v-header","header added byy vertx filter");
            rc.next();
        },10);
    }

}
