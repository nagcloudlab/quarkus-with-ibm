package org.acme;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.web.Route;
import io.smallrye.mutiny.Uni;

// Reactive Programming Library ==> Mutiny

@ApplicationScoped
public class ReactiveRoutes {

//	@Route(path = "/reactive-hello")
//	public String hello() {
//		System.out.println(Thread.currentThread()); // io..
////		try {
////			TimeUnit.SECONDS.sleep(10);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//		return "reactive Hello";
//	}
	
	@Route(path = "/reactive-hello")
	public Uni<String> hello() {
		System.out.println(Thread.currentThread()); // io..
		return Uni.createFrom()
				  .item(()->{return "reactive/async hello";})
				  .onItem()
				  .delayIt()
				  .by(Duration.ofSeconds(5));
	}

	

}
