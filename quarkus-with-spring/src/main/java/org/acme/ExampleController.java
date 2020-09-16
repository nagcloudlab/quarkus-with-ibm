package org.acme;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class ExampleController {

	@Autowired
	GreetinService greetingService;

	@Autowired
	Locale defaultLocale;

	@Inject
	ConfigProperties props;

	@GetMapping
	public String hello() {
		return props.getPing() + "-" + greetingService.getMessage() + " - " + defaultLocale.getCountry();
	}
}