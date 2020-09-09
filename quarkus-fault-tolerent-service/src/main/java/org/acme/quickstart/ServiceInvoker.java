package org.acme.quickstart;

import java.util.concurrent.CompletableFuture;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@ApplicationScoped
public class ServiceInvoker {

	@Inject
	FailureSimulator failureSimulator;

	// tag::retries[]
	@Retry(maxRetries = 3, // <1>
			delay = 1000) // <2>
	@Fallback(RecoverHelloMessageFallback.class) // <3>
	public String getHelloWithFallback() {
		System.out.println("ServiceInvoker:getHelloWithFallback");
		failureSimulator.failAlways();
		return "hello";
	}

	public static class RecoverHelloMessageFallback implements FallbackHandler<String> { // <4>
		@Override
		public String handle(ExecutionContext executionContext) {
			System.out.println("RecoverHelloMessageFallback:handle");
			return "good bye";
		}

	}
	// end::retries[]

	// tag::timeout[]
	@Timeout(value = 2000) // <1>
	public String getHelloWithTimeout() {
		failureSimulator.longMethod();
		return "hello";
	}
	// end::timeout[]

	// tag::bulkhead[]
	@Bulkhead(value = 4) // <1>
	public String getHelloBulkhead() {
		failureSimulator.shortMethod();
		return "hello";
	}

	// end::bulkhead[]

	// tag::circuitbreaker[]
	@CircuitBreaker(requestVolumeThreshold = 4, // <1>
			failureRatio = 0.75, // <2>
			delay = 2000) // <3>
	public String getHelloCircuitBreaker() {
		failureSimulator.fail4Consecutive();
		return "hello";
	}
	// end::circuitbreaker[]

	
	public String longRunning() {
		// ......
		String result = " bla bla";
		executeAsync();
		return result;
	}

	@Asynchronous
	public CompletableFuture<String> executeAsync() {
		return CompletableFuture.supplyAsync(() -> {
//    		logging , audting...  3s
			return "...";
		});
	}

}
