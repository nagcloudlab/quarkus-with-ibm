package com;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

//----------------------------------------------
// service Layer
//----------------------------------------------

class Service {
	public Observable<Integer> getNumbers() {
		// 10 numbers
		// Observable stream
		return Observable.create(e -> {

			int n = 1;
			while (n <= 10) {
				e.onNext(n);
				TimeUnit.SECONDS.sleep(3);
				n++;
			}
			e.onComplete();

		});

	}
}

//----------------------------------------------
//web Layer
//----------------------------------------------

class Controller {

	Service service = new Service();

	public void doGet() {
		Observable<Integer> observableStream = service.getNumbers();
		observableStream.subscribe(
				next -> System.out.println(next), 
				error -> System.out.println(error),
				() -> System.out.println("done"));
	}
}

public class App {

	public static void main(String[] args) {

		Controller controller = new Controller();
		controller.doGet();

	}

}
