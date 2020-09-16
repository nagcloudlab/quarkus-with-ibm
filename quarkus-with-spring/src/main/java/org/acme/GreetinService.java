package org.acme;

import org.springframework.stereotype.Service;

@Service
public class GreetinService {

	public String getMessage() {
		return "Quarkus+String";
	}
	
}
