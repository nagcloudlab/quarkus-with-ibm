package org.acme;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConnfiguration {

	@Bean
	public Locale getDefaultLocale() {
		return Locale.getDefault();
	}

}
